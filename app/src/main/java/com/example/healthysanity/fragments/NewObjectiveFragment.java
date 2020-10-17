package com.example.healthysanity.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.healthysanity.R;
import com.example.healthysanity.database.DatabaseHelper;
import com.example.healthysanity.enums.ObjectiveType;


public class NewObjectiveFragment extends Fragment
{
    private Button addButton;
    private EditText objectiveNameInput;
    private EditText objectiveDescriptionInput;
    private EditText objectiveTimeInput;
    private RadioGroup objectiveTypeRadioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_new_objective, container, false);

        addButton = view.findViewById(R.id.addButton);
        objectiveNameInput = view.findViewById(R.id.editTextObjectiveName);
        objectiveDescriptionInput = view.findViewById(R.id.editTextDescription);
        objectiveTimeInput = view.findViewById(R.id.editTextTime);
        objectiveTypeRadioGroup = view.findViewById(R.id.radioGroup);

        RadioButton radioButton = view.findViewById(R.id.radioButtonPhysical);
        radioButton.setSelected(true);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String objectiveName = objectiveNameInput.getText().toString().trim();
                String objectiveDescription = objectiveDescriptionInput.getText().toString().trim();
                int objectiveTime = 0;
                try
                {
                    objectiveTime = Integer.parseInt(objectiveTimeInput.getText().toString());
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                    Toast.makeText(getContext(), "Podaj czas podany w minutach!", Toast.LENGTH_SHORT);
                    return;
                }

                if(objectiveName.length() <= 0 || objectiveDescription.length() <= 0)
                {
                    Toast.makeText(getContext(), "Wypełnij wszystkie wymagane pola!", Toast.LENGTH_SHORT);
                    return;
                }

                int selectedId = objectiveTypeRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = v.findViewById(selectedId);
                String radioButtonText = radioButton.getText().toString();
                ObjectiveType objectiveType = ObjectiveType.PHYSICAL;
                switch (radioButtonText)
                {
                    case "Fizyczna":
                        objectiveType = ObjectiveType.PHYSICAL;
                        break;
                    case "Psychiczna":
                        objectiveType = ObjectiveType.MENTAL;
                        break;
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
                databaseHelper.addObjective(objectiveName, objectiveDescription, objectiveType, objectiveTime);
            }
        });

        return view;
    }

}