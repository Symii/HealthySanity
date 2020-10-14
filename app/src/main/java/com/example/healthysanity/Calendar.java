package com.example.healthysanity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

public class Calendar extends AppCompatActivity
{

    CalendarView calendarView;
    TextView selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        ImageView backArrowImage = findViewById(R.id.backImage);
        backArrowImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeActivity(MainActivity.class);
            }
        });

        calendarView = findViewById(R.id.calendarView);
        selectedDate = findViewById(R.id.selectedDateTextView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = "Wybrana data: " + dayOfMonth +"." + month + "." + year + "r.";
                selectedDate.setText(date);
            }
        });

    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}