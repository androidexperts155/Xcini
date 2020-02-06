package xcini.rv.com.xcini;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.Utils;

public class ResetPin extends AppCompatActivity implements View.OnClickListener, CommonFunctions {
    private String data[];
    private RadioButton rd_phone, rd_email;
    private EditText ed_phone;
    Button tv_backtosignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pin);
        parseIntentData();
        findViews();
        setData();
        setOnClicks();
    }

    @Override
    public void setData() {
        data = new String[2];
        data[0] = "Enter the email you used in your XCINI profile. A password reset link will be sent to you by email.";
        data[1] = "Enter the phone number you used in your XCINI profile. A password will be sent to you by Message.";
    }

    @Override
    public void setOnClicks() {
        rd_email.setOnClickListener(this);
        rd_phone.setOnClickListener(this);
    }

    @Override
    public void findViews() {
        rd_phone = (RadioButton) findViewById(R.id.rd_phone);
        rd_email = (RadioButton) findViewById(R.id.rd_email);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        tv_backtosignin = (Button) findViewById(R.id.tv_backtosignin);
    }

    @Override
    public void parseIntentData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rd_email:
                rd_email.setButtonDrawable(R.mipmap.radio2);
                rd_phone.setButtonDrawable(R.mipmap.radio1);
                ed_phone.setHint("Enter your registered Email");
                ed_phone.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                validate();
                break;

            case R.id.rd_phone:
                rd_phone.setButtonDrawable(R.mipmap.radio2);
                rd_email.setButtonDrawable(R.mipmap.radio1);
                ed_phone.setHint("Enter your registered Phone number");
                ed_phone.setInputType(InputType.TYPE_CLASS_PHONE);
                validate();
                break;

            case R.id.tv_backtosignin:
                onBackPressed();
                break;
        }
    }

    private boolean validate() {
        if (ed_phone.getText().toString().equals("")) {
            if (ed_phone.getText().toString().equals("")) {
                ed_phone.setError("Required field");
            }
            if (ed_phone.getText().toString().equals("")) {
                ed_phone.setError("Required field");
            }
            return false;
        }

        if (rd_phone.isChecked()) {
            if (ed_phone.getText().toString().toCharArray().length != 13) {
                ed_phone.setError("Invalid Phone number");
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
