import ml.gamemodepvp.bukkit.CoreMain;
import ml.gamemodepvp.Modules.classes.WncListener;
import ml.gamemodepvp.bukkit.events.ItemActionFiredEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by bfox1 on 6/13/2015.
 * In God We Trust.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ItemActionFiredEvent.class)
public class test
{

    @Test
            public void testOnItemAction() {
        InventoryClickEvent event = PowerMockito.mock(InventoryClickEvent.class);
        Player mockPlayer = PowerMockito.mock(Player.class);
        CoreMain main = mock(CoreMain.class);
        when(mockPlayer.getName()).thenReturn("Name");

        when(event.getWhoClicked()).thenReturn(mockPlayer);

        WncListener listener = new WncListener(main);
        listener.onInventoryAction(event);
        verify(mockPlayer).sendMessage(anyString());
    }



}
