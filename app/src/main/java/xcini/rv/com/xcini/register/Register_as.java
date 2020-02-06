package xcini.rv.com.xcini.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import xcini.rv.com.xcini.Init;
import xcini.rv.com.xcini.R;
import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.Temp_SignupSharedPreferences;

public class Register_as extends AppCompatActivity implements View.OnClickListener, CommonFunctions {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Temp_SignupSharedPreferences temp_shared_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as);
        findViews();
        setData();
        setOnClicks();
        parseIntentData();
    }

    protected void register(String userType) {
        startActivity(new Intent(Register_as.this, Register.class).putExtra("usertype", userType));
        overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.li_requester:
                editor.putString(temp_shared_preferences.USER_TYPE, "customer");
                editor.commit();
                register("1");
                break;
            case R.id.li_provider:
                editor.putString(temp_shared_preferences.USER_TYPE, "provider");
                editor.commit();
                register("2");
                break;
            case R.id.li_both:
                editor.putString(temp_shared_preferences.USER_TYPE, "both");
                editor.commit();
                register("3");
                break;
        }
    }

    @Override
    public void setData() {
        temp_shared_preferences = new Temp_SignupSharedPreferences();
        sharedPreferences = temp_shared_preferences.getSharedPrefrenceRefrence(Register_as.this);
        editor = sharedPreferences.edit();

        sharedPreferences = ((Init) this.getApplication()).getSharedPreferences();
    }

    @Override
    public void setOnClicks() {
        findViewById(R.id.li_requester).setOnClickListener(this);
        findViewById(R.id.li_provider).setOnClickListener(this);
        findViewById(R.id.li_both).setOnClickListener(this);
    }

    @Override
    public void findViews() {

    }

    @Override
    public void parseIntentData() {

    }
}
