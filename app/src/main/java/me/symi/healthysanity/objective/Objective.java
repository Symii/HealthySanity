package me.symi.healthysanity.objective;

import android.os.Parcel;
import android.os.Parcelable;

import me.symi.healthysanity.enums.ObjectiveType;

public class Objective implements IObjective, Parcelable
{
    private int ID;
    private String name;
    private String description;
    private ObjectiveType type;
    private int time;

    public Objective(int ID, String name, String description, ObjectiveType type, int time)
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.type = type;
        this.time = time;
    }

    @Override
    public String getObjectiveName()
    {
        return name;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public ObjectiveType getType()
    {
        return type;
    }

    @Override
    public int getTime()
    {
        return time;
    }

    @Override
    public int getID()
    {
        return ID;
    }


    protected Objective(Parcel in)
    {
        ID = in.readInt();
        name = in.readString();
        description = in.readString();
        type = (ObjectiveType) in.readValue(ObjectiveType.class.getClassLoader());
        time = in.readInt();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(ID);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeValue(type);
        dest.writeInt(time);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Objective> CREATOR = new Parcelable.Creator<Objective>()
    {
        @Override
        public Objective createFromParcel(Parcel in)
        {
            return new Objective(in);
        }

        @Override
        public Objective[] newArray(int size)
        {
            return new Objective[size];
        }
    };
}