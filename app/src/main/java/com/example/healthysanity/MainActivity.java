package com.example.healthysanity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private final static String USERNAME = "Użytkownik";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView usernameTextView = (TextView) findViewById(R.id.textUsername);
        usernameTextView.setText(USERNAME);

        LinearLayout categoryLayout = findViewById(R.id.layoutCategory);
        categoryLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openCategoryActivity();
            }
        });

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

    private void openCategoryActivity()
    {
        Intent intent = new Intent(this, CategoryList.class);
        startActivity(intent);
    }

}