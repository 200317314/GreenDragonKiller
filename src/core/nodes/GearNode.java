package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.TaskNode;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class GearNode extends TaskNode {
    @Override
    public boolean accept() {
        return canEquip() || getInventory().contains(item -> item != null && item.getName().contains("Ring of dueling"));
    }

    @Override
    public int execute() {
        if (getTabs().isOpen(Tab.INVENTORY)) {
            if (getInventory().contains(item -> item != null && item.getName().contains("Ring of dueling"))) {
                getInventory().interact((item -> item != null && item.getName().contains("Ring of dueling")), "Wear");
                sleepUntil(() -> getEquipment().contains(item -> item != null && item.getName().contains("Ring of dueling")), 3400);
            }

            if (getInventory().contains("Adamant full helm")) {
                getInventory().interact("Adamant full helm", "Wear");
                sleepUntil(() -> getEquipment().contains("Adamant full helm"), 3400);
            }

            if (getInventory().contains("Amulet of strength")) {
                getInventory().interact("Amulet of strength", "Wear");
                sleepUntil(() -> getEquipment().contains("Amulet of strength"), 3400);
            }

            if (getInventory().contains("Dragon scimitar")) {
                getInventory().interact("Dragon scimitar", "Wield");
                sleepUntil(() -> getEquipment().contains("Dragon scimitar"), 3400);
            }

            if (getInventory().contains("Dragon longsword")) {
                getInventory().interact("Dragon longsword", "Wield");
                sleepUntil(() -> getEquipment().contains("Dragon longsword"), 3400);
            }

            if (getInventory().contains("Dragon sword")) {
                getInventory().interact("Dragon sword", "Wield");
                sleepUntil(() -> getEquipment().contains("Dragon sword"), 3400);
            }

            if (getInventory().contains("Abyssal whip")) {
                getInventory().interact("Abyssal whip", "Wield");
                sleepUntil(() -> getEquipment().contains("Abyssal whip"), 3400);
            }

            if (getInventory().contains("Granite body")) {
                getInventory().interact("Granite body", "Wear");
                sleepUntil(() -> getEquipment().contains("Granite body"), 3400);
            }

            if (getInventory().contains("Anti-dragon shield")) {
                getInventory().interact("Anti-dragon shield", "Wear");
                sleepUntil(() -> getEquipment().contains("Anti-dragon shield"), 3400);
            }

            if (getInventory().contains("Rune platelegs")) {
                getInventory().interact("Rune platelegs", "Wear");
                sleepUntil(() -> getEquipment().contains("Rune platelegs"), 3400);
            }
        }  else {
            getTabs().open(Tab.INVENTORY);
        }
        return (int) Calculations.nextGaussianRandom(400, 200);
    }

    private boolean canEquip(){
        return (getInventory().contains(item -> item != null && item.getName().contains("Ring of dueling"))
        || getInventory().contains(item -> item != null && item.getName().contains("Adamant full helm"))
                || getInventory().contains(item -> item != null && item.getName().contains("Amulet of strength"))
                || getInventory().contains(item -> item != null && item.getName().contains("Anti-dragon shield"))
                || getInventory().contains(item -> item != null && item.getName().contains("Granite body"))
                || getInventory().contains(item -> item != null && item.getName().contains("Dragon sword"))
                || getInventory().contains(item -> item != null && item.getName().contains("Dragon longsword"))
                || getInventory().contains(item -> item != null && item.getName().contains("Abyssal whip"))
                || getInventory().contains(item -> item != null && item.getName().contains("Rune platelegs"))
                || getInventory().contains(item -> item != null && item.getName().contains("Dragon scimitar"))) && !API.hasGear(this);
    }
}
