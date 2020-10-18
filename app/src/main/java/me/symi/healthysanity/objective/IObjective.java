package me.symi.healthysanity.objective;

import me.symi.healthysanity.enums.ObjectiveType;

public interface IObjective
{
    String getObjectiveName();
    String getDescription();
    ObjectiveType getType();
    int getTime();
}
