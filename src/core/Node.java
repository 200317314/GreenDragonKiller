package core;

import org.dreambot.api.methods.MethodContext;

/**
 * Created by 7804364 on 4/8/2017.
 */
public abstract class Node {
    private MethodContext ctx;

    public Node(MethodContext context){
        this.ctx = context;
    }
    public int priority () {
        return 0;
    }
    public abstract boolean accept();

    public abstract int execute();

    public MethodContext getContext(){
        return ctx;
    }
}
