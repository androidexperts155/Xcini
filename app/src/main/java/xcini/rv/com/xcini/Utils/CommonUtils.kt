package xcini.rv.com.xcini.Utils

import android.content.Context
import xcini.rv.com.xcini.R

class CommonUtils {

    companion object {
        fun getPref(activity: Context, keys: String, type: Any): Any {

            val sharedPref = activity.getSharedPreferences(activity.getString(R.string.app_name), Context.MODE_PRIVATE)
            if (type is Boolean)
                return sharedPref.getBoolean(keys, false)
            else return sharedPref.getString(keys, "")!!
        }

        fun getPref(activity: Context, keys: String): String {

            val sharedPref = activity.getSharedPreferences(
                    activity.getString(R.string.app_name), Context.MODE_PRIVATE)
            return sharedPref.getString(keys, "")!!
        }


        fun savePref(context: Context, keys: String, values: Any) {
            val sharedPref = context.getSharedPreferences(
                    context.getString(R.string.app_name), Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            if (values is String)
                editor.putString(keys, values)
            else if (values is Boolean)
                editor.putBoolean(keys, values)

            editor.commit()
        }

        fun clearPrf(context: Context){
            val sharedPref = context.getSharedPreferences(
                    context.getString(R.string.app_name), Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
        }

    }

}