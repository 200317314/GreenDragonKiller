package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import java.util.List;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class WildyWalkNode extends TaskNode {
    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean accept() {
        return (!API.hasFood(this) || !API.hasTeleports(this)) && getCombat().isInWild() || (!getInventory().contains("Tuna") && getInventory().isFull() && getCombat().isInWild());
    }

    @Override
    public int execute() {
        Tile teleportTile = new Tile(3345, 3658, 0);
        if (wildyLevel() <= 20) {
            if (getTabs().isOpen(Tab.EQUIPMENT)) {
                getEquipment().get(item -> item != null && item.getName().contains("Ring of dueling")).interact("Clan Wars");
                sleepUntil(() -> !getCombat().isInWild(), 3400);
            } else {
                getTabs().open(Tab.EQUIPMENT);
            }
        } else {
            getWalking().walk(teleportTile);
        }
        return (int) Calculations.nextGaussianRandom(400, 200);
    }

    public int wildyLevel() {
        List<WidgetChild> list = getWidgets().getWidgets(w -> w != null && w.isVisible() && w.getText() != null && w.getText().contains("Level:"));
        if (list != null && !list.isEmpty()) {
            return Integer.parseInt(list.get(0).getText().replaceAll("Level:","").trim());
        }
        return 0;
    }
}
