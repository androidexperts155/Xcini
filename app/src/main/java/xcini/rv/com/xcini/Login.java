package xcini.rv.com.xcini;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xcini.rv.com.xcini.Beans.Login_Pojo;
import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.CommonUtils;
import xcini.rv.com.xcini.Utils.ConnectionDetector;
import xcini.rv.com.xcini.Utils.Keys;
import xcini.rv.com.xcini.Utils.SharedPrefConstants;
import xcini.rv.com.xcini.Utils.Utils;
import xcini.rv.com.xcini.Utils.Web_Service;

public class Login extends AppCompatActivity implements View.OnClickListener, CommonFunctions {

    private RadioButton rd_phone, rd_email;
    private EditText ed_phone, ed_pin;
    private CheckBox chk_rememberme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setData();
        setOnClicks();
        parseIntentData();
    }

    public void setData() {

    }

    public void setOnClicks() {
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.tv_forgot).setOnClickListener(this);
        findViewById(R.id.tv_notamember).setOnClickListener(this);
        findViewById(R.id.tv_forgot).setOnClickListener(this);
        findViewById(R.id.rd_email).setOnClickListener(this);
        findViewById(R.id.rd_phone).setOnClickListener(this);
    }

    @Override
    public void findViews() {
        rd_phone = (RadioButton) findViewById(R.id.rd_phone);
        rd_email = (RadioButton) findViewById(R.id.rd_email);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_pin = (EditText) findViewById(R.id.ed_pin);
    }

    @Override
    public void parseIntentData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                login();
                break;

            case R.id.tv_forgot:
                startActivity(new Intent(Login.this, ResetPin.class));
                overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
                break;

            case R.id.tv_notamember:
                startActivity(new Intent(Login.this, Choose.class));
                overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
                break;
            case R.id.rd_phone:
                if (rd_phone.isChecked()) {
                    rd_phone.setButtonDrawable(R.mipmap.radio2);
                    rd_email.setButtonDrawable(R.mipmap.radio1);

                    rd_email.setChecked(false);

                    ed_phone.setText("");
                    ed_phone.setHint("Phone Number");
                    ed_phone.setInputType(InputType.TYPE_CLASS_PHONE);
                }
                break;
            case R.id.rd_email:
                if (rd_email.isChecked()) {
                    rd_email.setButtonDrawable(R.mipmap.radio2);
                    rd_phone.setButtonDrawable(R.mipmap.radio1);

                    rd_phone.setChecked(false);

                    ed_phone.setText("");
                    ed_phone.setHint("Email");
                    ed_phone.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                }
                break;
        }
    }

    protected void login() {
        if (validate()) {
            ConnectionDetector cd = new ConnectionDetector(this);
            cd.isConnectingToInternet();
            if (cd.isConnectingToInternet() == false) {
                Toast.makeText(this, "No internet available", Toast.LENGTH_SHORT).show();
            } else {
                String email, phone;
                email = phone = "";
                if (rd_phone.isChecked()) {
                    phone = ed_phone.getText().toString();
                    email = "";
                } else {
                    email = ed_phone.getText().toString();
                    phone = "";
                }

                Utils.show_progress_bar(this);
                Call<Login_Pojo> call = new Web_Service().getApiService().login(Keys.Authorisation, Keys.Accept, email, phone, ed_pin.getText().toString());

                call.enqueue(new Callback<Login_Pojo>() {
                    @Override
                    public void onResponse(Call<Login_Pojo> call, Response<Login_Pojo> response) {
                        Utils.hide_progress_bar();
                        try {

                            if(response.body()!=null){

                                Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                                CommonUtils.Companion.savePref(Login.this, SharedPrefConstants.accessToken, response.body().getToken());
                                startActivity(new Intent(Login.this, HomeFragments.class));
                                finishAffinity();
                            }
                            else {
                                Toast.makeText(Login.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
//                            if (response.body().getId() > 0)

                        } catch (Exception e) {
                            Toast.makeText(Login.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login_Pojo> call, Throwable t) {
                        Utils.hide_progress_bar();
                        Toast.makeText(Login.this, "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }


        
       /* if (ed_phone.getText().toString().equals("1")) {
            startActivity(new Intent(Login.this, HomeFragments.class));
            overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
        } else if (ed_phone.getText().toString().equals("2")) {
            startActivity(new Intent(Login.this, Home.class));
            overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
        } else {
            startActivity(new Intent(Login.this, InterestsAndTransportationTypes.class));
            overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
        }*/
    }

    private boolean validate() {
        if (ed_phone.getText().toString().equals("") || ed_pin.getText().toString().equals("")) {
            if (ed_pin.getText().toString().equals("")) {
                ed_pin.setError("Required field");
            }
            if (ed_phone.getText().toString().equals("")) {
                ed_phone.setError("Required field");
            }
            return false;
        }

        if (rd_phone.isChecked()) {
            if (ed_phone.getText().toString().toCharArray().length != 13) {
                ed_phone.setError("Invalid Phone number. Try with country code");
                return false;
            }
        } else if (rd_email.isChecked()) {
            if (Utils.isValidEmail(ed_phone.getText().toString()) == false) {
                ed_phone.setError("Invalid Email");
                return false;
            }
        }
        return true;
    }
}
