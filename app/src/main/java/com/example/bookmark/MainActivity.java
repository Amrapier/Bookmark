package com.example.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<String> myBookMark = new ArrayList<>();
        ListView myListView = (ListView) findViewById(R.id.lv_bookmark);
        EditText editText =(EditText) findViewById(R.id.et_url);
        ArrayAdapter myListViewAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,myBookMark);
        myListView.setAdapter(myListViewAdapter);
        Button button = (Button) findViewById(R.id.bt_addurl);

        // add button clickListener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUrl = editText.getText().toString();
                myBookMark.add(inputUrl);
                editText.setText("");
                myListViewAdapter.notifyDataSetChanged();
            }
        });

        // listview clickListener
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Determine whether the url is valid
                if (myBookMark.get(i).equals(null)|| myBookMark.get(i).equals("")){
                    Toast.makeText(getApplicationContext(),"URL is invalid",Toast.LENGTH_SHORT).show();
                }else {
                    //the url is valid, go to the ActivityWeb
                    Intent intent = new Intent(getApplicationContext(),ActivityWeb.class);
                    intent.putExtra("URL",myBookMark.get(i).toString());
                    startActivity(intent);
                }
            }
        });

    }
}