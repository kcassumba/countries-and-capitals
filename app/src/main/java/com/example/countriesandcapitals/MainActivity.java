package com.example.countriesandcapitals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText countrybox;
    private EditText capitalbox;
    private Button addbutton;
    private Button viewbutton;
    private boolean add;

    //ASK FISCHBACH FOR HELP my program; having difficulty knowing if it is add or editing and stops
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        countrybox = findViewById(R.id.countrybox);
        capitalbox = findViewById(R.id.capitalbox);
        addbutton = findViewById(R.id.addbutton);
        viewbutton = findViewById(R.id.viewbutton);

        Intent i = getIntent();
        add = i.getBooleanExtra("ADD", true);
        if(add){
            //add a movie
            addbutton.setText("ADD COUNTRY");
        }else{
            //edit a movie
            addbutton.setText("EDIT COUNTRY");
            countrybox.setText(i.getStringExtra("COUNTRY"));
            capitalbox.setText(i.getStringExtra("CAPITAL"));

        }



    }

    public void addPressed(View v){

        String country = countrybox.getText().toString();
        String capital = capitalbox.getText().toString();
        DatabaseManager dbm = new DatabaseManager(this);

       if(add) {
            dbm.insert(country, capital);
        }else{
          dbm.updateByCountry(country, capital);
       }
       // finish();

    }

    public void viewPressed(View v){
        Intent i = new Intent(this, CountryViewActivity.class);
        startActivityForResult(i, 1);
    }





}
