package com.example.czero.androidstudy.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.example.czero.androidstudy.R;
import com.example.czero.androidstudy.base.BaseFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by czero on 7/24/16.
 */

public class PersonFragment extends BaseFragment {
    private Button paishezhaopian,huoqutuku;
    CircleImageView circleImageView;
    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE = 2;
    private static int CROP_REQUEST_CODE = 3;

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        return fragment;
    }
    @Override
    protected int obtainLayoutID() {

        return R.layout.fragment_person;
    }

    @Override
    protected void onViewCreated(View view) {
       paishezhaopian= (Button) view.findViewById(R.id.paishezhaopian);
        huoqutuku= (Button) view.findViewById(R.id.huoqutuku);
        circleImageView= (CircleImageView) view.findViewById(R.id.user_face);
        paishezhaopian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });
       huoqutuku.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent1= new Intent(Intent.ACTION_GET_CONTENT);
               intent1.setType("image/*");
               startActivityForResult(intent1, GALLERY_REQUEST_CODE);

           }
       });
    }
    private Uri saveBitmap(Bitmap bm) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/com.example.czero.uploadimage");
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir.getAbsoluteFile() + "xxx.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Uri convertUri(Uri uri)
    {
        InputStream is = null;
        try {
            is = getActivity().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void startImageZoom(Uri uri)
    {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE)
        {
            if(data == null)
            {
                return;
            }
            else
            {
                Bundle extras = data.getExtras();
                if(extras != null)
                {
                    Bitmap bm = extras.getParcelable("data");
                    Uri uri = saveBitmap(bm);
                    startImageZoom(uri);
                }
            }
        }
        else if(requestCode == GALLERY_REQUEST_CODE)
        {
            if(data == null)
            {
                return;
            }
            Uri uri;
            uri = data.getData();
            Uri fileUri = convertUri(uri);
            startImageZoom(fileUri);
        }
        else if(requestCode == CROP_REQUEST_CODE)
        {
            if(data == null)
            {
                return;
            }
            Bundle extras = data.getExtras();
            if(extras == null){
                return;
            }
            Bitmap bm = extras.getParcelable("data");
            circleImageView.setImageBitmap(bm);
//            ImageView imageView = (ImageView)findViewById(R.id.touxiang);
//            imageView.setImageBitmap(bm);


        }
    }
    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }


}
