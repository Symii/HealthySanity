package com.example.healthysanity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

}