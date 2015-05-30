package ml.gamemodepvp.ranks.util;

/**
 * Created by bfox1 on 5/6/2015.
 */
public class MathematicsUtility {


    public static int calcExpBase(int epk, int levelCap, int currentLevel)
    {
        int expReturn = (int)(epk*levelCap*currentLevel*0.7);
       return expReturn;
    }

    public static int calcExpGain(int epk, double otherLevelMulitplier, double kilstreak)
    {
        int expGain = (int)(epk*otherLevelMulitplier*kilstreak);
        return expGain;
    }

}
