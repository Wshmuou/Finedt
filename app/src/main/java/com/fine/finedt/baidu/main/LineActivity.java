package com.fine.finedt.baidu.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fine.finedt.R;
import com.fine.finedt.baidu.LocationApplication;
import com.fine.finedt.baidu.retrofit.Value;
import com.fine.finedt.baidu.retrofit.retrofitUtils;

public class LineActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        ((LocationApplication)getApplicationContext()).getActivity(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        retrofitUtils.send(null,Value.download ,0);
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        ((LocationApplication)getApplicationContext()).getListView(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LineActivity.this,"position="+position+"Str[position]="+((LocationApplication) getApplicationContext()).getStr()[position],Toast.LENGTH_LONG).show();
                ((LocationApplication) getApplicationContext()).setPosition(position);
                Intent intent=new Intent(LineActivity.this,IndoorLocationActivity.class);
                startActivity(intent);
            }
        });
    }
}
