package me.symi.healthysanity.utils;

import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;

public class DPUtils {

    public static int getPixelsFromDP(AppCompatActivity instance, int dpValue)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, instance.getResources().getDisplayMetrics());
    }

}
