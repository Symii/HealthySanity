package com.example.healthysanity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.healthysanity.utils.DPUtils;

import org.w3c.dom.Text;

import java.util.Random;

public class CalendarActivity extends AppCompatActivity
{

    private CalendarView calendarView;
    private TextView selectedDate;

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

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                month += 1;
                String date = "Wybrana data: " + dayOfMonth +"." + month + "." + year + "r.";
                selectedDate.setText(date);

                buildDynamicObjectives();
            }
        });
    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }

    private void buildDynamicObjectives()
    {
        LinearLayout container = findViewById(R.id.task_container);
        LinearLayout noObjectivesLayout = findViewById(R.id.no_objectives_layout);

        if(container.getChildCount() >= 1)
        {
            container.removeAllViews();
        }

        Random random = new Random();
        if(random.nextInt(2) == 0)
        {
            noObjectivesLayout.setVisibility(View.VISIBLE);
            return;
        }

        noObjectivesLayout.setVisibility(View.GONE);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                DPUtils.getPixelsFromDP(this, 40),
                DPUtils.getPixelsFromDP(this, 40)
        );

        params.setMargins(DPUtils.getPixelsFromDP(this, 20),
                0,
                DPUtils.getPixelsFromDP(this, 20),
                DPUtils.getPixelsFromDP(this, 40)
        );

        LinearLayout objectiveLayout = new LinearLayout(this);
        objectiveLayout.setLayoutParams(params);

        objectiveLayout.setBackgroundResource(R.drawable.bg_button);
        int padding = DPUtils.getPixelsFromDP(this, 10);
        objectiveLayout.setPadding(padding, padding, padding, padding);
        objectiveLayout.setId(R.id.objective1);

        ImageView icon = new ImageView(this);
        icon.setLayoutParams(iconParams);
        icon.setImageResource(R.drawable.ic_person_running_on_a_treadmill_silhouette_from_side_view);
        icon.setColorFilter(Color.rgb(255, 255, 255));

        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        textViewParams.setMargins(5, 0, 0, 0);

        TextView textView = new TextView(this);
        textView.setLayoutParams(textViewParams);
        textView.setText("Przebiegnij dystans 2 kilometrów");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.rgb(255, 255, 255));
        textView.setTextSize(18);

        ImageView completeIcon = new ImageView(this);
        completeIcon.setLayoutParams(iconParams);
        completeIcon.setImageResource(R.drawable.ic_done_24);
        completeIcon.setColorFilter(Color.rgb(0, 255, 0));

        // Adding content items to objective layout
        objectiveLayout.addView(icon);
        objectiveLayout.addView(textView);
        objectiveLayout.addView(completeIcon);
        // Finally add our objective to container layout
        container.addView(objectiveLayout);
    }
}