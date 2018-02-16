package com.oxvsys.ImageClassifier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.oxvsys.ImageClassifier.model.DataItem;
import com.oxvsys.ImageClassifier.sample.SampleDataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DataItem dataItem = new DataItem();
        List<DataItem> dataItemList = SampleDataProvider.dataItemList;
        List<String> itemsNames = new ArrayList<>();

        for (DataItem item : dataItemList) {
            itemsNames.add(item.getItemName());
        }
        Collections.sort(itemsNames);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_list_item_1, itemsNames
//        );
//        ListView listView = findViewById(android.R.id.list);
//        listView.setAdapter(adapter);
        DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);
        RecyclerView recyclerView = findViewById(R.id.rvItem);
        recyclerView.setAdapter(adapter);
    }

}
