package model.figures;

import java.util.List;
import model.Action;
import model.Board;
import model.Figure;
import model.Position;

public class Bishop implements Figure {

    private Position position;
    private final String colour;

    public Bishop(Position posittion, String colour) {
        this.position = posittion;
        this.colour = colour;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public double getPuntuation() {
        return 3;
    }

    @Override
    public char getFigureName() {
        return 'B';
    }

    @Override
    public boolean move(Position position, Board board) {
        if (board.getCeld(position.getX(), position.getY()).getFigure() != null && board.getCeld(position.getX(), position.getY()).getFigure().getColour().equals(colour)) {
            return false; //no come a sus aliados
        }
        if (board.insideBounds(position) && validMove(position)) {
            if (this.position.getX() < position.getX() && this.position.getY() < position.getY()) { //se mueve hacia abajo a la dereca
                for (int i = 1; i <= position.getX() - this.position.getX(); i++) {
                    if (this.position.getX() + i == position.getX()) {
                        return true;
                    } else if (board.getCeld(this.position.getX() + i, this.position.getY() + i).getFigure() != null) {
                        return false;
                    }
                }
            } else if (this.position.getX() > position.getX() && this.position.getY() > position.getY()) { //arriba a la izquierda
                for (int i = 1; i <= this.position.getX() - position.getX(); i++) {
                    if (this.position.getX() - i == position.getX()) {
                        return true;
                    } else if (board.getCeld(this.position.getX() - i, this.position.getY() - i).getFigure() != null) {
                        return false;
                    }
                }
            } else if (this.position.getX() < position.getX() && this.position.getY() > position.getY()) {//abajo izquierda
                for (int i = 1; i <= position.getX() - this.position.getX(); i++) {
                    if (this.position.getX() + i == position.getX()) {
                        return true;
                    } else if (board.getCeld(this.position.getX() + i, this.position.getY() - i).getFigure() != null) {
                        return false;
                    }
                }
            } else { //Movimiento hacia arriba a la derecha
                for (int i = 1; i <= this.position.getX() - position.getX(); i++) {
                    if (this.position.getX() - i == position.getX()) {
                        return true;
                    } else if (board.getCeld(this.position.getX() - i, this.position.getY() + i).getFigure() != null) {
                        return false;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public double attackPuntuation(Position position, Board board) {
        double result = 0;
        for (int i = 1; i <= (board.getHeight() - (position.getX() + 1) < board.getWidth() - (position.getY() + 1) ? board.getHeight() - (position.getX() + 1) : board.getWidth() - (position.getY() + 1)); i++) {
            if (board.getCeld(position.getX() + i, position.getY() + i).getFigure() != null) {
                if (!colour.equals(board.getCeld(position.getX() + i, position.getY() + i).getFigure().getColour())) {
                    result = result + board.getCeld(position.getX() + i, position.getY() + i).getFigure().getPuntuation();
                }
                break;
            }
        }
        for (int i = 1; i <= (position.getX() < position.getY() ? position.getX() : position.getY()); i++) {
            if (board.getCeld(position.getX() - i, position.getY() - i).getFigure() != null) {
                if (!colour.equals(board.getCeld(position.getX() - i, position.getY() - i).getFigure().getColour())) {
                    result = result + board.getCeld(position.getX() - i, position.getY() - i).getFigure().getPuntuation();
                }
                break;
            }
        }
        for (int i = 1; i <= (board.getHeight() - (position.getX() + 1) < position.getY() ? board.getHeight() - (position.getX() + 1) : position.getY()); i++) {
            if (board.getCeld(position.getX() + i, position.getY() - i).getFigure() != null) {
                if (!colour.equals(board.getCeld(position.getX() + i, position.getY() - i).getFigure().getColour())) {
                    result = result + board.getCeld(position.getX() + i, position.getY() - i).getFigure().getPuntuation();
                }
                break;
            }

        }
        for (int i = 1; i <= (position.getX() < board.getWidth() - (position.getY() + 1) ? position.getX() : board.getWidth() - (position.getY() + 1)); i++) {
            if (board.getCeld(position.getX() - i, position.getY() + i).getFigure() != null) {
                if (!colour.equals(board.getCeld(position.getX() - i, position.getY() + i).getFigure().getColour())) {
                    result = result + board.getCeld(position.getX() - i, position.getY() + i).getFigure().getPuntuation();
                }
                break;
            }
        }
        return result;
    }

    private boolean validMove(Position position) {
        if ((this.position.getX() > position.getX() && this.position.getY() > position.getY()) || (this.position.getX() < position.getX() && this.position.getY() < position.getY())) {
            return this.position.getX() - this.position.getY() == position.getX() - position.getY();
        } else {
            return this.position.getX() + this.position.getY() == position.getX() + position.getY();
        }
    }

    @Override
    public List<Action> possiblesMoves(List<Action> list, String colour, Board board) {
        for (int i = 1; i <= (board.getHeight() - (position.getX() + 1) < board.getWidth() - (position.getY() + 1) ? board.getHeight() - (position.getX() + 1) : board.getWidth() - (position.getY() + 1)); i++) {
            if (move(new Position(position.getX() + i, position.getY() + i), board)) {
                list.add(new Action(position, new Position(position.getX() + i, position.getY() + i)));
            }
        }
        for (int i = 1; i <= (position.getX() < position.getY() ? position.getX() : position.getY()); i++) {
            if (move(new Position(position.getX() - i, position.getY() - i), board)) {
                list.add(new Action(position, new Position(position.getX() - i, position.getY() - i)));
            }
        }
        for (int i = 1; i <= (board.getHeight() - (position.getX() + 1) < position.getY() ? board.getHeight() - (position.getX() + 1) : position.getY()); i++) {
            if (move(new Position(position.getX() + i, position.getY() - i), board)) {
                list.add(new Action(position, new Position(position.getX() + i, position.getY() - i)));
            }
        }
        for (int i = 1; i <= (position.getX() < board.getWidth() - (position.getY() + 1) ? position.getX() : board.getWidth() - (position.getY() + 1)); i++) {
            if (move(new Position(position.getX() - i, position.getY() + i), board)) {
                list.add(new Action(position, new Position(position.getX() - i, position.getY() + i)));
            }
        }
        return list;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }
    
    //Funciones no necearias para esta figura
    @Override
    public void setFirstMove(boolean b) {
    }
    @Override
    public boolean getFirstMove() {
        return false;
    }

    @Override
    public Figure copy() {
        Figure copy = new Bishop(position, colour);
        return copy;
    }

}
