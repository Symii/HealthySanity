package me.symi.healthysanity.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.symi.healthysanity.R;

import me.symi.healthysanity.activities.MasterActivity;
import me.symi.healthysanity.enums.ObjectiveType;
import me.symi.healthysanity.fragments.MentalObjectivesFragment;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "HealthySanity.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "objectives";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "objective_name";
    public static final String COLUMN_DESCRIPTION = "objective_description";
    public static final String COLUMN_TYPE = "objective_type";
    public static final String COLUMN_TIME = "objective_time"; // time in minutes

    public DatabaseHelper( Context context)
    {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String create_table_sql = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_TYPE + " TEXT, "
                + COLUMN_TIME + " INTEGER);";

        db.execSQL(create_table_sql);
        new DefaultDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addObjective(String name, String description, ObjectiveType objectiveType, int timeInMinutes)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_TYPE, objectiveType.toString());
        contentValues.put(COLUMN_TIME, timeInMinutes);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
        {
            Toast.makeText(context, "Błąd...", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context, "Pomyślnie stworzyłeś nowe zadanie!", Toast.LENGTH_LONG).show();
        }

    }

    public Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;

        if(database != null)
        {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor getObjectives(ObjectiveType objectiveType)
    {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_TYPE + "='" + objectiveType.toString() + "'";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;

        if(database != null)
        {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
}
