package com.pandian.samuvel.capturecameratuto;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button mOpenCameraButton;
    private ImageView mResultImageView;
    private static final int CAMERA_REQ =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mOpenCameraButton = (Button) findViewById(R.id.openCameraButton);
        mResultImageView = (ImageView) findViewById(R.id.resultImageView);

        mOpenCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(cameraIntent,CAMERA_REQ);
            }
        });
    }
    private File getFile(){
        File folder = new File("sdcard/Capture_Camera_Tuto");
        if(!folder.exists()){
            folder.mkdir();
        }
        File file = new File(folder,"image.jpg");
        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Activity.RESULT_OK){
        String mPath = "sdcard/Capture_Camera_Tuto/image.jpg";
        mResultImageView.setImageDrawable(Drawable.createFromPath(mPath));}
        else {
            Toast.makeText(getApplicationContext(),"Please Taske Picture to Check In",Toast.LENGTH_SHORT).show();
        }
    }
}
