package me.symi.healthysanity.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.symi.healthysanity.MainActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.symi.healthysanity.R;

import me.symi.healthysanity.enums.CategoryType;
import me.symi.healthysanity.fragments.CategoryChooseFragment;
import me.symi.healthysanity.fragments.MentalObjectivesFragment;
import me.symi.healthysanity.fragments.NewObjectiveFragment;
import me.symi.healthysanity.fragments.PhysicalObjectivesFragment;
import me.symi.healthysanity.utils.DPUtil;
import me.symi.healthysanity.utils.FileUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CalendarActivity extends AppCompatActivity
{

    private CalendarView calendarView;
    private TextView selectedDate;
    private LinearLayout addNewObjectiveButton;
    private String date;
    private BottomNavigationView bottomNavigationView;

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
        addNewObjectiveButton = findViewById(R.id.addNewObjectiveButton);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuCalendar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                if(item.getTitle().equals("Strona główna"))
                {
                    changeActivity(MainActivity.class);
                    return true;
                }
                else if(item.getTitle().equals("Kalendarz"))
                {
                    return true;
                }
                else if(item.getTitle().equals("Kategorie"))
                {
                    changeActivity(CategoryListActivity.class);
                    return true;
                }
                else if(item.getTitle().equals("Wyzwania"))
                {
                    return true;
                }

                return false;
            }
        });

        setCurrentDate();

        addNewObjectiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(MasterActivity.class);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                month += 1;
                date = dayOfMonth + "-" + month + "-" + year;
                String formatedDate = "Wybrana data: " + date;
                selectedDate.setText(formatedDate);

                buildObjectiveLayout();
            }
        });

        Button todayButton = findViewById(R.id.today_button);
        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentDate();
            }
        });
    }


    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        intent.putExtra("date", date);
        startActivity(intent);
    }

    private void setCurrentDate()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy"); // dd/MM/yyyy HH:mm:ss.SSS
        Date now = new Date();
        String strDate = simpleDateFormat.format(now);
        date = strDate;

        String today = "Wybrana data: " + strDate;
        selectedDate.setText(today);
        calendarView.setDate(System.currentTimeMillis());
    }

    private LinearLayout buildObjectiveLayout()
    {
        LinearLayout container = findViewById(R.id.task_container);
        LinearLayout noObjectivesLayout = findViewById(R.id.no_objectives_layout);
        DPUtil dpUtils = new DPUtil(getResources());

        if(container.getChildCount() >= 1)
        {
            container.removeAllViews();
        }

        Random random = new Random();
        if(random.nextInt(2) == 0)
        {
            noObjectivesLayout.setVisibility(View.VISIBLE);
            return null;
        }

        noObjectivesLayout.setVisibility(View.GONE);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
                dpUtils.getPixelsFromDP(40),
                dpUtils.getPixelsFromDP(40)
        );

        params.setMargins(
                dpUtils.getPixelsFromDP(20),
                0,
                dpUtils.getPixelsFromDP(20),
                dpUtils.getPixelsFromDP(40)
        );

        LinearLayout objectiveLayout = new LinearLayout(this);
        objectiveLayout.setLayoutParams(params);

        objectiveLayout.setBackgroundResource(R.drawable.bg_button);
        int padding = dpUtils.getPixelsFromDP(10);
        objectiveLayout.setPadding(padding, padding + 20, padding, padding + 20);
        objectiveLayout.setId(View.generateViewId());

        ImageView icon = new ImageView(this);
        icon.setLayoutParams(iconParams);
        icon.setImageResource(R.drawable.ic_person_running_on_a_treadmill_silhouette_from_side_view);
        icon.setColorFilter(Color.rgb(255, 255, 255));

        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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
        objectiveLayout.addView(completeIcon);
        objectiveLayout.addView(textView);
        // Finally add our objective to container layout
        container.addView(objectiveLayout);

        return objectiveLayout;
    }
}