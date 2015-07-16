package model;

public class Position implements Cloneable {

    private int x;
    private int y;
    
    public Position(String position){
        this(Integer.parseInt(position.split(",")[0]),Integer.parseInt(position.split(",")[1]));
    }
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int euclideDistance(Position position) {
        int x = this.x - position.getX();
        int y = this.y - position.getY();
        return x*x+y*y;
    }

    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }
    

}
