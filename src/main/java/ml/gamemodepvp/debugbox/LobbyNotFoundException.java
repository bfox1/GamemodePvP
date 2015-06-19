package ml.gamemodepvp.debugbox;

/**
 * Created by bfox1 on 6/18/2015.
 * In God We Trust.
 */
public class LobbyNotFoundException extends RuntimeException {


    private String lobbyName;

    public LobbyNotFoundException()
    {

    }

    public LobbyNotFoundException(String msg, String lobbyName)
    {
        super(msg);
        this.lobbyName = lobbyName;
    }

    public LobbyNotFoundException(String msg, Throwable cause)
    {
        super(msg, cause);
    }

    public LobbyNotFoundException(Throwable cause)
    {
        super(cause);
    }

    public LobbyNotFoundException(String msg, Throwable cause, boolean enabledSuppression, boolean writableStackTrace)
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
        return super.getMessage() + "No lobby found named: " + lobbyName;
    }

}
