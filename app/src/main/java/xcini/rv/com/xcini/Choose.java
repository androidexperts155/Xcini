package xcini.rv.com.xcini;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import xcini.rv.com.xcini.register.Register_as;

public class Choose extends AppCompatActivity implements View.OnClickListener {
    String data[];
    private ViewFlipper viewfliper;
    private TextView tv1, tv2, tv3, tv4, tv_detail;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        setData();
        setOnClicks();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setData() {
        data = new String[4];
        data[0] = "Xcini is a real time delivery sharing service for consumers and enterprises worldwide.";
        data[1] = "Xcini delivers goods and services to REQUESTERS doorstep with just the click of a button.";
        data[2] = "Xcini services teo network user types. Providers and Requesters.";
        data[3] = "It is simple to use and angeges everyday people with everyday consumers who need assistance in delivering, transporting, moving, or carrying goods and services to destinations.";

        viewfliper = (ViewFlipper) findViewById(R.id.viewflipper);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv_detail = (TextView) findViewById(R.id.tv_detail);

        viewfliper.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                int q = viewfliper.getDisplayedChild();
                switch (q) {
                    case 0:
                        tv1.setBackgroundResource(R.drawable.bg_blue);
                        tv2.setBackgroundResource(R.drawable.bg_gray);
                        tv3.setBackgroundResource(R.drawable.bg_gray);
                        tv4.setBackgroundResource(R.drawable.bg_gray);
                        tv_detail.setText(data[0]);
                        break;

                    case 1:
                        tv1.setBackgroundResource(R.drawable.bg_gray);
                        tv2.setBackgroundResource(R.drawable.bg_blue);
                        tv3.setBackgroundResource(R.drawable.bg_gray);
                        tv4.setBackgroundResource(R.drawable.bg_gray);
                        tv_detail.setText(data[1]);
                        break;

                    case 2:
                        tv1.setBackgroundResource(R.drawable.bg_gray);
                        tv2.setBackgroundResource(R.drawable.bg_gray);
                        tv3.setBackgroundResource(R.drawable.bg_blue);
                        tv4.setBackgroundResource(R.drawable.bg_gray);
                        tv_detail.setText(data[2]);
                        break;

                    case 3:
                        tv1.setBackgroundResource(R.drawable.bg_gray);
                        tv2.setBackgroundResource(R.drawable.bg_gray);
                        tv3.setBackgroundResource(R.drawable.bg_gray);
                        tv4.setBackgroundResource(R.drawable.bg_blue);
                        tv_detail.setText(data[3]);

                }
            }
        });

    }

    private void setOnClicks() {
        findViewById(R.id.tv_signin).setOnClickListener(this);
        findViewById(R.id.tv_signup).setOnClickListener(this);
    }

    protected void signup() {
        startActivity(new Intent(Choose.this, Register_as.class));
        overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
    }

    protected void signin() {
        startActivity(new Intent(Choose.this, Login.class));
        overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_signin:
                signin();
                break;

            case R.id.tv_signup:

                signup();
                break;
        }

    }
}
