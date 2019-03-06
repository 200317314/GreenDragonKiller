package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.widget.Widget;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class WildyTeleportNode extends TaskNode {
    @Override
    public boolean accept() {
        return canWildyTeleport();
    }

    @Override
    public int execute() {
        Widget teleportOptions = getWidgets().getWidget(219);
        GameObject caveExit = getGameObjects().closest(679);
        if (caveExit == null && !getCombat().isInWild()) {
            if (getTabs().isOpen(Tab.INVENTORY)) {
                if (teleportOptions != null && teleportOptions.isVisible()) {
                    teleportOptions.getChild(0).getChild(3).interact();
                    sleep(4600);
                } else {
                    getInventory().get((item -> item != null && item.getName().contains("Games necklace"))).interact("Rub");
                    sleepUntil(() -> teleportOptions != null && teleportOptions.isVisible(), 2200);
                }
            } else {
                getTabs().open(Tab.INVENTORY);
            }
        }

        if (caveExit != null) {
            if (teleportOptions != null && teleportOptions.isVisible()) {
                teleportOptions.getChild(0).getChild(1).interact();
                sleepUntil(() -> getCombat().isInWild(), 2400);
            } else {
                caveExit.interact("Exit");
                sleepUntil(() -> teleportOptions != null, 2400);
            }
        }
        return (int) Calculations.nextGaussianRandom(400, 200);
    }

    private boolean canWildyTeleport() {
        GameObject caveExit = getGameObjects().closest(679);
        return !getCombat().isInWild() && API.hasGear(this) && API.hasTeleports(this) && API.hasFood(this) || caveExit != null;
    }
}
