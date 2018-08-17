package com.example.juan.json_opendata;

/**
 * Created by juan on 2018/8/17.
 */
//建立contact將取得的資料設定給變數用，顯示在Listview上用
public class Contact {
    private String name;
    private String address;

    public Contact(String name,String address){
        this.name=name;
        this.address=address;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

}
