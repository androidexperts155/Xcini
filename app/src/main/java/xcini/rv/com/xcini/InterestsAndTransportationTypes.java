package xcini.rv.com.xcini;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;

import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.Temp_SignupSharedPreferences;

public class InterestsAndTransportationTypes extends AppCompatActivity implements View.OnClickListener, CommonFunctions {

    private CheckBox chk_xdelivery, chk_xshopper, chk_moving, chk_xtouring, chk_driver, chk_all, chk_bicycle, chk_motercycle, chk_car, chk_suv, chk_truck, chk_van, chk_other;
    private ArrayList<CheckBox> services;
    private ArrayList<CheckBox> transportation;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Temp_SignupSharedPreferences temp_shared_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests_and_transportation_types);
        findViews();
        setData();
        setOnClicks();
        parseIntentData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                if (validate()) {
                    editor.putString(temp_shared_preferences.SERVICES, calculate(1));
                    editor.putString(temp_shared_preferences.SERVICES, calculate(2));
                    editor.commit();
                    if (calculate(2).equals("Bicycle")) {
                        startActivity(new Intent(InterestsAndTransportationTypes.this, RecognizerId_Bicycle.class));
                        overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
                    } else {
                        startActivity(new Intent(InterestsAndTransportationTypes.this, BackgroundCheckInformation.class));
                        overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
                    }
                }
                break;
        }
    }

    @Override
    public void setData() {
        temp_shared_preferences = new Temp_SignupSharedPreferences();
        sharedPreferences = temp_shared_preferences.getSharedPrefrenceRefrence(InterestsAndTransportationTypes.this);
        editor = sharedPreferences.edit();

        services = new ArrayList<>();
        transportation = new ArrayList<>();

        services.add(chk_xdelivery);
        services.add(chk_xshopper);
        services.add(chk_moving);
        services.add(chk_xtouring);
        services.add(chk_driver);
        services.add(chk_all);

        transportation.add(chk_bicycle);
        transportation.add(chk_motercycle);
        transportation.add(chk_car);
        transportation.add(chk_suv);
        transportation.add(chk_truck);
        transportation.add(chk_van);
        transportation.add(chk_other);

        for (int j = 0; j < transportation.size(); j++) {
            final int finalJ = j;
            transportation.get(j).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (transportation.get(finalJ).isChecked()) {
                        for (int t = 0; t < transportation.size(); t++) {
                            if (finalJ != t) {
                                transportation.get(t).setChecked(false
                                );
                            }
                        }
                    }
                }
            });
        }

        chk_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (chk_all.isChecked()) {
                    for (int i = 0; i < services.size(); i++)
                        services.get(i).setChecked(true);
                } else {
                    for (int i = 0; i < services.size(); i++)
                        services.get(i).setChecked(false);
                }
            }
        });
    }

    @Override
    public void setOnClicks() {
        findViewById(R.id.tv_next).setOnClickListener(this);
    }

    @Override
    public void findViews() {
        chk_xdelivery = (CheckBox) findViewById(R.id.chk_xdelivery);
        chk_xshopper = (CheckBox) findViewById(R.id.chk_xshopper);
        chk_moving = (CheckBox) findViewById(R.id.chk_moving);
        chk_xtouring = (CheckBox) findViewById(R.id.chk_xtouring);
        chk_driver = (CheckBox) findViewById(R.id.chk_driver);
        chk_all = (CheckBox) findViewById(R.id.chk_all);

        chk_bicycle = (CheckBox) findViewById(R.id.chk_bicycle);
        chk_motercycle = (CheckBox) findViewById(R.id.chk_motercycle);
        chk_car = (CheckBox) findViewById(R.id.chk_car);
        chk_suv = (CheckBox) findViewById(R.id.chk_suv);
        chk_truck = (CheckBox) findViewById(R.id.chk_truck);
        chk_van = (CheckBox) findViewById(R.id.chk_van);
        chk_other = (CheckBox) findViewById(R.id.chk_other);
    }

    @Override
    public void parseIntentData() {

    }

    private String calculate(int type) {
        String val = "";
        if (type == 1) {
            for (int i = 0; i < services.size() - 1; i++) {
                if (services.get(i).isChecked()) {
                    if (val.equals(""))
                        val = val + services.get(i).getText();
                    else
                        val = val + ", " + services.get(i).getText();
                }
            }
            return val;
        } else {
            for (int i = 0; i < transportation.size(); i++) {
                if (transportation.get(i).isChecked()) {
                    val = (String) transportation.get(i).getText();
                }
            }
            return val;
        }

    }

    private boolean validate() {
        if (calculate(1).equals("")) {
            Toast.makeText(this, "Select atleast one service interest.", Toast.LENGTH_SHORT).show();
            return false;

        } else if (calculate(2).equals("")) {
            Toast.makeText(this, "Select atleast one transportation type.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
