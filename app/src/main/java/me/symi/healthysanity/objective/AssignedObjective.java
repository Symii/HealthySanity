package me.symi.healthysanity.objective;

public class AssignedObjective implements IAssignedObjective
{
    private int ID;
    private String date;
    private String startTime;

    public AssignedObjective(int ID, String date, String startTime)
    {
        this.ID = ID;
        this.date = date;
        this.startTime = startTime;
    }

    @Override
    public int getID()
    {
        return ID;
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
}
