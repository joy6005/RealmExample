package com.jackandgibbs.c0696863_test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.jackandgibbs.c0696863_test2.R;
import io.realm.Realm;
import io.realm.RealmResults;

public class EmployeeList extends AppCompatActivity
{
    Realm realm;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(com.jackandgibbs.c0696863_test2.R.layout.activity_employee_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Employees");

        lv= (ListView) findViewById(com.jackandgibbs.c0696863_test2.R.id.listView);

        Realm.init(this);

        realm=Realm.getDefaultInstance();

        RealmResults<EmployeeData> test = realm.where(EmployeeData.class).findAll();
        final String[] ids=new String[test.size()];
        String[] names=new String[test.size()];

        for(int i=0;i<test.size();i++)
        {
            ids[i]=test.get(i).employeeID;
            names[i]=test.get(i).employeeName;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, names);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent in = new Intent(EmployeeList.this, EmployeeDetail.class);
                in.putExtra("EID", ids[i]);
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
