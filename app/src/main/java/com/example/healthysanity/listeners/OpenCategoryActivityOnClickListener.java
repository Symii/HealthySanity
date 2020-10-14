package com.example.healthysanity.listeners;

import android.content.Intent;
import android.view.View;

import com.example.healthysanity.CategoryListActivity;
import com.example.healthysanity.MainActivity;

public class OpenCategoryActivityOnClickListener implements View.OnClickListener
{
    MainActivity instance;

    public OpenCategoryActivityOnClickListener(MainActivity instance)
    {
        this.instance = instance;
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(instance, CategoryListActivity.class);
        instance.startActivity(intent);
    }

}
