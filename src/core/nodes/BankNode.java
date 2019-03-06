package core.nodes;

import core.API;
import core.Constants;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.items.Item;

import java.util.Arrays;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class BankNode extends TaskNode {
    private String[] lootList = {"Dragon bones", "Green dragonhide", "Grimy ranarr weed", "Ensouled dragon head", "Rune dagger", "Nature rune"};

    @Override
    public boolean accept() {
        return (!API.hasFood(this) || hasGear() || (!API.hasTeleports(this) && !getInventory().contains(item -> item != null && item.getName().contains("Ring of dueling")))) && canBank() || (!getCombat().isInWild() && getInventory().contains(item -> item != null && Arrays.asList(lootList).contains(item.getName())))
                || (!getCombat().isInWild() && getInventory().isFull() && !API.hasFood(this));
    }

    @Override
    public int execute() {
        if (getBank().isOpen()) {
            if (!getInventory().isEmpty()) {
                for (Item item : getInventory().getCollection()) {
                    if (item != null) {
                        if (item.getName().equals("Dragon bones")) {
                            Constants.dBones += item.getAmount();
                        }

                        if (item.getName().equals("Green dragonhide")) {
                            Constants.dHides += item.getAmount();
                        }

                        if (item.getName().equals("Grimy ranarr weed")) {
                            Constants.rWeed += item.getAmount();
                        }

                        if (item.getName().equals("Ensouled dragon head")) {
                            Constants.heads += item.getAmount();
                        }

                        if (item.getName().equals("Rune dagger")) {
                            Constants.daggers += item.getAmount();
                        }

                        if (item.getName().equals("Nature rune")) {
                            Constants.runes += item.getAmount();
                        }
                    }
                }

                getBank().depositAllItems();
                sleepUntil(() -> getInventory().isEmpty(), 2400);
            }

            if (!getInventory().contains(item -> item != null && item.getName().contains("Games necklace"))) {
                if (getBank().contains(item -> item != null && item.getName().contains("Games necklace") && !item.getName().equals("Games necklace(1)"))) {
                    getBank().withdraw((item -> item != null && item.getName().contains("Games necklace") && !item.getName().equals("Games necklace(1)")), 1);
                    sleepUntil(() -> getInventory().contains(item -> item != null && item.getName().contains("Games necklace")), 6000);
                }
            }

            if (!API.hasFood(this)) {
                if (getBank().count("Lobster") >= 13) {
                    getBank().withdraw("Lobster", 13);
                    sleepUntil(() -> getInventory().contains("Lobster"), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().equals("Amulet of strength")) && !getInventory().contains("Amulet of strength")) {
                if (getBank().contains("Amulet of strength")) {
                    getBank().withdraw("Amulet of strength", 1);
                    sleepUntil(() -> getInventory().contains("Amulet of strength"), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().equals("Anti-dragon shield")) && !getInventory().contains("Anti-dragon shield")) {
                if (getBank().contains("Anti-dragon shield")) {
                    getBank().withdraw("Anti-dragon shield", 1);
                    sleepUntil(() -> getInventory().contains("Anti-dragon shield"), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().contains("Ring of dueling")) && !getInventory().contains(item -> item.getName().contains("Ring of dueling"))) {
                if (getBank().contains(item -> item != null && item.getName().contains("Ring of dueling"))) {
                    getBank().withdraw((item -> item != null && item.getName().contains("Ring of dueling")), 1);
                    sleepUntil(() -> getInventory().contains(item -> item != null && item.getName().contains("Ring of dueling")), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().equals("Adamant full helm")) && !getInventory().contains("Adamant full helm")) {
                if (getBank().contains("Adamant full helm")) {
                    getBank().withdraw("Adamant full helm", 1);
                    sleepUntil(() -> getInventory().contains("Adamant full helm"), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().equals("Dragon scimitar")) && !getInventory().contains("Dragon scimitar")) {
                if (getBank().contains("Dragon scimitar")) {
                    getBank().withdraw("Dragon scimitar", 1);
                    sleepUntil(() -> getInventory().contains("Dragon scimitar"), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().equals("Dragon sword")) && !getInventory().contains("Dragon sword")) {
                if (getBank().contains("Dragon sword")) {
                    getBank().withdraw("Dragon sword", 1);
                    sleepUntil(() -> getInventory().contains("Dragon sword"), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().equals("Dragon longsword")) && !getInventory().contains("Dragon longsword")) {
                if (getBank().contains("Dragon longsword")) {
                    getBank().withdraw("Dragon longsword", 1);
                    sleepUntil(() -> getInventory().contains("Dragon longsword"), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().equals("Abyssal whip")) && !getInventory().contains("Abyssal whip")) {
                if (getBank().contains("Abyssal whip")) {
                    getBank().withdraw("Abyssal whip", 1);
                    sleepUntil(() -> getInventory().contains("Abyssal whip"), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().equals("Granite body")) && !getInventory().contains("Granite body")) {
                if (getBank().contains("Granite body")) {
                    getBank().withdraw("Granite body", 1);
                    sleepUntil(() -> getInventory().contains("Granite body"), 6000);
                }
            }

            if (!getEquipment().contains(item -> item != null && item.getName().equals("Rune platelegs")) && !getInventory().contains("Rune platelegs")) {
                if (getBank().contains("Rune platelegs")) {
                    getBank().withdraw("Rune platelegs", 1);
                    sleepUntil(() -> getInventory().contains("Rune platelegs"), 6000);
                }
            }

            if (getBank().isOpen()) {
                sleepUntil(() -> getBank().close(), 2400);
            }
        } else {
            getBank().openClosest();
            sleepUntil(() -> getBank().isOpen(), 1800);
        }
        return (int) Calculations.nextGaussianRandom(600, 100);
    }

    private boolean canBank() {
        return !getCombat().isInWild();
    }

    private boolean hasGear() {
        return !API.hasGear(this) && !getInventory().contains("Adamant full helm") && !getInventory().contains("Anti-dragon shield") && !getInventory().contains("Amulet of strength")
                && !getInventory().contains("Dragon scimitar") && !getInventory().contains("Abyssal whip") && !getInventory().contains("Dragon longsword") && !getInventory().contains("Dragon sword") && !getInventory().contains("Granite body") && !getInventory().contains("Rune platelegs")
                && !getInventory().contains(item -> item != null && item.getName().contains("Ring of dueling"));
    }
}
