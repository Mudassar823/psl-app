package com.sasteyguru.pslscheduleandlivescores;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class BitmapUtills {


    public static String insertimage(ContentResolver cr,Bitmap source,String title,String des) throws IOException {

        ContentValues contentValues=new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE,title);
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME,title);
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,title);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
        contentValues.put(MediaStore.Images.Media.DATE_ADDED,System.currentTimeMillis());
        contentValues.put(MediaStore.Images.Media.DATE_TAKEN,System.currentTimeMillis());
        Uri uri=null;
        String url=null;


        uri=cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
        if (source!=null){
            OutputStream outputStream=cr.openOutputStream(uri);
            source.compress(Bitmap.CompressFormat.JPEG,50,outputStream);
            outputStream.close();
            long id= ContentUris.parseId(uri);
            Bitmap minithumb=MediaStore.Images.Thumbnails.getThumbnail(cr,id,MediaStore.Images.Thumbnails.MINI_KIND,null);
            storethumbnail(cr,minithumb,50f,50f,id,MediaStore.Images.Thumbnails.MINI_KIND);

        }

else {


    cr.delete(uri,null,null);
    cr=null;
        }
if (uri!=null)
    url=uri.toString();
    return url;


    }

    private static final Bitmap storethumbnail(ContentResolver cr, Bitmap source, float width, float hieght,long id, int miniKind) {

        Matrix matrix=new Matrix();
        float Scalex=width/source.getWidth();
        float scaley=hieght/source.getHeight();
        matrix.setScale(Scalex,scaley);
        Bitmap thumb=Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),matrix,true);
        ContentValues contentValues=new ContentValues(4);
        contentValues.put(MediaStore.Images.Thumbnails.KIND,miniKind);
        contentValues.put(MediaStore.Images.Thumbnails.IMAGE_ID,id);
        contentValues.put(MediaStore.Images.Thumbnails.WIDTH,width);
        contentValues.put(MediaStore.Images.Thumbnails.HEIGHT,hieght);
        Uri uri=cr.insert(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,contentValues);
        try{

            OutputStream outputStream=cr.openOutputStream(uri);
            thumb.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            outputStream.close();
            return thumb;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
