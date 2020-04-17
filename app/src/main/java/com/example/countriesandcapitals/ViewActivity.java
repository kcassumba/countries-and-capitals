package com.example.countriesandcapitals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewActivity extends AppCompatActivity {
    private TextView display_country;
    private TextView display_capital;
    private Button editbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        display_capital = findViewById(R.id.display_capital);
        display_country = findViewById(R.id.display_country);
        editbutton = findViewById(R.id.editbutton);

        DatabaseManager dbm = new DatabaseManager(this);
        Intent i = getIntent();
        String country = i.getStringExtra("COUNTRY");
        String[] entry = dbm.get(country);
        display_country.setText(entry[0]);
        display_capital.setText(entry[1]);
    }

    public void editPressed(View v){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("ADD", false);
        i.putExtra("COUNTRY",display_country.getText().toString());
        i.putExtra("CAPITAL", display_capital.getText().toString());
        startActivity(i);

    }




}
