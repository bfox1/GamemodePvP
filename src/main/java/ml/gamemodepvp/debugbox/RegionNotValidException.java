package ml.gamemodepvp.debugbox;

import ml.gamemodepvp.management.LobbyManager;

/**
 * Created by bfox1 on 7/14/2015.
 * In God We Trust.
 */
public class RegionNotValidException extends RuntimeException {

    private String regionName;

    public RegionNotValidException()
    {

    }

    public RegionNotValidException(String msg, String regionName)
    {
        super(msg);
        this.regionName = regionName;

    }

    public RegionNotValidException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public RegionNotValidException(Throwable cause)
    {
        super(cause);
    }

    public RegionNotValidException(String msg, Throwable cause, boolean enabledSuppression, boolean writableStackTrace)
    {
        super(msg, cause, enabledSuppression, writableStackTrace);
    }



    @Override
    public String toString()
    {
        return super.toString();
    }

    @Override
    public String getMessage()
    {
        return super.getMessage() + "No lobby found named: " + regionName;
    }
}
