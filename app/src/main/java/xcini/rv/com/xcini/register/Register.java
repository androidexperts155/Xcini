package xcini.rv.com.xcini.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xcini.rv.com.xcini.Beans.Countries_Pojo;
import xcini.rv.com.xcini.Beans.ForgotPassword_Pojo;
import xcini.rv.com.xcini.InterestsAndTransportationTypes;
import xcini.rv.com.xcini.Login;
import xcini.rv.com.xcini.R;
import xcini.rv.com.xcini.SendOtp;
import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.ConnectionDetector;
import xcini.rv.com.xcini.Utils.Keys;
import xcini.rv.com.xcini.Utils.Temp_SignupSharedPreferences;
import xcini.rv.com.xcini.Utils.Utils;
import xcini.rv.com.xcini.Utils.Web_Service;

public class Register extends AppCompatActivity implements View.OnClickListener, CommonFunctions {
    String countryid, userType;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Temp_SignupSharedPreferences temp_shared_preferences;
    private Spinner sp_country;
    private ArrayList<Countries_Pojo.Country> countries;
    private TextView tv_code;
    private EditText ed_first_name, ed_last_name, ed_email, ed_phone, ed_password, ed_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        setOnClicks();
        parseIntentData();
        setData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(Register.this, Login.class));
                overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
                break;
            case R.id.tv_submit:
                if (validate()) {
                    if (userType.equals("1")) {
                        new RegisterCall().execute();
                    } else if (userType.equals("2")) {
                        editor.putString(temp_shared_preferences.FIRST_NAME, ed_first_name.getText().toString());
                        editor.putString(temp_shared_preferences.LAST_NAME, ed_last_name.getText().toString());
                        editor.putString(temp_shared_preferences.COUNTRY_ID, countryid);
                        editor.putString(temp_shared_preferences.ROLE, "1");
                        editor.putString(temp_shared_preferences.EMAIL, ed_email.getText().toString());
                        editor.putString(temp_shared_preferences.PHONE, tv_code.getText().toString() + ed_phone.getText().toString());
                        editor.putString(temp_shared_preferences.PASSWORD, ed_password.getText().toString());
                        editor.commit();
                        startActivity(new Intent(Register.this, InterestsAndTransportationTypes.class));
                        overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
                    }
                }
                break;
        }
    }

    @Override
    public void setData() {
        temp_shared_preferences = new Temp_SignupSharedPreferences();
        sharedPreferences = temp_shared_preferences.getSharedPrefrenceRefrence(Register.this);
        editor = sharedPreferences.edit();
        countries = new ArrayList<>();

        ConnectionDetector cd = new ConnectionDetector(Register.this);
        cd.isConnectingToInternet();
        if (cd.isConnectingToInternet() == false) {
            Toast.makeText(Register.this, "No internet available", Toast.LENGTH_SHORT).show();
        }

        else {
            Utils.show_progress_bar(Register.this);

            new Countries().execute();
        }


        countryid = "";
    }

    @Override
    public void setOnClicks() {
        findViewById(R.id.tv_login).setOnClickListener(this);
        findViewById(R.id.tv_submit).setOnClickListener(this);
    }

    @Override
    public void findViews() {
        sp_country = (Spinner) findViewById(R.id.sp_country);
        tv_code = (TextView) findViewById(R.id.tv_code);

        ed_first_name = (EditText) findViewById(R.id.ed_first_name);
        ed_last_name = (EditText) findViewById(R.id.ed_last_name);
        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_phone = (EditText) findViewById(R.id.ed_phone);
        ed_password = (EditText) findViewById(R.id.ed_password);
        ed_confirm_password = (EditText) findViewById(R.id.ed_confirm_password);

    }

    @Override
    public void parseIntentData() {
        userType = getIntent().getStringExtra("usertype");
    }

    private boolean validate() {

        if (ed_first_name.getText().toString().equals("")
                || ed_last_name.getText().toString().equals("")
                || ed_email.getText().toString().equals("")
                || ed_phone.getText().toString().equals("")
                || ed_password.getText().toString().equals("")
                || ed_confirm_password.getText().toString().equals("")) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Utils.isValidEmail(ed_email.getText().toString())) {
            Toast.makeText(this, "Invalid email.", Toast.LENGTH_SHORT).show();
            ed_email.setError("Invalid");
            return false;
        } else if (ed_phone.getText().toString().toCharArray().length < 10) {
            Toast.makeText(this, "Invalid phone.", Toast.LENGTH_SHORT).show();
            ed_phone.setError("Invalid");
            return false;
        } else if (!ed_password.getText().toString().equals(ed_confirm_password.getText().toString())) {
            Toast.makeText(this, "Password and confirm password should match.", Toast.LENGTH_SHORT).show();
            ed_confirm_password.setError("Do not matched with password.");
            return false;
        }

        return true;
    }

    class Countries extends AsyncTask {
        @Override
        protected Object doInBackground(final Object[] objects) {

                Call<Countries_Pojo> call = new Web_Service().getApiService().getCountries(Keys.Accept);
                call.enqueue(new Callback<Countries_Pojo>() {
                    @Override
                    public void onResponse(Call<Countries_Pojo> call, Response<Countries_Pojo> response) {

                        Utils.hide_progress_bar();

                        if (response.body().getCount() > 0) {
                            for (int i = 0; i < response.body().getCount(); i++)
                                countries.add(response.body().getCountries().get(i));
                            onPostExecute(objects);
                        }
                    }

                    @Override
                    public void onFailure(Call<Countries_Pojo> call, Throwable t) {
                        Utils.hide_progress_bar();
                        Log.e("OnFailure", t.getMessage());
                    }
                });

            return null;
        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            sp_country.setAdapter(new MyAdapter(Register.this, countries));


            sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    tv_code.setText(countries.get(i).getCountry().getInternationalPhoneCode() + "");
                    countryid = countries.get(i).getCountry().getId() + "";
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }
    }

    class RegisterCall extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            if (new ConnectionDetector(Register.this).isConnectingToInternet()) {
                Utils.show_progress_bar(Register.this);
                RequestBody role = RequestBody.create(okhttp3.MultipartBody.FORM, "customer");
                RequestBody first_name = RequestBody.create(okhttp3.MultipartBody.FORM, ed_first_name.getText().toString());
                RequestBody last_name = RequestBody.create(okhttp3.MultipartBody.FORM, ed_last_name.getText().toString());
                RequestBody country_id = RequestBody.create(okhttp3.MultipartBody.FORM, countryid);
                RequestBody email = RequestBody.create(okhttp3.MultipartBody.FORM, ed_email.getText().toString());
                RequestBody phone_number = RequestBody.create(okhttp3.MultipartBody.FORM, tv_code.getText().toString() + ed_phone.getText().toString());
                RequestBody password = RequestBody.create(okhttp3.MultipartBody.FORM, ed_confirm_password.getText().toString());
                RequestBody confirm_password = RequestBody.create(okhttp3.MultipartBody.FORM, ed_confirm_password.getText().toString());
                RequestBody eula = RequestBody.create(okhttp3.MultipartBody.FORM, "1");

                MultipartBody.Part imageFileBody = MultipartBody.Part.createFormData("user[image_attributes][attachment]", "");

                Call<ForgotPassword_Pojo> call = new Web_Service().getApiService().signup(Keys.Authorisation,
                        Keys.Accept,
                        role,
                        first_name,
                        last_name,
                        email,
                        phone_number,
                        password,
                        confirm_password,
                        country_id,
                        eula
                );
                call.enqueue(new Callback<ForgotPassword_Pojo>() {
                    @Override
                    public void onResponse(Call<ForgotPassword_Pojo> call, Response<ForgotPassword_Pojo> response) {
                        Utils.hide_progress_bar();
                        try {
                            if (response.body().getSuccess().equals("true")) {
                                Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, SendOtp.class).putExtra("phone", tv_code.getText().toString() + ed_phone.getText().toString()).putExtra("email", ed_email.getText().toString()).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                            } else {
                                Toast.makeText(Register.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(Register.this, "Invalid phone number or already registered.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForgotPassword_Pojo> call, Throwable t) {
                        Utils.hide_progress_bar();
                    }
                });

            } else {
                Toast.makeText(Register.this, "No internet available", Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }

}

class MyAdapter extends BaseAdapter {

    Context c;
    ArrayList<Countries_Pojo.Country> objects;

    public MyAdapter(Context context, ArrayList<Countries_Pojo.Country> objects) {
        super();
        this.c = context;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Countries_Pojo.Country cur_obj = objects.get(position);
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View row = inflater.inflate(R.layout.country_item, parent, false);
        TextView label = (TextView) row.findViewById(R.id.tv_name);
        label.setText(cur_obj.getCountry().getName());
        ImageView icon = (ImageView) row.findViewById(R.id.img_flag);
        Picasso.with(c).load("http://35.163.30.111" + cur_obj.getCountry().getImages().get64x64()).into(icon);

        return row;
    }
}

