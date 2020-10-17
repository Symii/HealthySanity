package com.example.healthysanity.listeners;

import android.view.View;

import com.example.healthysanity.MainActivity;

public class OpenStatisticsActivityOnClickListener implements View.OnClickListener
{
    MainActivity instance;

    public OpenStatisticsActivityOnClickListener(MainActivity instance)
    {
        this.instance = instance;
    }

    @Override
    public void onClick(View v)
    {
        // TODO:
        //Intent intent = new Intent(instance, CategoryListActivity.class);
        //instance.startActivity(intent);
    }

}
