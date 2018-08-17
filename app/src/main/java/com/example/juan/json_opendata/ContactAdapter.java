package com.example.juan.json_opendata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by juan on 2018/8/17.
 */

public class ContactAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<Contact> contactList; //使用LIST陣列存放資料(JSON式陣列字串) Contact是自訂的類別

    public ContactAdapter(Context c , List<Contact> contactList) {
        //layoutInflater = LayoutInflater.from(c);
        layoutInflater =(LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        if(contactList!=null){
            return contactList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(contactList!=null){
            return contactList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        if(contactList!=null) {
            return contactList.indexOf(getItem(i));
        }
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Contact data = contactList.get(i);
        view = layoutInflater.inflate(R.layout.item,null);

        TextView name = view.findViewById(R.id.nametext);
        TextView address = view.findViewById(R.id.address);

        name.setText(data.getName());
        address.setText(data.getAddress());

        return view;
    }
}
