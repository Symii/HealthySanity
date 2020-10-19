package me.symi.healthysanity.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.symi.healthysanity.R;

import me.symi.healthysanity.MainActivity;

public class SetupActivity extends AppCompatActivity
{
    private EditText editTextName;
    private Button saveNameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        editTextName = findViewById(R.id.editTextName);
        saveNameButton = findViewById(R.id.saveNameButton);
        saveNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        // disable back button
    }

    private void onButtonClick()
    {
        String value = editTextName.getText().toString().trim();
        if(value.length() <= 2)
        {
            Toast.makeText(this, "Imie musi mieć minimum 3 znaki", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", value);
        startActivity(intent);
    }
}