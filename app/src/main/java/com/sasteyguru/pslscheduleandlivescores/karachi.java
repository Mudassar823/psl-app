package com.sasteyguru.pslscheduleandlivescores;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.snackbar.Snackbar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;

public class karachi extends AppCompatActivity {
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7;
    private ImageButton imageButton1,imageButton2,imageButton3,imageButton4,imageButton5,imageButton6,imageButton7,imageButton8;
    Dialog dialog;
    RelativeLayout relativeLayout;
    RelativeLayout pick,select,share;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karachi);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Karachi Kings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /////////////////////////////////////////////
        prepareAd();
        relativeLayout=(RelativeLayout)findViewById(R.id.krl);
        imageView1=(ImageView)findViewById(R.id.profileimage);
        imageView2=(ImageView)findViewById(R.id.karachiframe1);
        imageView3=(ImageView)findViewById(R.id.karachiframe2);
        imageView4=(ImageView)findViewById(R.id.karachif3);
        imageView5=(ImageView)findViewById(R.id.karachif4);
        imageView6=(ImageView)findViewById(R.id.krlog1);
        imageView7=(ImageView)findViewById(R.id.krlog2);
        /////////////////////////////////////////////////
        pick=findViewById(R.id.pick2);
        select=findViewById(R.id.selectframe2);
        share=findViewById(R.id.share2);


        ///////////////////////////////////////////////////
        imageButton5=(ImageButton)findViewById(R.id.imgbk1);
        imageButton6=(ImageButton)findViewById(R.id.imgbk2);
        imageButton7=(ImageButton)findViewById(R.id.imgbk3);
        imageButton8=(ImageButton)findViewById(R.id.imgbk4);
        ////////////////////////////////////////////////////
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
checkandroidversion();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ( imageView1.getDrawable() == null) {
                    Toast.makeText(getApplicationContext(),"FIRST PICK IMAGE",Toast.LENGTH_SHORT).show();
                }

                else{

                    try {
                        save();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        });


    }
    public void open(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.kara);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();}
    public void framek(View view){
        imageView2.setVisibility(View.VISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
        imageView4.setVisibility(View.INVISIBLE);
        imageView5.setVisibility(View.INVISIBLE);
        imageView6.setVisibility(View.VISIBLE);
        imageView7.setVisibility(View.INVISIBLE);
        dialog.dismiss();

    }
    public void frame2k(View view){
        imageView2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.VISIBLE);
        imageView4.setVisibility(View.INVISIBLE);
        imageView5.setVisibility(View.INVISIBLE);
        imageView6.setVisibility(View.VISIBLE);
        imageView7.setVisibility(View.INVISIBLE);
        dialog.dismiss();

    }
    public void frame3k(View view){
        imageView2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
        imageView4.setVisibility(View.VISIBLE);
        imageView5.setVisibility(View.INVISIBLE);
        imageView6.setVisibility(View.INVISIBLE);
        imageView7.setVisibility(View.VISIBLE);
        dialog.dismiss();

    }
    public void frame4k(View view) {
        imageView2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
        imageView4.setVisibility(View.INVISIBLE);
        imageView5.setVisibility(View.VISIBLE);
        imageView6.setVisibility(View.VISIBLE);
        imageView7.setVisibility(View.INVISIBLE);
        dialog.dismiss();
    }
    public static Bitmap viewtobitmap(View view, int width, int hieght){
        Bitmap bitmap=Bitmap.createBitmap(width,hieght,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;

    }

    public void save() throws IOException {

        Bitmap bitmap = viewtobitmap(relativeLayout, relativeLayout.getWidth(), relativeLayout.getHeight());

        final String path=BitmapUtills.insertimage(getContentResolver(),bitmap,System.currentTimeMillis()+"_profile.jpg",null);
        if (!TextUtils.isEmpty(path)){


            Snackbar snackbar=Snackbar.make(relativeLayout,"Saved to Gallery",Snackbar.LENGTH_LONG)
                    .setAction("open", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            open(path);
                        }
                    });
            snackbar.show();

        }
        else {
            Snackbar snackbar=Snackbar.make(relativeLayout,"image not saved",Snackbar.LENGTH_LONG);
            snackbar.show();



        }
    }

    private void open(String path) {
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path),"image/*");
        startActivity(intent);

    }

    private void prepareAd(){
        mInterstitialAd=new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.id_for_interstitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    private void refreshgallery(File file) {
        Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }




    public void checkandroidversion(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 555);
            }catch (Exception e){

            }
        } else {
            pickImage();
        }



    }
    public void pickImage() {
        CropImage.startPickImageActivity(this);
    }
    private void croprequest(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //RESULT FROM SELECTED IMAGE
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);
            croprequest(imageUri);
        }

        //RESULT FROM CROPING ACTIVITY
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getUri());

                    imageView1.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 555 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        } else {
            checkandroidversion();
        }
    }
    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener(){
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }
        else{
            super.onBackPressed();}
    }
    }
