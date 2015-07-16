package model.figures;

import java.util.List;
import model.Action;
import model.Board;
import model.Figure;
import model.Position;

public class Knight implements Figure {

    private Position position;
    private final String colour;

    public Knight(Position posittion, String colour) {
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
        return 'H';
    }

    @Override
    public boolean move(Position position, Board board) {
        if (!board.insideBounds(position) || (board.getCeld(position.getX(), position.getY()).getFigure() != null && board.getCeld(position.getX(), position.getY()).getFigure().getColour().equals(colour))) {
            return false; //no come a sus aliados
        }
        return validMove(position);
    }

    @Override
    public double attackPuntuation(Position position, Board board) {
        double result = 0;
        if (board.insideBounds(new Position(position.getX() - 2, position.getY() - 1)) && board.getCeld(position.getX() - 2, position.getY() - 1).getFigure() != null && !board.getCeld(position.getX() - 2, position.getY() - 1).getFigure().getColour().equals(this.colour)) {
            result += board.getCeld(position.getX() - 2, position.getY() - 1).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() - 2, position.getY() + 1)) && board.getCeld(position.getX() - 2, position.getY() + 1).getFigure() != null && !board.getCeld(position.getX() - 2, position.getY() + 1).getFigure().getColour().equals(this.colour)) {
            result += board.getCeld(position.getX() - 2, position.getY() + 1).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() - 1, position.getY() + 2)) && board.getCeld(position.getX() - 1, position.getY() + 2).getFigure() != null && !board.getCeld(position.getX() - 1, position.getY() + 2).getFigure().getColour().equals(this.colour)) {
            result += board.getCeld(position.getX() - 1, position.getY() + 2).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() + 1, position.getY() + 2)) && board.getCeld(position.getX() + 1, position.getY() + 2).getFigure() != null && !board.getCeld(position.getX() + 1, position.getY() + 2).getFigure().getColour().equals(this.colour)) {
            result += board.getCeld(position.getX() + 1, position.getY() + 2).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() + 2, position.getY() + 1)) && board.getCeld(position.getX() + 2, position.getY() + 1).getFigure() != null && !board.getCeld(position.getX() + 2, position.getY() + 1).getFigure().getColour().equals(this.colour)) {
            result += board.getCeld(position.getX() + 2, position.getY() + 1).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() + 2, position.getY() - 1)) && board.getCeld(position.getX() + 2, position.getY() - 1).getFigure() != null && !board.getCeld(position.getX() + 2, position.getY() - 1).getFigure().getColour().equals(this.colour)) {
            result += board.getCeld(position.getX() + 2, position.getY() - 1).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() + 1, position.getY() - 2)) && board.getCeld(position.getX() + 1, position.getY() - 2).getFigure() != null && !board.getCeld(position.getX() + 1, position.getY() - 2).getFigure().getColour().equals(this.colour)) {
            result += board.getCeld(position.getX() + 1, position.getY() - 2).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() - 1, position.getY() - 2)) && board.getCeld(position.getX() - 1, position.getY() - 2).getFigure() != null && !board.getCeld(position.getX() - 1, position.getY() - 2).getFigure().getColour().equals(this.colour)) {
            result += board.getCeld(position.getX() - 1, position.getY() - 2).getFigure().getPuntuation();
        }
        return result;
    }

    private boolean validMove(Position position) {
        return this.position.euclideDistance(position) == 5;
    }

    @Override
    public List<Action> possiblesMoves(List<Action> list, String colour, Board board) {
        if (move(new Position(position.getX() - 2, position.getY() - 1), board)) {
            list.add(new Action(position, new Position(position.getX() - 2, position.getY() - 1)));
        }
        if (move(new Position(position.getX() - 2, position.getY() + 1), board)) {
            list.add(new Action(position, new Position(position.getX() - 2, position.getY() + 1)));
        }
        if (move(new Position(position.getX() - 1, position.getY() + 2), board)) {
            list.add(new Action(position, new Position(position.getX() - 1, position.getY() + 2)));
        }
        if (move(new Position(position.getX() + 1, position.getY() + 2), board)) {
            list.add(new Action(position, new Position(position.getX() + 1, position.getY() + 2)));
        }
        if (move(new Position(position.getX() + 2, position.getY() + 1), board)) {
            list.add(new Action(position, new Position(position.getX() + 2, position.getY() + 1)));
        }
        if (move(new Position(position.getX() + 2, position.getY() - 1), board)) {
            list.add(new Action(position, new Position(position.getX() + 2, position.getY() - 1)));
        }
        if (move(new Position(position.getX() + 1, position.getY() - 2), board)) {
            list.add(new Action(position, new Position(position.getX() + 1, position.getY() - 2)));
        }
        if (move(new Position(position.getX() - 1, position.getY() - 2), board)) {
            list.add(new Action(position, new Position(position.getX() - 1, position.getY() - 2)));
        }
        return list;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public void setFirstMove(boolean b) {
    }

    @Override
    public boolean getFirstMove() {
        return false;
    }

    @Override
    public Figure copy() {
        Figure copy= new Knight(position, colour);
        return copy;
    }

}
