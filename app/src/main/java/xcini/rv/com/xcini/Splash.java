package xcini.rv.com.xcini;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import xcini.rv.com.xcini.Utils.CommonUtils;
import xcini.rv.com.xcini.Utils.SharedPrefConstants;

public class Splash extends AppCompatActivity {
    Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }
                    if (CommonUtils.Companion.getPref(Splash.this, SharedPrefConstants.accessToken).isEmpty())
                        startActivity(new Intent(Splash.this, Choose.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    else
                        startActivity(new Intent(Splash.this, HomeFragments.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                    overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);


                } catch (InterruptedException e) {
                }
            }
        };
        splashTread.start();

    }
}
