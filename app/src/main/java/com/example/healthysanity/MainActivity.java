package com.example.healthysanity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout categoryLayout = findViewById(R.id.layoutCategory);
        categoryLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openCategoryActivity();
            }
        });

    }

    private void openCategoryActivity()
    {
        Intent intent = new Intent(this, CategoryList.class);
        startActivity(intent);
    }

}