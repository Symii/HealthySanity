package com.example.healthysanity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.healthysanity.enums.CategoryType;

public class CategoryChooseFragment extends Fragment {

    private MasterActivity instance;
    public CategoryChooseFragment(MasterActivity instance)
    {
        this.instance = instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_category_choose, container, false);

        LinearLayout physicalCategory = view.findViewById(R.id.physical_layout);
        LinearLayout mentalCategory = view.findViewById(R.id.mental_layout);
        LinearLayout ownCategory = view.findViewById(R.id.ownCategoryLayout);

        physicalCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new PhysicalObjectivesFragment());
                instance.getBottomNavigationView().setSelectedItemId(R.id.physical_category);
            }
        });

        mentalCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new MentalObjectivesFragment());
                instance.getBottomNavigationView().setSelectedItemId(R.id.mental_category);
            }
        });

        ownCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new NewObjectiveFragment());
                instance.getBottomNavigationView().setSelectedItemId(R.id.custom_category);
            }
        });

        return view;
    }

    public void showFragment(Fragment fragment)
    {
        FragmentTransaction transaction = instance.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_framelayout, fragment, fragment.getClass().getName());
        transaction.commit();
    }



}