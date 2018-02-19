package com.oxvsys.ImageClassifier;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.oxvsys.ImageClassifier.database.DBHelper;
import com.oxvsys.ImageClassifier.database.DataSource;
import com.oxvsys.ImageClassifier.model.DataItem;
import com.oxvsys.ImageClassifier.sample.SampleDataProvider;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListItem extends AppCompatActivity {

    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        mDataSource = new DataSource(this);
        mDataSource.open();
        List<DataItem> dataItemList = SampleDataProvider.dataItemList;

        Toast.makeText(this, "Database acquired", Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        for (DataItem item : dataItemList) {
            try {
                mDataSource.createItem(item);
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }

        setSupportActionBar(toolbar);
        DataItem dataItem = new DataItem();
        List<String> itemsNames = new ArrayList<>();

        for (DataItem item : dataItemList) {
            itemsNames.add(item.getItemName());
        }
        Collections.sort(itemsNames);
        DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);
        RecyclerView recyclerView = findViewById(R.id.rvItem);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }
}