package me.symi.healthysanity.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.symi.healthysanity.R;

import me.symi.healthysanity.MainActivity;

public class ProfileActivity extends AppCompatActivity
{
    private String usernname;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if(getIntent().getStringExtra("usernname") == null)
        {
            changeActivity(MainActivity.class);
            return;
        }

        usernname = getIntent().getStringExtra("usernname");

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(usernname);

        TextView changeNameTextView = findViewById(R.id.changeNameTextView);
        changeNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(SetupActivity.class);
            }
        });

        ImageView backImage = findViewById(R.id.backImage);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(MainActivity.class);
            }
        });
    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }
}