package me.symi.healthysanity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.symi.healthysanity.R;

import me.symi.healthysanity.activities.CalendarActivity;
import me.symi.healthysanity.activities.CategoryListActivity;
import me.symi.healthysanity.activities.InfoActivity;
import me.symi.healthysanity.activities.ProfileActivity;
import me.symi.healthysanity.activities.SetupActivity;
import me.symi.healthysanity.utils.FileUtil;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private String username = "Użytkownik";
    private DrawerLayout drawer;
    private BottomNavigationView bottomNavigationView;
    private FileUtil fileUtil = new FileUtil();
    private static final String USERNAME_FILE_NAME = "username.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getStringExtra("name") != null)
        {
            final String name = getIntent().getStringExtra("name");
            fileUtil.writeToFile(this, USERNAME_FILE_NAME, name);
            username = name;
        }
        else
        {
            final String nameFromFile = fileUtil.readFromFile(this, USERNAME_FILE_NAME);
            if(nameFromFile != null && nameFromFile.length() >= 3)
            {
                username = nameFromFile;
            }
            else
            {
                changeActivity(SetupActivity.class);
                return;
            }
        }

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        TextView usernameTextView = findViewById(R.id.textUsername);
        usernameTextView.setText(username);

        LinearLayout categoryLayout = findViewById(R.id.layoutCategory);
        categoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(CategoryListActivity.class);
            }
        });

        LinearLayout calendarLayout = findViewById(R.id.layoutCalendar);
        calendarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeActivity(CalendarActivity.class);
            }
        });

        LinearLayout layoutChallenge = findViewById(R.id.layoutChallenge);
        layoutChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "W trakcie tworzenia", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout layoutRewardBadges = findViewById(R.id.layoutRewardBadges);
        layoutRewardBadges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "W trakcie tworzenia", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout layoutProfile = findViewById(R.id.layoutProfile);
        layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("usernname", username);
                startActivity(intent);
            }
        });

        LinearLayout layoutInfo = findViewById(R.id.layoutInfo);
        layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(InfoActivity.class);
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();*/

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

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                if(item.getTitle().equals("Strona główna"))
                {
                    return true;
                }
                else if(item.getTitle().equals("Kalendarz"))
                {
                    changeActivity(CalendarActivity.class);
                    return true;
                }
                else if(item.getTitle().equals("Kategorie"))
                {
                    changeActivity(CategoryListActivity.class);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch(item.getItemId())
        {
            case R.id.nav_home:
                break;
            case R.id.nav_calendar:
                changeActivity(CalendarActivity.class);
                break;
            case R.id.nav_category:
                changeActivity(CategoryListActivity.class);
                break;
            /*case R.id.nav_challenge:
                break;
            case R.id.nav_reward_badges:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_stats:
                break;*/
            default:
                Toast.makeText(getBaseContext(), "W trakcie tworzenia", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}