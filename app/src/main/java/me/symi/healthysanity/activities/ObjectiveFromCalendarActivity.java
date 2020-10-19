package me.symi.healthysanity.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.symi.healthysanity.R;

import me.symi.healthysanity.database.DatabaseHelper;
import me.symi.healthysanity.objective.AssignedObjective;

public class ObjectiveFromCalendarActivity extends AppCompatActivity
{
    private AssignedObjective assignedObjective;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objective_from_calendar);


        if(getIntent().getParcelableExtra("AssignedObjective") == null)
        {
            changeActivity(CalendarActivity.class);
            return;
        }

        assignedObjective = getIntent().getParcelableExtra("AssignedObjective");
        setupTextViews();

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatabaseHelper databaseHelper = new DatabaseHelper(ObjectiveFromCalendarActivity.this);
                databaseHelper.deleteAssignedObjectiveByID(assignedObjective.getAssignedID());
                changeActivity(CalendarActivity.class);
            }
        });

        ImageView backImage = findViewById(R.id.backImage);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(CalendarActivity.class);
            }
        });

    }

    private void setupTextViews()
    {
        TextView objectiveNameTextView = findViewById(R.id.objectiveName);
        objectiveNameTextView.setText(assignedObjective.getObjective().getObjectiveName());

        TextView objectiveDescriptionTextView = findViewById(R.id.objectiveDescription);
        objectiveDescriptionTextView.setText(assignedObjective.getObjective().getDescription());

        TextView objectiveDateTextView = findViewById(R.id.objectiveDate);
        objectiveDateTextView.setText(assignedObjective.getDate());

        TextView objectiveTypeTextView = findViewById(R.id.objectiveType);
        objectiveTypeTextView.setText(assignedObjective.getObjective().getType().toString());

        TextView objectiveHourTextView = findViewById(R.id.objectiveHour);
        objectiveHourTextView.setText(assignedObjective.getStartTime());

        String time = assignedObjective.getObjective().getTime() + " minut";
        TextView objectiveTimeTextView = findViewById(R.id.objectiveTimeTextView);
        objectiveTimeTextView.setText(time);

        TextView assignObjectiveTextView = findViewById(R.id.assignObjectiveText);
        assignObjectiveTextView.setText("Zadanie #" + assignedObjective.getAssignedID());

    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }
}