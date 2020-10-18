package me.symi.healthysanity.listeners;

import android.content.Intent;
import android.view.View;

import me.symi.healthysanity.MainActivity;
import me.symi.healthysanity.activities.CategoryListActivity;

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
        Intent intent = new Intent(instance, CategoryListActivity.class);
        instance.startActivity(intent);
    }

}
