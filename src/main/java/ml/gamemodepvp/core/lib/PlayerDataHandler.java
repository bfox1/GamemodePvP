package ml.gamemodepvp.core.lib;

import ml.gamemodepvp.ranks.Rank;

import java.io.*;
import java.util.UUID;

/**
 * Created by bfox1 on 5/4/2015.
 */
public class PlayerDataHandler {

    private CorePlayerData playerData;
    private UUID playerID;

    public PlayerDataHandler(CorePlayerData playerData)
    {
        this.playerData = playerData;
        this.playerID = playerData.getId();
    }

    public void saveData() {
        try {
            File file = new File("plugins/GamemodePVP/playerdata/" + this.playerID + ".ser");
            File parentDir = file.getParentFile();
            if(!parentDir.exists())
            {
                parentDir.mkdirs();

            }
            ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(file));
            bw.writeObject(this.playerData);
            bw.close();


        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public CorePlayerData loadData()
    {
        CorePlayerData data = null;
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("plugins/GamemodePVP/playerdata/" + this.playerID + ".ser"));
            data = (CorePlayerData) ois.readObject();
            ois.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return data;
    }

}
