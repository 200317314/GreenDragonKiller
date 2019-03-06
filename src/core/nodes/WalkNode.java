package core.nodes;

import core.API;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.TaskNode;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class WalkNode extends TaskNode {
    //private static Area dragonArea = new Area(3321, 3708, 3351, 3671);

    @Override
    public boolean accept() {
        return canWalk();
    }

    @Override
    public int execute() {
        getWalking().getWebPathFinder();
        getWalking().walk(API.walkBack);
        return (int) Calculations.nextGaussianRandom(200, 100);
    }

    private boolean canWalk() {
        return getCombat().isInWild() && getWalking().shouldWalk(6) && !API.getDragonArea().contains(getLocalPlayer()) && API.hasFood(this) && API.hasTeleports(this) && API.hasGear(this);
    }
}
