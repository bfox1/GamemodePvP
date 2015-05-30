package ml.gamemodepvp.world.handler;

import ml.gamemodepvp.world.generator.ArenaChunkGenerator;
import ml.gamemodepvp.world.util.WorldController;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;

/**
 * Created by bfox1 on 5/10/2015.
 */
public class WorldDataHandler {


    private WorldController wc;
    public WorldDataHandler(WorldController wc)
    {
        this.wc = wc;
    }


    public void saveWorldData()
    {

        try {
            System.out.println("TRY??");
            File file = new File("plugins/GamemodePVP/worlddata/" + this.wc.getWorldName() + ".ser");
            File parentDir = file.getParentFile();
            if(!parentDir.exists())
            {
                parentDir.mkdirs();

            }
            ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(file));
            bw.writeObject(this.wc);
            System.out.println("SAVED");
            bw.close();


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadWorldData()
    {
        final File file = new File("plugins/GamemodePVP/worlddata");
        if(!file.exists())
        {
            file.mkdirs();

        }
        //listFilesForFolder(file);
    }

    public World listFilesForFolder(final File folder, String name)
    {
        for(final File fileEntry : folder.listFiles())
        {
            if(fileEntry.isDirectory())
            {
                listFilesForFolder(fileEntry, name);
            }
            else if(fileEntry.getName().equalsIgnoreCase(name))
            {
                System.out.println(fileEntry.getName());
                try
                {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("plugins/GamemodePVP/worlddata/" + fileEntry.getName() + ".ser"));
                    this.wc = (WorldController)ois.readObject();
                    this.wc.setWorldName(fileEntry.getName());
                    this.wc.setWc(new WorldCreator(fileEntry.getName()), fileEntry.getName());
                    this.wc.getWc().environment(World.Environment.NORMAL);
                    this.wc.getWc().generator(new ArenaChunkGenerator());
                    this.wc.setWorld(Bukkit.createWorld(this.wc.getWc()));
                    ois.close();
                    return this.wc.getWorld();
                    //Bukkit.unloadWorld(this.worldConfiguration.getWorld(), true);

                } catch (IOException e)
                {
                    e.printStackTrace();
                } catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void createWorldData(String name)
    {
        this.wc.setWc(new WorldCreator(name), name);
        this.wc.getWc().environment(World.Environment.NORMAL);
        this.wc.getWc().generator(new ArenaChunkGenerator());
        Bukkit.createWorld(this.wc.getWc());
    }

    public World generateData(String name)
    {
        final File file = new File("plugins/GamemodePVP/worlddata");
        return listFilesForFolder(file, name);
    }

    public WorldController returnWorldControl()
    {
        return this.wc;
    }
}
