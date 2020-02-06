package xcini.rv.com.xcini.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Temp_SignupSharedPreferences {

    public String FIRST_NAME = "first_name";
    public String LAST_NAME = "last_name";
    public String COUNTRY_ID = "country_id";
    public String ROLE = "role";
    public String EMAIL = "email";
    public String PHONE = "phone";
    public String PASSWORD = "password";
    public String USER_TYPE = "user_type";

    //    screen2
    public String SERVICES = "services";
    public String TRANSPORTATION = "transportation";

    //    screen3
    public String ID_PIC = "idpic";
    public String DOB = "dob";
    public String ID = "id";
    public String LICENCENUMBER = "licence_number";
    public String PERSONAL_PROVIDER = "licence_number";
    public String XCINI_INSURANCE = "xciniinsurance";


    public SharedPreferences getSharedPrefrenceRefrence(Context activity) {
        SharedPreferences sharedpreferences = activity.getSharedPreferences("xcini.rv.com.xcini.Utils", Context.MODE_PRIVATE);
        return sharedpreferences;
    }


}
