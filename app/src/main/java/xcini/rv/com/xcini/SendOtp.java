package xcini.rv.com.xcini;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xcini.rv.com.xcini.Beans.ForgotPassword_Pojo;
import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.Keys;
import xcini.rv.com.xcini.Utils.Utils;
import xcini.rv.com.xcini.Utils.Web_Service;

public class SendOtp extends AppCompatActivity implements CommonFunctions, View.OnClickListener {
    private EditText ed_phone;
    private String phone, email;
    private RadioGroup ver_group;
    private RadioButton rd_phone, rd_email;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        findViews();
        setOnClicks();
        parseIntentData();
        setData();

        ver_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rd_phone:
                        rd_phone.setButtonDrawable(ContextCompat.getDrawable(SendOtp.this, R.mipmap.radio2));
                        rd_email.setButtonDrawable(ContextCompat.getDrawable(SendOtp.this, R.mipmap.radio1));

                        ed_phone.setText(phone);
                        ed_phone.setInputType(InputType.TYPE_CLASS_PHONE);
                        break;
                    case R.id.rd_email:
                        rd_phone.setButtonDrawable(ContextCompat.getDrawable(SendOtp.this, R.mipmap.radio1));
                        rd_email.setButtonDrawable(ContextCompat.getDrawable(SendOtp.this, R.mipmap.radio2));

                        ed_phone.setText(email);
                        ed_phone.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (validate()) {
                    new OtpCall().execute();
                }
                break;
        }
    }

    @Override
    public void setData() {
        ed_phone.setText(phone);
    }

    @Override
    public void setOnClicks() {
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void findViews() {
        ed_phone = findViewById(R.id.ed_phone);
        ver_group = findViewById(R.id.ver_group);
        rd_email = findViewById(R.id.rd_email);
        rd_phone = findViewById(R.id.rd_phone);
    }

    @Override
    public void parseIntentData() {
        phone = getIntent().getStringExtra("phone");
        email = getIntent().getStringExtra("email");
    }

    private boolean validate() {
        if (!phone.equals(ed_phone.getText().toString())) {
            ed_phone.setError("Not same as signup");
            return false;
        }
        return true;
    }

    class OtpCall extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            Utils.show_progress_bar(SendOtp.this);
            Call<ForgotPassword_Pojo> call = new Web_Service().getApiService().sendOtp(Keys.Authorisation, Keys.Accept, ed_phone.getText().toString());
            call.enqueue(new Callback<ForgotPassword_Pojo>() {
                @Override
                public void onResponse(Call<ForgotPassword_Pojo> call, Response<ForgotPassword_Pojo> response) {
                    Utils.hide_progress_bar();

                    try {
                        if (response.body().getSuccess().equals("true")) {
                            Toast.makeText(SendOtp.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SendOtp.this, VerifyOtp.class).putExtra("phone", ed_phone.getText().toString()));
                        } else {
                            Toast.makeText(SendOtp.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(SendOtp.this, response.body().getError(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ForgotPassword_Pojo> call, Throwable t) {
                    Utils.hide_progress_bar();

                }
            });

            return null;
        }
    }
}
