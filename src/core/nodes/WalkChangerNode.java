package core.nodes;

import core.API;
import org.dreambot.api.script.TaskNode;

/**
 * Created by 7804364 on 3/9/2018.
 */
public class WalkChangerNode extends TaskNode {
    @Override
    public boolean accept() {
        return API.walkChanger.elapsed() >= 600 * 1000;
    }

    @Override
    public int execute() {
        API.walkChanger.reset();
        API.walkBack = API.getDragonArea().getRandomTile();
        return 0;
    }
}
