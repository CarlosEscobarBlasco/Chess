package model;

public class Celd{
    private Figure figure;

    public Celd(Figure figure) {
        this.figure = figure;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
    
    @Override
    public String toString() {
        return figure.getFigureName()+"";
    }
}
