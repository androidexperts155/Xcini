package xcini.rv.com.xcini;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

import xcini.rv.com.xcini.Utils.CommonFunctions;
import xcini.rv.com.xcini.Utils.Imageutils;
import xcini.rv.com.xcini.Utils.Utils;

public class TransportInformation extends AppCompatActivity implements CommonFunctions, View.OnClickListener, Imageutils.ImageAttachmentListener {
    private File localFile;
    private Imageutils imgutils;
    private Bitmap bitmap;
    private String file_name;
    private ImageView img_id;


    private EditText ed_make, ed_year, ed_bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_information);
        findViews();
        setData();
        parseIntentData();
        setOnClicks();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_id:
                chooseImage();
                break;
        }
    }

    @Override
    public void setData() {

        imgutils = new Imageutils(TransportInformation.this);
    }

    @Override
    public void setOnClicks() {
        findViewById(R.id.img_id).setOnClickListener(this);

    }

    @Override
    public void findViews() {
        img_id = (ImageView) findViewById(R.id.img_id);
        ed_make = (EditText) findViewById(R.id.ed_make);
        ed_year = (EditText) findViewById(R.id.ed_year);
        ed_bank = (EditText) findViewById(R.id.ed_bank);
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
        localFile = Utils.getFileFromImage(TransportInformation.this, filename, bitmap);
        img_id.setImageBitmap(file);
    }

    public void chooseImage() {
        final Dialog dialog = new Dialog(TransportInformation.this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setCancelable(true);

        dialog.getWindow().setBackgroundDrawableResource(R.color.translucent_black);
        LayoutInflater inflater = (LayoutInflater) TransportInformation.this.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

                if (ContextCompat.checkSelfPermission(TransportInformation.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(TransportInformation.this, new String[]{Manifest.permission.CAMERA}, 1212);
                } else {
                    dialog.dismiss();
                    imgutils.launchCamera(1);
                }
            }
        });

        button_gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(TransportInformation.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(TransportInformation.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1211);
                }
                dialog.dismiss();
                imgutils.launchGallery(1);
            }
        });
    }

    private boolean validate() {
        if (ed_make.getText().toString().equals("")) {
            ed_make.setError("Required");
            return false;
        } else if (ed_bank.getText().toString().equals("")) {
            ed_bank.setError("Required");
            return false;

        } else if (ed_year.getText().toString().equals("")) {
            ed_year.setError("Required");
            return false;
        } else if (localFile.getAbsolutePath().equals("")) {
            Toast.makeText(this, "Upload profile image first.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
