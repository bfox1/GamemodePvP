package ml.gamemodepvp.Modules.ranks.handler;

import ml.gamemodepvp.util.MathematicsUtility;

import java.io.Serializable;

/**
 * Created by bfox1 on 5/6/2015.
 */
public class LevelHandler implements Serializable{

    private int playerLevel;
    private ExperienceHandler handler;
    private boolean isMaxLevel = false;
    private int maxLevel = 60;


    public LevelHandler(int playerLevel, ExperienceHandler experienceHandler)
    {
        this.playerLevel = playerLevel;
        this.handler = experienceHandler;
        handler.setExpToNextLevel(MathematicsUtility.calcExpBase(handler.getEpk(), handler.getlevelCap(), playerLevel));
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public ExperienceHandler getExperienceHandler()
    {
        return this.handler;
    }

    public void playerGainExp(int exp)
    {
        if(!isMaxLevel)
        {
            this.handler.gainedEXP(exp);
            if (isGreaterThan()) {
                this.playerLevel++;
                playerGainExp(this.handler.getExp() - this.handler.getExpToNextLevel());
            }
        } else
        {
            this.isMaxLevel = true;
        }
    }

    public boolean isGreaterThan()
    {
        if(handler.getExp() >= handler.getExpToNextLevel())
        {
            return true;
        }
        return false;
    }

    public boolean isMaxLevel()
    {
        if(this.playerLevel >= this.maxLevel)
        {
            return true;
        }
        return false;
    }


    public int getMaxLevel() {
        return maxLevel;
    }
}
