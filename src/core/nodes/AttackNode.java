package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.interactive.NPC;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class AttackNode extends TaskNode {
    //private static Area dragonArea = new Area(3327, 3708, 3354, 3668);
    //private NPC dragon;

    @Override
    public boolean accept() {
        return (canAttack() || canReAttack()) && API.hasFood(this) && API.hasTeleports(this);
    }

    @Override
    public int execute() {
        if (!canReAttack()) {
            NPC dragon = getNpcs().closest(n -> n != null && !n.isInCombat() && getMap().canReach(n) && n.getName().equals("Green dragon"));
            if (dragon != null && dragon.isOnScreen()) {
                dragon.interact("Attack");
                sleepUntil(() -> getLocalPlayer().isInCombat(), 2000 + API.sleepTime());
            }
        } else {
            getLocalPlayer().getCharacterInteractingWithMe().interact("Attack");
            sleepUntil(() -> getLocalPlayer().isInCombat(), 2000 + API.sleepTime());
        }
        return (int) Calculations.nextGaussianRandom(400, 200);
    }

    private boolean canAttack() {
        NPC dragon = getNpcs().closest(n -> n != null && !n.isInCombat() && getMap().canReach(n) && n.getName().equals("Green dragon"));
        return dragon != null && !getLocalPlayer().isInCombat() && getLocalPlayer().getCharacterInteractingWithMe() == null && API.getDragonArea().contains(getLocalPlayer());
    }

    private boolean canReAttack() {
        return !getLocalPlayer().isInCombat() && getLocalPlayer().getCharacterInteractingWithMe() != null && API.getDragonArea().contains(getLocalPlayer());
    }
}
