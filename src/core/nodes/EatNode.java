package core.nodes;

import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.items.Item;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class EatNode extends TaskNode {
    @Override
    public int priority() {
        return 3;
    }

    @Override
    public boolean accept() {
        return canEat();
    }

    @Override
    public int execute() {
        if (getTabs().isOpen(Tab.INVENTORY)) {
            Item tuna = getInventory().get("Lobster");
            tuna.interact("Eat");
            sleep(2400);
        } else {
            getTabs().open(Tab.INVENTORY);
        }
        return 0;
    }

    private boolean canEat() {
        return getInventory().contains("Lobster") && getCombat().getHealthPercent() < 70;
    }
}
