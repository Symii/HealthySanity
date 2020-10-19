package me.symi.healthysanity.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.symi.healthysanity.R;

import me.symi.healthysanity.database.DatabaseHelper;
import me.symi.healthysanity.enums.ObjectiveType;
import me.symi.healthysanity.exceptions.NoSuchObjectiveException;
import me.symi.healthysanity.objective.AssignedObjective;
import me.symi.healthysanity.objective.Objective;

public class AssignObjectiveActivity extends AppCompatActivity
{
    private String date;
    private Objective objective;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_objective);

        date = getIntent().getStringExtra("date");

        int objectiveID = getIntent().getIntExtra("id", -1);
        String objectiveName = getIntent().getStringExtra("name");
        String objectiveDescription = getIntent().getStringExtra("description");
        String objectiveType = getIntent().getStringExtra("type");
        int objectiveTime = getIntent().getIntExtra("time", 0);
        objective = new Objective(objectiveID, objectiveName, objectiveDescription, ObjectiveType.valueOf(objectiveType), objectiveTime);


        ImageView backImage = findViewById(R.id.backImage);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity(MasterActivity.class);
            }
        });

        TextView objectiveNameTextView = findViewById(R.id.objectiveName);
        objectiveNameTextView.setText(objectiveName);

        TextView objectiveDescriptionTextView = findViewById(R.id.objectiveDescription);
        objectiveDescriptionTextView.setText(objectiveDescription);

        TextView objectiveDateTextView = findViewById(R.id.objectiveDate);
        objectiveDateTextView.setText(date);

        TextView objectiveTypeTextView = findViewById(R.id.objectiveType);
        objectiveTypeTextView.setText(objectiveType);

        TextView objectiveTimeTextView = findViewById(R.id.objectiveTime);
        objectiveTimeTextView.setText(objectiveTime + " minut");

        Button acceptButton = findViewById(R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText startTimeEditText = findViewById(R.id.editTextTime);
                String startTime = startTimeEditText.getText().toString().trim();
                DatabaseHelper databaseHelper = new DatabaseHelper(AssignObjectiveActivity.this);

                try
                {
                    databaseHelper.assignObjective(objective, date, startTime);
                }
                catch (NoSuchObjectiveException exception)
                {
                    Log.e("Error: ", exception.getMessage());
                }
            }
        });

    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        intent.putExtra("date", date);
        startActivity(intent);
    }
}