package me.symi.healthysanity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.symi.healthysanity.R;

import me.symi.healthysanity.activities.CalendarActivity;
import me.symi.healthysanity.listeners.OpenStatisticsActivityOnClickListener;

public class MainActivity extends AppCompatActivity
{
    private final static String USERNAME = "Użytkownik";
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView usernameTextView = findViewById(R.id.textUsername);
        usernameTextView.setText(USERNAME);

        LinearLayout categoryLayout = findViewById(R.id.layoutCategory);
        categoryLayout.setOnClickListener(new OpenStatisticsActivityOnClickListener(this));

        LinearLayout calendarLayout = findViewById(R.id.layoutCalendar);
        calendarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeActivity(CalendarActivity.class);
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ImageView menuImage = findViewById(R.id.imageMenu);
        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawer.isDrawerOpen(GravityCompat.START) == false)
                {
                    drawer.openDrawer(Gravity.RIGHT);
                }
            }
        });

    }


    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }

}