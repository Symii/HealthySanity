package me.symi.healthysanity.exceptions;

public class NoSuchObjectiveException extends Exception
{
    public NoSuchObjectiveException(String message)
    {
        super(message);
    }

    public NoSuchObjectiveException(Throwable cause)
    {
        super(cause);
    }

    public NoSuchObjectiveException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public String getMessage()
    {
        return super.getMessage();
    }

}
