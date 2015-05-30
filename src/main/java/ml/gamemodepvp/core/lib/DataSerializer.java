package ml.gamemodepvp.core.lib;

import java.io.Serializable;

/**
 * Created by bfox1 on 5/5/2015.
 */
public class DataSerializer implements Serializable {
    private static final long serialVersionUID = 13375413L;
    private CorePlayerData data = null;

    public DataSerializer(CorePlayerData data)
    {
        this.data = data;
    }

    public CorePlayerData getData() {
        return data;
    }

    public void setData(CorePlayerData data) {
        this.data = data;
    }
}
