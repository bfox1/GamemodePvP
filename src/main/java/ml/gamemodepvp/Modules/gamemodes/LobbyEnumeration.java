package ml.gamemodepvp.Modules.gamemodes;

/**
 * Created by bfox1 on 6/11/2015.
 * In God We Trust.
 */
public enum LobbyEnumeration {

    GAMELOBBY("GameLobby"),
    WAITINGLOBBY("WaitingLobby");

    private String lobbyName;

    LobbyEnumeration(String lobbyName)
    {
        this.lobbyName = lobbyName;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }
}
