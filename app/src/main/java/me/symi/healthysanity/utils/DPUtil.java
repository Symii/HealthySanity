package me.symi.healthysanity.utils;

import android.content.res.Resources;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;

public class DPUtil
{
    private Resources resources;

    public DPUtil(Resources resources)
    {
        this.resources = resources;
    }

    public int getPixelsFromDP(int dpValue)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, resources.getDisplayMetrics());
    }

}
