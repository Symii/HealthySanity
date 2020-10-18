package me.symi.healthysanity.objective;

import me.symi.healthysanity.enums.ObjectiveType;

public class Objective implements IObjective
{
    private String name;
    private String description;
    private ObjectiveType type;
    private int time;

    public Objective(String name, String description, ObjectiveType type, int time)
    {
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

}
