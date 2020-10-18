package me.symi.healthysanity.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import me.symi.healthysanity.enums.ObjectiveType;
import me.symi.healthysanity.exceptions.NoSuchObjectiveException;
import me.symi.healthysanity.objective.AssignedObjective;
import me.symi.healthysanity.objective.IAssignedObjective;
import me.symi.healthysanity.objective.IObjective;
import me.symi.healthysanity.objective.Objective;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "HealthySanity.db";
    private static final int DATABASE_VERSION = 3;

    public static final String OBJECTIVE_TABLE_NAME = "objectives";
    public static final String OBJECTIVE_ID = "id"; // (int) Primary key
    public static final String OBJECTIVE_NAME = "objective_name"; // (text)
    public static final String OBJECTIVE_DESCRIPTION = "objective_description"; // (text)
    public static final String OBJECTIVE_TYPE = "objective_type"; // (text)
    public static final String OBJECTIVE_TIME = "objective_time"; // (int) time in minutes

    public static final String ASSIGNED_TABLE_NAME = "assigned";
    public static final String ASSIGNED_ID = "id"; // (int) Primary key
    public static final String ASSIGNED_OBJECTIVE_ID = "objective_id"; // (int) Foreign key
    public static final String ASSIGNED_DATE = "date"; // (text)
    public static final String ASSIGNED_START_TIME = "time"; // (text)
    public static final String ASSIGNED_COMPLETE = "complete"; // (boolean)

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String create_objective_table_sql = "CREATE TABLE " + OBJECTIVE_TABLE_NAME + " ("
                + OBJECTIVE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OBJECTIVE_NAME + " TEXT, "
                + OBJECTIVE_DESCRIPTION + " TEXT, "
                + OBJECTIVE_TYPE + " TEXT, "
                + OBJECTIVE_TIME + " INTEGER);";
        db.execSQL(create_objective_table_sql);

        String create_assigned_objectives_table_sql = "CREATE TABLE " + ASSIGNED_TABLE_NAME + " ("
                + ASSIGNED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ASSIGNED_OBJECTIVE_ID + " INTEGER REFERENCES objectives (id), "
                + ASSIGNED_DATE + " TEXT, "
                + ASSIGNED_START_TIME + " TEXT, "
                + ASSIGNED_COMPLETE + " BOOLEAN);";
        db.execSQL(create_assigned_objectives_table_sql);

        new DefaultDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + OBJECTIVE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ASSIGNED_TABLE_NAME);
        onCreate(db);
    }

    public void addObjective(IObjective objective)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(OBJECTIVE_NAME, objective.getObjectiveName());
        contentValues.put(OBJECTIVE_DESCRIPTION, objective.getDescription());
        contentValues.put(OBJECTIVE_TYPE, objective.getType().toString());
        contentValues.put(OBJECTIVE_TIME, objective.getTime());

        long result = db.insert(OBJECTIVE_TABLE_NAME, null, contentValues);
        if(result == -1)
        {
            Toast.makeText(context, "Błąd...", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context, "Pomyślnie stworzyłeś nowe zadanie!", Toast.LENGTH_LONG).show();
        }

    }

    public void assignObjective(IAssignedObjective assignedObjective) throws NoSuchObjectiveException
    {
        if(containsObjectiveWithID(assignedObjective.getID()) == false)
        {
            throw new NoSuchObjectiveException("Can not find objective with this ID!");
        }

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ASSIGNED_OBJECTIVE_ID, assignedObjective.getID());
        contentValues.put(ASSIGNED_DATE, assignedObjective.getDate());
        contentValues.put(ASSIGNED_START_TIME, assignedObjective.getStartTime());
        contentValues.put(ASSIGNED_COMPLETE, false);

        long result = db.insert(ASSIGNED_TABLE_NAME, null, contentValues);
        if(result == -1)
        {
            Toast.makeText(context, "Błąd...", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(context, "Pomyślnie przypisałeś zadanie!", Toast.LENGTH_LONG).show();
        }

    }

    public boolean containsObjectiveWithID(int ID)
    {
        String query = "SELECT * FROM " + OBJECTIVE_TABLE_NAME + " WHERE " + OBJECTIVE_ID + "=" + ID;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;

        if(database != null)
        {
            cursor = database.rawQuery(query, null);
            if(cursor.getCount() >= 1)
            {
                return true;
            }
        }
        return false;
    }

    public Objective getObjectiveByID(int ID)
    {
        String query = "SELECT * FROM " + OBJECTIVE_TABLE_NAME + " WHERE " + OBJECTIVE_ID + "=" + ID;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;

        if(database != null)
        {
            cursor = database.rawQuery(query, null);
            if(cursor.moveToNext())
            {
                final String name = cursor.getString(1);
                final String description = cursor.getString(2);
                final String type = cursor.getString(3);
                final int time = cursor.getInt(4);

                return new Objective(name, description, ObjectiveType.valueOf(type), time);
            }
        }
        return null;
    }

    public ArrayList<Objective> getObjectivesByDate(String date)
    {
        String query = "SELECT "
                + OBJECTIVE_NAME + ", "
                + OBJECTIVE_DESCRIPTION + ", "
                + OBJECTIVE_TIME + ", "
                + OBJECTIVE_TYPE
                + " FROM " + OBJECTIVE_TABLE_NAME
                + " INNER JOIN " + ASSIGNED_TABLE_NAME + " ON "
                + OBJECTIVE_TABLE_NAME + "." + OBJECTIVE_ID
                + " = " + ASSIGNED_TABLE_NAME + "." + ASSIGNED_OBJECTIVE_ID
                + " WHERE " + ASSIGNED_DATE + "='" + date + "';";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor;

        if(database != null)
        {
            cursor = database.rawQuery(query, null);
            ArrayList<Objective> objectives = new ArrayList<>();
            while(cursor.moveToNext())
            {
                final String name = cursor.getString(0);
                final String description = cursor.getString(1);
                final int time = cursor.getInt(2);
                final ObjectiveType type = ObjectiveType.valueOf(cursor.getString(3));

                objectives.add(new Objective(name, description, type, time));
            }
            return objectives;
        }

        return null;
    }

    public Cursor readAllData()
    {
        String query = "SELECT * FROM " + OBJECTIVE_TABLE_NAME;
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
        String query = "SELECT * FROM " + OBJECTIVE_TABLE_NAME + " WHERE " + OBJECTIVE_TYPE + "='" + objectiveType.toString() + "'";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = null;

        if(database != null)
        {
            cursor = database.rawQuery(query, null);
        }
        return cursor;
    }
}
