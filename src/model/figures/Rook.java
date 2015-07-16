package model.figures;

import java.util.List;
import model.Action;
import model.Board;
import model.Figure;
import model.Position;

public class Rook implements Figure {

    private Position position;
    private boolean firstMove;
    private final String colour;

    public Rook(Position posittion, String colour) {
        this.position = posittion;
        this.colour = colour;
        firstMove = true;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public double getPuntuation() {
        return 5;
    }

    @Override
    public char getFigureName() {
        return 'R';
    }

    @Override
    public boolean move(Position position, Board board) {
        if (board.getCeld(position.getX(), position.getY()).getFigure() != null && board.getCeld(position.getX(), position.getY()).getFigure().getColour().equals(colour)) {
            return false; //no come a sus aliados
        }
        if (board.insideBounds(position) && validMove(position)) {
            if (position.getX() == this.position.getX()) { //se mueve en el eje horizontal
                if (position.getY() - this.position.getY() < 0) {   //se muee hacia la izquierda (decrementa la y)
                    for (int i = this.position.getY() - 1; i >= position.getY(); i--) {
                        if (position.getY() == i) {
                            return true;
                        }
                        if (board.getCeld(position.getX(), i).getFigure() != null) {
                            return false;
                        }
                    }
                } else {    //se mueve a la derecha (aumenta la y)
                    for (int i = this.position.getY() + 1; i <= position.getY(); i++) {
                        if (position.getY() == i) {
                            return true;
                        }
                        if (board.getCeld(position.getX(), i).getFigure() != null) {
                            return false;
                        }
                    }
                }
            } else {  //se mueve en el eje vertical
                if (position.getX() - this.position.getX() < 0) {   //se muee hacia abajo (decrementa la x)
                    for (int i = this.position.getX() - 1; i >= position.getX(); i--) {
                        if (position.getX() == i) {
                            return true;
                        }
                        if (board.getCeld(i, position.getY()).getFigure() != null) {
                            return false;
                        }
                    }
                } else {    //se mueve a la arriba (aumenta la x)
                    for (int i = this.position.getX() + 1; i <= position.getX(); i++) {
                        if (position.getX() == i) {
                            return true;
                        }
                        if (board.getCeld(i, position.getY()).getFigure() != null) {
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public double attackPuntuation(Position position, Board board) {
        double result = 0;
        for (int i = position.getX() + 1; i < board.getHeight(); i++) {
            if (board.getCeld(i, position.getY()).getFigure() != null) {
                if (!colour.equals(board.getCeld(i, position.getY()).getFigure().getColour())) {
                    result += board.getCeld(i, position.getY()).getFigure().getPuntuation();
                }
                break;
            }
        }
        for (int i = position.getX() - 1; i >= 0; i--) {
            if (board.getCeld(i, position.getY()).getFigure() != null) {
                if (!colour.equals(board.getCeld(i, position.getY()).getFigure().getColour())) {
                    result += board.getCeld(i, position.getY()).getFigure().getPuntuation();
                }
                break;
            }
        }
        for (int i = position.getY() + 1; i < board.getWidth(); i++) {
            if (board.getCeld(position.getX(), i).getFigure() != null) {
                if (!colour.equals(board.getCeld(position.getX(), i).getFigure().getColour())) {
                    result += board.getCeld(position.getX(), i).getFigure().getPuntuation();
                }
                break;
            }
        }
        for (int i = position.getY() - 1; i >= 0; i--) {
            if (board.getCeld(position.getX(), i).getFigure() != null) {
                if (!colour.equals(board.getCeld(position.getX(), i).getFigure().getColour())) {
                    result += board.getCeld(position.getX(), i).getFigure().getPuntuation();
                }
                break;
            }
        }
        return result;
    }

    private boolean validMove(Position position) {
        if (position.getX() == this.position.getX()) {
            return true;
        } else if (position.getY() == this.position.getY()) {
            return true;
        }
        return false;
    }

    @Override
    public List<Action> possiblesMoves(List<Action> list, String colour, Board board) {
        for (int i = this.position.getX() + 1; i < board.getHeight(); i++) {
            if (move(new Position(i, position.getY()), board)) {
                list.add(new Action(this.position, new Position(i, position.getY())));
            }
        }
        for (int i = this.position.getX() - 1; i >= 0; i--) {
            if (move(new Position(i, position.getY()), board)) {
                list.add(new Action(this.position, new Position(i, position.getY())));
            }
        }
        for (int i = this.position.getY() + 1; i < board.getWidth(); i++) {
            if (move(new Position(position.getX(), i), board)) {
                list.add(new Action(this.position, new Position(position.getX(), i)));
            }
        }
        for (int i = this.position.getY() - 1; i >= 0; i--) {
            if (move(new Position(position.getX(), i), board)) {
                list.add(new Action(this.position, new Position(position.getX(), i)));
            }
        }
        return list;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
    
    @Override
    public boolean getFirstMove() {
        return firstMove;
    }

    @Override
    public Figure copy() {
        Figure copy = new Rook(position, colour);
        copy.setFirstMove(firstMove);
        return copy;
    }

}
