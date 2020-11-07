package com.gzeinnumer.mylibdynamiccheckbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DynamicCheckBox dynamicCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dynamicCheckBox = findViewById(R.id.dc);

//        sample1();
//        sample2();
    }

    private void sample1() {
        ArrayList<String> listString = new ArrayList<String>();
        listString.add("Satu");
        listString.add("Dua");
        listString.add("Tiga");
        listString.add("Empat");

        dynamicCheckBox.setItemList(listString).setOnCheckedChangeListener(new DynamicCheckBox.OnCheckedChangeListener<String>() {
            @Override
            public void onCheckedChanged(ArrayList<String> items) {
                for (int i=0; i<items.size(); i++){
                    Log.d(TAG, "onCheckedChanged: "+items.get(i));
                }
            }

            @Override
            public void onCheckedShow(String clickedValue) {
                Log.d(TAG, "onCheckedShow: "+clickedValue);
            }
        });
    }

    private void sample2() {
        ArrayList<ExampleModel> listObject = new ArrayList<>();
        listObject.add(new ExampleModel(1, "Zein", "Balbar"));
        listObject.add(new ExampleModel(2, "Zein2", "Balbar2"));
        listObject.add(new ExampleModel(3, "Zein3", "Balbar3"));
        listObject.add(new ExampleModel(4, "Zein4", "Balbar4"));

        dynamicCheckBox.setItemList(listObject).setOnCheckedChangeListener(new DynamicCheckBox.OnCheckedChangeListener<ExampleModel>() {
            @Override
            public void onCheckedChanged(ArrayList<ExampleModel> items) {
                for (int i=0; i<items.size(); i++){
                    Log.d(TAG, "onCheckedChanged: "+items.get(i).getName());
                    Log.d(TAG, "onCheckedChanged: "+items.get(i).getAddress());
                }
            }

            @Override
            public void onCheckedShow(String clickedValue) {
                Log.d(TAG, "onCheckedShow: "+clickedValue);
            }
        });
    }
}