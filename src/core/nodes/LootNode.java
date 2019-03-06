package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;

import java.util.Arrays;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class LootNode extends TaskNode {
    private String[] lootList = {"Dragon bones", "Green dragonhide", "Grimy ranarr weed", "Ensouled dragon head", "Rune dagger", "Nature rune"};
    //private static Area dragonArea = new Area(3327, 3708, 3354, 3668);
    private GroundItem loot;

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean accept() {
        loot = getGroundItems().closest(groundItem -> groundItem != null && API.getDragonArea().contains(groundItem) && groundItem.exists() && Arrays.asList(lootList).contains(groundItem.getName()));
        return canLoot();
    }

    @Override
    public int execute() {
        if (!getInventory().isFull()) {
            if (loot != null) {
                if (loot.isOnScreen()) {
                    loot.interact("Take");
                    sleepUntil(() -> !loot.exists(), 3000 + API.sleepTime());
                } else if (getWalking().shouldWalk(6)) {
                    getWalking().walk(loot);
                }
            }
        } else if (getInventory().isFull() && getInventory().contains("Lobster")) {
            if (getTabs().isOpen(Tab.INVENTORY)) {
                Item tuna = getInventory().get("Lobster");
                tuna.interact("Eat");
                sleep(2400);
            } else {
                getTabs().open(Tab.INVENTORY);
            }
        }
        return (int) Calculations.nextGaussianRandom(400, 200);
    }

    private boolean canLoot() {
        return loot != null && loot.exists() && !getInventory().isFull() || (getInventory().contains("Lobster") && getInventory().isFull());
    }
}
