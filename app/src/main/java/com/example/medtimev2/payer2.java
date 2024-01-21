package com.example.medtimev2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class payer2 extends AppCompatActivity {

    String[] item = {"1","2","3","4","5","6"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payer2);

        autoCompleteTextView = findViewById(R.id.DropDownMenuMed);
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, item);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(payer2.this, "Item " + item, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
