package com.example.juan.json_opendata;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan on 2018/8/17.
 */

public class LoadingDATA extends AsyncTask<String,Void,List<Contact>>{
    private Context context;
    private ProgressDialog progressDialog;

    public LoadingDATA(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);

    }

    @Override
    protected List<Contact> doInBackground(String... strings) {
        List<Contact> result = new ArrayList<Contact>();
        URL url = null;
        try{
            url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //JSON資料流用 BufferedReader→InputStreamReader→getInputStream
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) !=null){ //一條一條資料讀取再結合成字串
                sb.append(line);
               // Log.i("HAHA",line);
            }
            reader.close(); //關閉資料流

            JSONArray jsonArray = new JSONArray(sb.toString()); //再將字串轉換成JSONArray型態
            for(int i = 0 ; i<jsonArray.length();i++){
                if(jsonArray.getJSONObject(i)!=null) {
                    result.add(convertContact(jsonArray.getJSONObject(i)));
                    //Log.v("data=", jsonArray.getJSONObject(i).toString());
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    private Contact convertContact(JSONObject jsonObject) throws JSONException{
        String name = jsonObject.getString("Name");
        String address = jsonObject.getString("Address");
        String tel = jsonObject.getString("Tel");
        String img = jsonObject.getString("PicURL");
        Log.i("img=",img);
        return new Contact(name,address,tel,img);
    }
    @Override
    protected void onPreExecute() {  //預先執行的方法(在背景執行前執行
        super.onPreExecute();
        progressDialog.setMessage("檔案下載中...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(List<Contact> contactList) { //在背景執行完執行
        super.onPostExecute(contactList);
        progressDialog.dismiss();
    }
}
