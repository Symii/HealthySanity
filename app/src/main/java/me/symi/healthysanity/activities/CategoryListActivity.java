package me.symi.healthysanity.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import me.symi.healthysanity.MainActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.symi.healthysanity.R;

public class CategoryListActivity extends AppCompatActivity
{
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        ImageView backImage = findViewById(R.id.backImage);

        backImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeActivity(MainActivity.class);
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuCategory);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                if(item.getTitle().equals("Strona główna"))
                {
                    changeActivity(MainActivity.class);
                    return true;
                }
                else if(item.getTitle().equals("Kalendarz"))
                {
                    changeActivity(CalendarActivity.class);
                    return true;
                }
                else if(item.getTitle().equals("Kategorie"))
                {
                    return true;
                }
                else if(item.getTitle().equals("Wyzwania"))
                {
                    return true;
                }

                return false;
            }
        });
    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }
}