package ml.gamemodepvp.util;

/**
 * Created by bfox1 on 5/6/2015.
 * In God We Trust.
 */
public class MathematicsUtility {


    public static int calcExpBase(int epk, int levelCap, int currentLevel)
    {
        return (int)(epk*levelCap*currentLevel*0.7);
    }

    public static int calcExpGain(int epk, double otherLevelMulitplier, double kilstreak)
    {
        return (int)(epk*otherLevelMulitplier*kilstreak);
    }

}
