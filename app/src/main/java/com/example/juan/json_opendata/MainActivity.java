package com.example.juan.json_opendata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ContactAdapter contactAdapter;
    private List<Contact> list;
    private LoadingDATA loadingDATA ;

    @Override
    protected void onResume() {
        super.onResume();
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView =findViewById(R.id.listview);
        loadingDATA = new LoadingDATA(this);

        try{
            list = loadingDATA.execute("http://data.coa.gov.tw/Service/RedirectService.aspx?UnitId=193&url=http%3a%2f%2fdata.coa.gov.tw%2fService%2fOpenData%2fODwsv%2fODwsvTravelFood.aspx").get();

        }catch (Exception e){
            e.printStackTrace();
        }
        contactAdapter = new ContactAdapter(this,list);
        listView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,MainDataActivity.class);
                intent.putExtra("name",list.get(i).getName());
                intent.putExtra("address",list.get(i).getAddress());
                intent.putExtra("tel",list.get(i).getTel());
                intent.putExtra("img",list.get(i).getImg());
                startActivity(intent);

            }
        });

    }
}
