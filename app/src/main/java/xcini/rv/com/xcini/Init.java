package xcini.rv.com.xcini;

import android.app.Application;
import android.content.SharedPreferences;

import xcini.rv.com.xcini.Utils.Temp_SignupSharedPreferences;

/**
 * Created by user on 24/2/18.
 */

public class Init extends Application {
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Temp_SignupSharedPreferences temp_shared_preferences;


    @Override
    public void onCreate() {
        super.onCreate();
        temp_shared_preferences = new Temp_SignupSharedPreferences();
        sharedPreferences = temp_shared_preferences.getSharedPrefrenceRefrence(this);
        editor = sharedPreferences.edit();
    }


    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public Temp_SignupSharedPreferences getTemp_shared_preferences() {
        return temp_shared_preferences;
    }
}
