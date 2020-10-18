package me.symi.healthysanity.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import me.symi.healthysanity.enums.ObjectiveType;

public class DefaultDatabase
{
    private ArrayList<String> objectiveNames = new ArrayList<>();
    private ArrayList<Integer> objectiveTimes = new ArrayList<>(); // Time in minutes
    private ArrayList<String> objectiveDescriptions = new ArrayList<>();
    private ArrayList<ObjectiveType> objectiveTypes = new ArrayList<>();

    public DefaultDatabase(SQLiteDatabase db)
    {
        createObjective("Bieganie", 30, "Biegaj przez 30 minut", ObjectiveType.PHYSICAL);
        createObjective("Bieg sprintem", 5, "Biegaj sprintem przez 5 minut", ObjectiveType.PHYSICAL);
        createObjective("Yoga", 20, "Uprawiaj Yoge przez 20 minut", ObjectiveType.PHYSICAL);
        createObjective("Kroki", 120, "Zrób marsz kroków przez 120 minut", ObjectiveType.PHYSICAL);
        createObjective("Zdrowe odżywianie", 60, "Przygotuj zdrowe jedzenie", ObjectiveType.PHYSICAL);

        createObjective("Medytacja", 30, "Medytuj przez 30 minut", ObjectiveType.MENTAL);
        createObjective("Czytanie", 30, "Czytaj ksiązki przez 30 minut", ObjectiveType.MENTAL);
        createObjective("Granie na gitarze", 30, "Zagraj na gitarze przez 30 minut", ObjectiveType.MENTAL);
        createObjective("Instrument", 20, "Pograj na instrumencie przez 20 minut", ObjectiveType.MENTAL);
        createObjective("Kontakt z naturą", 60, "Wyjdź na spacer do lasu na 60 minut", ObjectiveType.MENTAL);

        for(int i = 0; i < objectiveNames.size(); i++)
        {
            ContentValues contentValues = getContentValues(objectiveNames.get(i), objectiveTimes.get(i)
                    , objectiveDescriptions.get(i), objectiveTypes.get(i));
            long result = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        }

    }

    private void createObjective(String name, int time, String description, ObjectiveType objectiveType)
    {
        objectiveNames.add(name);
        objectiveTimes.add(time);
        objectiveDescriptions.add(description);
        objectiveTypes.add(objectiveType);
    }

    private ContentValues getContentValues(String name, int time, String description, ObjectiveType objectiveType)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_NAME, name);
        contentValues.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
        contentValues.put(DatabaseHelper.COLUMN_TYPE, objectiveType.toString());
        contentValues.put(DatabaseHelper.COLUMN_TIME, time);

        return  contentValues;
    }

}
