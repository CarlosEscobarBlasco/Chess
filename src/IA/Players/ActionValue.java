
package IA.Players;

import model.Action;

public class ActionValue {
    private Action action;
    private double value;
    
    public ActionValue(Action action, double value){
        this.action = action;
        this.value = value;
    }

    public Action getAction() {
        return action;
    }

    public double getValue() {
        return value;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
}
