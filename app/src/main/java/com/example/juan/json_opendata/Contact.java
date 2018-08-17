package com.example.juan.json_opendata;

/**
 * Created by juan on 2018/8/17.
 */
//建立contact將取得的資料設定給變數用，顯示在Listview上用
public class Contact {
    private String name;
    private String address;
    private String tel;
    private String img;

    public Contact(String name,String address,String tel,String img){
        this.name=name;
        this.address=address;
        this.tel=tel;
        this.img=img;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getTel(){
        return tel;
    }
    public  String getImg(){
        return img;
    }
}
