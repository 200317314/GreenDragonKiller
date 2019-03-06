package core.nodes;

import org.dreambot.api.data.GameState;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.script.TaskNode;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import java.util.List;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class SkullCheckNode extends TaskNode {
    //private static Area dragonArea = new Area(3327, 3708, 3354, 3668);
    private static Area corpArea = new Area(3194, 3687, 3214, 3670);

    @Override
    public int priority() {
        return 99;
    }

    @Override
    public boolean accept() {
        return attackableExists();
    }

    @Override
    public int execute() {
        Tile teleportTile = new Tile(3345, 3658, 0);
        if (wildyLevel() <= 20 && getCombat().isInWild() && getLocalPlayer().isInCombat()) {
            if (getTabs().isOpen(Tab.EQUIPMENT)) {
                getEquipment().get(item -> item != null && item.getName().contains("Ring of dueling")).interact("Clan Wars");
                sleepUntil(() -> !getCombat().isInWild(), 2400);
            } else {
                getTabs().open(Tab.EQUIPMENT);
            }
        } else {
            getWalking().walk(teleportTile);
        }

        if (!getLocalPlayer().isInCombat() && !getLocalPlayer().isHealthBarVisible()) {
            World world = getWorlds().getRandomWorld(f -> f.isMembers() && !f.isDeadmanMode() && !f.isPVP() && f.getMinimumLevel() == 0
                    && f.getID() != 318 && f.getID() != getClient().getCurrentWorld() && !f.isTournamentWorld());
            getWorldHopper().hopWorld(world);
            sleepUntil(() -> getClient().getGameState() != GameState.HOPPING && getClient().getGameState() != GameState.LOADING && getClient().isLoggedIn() || !getLocalPlayer().isInCombat(), 1000);
        }
        return 0;
    }
    public int wildyLevel() {
        List<WidgetChild> list = getWidgets().getWidgets(w -> w != null && w.isVisible() && w.getText() != null && w.getText().contains("Level:"));
        if (list != null && !list.isEmpty()) {
            return Integer.parseInt(list.get(0).getText().replaceAll("Level:","").trim());
        }
        return 0;
    }

    public boolean attackableExists() {
        int myLevel = getLocalPlayer().getLevel();
        int wildylevel = wildyLevel();
        int min = myLevel - wildylevel, max = myLevel + wildylevel;
        // (dragonArea.contains(p) || (corpArea.contains(p) && corpArea.contains(getContext().getLocalPlayer())))
        if (wildylevel > 0 && getPlayers().all(p -> p != null && !p.equals(getLocalPlayer()) && p.isSkulled() && p.distance(getLocalPlayer().getTile()) < 16 && corpArea.contains(getLocalPlayer()) && p.getLevel() >= min && p.getLevel() <= max).size() > 0) {
            return true;
        }
        return false;
    }
}
