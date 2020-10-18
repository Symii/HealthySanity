package me.symi.healthysanity.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtil
{
    public void writeToFile(Context context, String data)
    {
        try
        {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("data.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();

        }
        catch(IOException exception)
        {
            Log.e("Exception", "File write failed: " + exception.toString());
        }
    }

    public String readFromFile(Context context)
    {
        String data = "";

        try
        {
            InputStream inputStream = context.openFileInput("data.txt");

            if(inputStream != null)
            {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while((receiveString = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                data = stringBuilder.toString();
            }

        }
        catch(FileNotFoundException exception)
        {
            Log.e("Exception", "File not found: " + exception.toString());
        }
        catch(IOException exception)
        {
            Log.e("Exception", "Can not read file: " + exception.toString());
        }

        return data;
    }
}
