package com.example.countriesandcapitals;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CountryViewActivity extends AppCompatActivity {
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_view);

        scrollView = findViewById(R.id.scrollView);
        scrollView.removeAllViews();
        final DatabaseManager dbm = new DatabaseManager(this);
        ArrayList<String> list = dbm.getCountries();
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(1);
        grid.setRowCount(list.size());

        for(final String country: list) {
            final TextView text = new TextView(this);
            text.setText(country);
            text.setTextSize(35);
            text.setClickable(true);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                    i.putExtra("COUNTRY", ((TextView) view).getText().toString());
                    startActivity(i);
                }
            });
            text.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                   String delete_country = ((TextView)view).getText().toString();
                   dbm.delete(delete_country);
                   return true;

                }
            });


                    grid.addView(text);
        };

        scrollView.addView(grid);




    }
}
