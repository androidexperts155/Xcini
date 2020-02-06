package xcini.rv.com.xcini;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragments extends FragmentActivity {

    private ViewPager pager;

    private Button bt_home, bt_earnings, bt_ratings, bt_accounts;
    private ImageView img_home, img_earnings, img_ratings, img_accounts;

    private ImageView[] imgarr;
    private Button[] buttonsarr;

    @OnClick(R.id.home)
    public void onClickHome() {
        home();
    }

    @OnClick(R.id.earnings)
    public void onClickearnings() {
        earnings();
    }

    @OnClick(R.id.ratings)
    public void onClickratings() {
        ratings();
    }

    @OnClick(R.id.accounts)
    public void onClickaccounts() {
        accounts();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragments);

        ButterKnife.bind(this);

        setData();
        reset(0);

    }

    private void setData() {
        bt_home = findViewById(R.id.bt_home);
        bt_earnings = findViewById(R.id.bt_earnings);
        bt_ratings = findViewById(R.id.bt_ratings);
        bt_accounts = findViewById(R.id.bt_accounts);

        img_home = findViewById(R.id.img_home);
        img_earnings = findViewById(R.id.img_earnings);
        img_ratings = findViewById(R.id.img_ratings);
        img_accounts = findViewById(R.id.img_accounts);


        buttonsarr = new Button[4];
        imgarr = new ImageView[4];

        buttonsarr[0] = bt_home;
        buttonsarr[1] = bt_earnings;
        buttonsarr[2] = bt_ratings;
        buttonsarr[3] = bt_accounts;

        imgarr[0] = img_home;
        imgarr[1] = img_earnings;
        imgarr[2] = img_ratings;
        imgarr[3] = img_accounts;

        pager = findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                reset(pager.getCurrentItem());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    protected void home() {
        reset(0);
    }

    protected void earnings() {
        reset(1);
    }

    protected void ratings() {
        reset(2);
    }

    protected void accounts() {
        reset(3);
    }

    private void reset(int index) {
        for (int i = 0; i < 4; i++) {
            if (i == index) {
                imgarr[i].setColorFilter(ContextCompat.getColor(this, R.color.black));
                buttonsarr[i].setTextColor(getResources().getColor(R.color.black));
                buttonsarr[i].setAllCaps(true);
                pager.setCurrentItem(i);
            } else {
                imgarr[i].setColorFilter(ContextCompat.getColor(this, R.color.black_overlay));
                buttonsarr[i].setTextColor(getResources().getColor(R.color.black_overlay));
                buttonsarr[i].setAllCaps(false);
            }
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return new HomeMap();

                case 1:
                    return new Earnings();

                case 2:
                    return new Ratings();

                case 3:
                    return new Account();

                default:
                    return new HomeMap();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
