
package model;

public class Action {
    private final Position from;
    private final Position to;

    public Action(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Action{" + "from=" + from.getX() + from.getY()+ ", to=" + to.getX() + to.getY()+'}';
    }
    
    
}
