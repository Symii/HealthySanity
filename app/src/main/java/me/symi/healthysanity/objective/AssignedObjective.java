package me.symi.healthysanity.objective;

import android.os.Parcel;
import android.os.Parcelable;

public class AssignedObjective implements IAssignedObjective, Parcelable
{
    private int assignedID;
    private String date;
    private String startTime;
    private Objective objective;

    public AssignedObjective(int assignedID, Objective objective, String date, String startTime)
    {
        this.assignedID = assignedID;
        this.objective = objective;
        this.date = date;
        this.startTime = startTime;
    }

    @Override
    public String getDate()
    {
        return date;
    }

    @Override
    public String getStartTime()
    {
        return startTime;
    }

    @Override
    public Objective getObjective()
    {
        return objective;
    }

    @Override
    public int getAssignedID()
    {
        return assignedID;
    }

    protected AssignedObjective(Parcel in)
    {
        assignedID = in.readInt();
        date = in.readString();
        startTime = in.readString();
        objective = (Objective) in.readValue(Objective.class.getClassLoader());
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(assignedID);
        dest.writeString(date);
        dest.writeString(startTime);
        dest.writeValue(objective);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<AssignedObjective> CREATOR = new Parcelable.Creator<AssignedObjective>()
    {
        @Override
        public AssignedObjective createFromParcel(Parcel in)
        {
            return new AssignedObjective(in);
        }

        @Override
        public AssignedObjective[] newArray(int size)
        {
            return new AssignedObjective[size];
        }
    };
}