package core.nodes;

import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.GameObject;

/**
 * Created by 7804364 on 2/24/2018.
 */
public class DebugNode extends TaskNode {

    @Override
    public boolean accept() {
        return !getCombat().isInWild() && (stuckInCaveWithLoot() || stuckInCaveWithNoAmulet());
    }

    @Override
    public int execute() {
        if (stuckInCaveWithLoot()) {
            if (getTabs().isOpen(Tab.EQUIPMENT)) {
                getEquipment().get(item -> item != null && item.getName().contains("Ring of dueling")).interact("Clan Wars");
                sleepUntil(() -> !getCombat().isInWild(), 2400);
            } else {
                getTabs().open(Tab.EQUIPMENT);
            }
        }

        if (stuckInCaveWithNoAmulet()) {
            if (getTabs().isOpen(Tab.EQUIPMENT)) {
                getEquipment().get(item -> item != null && item.getName().contains("Ring of dueling")).interact("Clan Wars");
                sleepUntil(() -> !getCombat().isInWild(), 2400);
            } else {
                getTabs().open(Tab.EQUIPMENT);
            }
        }
        return 4000;
    }

    private boolean stuckInCaveWithLoot() {
        GameObject caveExit = getGameObjects().closest(679);
        return caveExit != null && getInventory().contains("Dragon bones");
    }

    private boolean stuckInCaveWithNoAmulet() {
        GameObject caveExit = getGameObjects().closest(679);
        return caveExit != null && !getInventory().contains(item -> item != null && item.getName().contains("Games necklace"));
    }
}
