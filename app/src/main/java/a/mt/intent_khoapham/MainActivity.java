package a.mt.intent_khoapham;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imgTGDD,imgSMS,imgCamera;
    Intent intent;
    Button btnCamera;
    private final static int ma_gui=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if(requestCode==ma_gui&&resultCode==RESULT_OK&&data!=null)
        {
           Bitmap bitmap= (Bitmap) data.getExtras().get("data");
           imgCamera.setImageBitmap(bitmap);
       }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addEvents() {
        imgTGDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent=new Intent();
              intent.setAction(Intent.ACTION_VIEW);
              intent.setData(Uri.parse("http://thegioididong.com"));
              startActivity(intent);
            }
        });

        imgSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body","Chào Bạn.....");
                intent.setData(Uri.parse("sms:0913598386"));
                startActivity(intent);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,ma_gui);
              ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CAMERA},ma_gui);

            }
        });

    }

    private void addControls() {
        imgTGDD=findViewById(R.id.imgTGDG);
        imgSMS=findViewById(R.id.imgSMS);
        btnCamera=findViewById(R.id.btnCamera);
        imgCamera=findViewById(R.id.imgCamera);
    }
}
