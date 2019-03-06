package core;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.utilities.Timer;

/**
 * Created by 7804364 on 2/14/2018.
 */
public class API {
    public static Tile walkBack;

    public static Timer walkChanger = new Timer();

    public static boolean hasTeleports(MethodContext ctx) {
        return ctx.getEquipment().contains(item -> item != null && item.getName().contains("Ring of dueling")) && ctx.getInventory().contains(item -> item != null && item.getName().contains("Games necklace"));
    }

    public static boolean hasGear(MethodContext ctx) {
        return ctx.getEquipment().contains(item -> item != null && item.getName().equals("Anti-dragon shield")) && ctx.getEquipment().contains(item -> item != null && item.getName().equals("Adamant full helm"))
                && ctx.getEquipment().contains(item -> item != null && item.getName().equals("Amulet of strength")) && ctx.getEquipment().contains(item -> item != null && item.getName().equals("Rune platelegs"))
                && ctx.getEquipment().contains(item -> item != null && item.getName().equals("Granite body")) && ctx.getEquipment().contains(item -> item != null && item.getName().equals("Dragon scimitar"))
                || ctx.getEquipment().contains(item -> item != null && item.getName().equals("Dragon longsword"))
                || ctx.getEquipment().contains(item -> item != null && item.getName().equals("Dragon sword"))
                || ctx.getEquipment().contains(item -> item != null && item.getName().equals("Abyssal whip"));
    }

    public static boolean hasFood(MethodContext ctx) {
        return ctx.getInventory().contains("Lobster");
    }

    public static int sleepTime() {
        return (int) Calculations.nextGaussianRandom(400, 200);
    }

    public static Area getDragonArea() {
        return new Area(3327, 3708, 3354, 3668);
    }
}
