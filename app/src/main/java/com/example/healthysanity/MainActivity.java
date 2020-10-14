package com.example.healthysanity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.healthysanity.listeners.OpenCategoryActivityOnClickListener;

public class MainActivity extends AppCompatActivity
{
    private final static String USERNAME = "Użytkownik";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView usernameTextView = findViewById(R.id.textUsername);
        usernameTextView.setText(USERNAME);

        LinearLayout categoryLayout = findViewById(R.id.layoutCategory);
        categoryLayout.setOnClickListener(new OpenCategoryActivityOnClickListener(this));

        LinearLayout calendarLayout = findViewById(R.id.layoutCalendar);
        calendarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeActivity(Calendar.class);
            }
        });

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }

}