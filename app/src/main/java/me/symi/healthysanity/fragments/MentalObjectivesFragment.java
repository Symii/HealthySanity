package me.symi.healthysanity.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.symi.healthysanity.R;

import me.symi.healthysanity.activities.AssignObjectiveActivity;
import me.symi.healthysanity.database.DatabaseHelper;
import me.symi.healthysanity.enums.ObjectiveType;
import me.symi.healthysanity.utils.DPUtil;

public class MentalObjectivesFragment extends Fragment
{
    private String date;

    public MentalObjectivesFragment(String date)
    {
        this.date = date;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_mental_objectives, container, false);

        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
        final Cursor cursor = databaseHelper.getObjectives(ObjectiveType.MENTAL);
        if(cursor.getCount() <= 0)
        {
            LinearLayout noPhysicalObjectivesLayout = view.findViewById(R.id.no_physical_objectives);
            noPhysicalObjectivesLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            while (cursor.moveToNext())
            {
                final LinearLayout objectiveLayout = addObjectiveLayout(view, cursor.getString(1));
                final int ID = cursor.getInt(0);
                final String objectiveName = cursor.getString(1);
                final String objectiveDescription = cursor.getString(2);
                final String objectiveType = cursor.getString(3);
                final int objectiveTime = cursor.getInt(4);

                objectiveLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openAssignObjectiveActivity(objectiveName, objectiveDescription, objectiveType, objectiveTime, ID);
                    }
                });
            }
        }


        return  view;
    }

    private void openAssignObjectiveActivity(String name, String description, String type, int time, int ID)
    {
        Intent intent = new Intent(getActivity(), AssignObjectiveActivity.class);
        intent.putExtra("date", date);
        intent.putExtra("name", name);
        intent.putExtra("description", description);
        intent.putExtra("type", type);
        intent.putExtra("time", time);
        intent.putExtra("id", ID);

        startActivity(intent);
    }

    private LinearLayout addObjectiveLayout(View view, String objectiveName)
    {
        LinearLayout tasksContainer = view.findViewById(R.id.physical_tasks_container);

        LinearLayout objectiveLayout = new LinearLayout(view.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        DPUtil dpUtils = new DPUtil(getResources());
        params.setMargins(
                dpUtils.getPixelsFromDP(10),
                dpUtils.getPixelsFromDP(20),
                dpUtils.getPixelsFromDP(10),
                0);
        objectiveLayout.setLayoutParams(params);
        int padding = dpUtils.getPixelsFromDP(5);
        objectiveLayout.setPadding(padding, padding, padding, padding);
        objectiveLayout.setBackgroundResource(R.drawable.bg_button);

        ImageView imageView = new ImageView(view.getContext());
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                dpUtils.getPixelsFromDP(40),
                dpUtils.getPixelsFromDP(40)
        );
        imageView.setLayoutParams(imageParams);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageResource(R.drawable.ic_brain);
        imageView.setColorFilter(Color.rgb(255,255,255));

        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        textParams.setMargins(dpUtils.getPixelsFromDP(4), 0, 0, 0);
        textView.setLayoutParams(textParams);
        textView.setGravity(Gravity.CENTER);
        textView.setText(objectiveName);
        textView.setTextSize(19);
        textView.setTextColor(Color.rgb(255, 255, 255));
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);

        objectiveLayout.addView(imageView);
        objectiveLayout.addView(textView);
        tasksContainer.addView(objectiveLayout);

        return objectiveLayout;
    }
}