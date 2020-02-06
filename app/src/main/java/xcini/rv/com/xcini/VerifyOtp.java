package xcini.rv.com.xcini;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xcini.rv.com.xcini.Beans.Login_Pojo;
import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.CommonUtils;
import xcini.rv.com.xcini.Utils.Keys;
import xcini.rv.com.xcini.Utils.SharedPrefConstants;
import xcini.rv.com.xcini.Utils.Utils;
import xcini.rv.com.xcini.Utils.Web_Service;

public class VerifyOtp extends AppCompatActivity implements CommonFunctions, View.OnClickListener {

    private TextView tv_phone;
    private EditText ed_otp;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        findViews();
        setOnClicks();
        parseIntentData();
        setData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (validate())
                    new VerifyOtpCall().execute();

                break;
        }
    }

    @Override
    public void setData() {
        tv_phone.setText(phone);
    }

    @Override
    public void setOnClicks() {
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void findViews() {
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        ed_otp = (EditText) findViewById(R.id.ed_otp);
    }

    @Override
    public void parseIntentData() {
        phone = getIntent().getStringExtra("phone");
    }

    private boolean validate() {
        if (ed_otp.getText().equals("")) {
            ed_otp.setError("Required");

            return false;
        }
        return true;
    }

    private class VerifyOtpCall extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Utils.show_progress_bar(VerifyOtp.this);
            Call<Login_Pojo> call = new Web_Service().getApiService().verifyOtp(Keys.Authorisation, Keys.Accept, tv_phone.getText().toString(), ed_otp.getText().toString());
            call.enqueue(new Callback<Login_Pojo>() {
                @Override
                public void onResponse(Call<Login_Pojo> call, Response<Login_Pojo> response) {
                    Utils.hide_progress_bar();
                    try {
                        if (!response.body().getId().equals("")) {
                            Toast.makeText(VerifyOtp.this, "Success", Toast.LENGTH_SHORT).show();
                            CommonUtils.Companion.savePref(VerifyOtp.this, SharedPrefConstants.accessToken, response.body().getToken());
                            startActivity(new Intent(VerifyOtp.this, HomeFragments.class));
                        }
                    } catch (Exception e) {
                        Toast.makeText(VerifyOtp.this, "Invalid Otp", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Login_Pojo> call, Throwable t) {
                    Utils.hide_progress_bar();
                    Toast.makeText(VerifyOtp.this, "Invalid Otp", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }
}
