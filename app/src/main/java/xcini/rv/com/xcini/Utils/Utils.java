package xcini.rv.com.xcini.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by user on 28/12/17.
 */

public class Utils {
    public static ProgressDialog progressBar;

    public static boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void show_progress_bar(final Activity activity) {
        try {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    progressBar = new ProgressDialog(activity);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("Loading...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressBar.show();
                }
            });

        } catch (Exception e) {

        }
    }

    public static void hide_progress_bar() {
        progressBar.dismiss();
    }


    public static File getFileFromImage(Activity activity, String filename, Bitmap bitmap) {
        //create a file to write bitmap data
        File f = new File(activity.getCacheDir(), filename);
        try {
            f.createNewFile();
            //Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();

        } catch (IOException e) {
            Log.e("aaa", "-=-=IOException at onClick_signup -=" + e);
        }

        return f;
    }
}
