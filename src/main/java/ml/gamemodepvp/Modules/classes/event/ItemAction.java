package ml.gamemodepvp.Modules.classes.event;


import org.bukkit.Bukkit;


/**
 * Created by bfox1 on 6/2/2015.
 * In God We Trust.
 */
public enum ItemAction
{
    COMMAND
            {
                /**
                 * Dispatches the Command when Item is LeftClicked
                 * @param object ex(gamemode bfox1 1)
                 */
                @Override
                public void fireAction(Object object)
                {
                    if(object instanceof String)
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), (String)object);
                    else
                    {
                        new Exception("Object wasnt instance of String!");
                    }
                }
            },
    KIT
            {
                @Override
                public void fireAction(Object object)
                {


                }

            },
    INVENTORY
            {
                 @Override
                 public void fireAction(Object obj)
                 {

                 }
            },
    ITEM
            {
                @Override
                public void fireAction(Object obj)
                {

                }
            },
    ACTIVE
            {
                @Override
                public void fireAction(Object obj)
                {

                }
            };


    public abstract void fireAction(Object obj);
}
