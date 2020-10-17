package com.example.healthysanity.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.healthysanity.R;
import com.example.healthysanity.enums.CategoryType;
import com.example.healthysanity.fragments.CategoryChooseFragment;
import com.example.healthysanity.fragments.MentalObjectivesFragment;
import com.example.healthysanity.fragments.NewObjectiveFragment;
import com.example.healthysanity.fragments.PhysicalObjectivesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MasterActivity extends AppCompatActivity {

    private ImageView backImage;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        showFragment(CategoryType.CHOOSE_CATEGORY);

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
        startActivity(intent);
    }

    public void showFragment(CategoryType categoryType)
    {
        Fragment fragment = null;
        switch(categoryType)
        {
            case CHOOSE_CATEGORY:
                fragment = new CategoryChooseFragment(this);
                break;
            case PHYSICAL:
                fragment = new PhysicalObjectivesFragment();
                break;
            case MENTAL:
                fragment = new MentalObjectivesFragment();
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