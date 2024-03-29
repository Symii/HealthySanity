package me.symi.healthysanity.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.symi.healthysanity.R;
import me.symi.healthysanity.enums.CategoryType;
import me.symi.healthysanity.fragments.CategoryChooseFragment;
import me.symi.healthysanity.fragments.MentalObjectivesFragment;
import me.symi.healthysanity.fragments.NewObjectiveFragment;
import me.symi.healthysanity.fragments.PhysicalObjectivesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MasterActivity extends AppCompatActivity {

    private ImageView backImage;
    private BottomNavigationView bottomNavigationView;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        showFragment(CategoryType.CHOOSE_CATEGORY);

        date = getIntent().getStringExtra("date");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                if(item.getTitle().equals("Kategoria fizyczna"))
                {
                    showFragment(CategoryType.PHYSICAL);
                    return true;
                }
                else if(item.getTitle().equals("Kategoria psychiczna"))
                {
                    showFragment(CategoryType.MENTAL);
                    return true;
                }
                else if(item.getTitle().equals("Własne zadania"))
                {
                    showFragment(CategoryType.CUSTOM);
                    return true;
                }
                return false;
            }
        });

        backImage = findViewById(R.id.backImage);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(CalendarActivity.class);
            }
        });

    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        intent.putExtra("date", date);
        startActivity(intent);
    }

    public void showFragment(CategoryType categoryType)
    {
        Fragment fragment = null;
        switch(categoryType)
        {
            case CHOOSE_CATEGORY:
                fragment = new CategoryChooseFragment(this, date);
                break;
            case PHYSICAL:
                fragment = new PhysicalObjectivesFragment(date);
                break;
            case MENTAL:
                fragment = new MentalObjectivesFragment(date);
                break;
            case CUSTOM:
                fragment = new NewObjectiveFragment();
        }

        if(fragment != null)
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_framelayout, fragment, fragment.getClass().getName());
            transaction.commit();
        }

    }

    public BottomNavigationView getBottomNavigationView()
    {
        return bottomNavigationView;
    }
}