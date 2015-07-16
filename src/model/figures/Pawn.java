package model.figures;

import java.util.List;
import model.Action;
import model.Board;
import model.Figure;
import model.Position;

public class Pawn implements Figure {

    private Position position;
    private final String colour;
    private boolean firstMove;

    @Override
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public Pawn(Position posittion, String colour) {
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
        return 1;
    }

    @Override
    public char getFigureName() {
        return 'P';
    }

    @Override
    public boolean move(Position position, Board board) {
        if (!board.insideBounds(position) || (board.getCeld(position.getX(), position.getY()).getFigure() != null && board.getCeld(position.getX(), position.getY()).getFigure().getColour().equals(colour))) {
            return false; //no come a sus aliados
        }
        if(this.position.euclideDistance(position)>2){
            if ("Black".equals(colour)) {
                if(board.getCeld(position.getX()-1, position.getY()).getFigure() != null)return false;
            }else{
                if(board.getCeld(position.getX()+1, position.getY()).getFigure() != null)return false;
            }
        }
        if (validMove(position)) {
            if (this.position.getY() == position.getY() && board.getCeld(position.getX(), position.getY()).getFigure() == null) {
                return true;
            } else if (this.position.getY() != position.getY() && board.getCeld(position.getX(), position.getY()).getFigure() != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double attackPuntuation(Position position, Board board) {
        double result = 0;
        if ("Black".equals(colour)) {
            if (board.insideBounds(new Position(position.getX() + 1, position.getY() + 1))&&board.getCeld(position.getX() + 1, position.getY() + 1).getFigure()!=null&&!board.getCeld(position.getX() + 1, position.getY() + 1).getFigure().getColour().equals(colour)) {
                result += board.getCeld(position.getX() + 1, position.getY() + 1).getFigure().getPuntuation();
            }
            if (board.insideBounds(new Position(position.getX() + 1, position.getY() - 1))&&board.getCeld(position.getX() + 1, position.getY() - 1).getFigure()!=null&&!board.getCeld(position.getX() + 1, position.getY() - 1).getFigure().getColour().equals(colour)) {
                result += board.getCeld(position.getX() + 1, position.getY() - 1).getFigure().getPuntuation();
            }
        } else {
            if (board.insideBounds(new Position(position.getX() - 1, position.getY() + 1))&&board.getCeld(position.getX() - 1, position.getY() + 1).getFigure()!=null&&!board.getCeld(position.getX() - 1, position.getY() + 1).getFigure().getColour().equals(colour)) {
                result += board.getCeld(position.getX() - 1, position.getY() + 1).getFigure().getPuntuation();
            }
            if (board.insideBounds(new Position(position.getX() - 1, position.getY() - 1))&&board.getCeld(position.getX() - 1, position.getY() - 1).getFigure()!=null&&!board.getCeld(position.getX() - 1, position.getY() - 1).getFigure().getColour().equals(colour)) {
                result += board.getCeld(position.getX() - 1, position.getY() - 1).getFigure().getPuntuation();
            }
        }
        return result;
    }

    private boolean validMove(Position position) {
        if ("White".equals(colour)) {
            if (this.position.getX() > position.getX()) {
                return !firstMove ? position.euclideDistance(this.position) <= 2 : position.euclideDistance(this.position) <= 4;
            }
        } else {
            if (this.position.getX() < position.getX()) {
                return !firstMove ? position.euclideDistance(this.position) <= 2 : position.euclideDistance(this.position) <= 4;
            }
        }
        return false;
    }

    @Override
    public List<Action> possiblesMoves(List<Action> list, String colour, Board board) {
        if ("White".equals(colour)) {
            if (move(new Position(position.getX() - 1, position.getY()), board)) {
                list.add(new Action(position, new Position(position.getX() - 1, position.getY())));
            }
            if (move(new Position(position.getX() - 2, position.getY()), board)) {
                list.add(new Action(position, new Position(position.getX() - 2, position.getY())));
            }
            if (move(new Position(position.getX() - 1, position.getY() + 1), board)) {
                list.add(new Action(position, new Position(position.getX() - 1, position.getY() + 1)));
            }
            if (move(new Position(position.getX() - 1, position.getY() - 1), board)) {
                list.add(new Action(position, new Position(position.getX() - 1, position.getY() - 1)));
            }
        } else {
            if (move(new Position(position.getX() + 1, position.getY()), board)) {
                list.add(new Action(position, new Position(position.getX() + 1, position.getY())));
            }
            if (move(new Position(position.getX() + 2, position.getY()), board)) {
                list.add(new Action(position, new Position(position.getX() + 2, position.getY())));
            }
            if (move(new Position(position.getX() + 1, position.getY() + 1), board)) {
                list.add(new Action(position, new Position(position.getX() + 1, position.getY() + 1)));
            }
            if (move(new Position(position.getX() + 1, position.getY() - 1), board)) {
                list.add(new Action(position, new Position(position.getX() + 1, position.getY() - 1)));
            }
        }
        return list;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public boolean getFirstMove() {
        return firstMove;
    }

    @Override
    public Figure copy() {
        Figure copy = new Pawn(position, colour);
        copy.setFirstMove(firstMove);
        return copy;
    }

}
