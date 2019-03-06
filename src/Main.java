import core.API;
import core.nodes.*;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.web.node.CustomWebPath;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.impl.TaskScript;

/**
 * Created by 7804364 on 2/14/2018.
 */
@ScriptManifest(name = "GreenDragons", author = "7804364", version = 1.80D, description = "makes mad money.", category = Category.COMBAT)
public class Main extends TaskScript {

    @Override
    public void onStart() {
        CustomWebPath eastPath = new CustomWebPath(new Tile(3309, 3664), new Tile(3311, 3673), new Tile(3339, 3693), new Tile(3338, 3700)
                , new Tile(3340, 3709), new Tile(3317, 3674), new Tile(3321, 3674), new Tile(3327, 3671), new Tile(3333, 3667), new Tile(3333, 3673),
                new Tile(3337, 3677), new Tile(3337, 3684), new Tile(3342, 3690));
        eastPath.connectToStart(2309);
        getWalking().getWebPathFinder().addCustomWebPath(eastPath);

        CustomWebPath eastNorthPath = new CustomWebPath(new Tile(3306, 3690), new Tile(3306, 3690), new Tile(3309, 3690), new Tile(3315, 3689), new Tile(3320, 3689), new Tile(3329, 3689), new Tile(3338, 3695));
        eastNorthPath.connectToStart(2311);
        getWalking().getWebPathFinder().addCustomWebPath(eastNorthPath);

        CustomWebPath northPath = new CustomWebPath(new Tile(3211, 3681), new Tile(3211, 3681), new Tile(3218, 3683), new Tile(3231, 3686),
                new Tile(3237, 3689), new Tile(3248, 3692), new Tile(3258, 3692), new Tile(3267, 3692));
        northPath.connectToStart(5150);
        northPath.connectToEnd(5152);
        getWalking().getWebPathFinder().addCustomWebPath(northPath);

        API.walkBack = API.getDragonArea().getRandomTile();

        addNodes(new DebugNode());
        addNodes(new EatNode());
        addNodes(new GearNode());
        addNodes(new BankNode());
        addNodes(new WildyTeleportNode());
        addNodes(new WalkNode());
        addNodes(new LootNode());
        addNodes(new AttackNode());
        addNodes(new WildyWalkNode());
        addNodes(new SkullCheckNode());
        addNodes(new WalkChangerNode());
    }
}
