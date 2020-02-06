package xcini.rv.com.xcini;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.Imageutils;
import xcini.rv.com.xcini.Utils.Temp_SignupSharedPreferences;
import xcini.rv.com.xcini.Utils.Utils;

public class RecognizerId_Bicycle extends AppCompatActivity implements CommonFunctions, View.OnClickListener, Imageutils.ImageAttachmentListener {
    private LinearLayout li_cal;
    private CalendarView cal;
    private TextView tv_cal;
    private File localFile;
    private Imageutils imgutils;
    private Bitmap bitmap;
    private String file_name;
    private ImageView img_id;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Temp_SignupSharedPreferences temp_shared_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognizer_id__bicycle);
        findViews();
        setData();
        parseIntentData();
        setOnClicks();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_done:
                li_cal.setVisibility(View.GONE);
                break;

            case R.id.tv_cal:
                li_cal.setVisibility(View.VISIBLE);
                break;

            case R.id.img_id:
                chooseImage();
                break;
            case R.id.tv_next:
                if (validate()) {
                    editor.putString(temp_shared_preferences.ID_PIC, localFile.getAbsolutePath());
                    editor.putString(temp_shared_preferences.DOB, tv_cal.getText().toString());
                    editor.commit();

                    startActivity(new Intent(RecognizerId_Bicycle.this, TransportInformation.class));
                    overridePendingTransition(R.anim.fade_in1, R.anim.fade_out1);
                }
                break;
        }
    }

    @Override
    public void setData() {
        temp_shared_preferences = new Temp_SignupSharedPreferences();
        sharedPreferences = temp_shared_preferences.getSharedPrefrenceRefrence(this);
        editor = sharedPreferences.edit();

        imgutils = new Imageutils(RecognizerId_Bicycle.this);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String d = dayOfMonth + "/" + month + "/" + year;
                String curDate = String.valueOf(d);
                tv_cal.setText(curDate);
            }
        });
    }

    @Override
    public void setOnClicks() {
        findViewById(R.id.tv_done).setOnClickListener(this);
        findViewById(R.id.tv_cal).setOnClickListener(this);
        findViewById(R.id.img_id).setOnClickListener(this);
        findViewById(R.id.tv_next).setOnClickListener(this);


    }

    @Override
    public void findViews() {
        li_cal = (LinearLayout) findViewById(R.id.li_cal);
        cal = (CalendarView) findViewById(R.id.cal);
        tv_cal = (TextView) findViewById(R.id.tv_cal);
        img_id = (ImageView) findViewById(R.id.img_id);
    }

    @Override
    public void parseIntentData() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imgutils.onActivityResult(requestCode, resultCode, data);
    }

    public void image_attachment(int from, String filename, Bitmap file, Uri uri) {
        bitmap = file;
        file_name = filename;
        String path = Environment.getExternalStorageDirectory() + File.separator + "BodyTech" + File.separator;
        imgutils.createImage(file, filename, path, false);
        localFile = Utils.getFileFromImage(RecognizerId_Bicycle.this, filename, bitmap);
        img_id.setImageBitmap(file);
    }

    public void chooseImage() {
        final Dialog dialog = new Dialog(RecognizerId_Bicycle.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCancelable(true);

        dialog.getWindow().setBackgroundDrawableResource(R.color.translucent_black);
        LayoutInflater inflater = (LayoutInflater) RecognizerId_Bicycle.this.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.choosepicturelayout, null);

        dialog.setContentView(v);
        dialog.create();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialog.show();

        Button button_camera = (Button) v.findViewById(R.id.button_camera);
        Button button_gallary = (Button) v.findViewById(R.id.button_gallary);
        Button button_cancle = (Button) v.findViewById(R.id.button_cancle);

        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(RecognizerId_Bicycle.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(RecognizerId_Bicycle.this, new String[]{Manifest.permission.CAMERA}, 1212);
                } else {
                    dialog.dismiss();
                    imgutils.launchCamera(1);
                }
            }
        });

        button_gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(RecognizerId_Bicycle.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(RecognizerId_Bicycle.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1211);
                }
                dialog.dismiss();
                imgutils.launchGallery(1);
            }
        });
    }


    private boolean validate() {
        if (tv_cal.getText().toString().equals("")) {
            Toast.makeText(this, "Select date of birth first.", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            try {
                localFile.getAbsolutePath();
                return true;
            } catch (Exception e) {
                Toast.makeText(this, "Select recognizer id first.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

    }


}
