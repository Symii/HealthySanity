package me.symi.healthysanity.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.symi.healthysanity.R;
import me.symi.healthysanity.database.DatabaseHelper;
import me.symi.healthysanity.enums.ObjectiveType;


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
        final View view = inflater.inflate(R.layout.fragment_new_objective, container, false);

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

                if(objectiveName.length() <= 0 || objectiveDescription.length() <= 0)
                {
                    displayError("Błąd: musisz wypełnić wszystkie pola!");
                    return;
                }

                int objectiveTime = 0;
                try
                {
                    objectiveTime = Integer.parseInt(objectiveTimeInput.getText().toString());
                }
                catch (Exception exception)
                {
                    displayError("Błąd: podaj czas podany w minutach bez znakow specjalnych!");
                    return;
                }

                if(objectiveTypeRadioGroup.getCheckedRadioButtonId() == -1)
                {
                    displayError("Błąd: wybierz kategorie zadania!");
                    return;
                }

                int selectedId = objectiveTypeRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = view.findViewById(selectedId);
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

    private void displayError(final String errorMess)
    {
        final TextView errorTextView = getView().findViewById(R.id.errorTextView);
        errorTextView.setText(errorMess);
        errorTextView.setVisibility(View.VISIBLE);

        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if(errorTextView.getText().toString().equalsIgnoreCase(errorMess))
                {
                    errorTextView.setVisibility(View.GONE);
                }
            }
        }, 4000);
    }

}