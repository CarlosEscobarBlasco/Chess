package model;

import java.util.List;

public interface Figure {
    public boolean move (Position position,Board board);
    public double getPuntuation();     //Saber cuanto vale la figura
    public String getColour();      //Saber el color de la ficha (B o W)
    public char getFigureName();    //Saber la figura
    public double attackPuntuation(Position position,Board board);  //Saber si alguna figura esta dando jaque
    public List<Action> possiblesMoves(List<Action> list, String colour,Board board);
    public void setPosition(Position position);
    public Figure copy();
    public void setFirstMove(boolean firstMove); //Solo aplicable al peon, rey y torre
    public boolean getFirstMove(); //Solo aplicable al peon, rey y torre

}
