package me.symi.healthysanity.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.symi.healthysanity.R;

public class AssignObjectiveActivity extends AppCompatActivity
{
    private String date;
    private String objectiveName;
    private String objectiveDescription;
    private String objectiveType;
    private int objectiveTime; // in minutes

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_objective);

        date = getIntent().getStringExtra("date");
        objectiveName = getIntent().getStringExtra("name");
        objectiveDescription = getIntent().getStringExtra("description");
        objectiveType = getIntent().getStringExtra("type");
        objectiveTime = getIntent().getIntExtra("time", 0);

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

    }

    private void changeActivity(Class destination)
    {
        Intent intent = new Intent(this, destination);
        intent.putExtra("date", date);
        startActivity(intent);
    }
}