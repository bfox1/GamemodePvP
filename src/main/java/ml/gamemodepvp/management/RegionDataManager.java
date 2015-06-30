package ml.gamemodepvp.management;


import ml.gamemodepvp.CoreMain;

import ml.gamemodepvp.util.RegionTestUtility;
import ml.gamemodepvp.Modules.world.region.PlayerBuildingMode;
import ml.gamemodepvp.Modules.world.region.Region;
import org.bukkit.World;
import org.bukkit.entity.Player;

import org.bukkit.Location;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bfox1 on 5/17/2015.
 */
public final class RegionDataManager {

    private ArrayList<PlayerBuildingMode> ps = new ArrayList<PlayerBuildingMode>();
    private HashMap<String, HashMap<String, Region>> worldRegionList;

    public RegionDataManager()
    {
        this.worldRegionList = new HashMap<String, HashMap<String, Region>>();
    }

    public void addNewWorldRegion(HashMap<String, Region> hashMap, World world)
    {
        this.worldRegionList.put(world.getName(), hashMap);

    }
    public void removeWorldRegion(String regionName, String worldName)
    {
        HashMap<String, Region> hashMap = loadRegionList(worldName);
        if(this.worldRegionList.containsKey(worldName) && hashMap.containsKey(regionName))
        {
            hashMap.remove(regionName);
        }
    }

    public void purgeWorldRegionData(World world)
    {

    }

    public Region getCurrentRegion(Player player) throws Exception {

        if(RegionTestUtility.getInRegion(this, player) != null)
        {
            return RegionTestUtility.getInRegion(this, player);
        }
        else
        {
            throw new Exception(" ");
        }

    }



    public void renameRegion(String worldName, String oldname, String newName)
    {
        HashMap<String, Region> hashMap = loadRegionList(worldName);
        if(hashMap.containsKey(oldname))
        {
            hashMap.put(newName, hashMap.get(oldname));
        }
    }





    public boolean checkIfRegion(Region region,Location location)
    {
        //System.out.println(location.getX() +" " + location.getY() +" " + location.getZ());
        return region.getHandler().checkBoundary((int)location.getX(), (int)location.getY(), (int)location.getZ());
    }


    /**
     * This Data gets saved upon onDisable().
     * @param main
     */
    public void saveWorldData(CoreMain main) {


        try {
            File file = new File("plugins/GamemodePVP/regionData/" + "WorldRegionData" + ".ser");
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();

            }
            ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(file));
            bw.writeObject(this.worldRegionList);

            main.getLogger().info("Region Data has been successfully saved.");

            bw.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This Data gets loaded upon onEnabled().
     * NEVER forcefully load data!! Could kill all regions!
     * @param main
     */
    public void loadWorldData(CoreMain main) {
                     //File file = new File("plugins/GamemodePVP/regionData");
                    try {
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("plugins/GamemodePVP/regionData/" + "WorldRegionData" + ".ser"));
                        this.worldRegionList = (HashMap<String, HashMap<String, Region>>) ois.readObject();
                        main.getLogger().info("Region Data has been successfully loaded.");
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
    }



    public HashMap<String, Region> loadRegionList(String worldName)
    {
        HashMap<String, Region> hasLoaded = null;
        if(this.worldRegionList.get(worldName) != null) {
            hasLoaded = this.worldRegionList.get(worldName);

        }
        return hasLoaded;
    }

    public Region getRegion(String regionName)
    {
        for(Map.Entry worldEntry : worldRegionList.entrySet())
        {
            if(worldEntry != null)
            {
                HashMap<String, Region> regionMap = (HashMap<String, Region>) worldEntry.getValue();
                for(Map.Entry regionEntry : regionMap.entrySet())
                {
                    if(regionEntry != null)
                    {
                        String entryName = (String) regionEntry.getKey();
                        if(entryName.equalsIgnoreCase(regionName))
                        {
                            return (Region)regionEntry.getValue();
                        }
                    }
                }
            }
        }

        return null;
    }




    /**
     * Returns the region the player is located in.
     * @param player
     * @return
     */
    public HashMap<String, Region> loadRegionList(Player player)
    {
        return loadRegionList(player.getWorld().getName());
    }

    /**
     * For Direct boolean to set if Player can Create a region Flag
     * @param isRegionBuildingMode
     * @param player
     */
    public void setRegionBuildingMode(boolean isRegionBuildingMode, Player player) {


        if(!isRegionBuildingMode) ps.remove(player);
        PlayerBuildingMode buildingMode = new PlayerBuildingMode(player);
        buildingMode.setBuildMode(isRegionBuildingMode);
        ps.add(buildingMode);
    }

    /**
     * To get the Region Boolean from player
     * @param player
     * @return
     */
    public boolean getRegionBuildingMode(Player player)
    {
        for(int i = 0; i< ps.toArray().length; i++)
        {
            if(ps.get(i).getPlayer() == player)
            {
                return ps.get(i).isBuildMode();
            }
        }
        return false;
    }

    /**
     * Sets Position 1 for Region
     * @param player
     * @param position
     */
    public void setRegionPosition1(Player player, Location position)
    {
        for(int i = 0; i< ps.toArray().length; i++)
        {
            if(ps.get(i).getPlayer() == player) ps.get(i).setPos1(position);
        }
    }

    /**
     * Sets Postion 2 for Region
     * @param player
     * @param position
     */
    public void setRegionPosition2(Player player, Location position)
    {
        for(int i = 0; i< ps.toArray().length; i++)
        {
            if(ps.get(i).getPlayer() == player) ps.get(i).setPos2(position);
        }
    }

    /**
     * Returns the Location data from Pos1 and Pos2
     * @param player
     * @return
     */
    public Location[] getRegionCuboid(Player player)
    {
        for(int i = 0; i< ps.toArray().length; i++)
        {
            if(ps.get(i).getPlayer() == player) return new Location[]  {ps.get(i).getPos1(), ps.get(i).getPos2()};
        }
        return null;
    }


}
