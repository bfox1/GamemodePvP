package ml.gamemodepvp.Modules.ranks.handler;

import java.io.Serializable;

/**
 * Created by bfox1 on 5/6/2015.
 */
public class ExperienceHandler implements Serializable{

    private int exp;

    private final int levelCap = 60;

    private final int epk = 25;

    private int expToNextLevel;

    public ExperienceHandler(int exp)
    {
        this.exp = exp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getlevelCap() {
        return levelCap;
    }

    public int getEpk() {
        return epk;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public void gainedEXP(int exp)
    {
        this.exp = this.getExp() + exp;
    }

    public void resetExp()
    {
        this.exp = 0;
    }
}
