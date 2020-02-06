package xcini.rv.com.xcini;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Categories extends AppCompatActivity {

    private ImageView img1, img2, img3, img4;
    private ImageView[] imgarr;
    private Button b1, b2, b3, b4;
    private int imgIndexes[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setData();

    }

    private void setData() {
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);

        imgarr = new ImageView[4];
        imgarr[0] = img1;
        imgarr[1] = img2;
        imgarr[2] = img3;
        imgarr[3] = img4;


        imgIndexes = new int[4];
        imgIndexes[0] = R.mipmap.food;
        imgIndexes[1] = R.mipmap.pakage;
        imgIndexes[2] = R.mipmap.ride;
        imgIndexes[3] = R.mipmap.pet;

        final float scale = Categories.this.getResources().getDisplayMetrics().density;
        int pixels = (int) (60 * scale + 0.5f);
        LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(pixels, pixels);
        lm.gravity = Gravity.CENTER;
        img1.setLayoutParams(lm);
        img1.setImageResource(imgIndexes[0]);
        reset(0);
    }

    protected void change1(View v) {
        img1.setImageResource(imgIndexes[0]);
        final float scale = Categories.this.getResources().getDisplayMetrics().density;
        int pixels = (int) (60 * scale + 0.5f);
        LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(pixels, pixels);
        lm.gravity = Gravity.CENTER;
        img1.setLayoutParams(lm);
        reset(0);
    }

    protected void change2(View v) {
        img2.setImageResource(imgIndexes[1]);
        final float scale = Categories.this.getResources().getDisplayMetrics().density;
        int pixels = (int) (60 * scale + 0.5f);
        LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(pixels, pixels);
        lm.gravity = Gravity.CENTER;
        img2.setLayoutParams(lm);
        reset(1);
    }

    protected void change3(View v) {
        img3.setImageResource(imgIndexes[2]);
        final float scale = Categories.this.getResources().getDisplayMetrics().density;
        int pixels = (int) (60 * scale + 0.5f);
        LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(pixels, pixels);
        lm.gravity = Gravity.CENTER;
        img3.setLayoutParams(lm);

        reset(2);
    }

    protected void change4(View v) {
        img4.setImageResource(imgIndexes[3]);
        final float scale = Categories.this.getResources().getDisplayMetrics().density;
        int pixels = (int) (60 * scale + 0.5f);
        LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(pixels, pixels);
        lm.gravity = Gravity.CENTER;
        img4.setLayoutParams(lm);
        reset(3);
    }

    protected void b1(View v) {
        imgIndexes[0] = R.mipmap.food;
        imgIndexes[1] = R.mipmap.pakage;
        imgIndexes[2] = R.mipmap.ride;
        imgIndexes[3] = R.mipmap.pet;

        b1.setBackgroundResource(R.color.colorPrimaryDark);
        b1.setTextColor(getResources().getColor(R.color.white));

        b2.setBackgroundResource(R.color.white);
        b2.setTextColor(getResources().getColor(R.color.black));

        b3.setBackgroundResource(R.color.white);
        b3.setTextColor(getResources().getColor(R.color.black));

        b4.setBackgroundResource(R.color.white);
        b4.setTextColor(getResources().getColor(R.color.black));

        final float scale = Categories.this.getResources().getDisplayMetrics().density;
        int pixels = (int) (60 * scale + 0.5f);
        LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(pixels, pixels);
        lm.gravity = Gravity.CENTER;
        img1.setLayoutParams(lm);
        img1.setImageResource(imgIndexes[0]);
        reset(0);
    }

    protected void b2(View v) {
        imgIndexes[0] = R.mipmap.groceries;
        imgIndexes[1] = R.mipmap.mall;
        imgIndexes[2] = R.mipmap.store;
        imgIndexes[3] = R.mipmap.housing;

        b1.setBackgroundResource(R.color.white);
        b1.setTextColor(getResources().getColor(R.color.black));

        b2.setBackgroundResource(R.color.colorPrimaryDark);
        b2.setTextColor(getResources().getColor(R.color.white));

        b3.setBackgroundResource(R.color.white);
        b3.setTextColor(getResources().getColor(R.color.black));

        b4.setBackgroundResource(R.color.white);
        b4.setTextColor(getResources().getColor(R.color.black));

        final float scale = Categories.this.getResources().getDisplayMetrics().density;
        int pixels = (int) (60 * scale + 0.5f);
        LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(pixels, pixels);
        lm.gravity = Gravity.CENTER;
        img1.setLayoutParams(lm);
        img1.setImageResource(imgIndexes[0]);
        reset(0);
    }

    protected void b3(View v) {
        imgIndexes[0] = R.mipmap.house;
        imgIndexes[1] = R.mipmap.office;
        imgIndexes[2] = R.mipmap.shop;
        imgIndexes[3] = R.mipmap.storage;

        b1.setBackgroundResource(R.color.white);
        b1.setTextColor(getResources().getColor(R.color.black));

        b2.setBackgroundResource(R.color.white);
        b2.setTextColor(getResources().getColor(R.color.black));

        b3.setBackgroundResource(R.color.colorPrimaryDark);
        b3.setTextColor(getResources().getColor(R.color.white));

        b4.setBackgroundResource(R.color.white);
        b4.setTextColor(getResources().getColor(R.color.black));

        final float scale = Categories.this.getResources().getDisplayMetrics().density;
        int pixels = (int) (60 * scale + 0.5f);
        LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(pixels, pixels);
        lm.gravity = Gravity.CENTER;
        img1.setLayoutParams(lm);
        img1.setImageResource(imgIndexes[0]);
        reset(0);
    }

    protected void b4(View v) {
        imgIndexes[0] = R.mipmap.school;
        imgIndexes[1] = R.mipmap.neighbourhood;
        imgIndexes[2] = R.mipmap.city;
        imgIndexes[3] = R.mipmap.state;

        b1.setBackgroundResource(R.color.white);
        b1.setTextColor(getResources().getColor(R.color.black));

        b2.setBackgroundResource(R.color.white);
        b2.setTextColor(getResources().getColor(R.color.black));

        b3.setBackgroundResource(R.color.white);
        b3.setTextColor(getResources().getColor(R.color.black));

        b4.setBackgroundResource(R.color.colorPrimaryDark);
        b4.setTextColor(getResources().getColor(R.color.white));

        final float scale = Categories.this.getResources().getDisplayMetrics().density;
        int pixels = (int) (60 * scale + 0.5f);
        LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(pixels, pixels);
        lm.gravity = Gravity.CENTER;
        img1.setLayoutParams(lm);
        img1.setImageResource(imgIndexes[0]);
        reset(0);

    }

    private void reset(int index) {
        for (int i = 0; i < 4; i++) {
            if (i != index) {
                imgarr[i].setImageResource(0);
                imgarr[i].setImageResource(R.mipmap.circle);
                final float scale = Categories.this.getResources().getDisplayMetrics().density;
                int pixels = (int) (35 * scale + 0.5f);
                LinearLayout.LayoutParams lm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, pixels);
                lm.gravity = Gravity.CENTER;
                imgarr[i].setLayoutParams(lm);
                imgarr[i].setBackgroundResource(0);
            } else {
                imgarr[i].setBackgroundResource(R.drawable.round);
            }
        }

    }

}
