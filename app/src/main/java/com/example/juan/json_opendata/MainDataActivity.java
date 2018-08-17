package com.example.juan.json_opendata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainDataActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView tvname,tvaddress,tvtel;
    private Bundle bundle;
    private String imagefile;
    private Bitmap bmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data);
        findView();
        bundle = this.getIntent().getExtras();
        tvname.setText(bundle.getString("name"));
        tvaddress.setText(bundle.getString("address"));
        tvtel.setText(bundle.getString("tel"));

        imagefile = bundle.getString("img");
        getImage(imagefile);
    }
    public void findView(){
        tvname = findViewById(R.id.tvname);
        tvaddress = findViewById(R.id.tvaddress);
        tvtel = findViewById(R.id.tvtel);
        imageView = findViewById(R.id.imageView);
    }
    //處理遠端圖檔讀取 ，利用Bitmap進行BitmapFactory處理
    private void getImage(final String urlString){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    bmp = BitmapFactory.decodeStream(conn.getInputStream());

                    Message message= mHandler.obtainMessage();
                    message.arg1=1;
                    mHandler.sendMessage(message);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }.start();
    }
    //由Handler設置圖檔
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            imageView.setImageBitmap(bmp);
            return false;
        }
    });

}
