package ml.gamemodepvp.Modules.classes.classadditives;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by bfox1 on 4/24/2015.
 */
public class Perks {


    private PotionEffectType type;
    private int amplified;
    private int duration;

    private PotionEffect potionEffect;

    public Perks(PotionEffectType type, int amplified, int duration)
    {
        this.type = type;
        this.amplified = amplified;
        this.duration = duration;
        this.setPotionEffect();
    }

    public void setPotionEffect()
    {
        PotionEffect effect = new PotionEffect(type, duration, amplified, true, true);
        this.potionEffect = effect;
    }


    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    public void setPotionEffect(PotionEffect potionEffect) {
        this.potionEffect = potionEffect;
    }
}
