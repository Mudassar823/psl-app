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

import java.io.IOException;

public class islamabad extends AppCompatActivity {
    private ImageButton b1,b2,b3,b4;
    RelativeLayout pick,select,share;
    Dialog dialog;
    ImageView i0,i1,i2,i3,i4,i5,i6;
    RelativeLayout relativeLayout;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_islamabad);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Islamabad United");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prepareAd();
        pick=findViewById(R.id.pick1);
        select=findViewById(R.id.selectframe1);
        share=findViewById(R.id.share1);

        relativeLayout=(RelativeLayout)findViewById(R.id.irl);
        i0=(ImageView)findViewById(R.id.profileimage);
        i1=(ImageView)findViewById(R.id.islamabadframe);
        i2=(ImageView)findViewById(R.id.islamabadframe2);
        i3=(ImageView)findViewById(R.id.islamabadframe1);
        i4=(ImageView)findViewById(R.id.islamabadframe4);
        i5=(ImageView)findViewById(R.id.islalogo);
        i6=(ImageView)findViewById(R.id.islalogo1);
        b1=(ImageButton)findViewById(R.id.imgb1);
        b2=(ImageButton)findViewById(R.id.imgb2);
        b3=(ImageButton)findViewById(R.id.imgb3);
        b4=(ImageButton)findViewById(R.id.imgb4);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkandroidversion();
            }
        });


      share.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            /*  Bitmap bitmap = viewtobitmap(relativeLayout, relativeLayout.getWidth(), relativeLayout.getHeight());
              String root = Environment.getExternalStorageDirectory().toString();
              File myDir = new File(root + "/saved_images");
              myDir.mkdirs();
              Random generator = new Random();
              int n = 10000;
              n = generator.nextInt(n);
              String fname = "Image-" + n + ".jpg";
              File file = new File(myDir, fname);
              if (file.exists()) file.delete();
              try {
                  FileOutputStream out = new FileOutputStream(file);
                  bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                  out.flush();
                  out.close();

              } catch (Exception e) {
                  e.printStackTrace();
              }



*/
              if ( i0.getDrawable() == null) {
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

    public static Bitmap viewtobitmap(View view, int width, int hieght){
        Bitmap bitmap=Bitmap.createBitmap(width,hieght,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;

    }


    public void open(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.isla);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    public void frame1(View view){
        i1.setVisibility(View.VISIBLE);
        i2.setVisibility(View.INVISIBLE);
        i3.setVisibility(View.INVISIBLE);
        i4.setVisibility(View.INVISIBLE);
        i6.setVisibility(View.INVISIBLE);
        i5.setVisibility(View.VISIBLE);
        dialog.dismiss();

    }
    public void frame2(View view){
        i1.setVisibility(View.INVISIBLE);
        i2.setVisibility(View.VISIBLE);
        i3.setVisibility(View.INVISIBLE);
        i4.setVisibility(View.INVISIBLE);
        i6.setVisibility(View.INVISIBLE);
        i5.setVisibility(View.VISIBLE);
        dialog.dismiss();

    }
    public void frame3(View view){
        i1.setVisibility(View.INVISIBLE);
        i2.setVisibility(View.INVISIBLE);
        i3.setVisibility(View.VISIBLE);
        i4.setVisibility(View.INVISIBLE);
        i6.setVisibility(View.VISIBLE);
        i5.setVisibility(View.INVISIBLE);
        dialog.dismiss();

    }
    public void frame4(View view){
        i1.setVisibility(View.INVISIBLE);
        i2.setVisibility(View.INVISIBLE);
        i3.setVisibility(View.INVISIBLE);
        i4.setVisibility(View.VISIBLE);
        i6.setVisibility(View.INVISIBLE);
        i5.setVisibility(view.VISIBLE);
        dialog.dismiss();

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

                    i0.setImageBitmap(bitmap);

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
