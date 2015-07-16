package model.figures;

import java.util.List;
import model.Action;
import model.Board;
import model.Figure;
import model.Position;

public class King implements Figure {

    public static double KING_VALUE = 10000;
    private Position position;
    private final String colour;
    private boolean firstMove;

    public King(Position posittion, String colour) {
        this.position = posittion;
        this.colour = colour;
        firstMove = true;
    }

    @Override
    public String getColour() {
        return colour;
    }

    public static void setKING_VALUE(int number) {
        King.KING_VALUE = KING_VALUE - number;
    }

    @Override
    public double getPuntuation() {
        return King.KING_VALUE;
    }

    @Override
    public char getFigureName() {
        return 'K';
    }

    @Override
    public boolean move(Position position, Board board) {
        if (!board.insideBounds(position) || (board.getCeld(position.getX(), position.getY()).getFigure() != null && board.getCeld(position.getX(), position.getY()).getFigure().getColour().equals(colour))) {
            return false; //no come a sus aliados
        }
//        if(inCheck(position, board))return false;
        if (castling(this.position, position, board) > 0) {
            return true;
        }
        return validMove(position);
    }

    @Override
    public double attackPuntuation(Position position, Board board) {
        double result = 0;
        if (board.insideBounds(new Position(position.getX() + 1, position.getY())) && board.getCeld(position.getX() + 1, position.getY()).getFigure() != null && !board.getCeld(position.getX() + 1, position.getY()).getFigure().getColour().equals(colour)) {
            result += board.getCeld(position.getX() + 1, position.getY()).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() + 1, position.getY() + 1)) && board.getCeld(position.getX() + 1, position.getY() + 1).getFigure() != null && !board.getCeld(position.getX() + 1, position.getY() + 1).getFigure().getColour().equals(colour)) {
            result += board.getCeld(position.getX() + 1, position.getY() + 1).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() + 1, position.getY() - 1)) && board.getCeld(position.getX() + 1, position.getY() - 1).getFigure() != null && !board.getCeld(position.getX() + 1, position.getY() - 1).getFigure().getColour().equals(colour)) {
            result += board.getCeld(position.getX() + 1, position.getY() - 1).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX(), position.getY() + 1)) && board.getCeld(position.getX(), position.getY() + 1).getFigure() != null && !board.getCeld(position.getX(), position.getY() + 1).getFigure().getColour().equals(colour)) {
            result += board.getCeld(position.getX(), position.getY() + 1).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX(), position.getY() - 1)) && board.getCeld(position.getX(), position.getY() - 1).getFigure() != null && !board.getCeld(position.getX(), position.getY() - 1).getFigure().getColour().equals(colour)) {
            result += board.getCeld(position.getX(), position.getY() - 1).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() - 1, position.getY())) && board.getCeld(position.getX() - 1, position.getY()).getFigure() != null && !board.getCeld(position.getX() - 1, position.getY()).getFigure().getColour().equals(colour)) {
            result += board.getCeld(position.getX() - 1, position.getY()).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() - 1, position.getY() + 1)) && board.getCeld(position.getX() - 1, position.getY() + 1).getFigure() != null && !board.getCeld(position.getX() - 1, position.getY() + 1).getFigure().getColour().equals(colour)) {
            result += board.getCeld(position.getX() - 1, position.getY() + 1).getFigure().getPuntuation();
        }
        if (board.insideBounds(new Position(position.getX() - 1, position.getY() - 1)) && board.getCeld(position.getX() - 1, position.getY() - 1).getFigure() != null && !board.getCeld(position.getX() - 1, position.getY() - 1).getFigure().getColour().equals(colour)) {
            result += board.getCeld(position.getX() - 1, position.getY() - 1).getFigure().getPuntuation();
        }
        return result;
    }

    private boolean validMove(Position position) {
        return position.euclideDistance(this.position) <= 2;
    }

    @Override
    public List<Action> possiblesMoves(List<Action> list, String colour, Board board) {
        if (firstMove) {
            if (move(new Position(position.getX(), position.getY() - 2), board)) {
                list.add(new Action(position, new Position(position.getX(), position.getY() - 2)));

            }
            if (move(new Position(position.getX(), position.getY() + 2), board)) {
                list.add(new Action(position, new Position(position.getX(), position.getY() + 2)));
            }
        }
        if (move(new Position(position.getX() - 1, position.getY()), board)) {
            list.add(new Action(position, new Position(position.getX() - 1, position.getY())));
        }
        if (move(new Position(position.getX() - 1, position.getY() + 1), board)) {
            list.add(new Action(position, new Position(position.getX() - 1, position.getY() + 1)));
        }
        if (move(new Position(position.getX() - 1, position.getY() - 1), board)) {
            list.add(new Action(position, new Position(position.getX() - 1, position.getY() - 1)));
        }
        if (move(new Position(position.getX() + 1, position.getY()), board)) {
            list.add(new Action(position, new Position(position.getX() + 1, position.getY())));
        }
        if (move(new Position(position.getX() + 1, position.getY() + 1), board)) {
            list.add(new Action(position, new Position(position.getX() + 1, position.getY() + 1)));
        }
        if (move(new Position(position.getX() + 1, position.getY() - 1), board)) {
            list.add(new Action(position, new Position(position.getX() + 1, position.getY() - 1)));
        }
        if (move(new Position(position.getX(), position.getY() - 1), board)) {
            list.add(new Action(position, new Position(position.getX(), position.getY() - 1)));
        }
        if (move(new Position(position.getX(), position.getY() + 1), board)) {
            list.add(new Action(position, new Position(position.getX(), position.getY() + 1)));
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

    public static int castling(Position from, Position to, Board board) {
        if (from.getX() == to.getX() && from.getY() == to.getY() + 2) {//largo
            if (board.getCeld(from.getX(), from.getY()).getFigure().getFirstMove() && board.getCeld(from.getX(), 0).getFigure() != null && board.getCeld(from.getX(), 0).getFigure().getFirstMove()) {
                for (int i = from.getY() - 1; i > 0; i--) {
                    if (board.getCeld(from.getX(), i).getFigure() != null) {
                        return 0;
                    }
                }
                if (canCastling(from, to, 1, board)) {
                    return 1;
                }
            }
        } else if (from.getX() == to.getX() && from.getY() == to.getY() - 2) {//corto
            if (board.getCeld(from.getX(), from.getY()).getFigure().getFirstMove() && board.getCeld(from.getX(), board.getWidth() - 1).getFigure() != null && board.getCeld(from.getX(), board.getWidth() - 1).getFigure().getFirstMove()) {
                for (int i = from.getY() + 1; i < to.getY(); i++) {
                    if (board.getCeld(from.getX(), i).getFigure() != null) {
                        return 0;
                    }
                }

                if (canCastling(from, to, 2, board)) {
                    return 2;
                }
            }
        }
        return 0;
    }

    static private boolean canCastling(Position from, Position to, int castlingType, Board board) {

        if (castlingType == 1) {//Enroque largo
            for (int i = from.getY(); i >= to.getY(); i--) {
                for (int j = 0; j < board.getHeight(); j++) {
                    for (int k = 0; k < board.getWidth(); k++) {
                        if (board.getCeld(j, k).getFigure() != null && !board.getCeld(j, k).getFigure().getColour().equals(board.getCeld(from.getX(), from.getY() - 4 + i).getFigure().getColour()) && board.getCeld(j, k).getFigure().attackPuntuation(new Position(j, k), board) >= King.KING_VALUE) {
                            board.getCeld(from.getX(), from.getY()).setFigure(board.getCeld(from.getX(), from.getY() - 4 + i).getFigure());
                            if (from.getY() - 4 + i != from.getY()) {
                                board.getCeld(from.getX(), from.getY() - 4 + i).setFigure(null);
                            }
                            return false;
                        }
                    }
                }

                board.getCeld(from.getX(), from.getY() - 5 + i).setFigure(board.getCeld(from.getX(), from.getY() - 4 + i).getFigure());
                board.getCeld(from.getX(), from.getY() - 4 + i).setFigure(null);
            }
            board.getCeld(from.getX(), from.getY()).setFigure(board.getCeld(from.getX(), from.getY() - 3).getFigure());
            board.getCeld(from.getX(), from.getY() - 3).setFigure(null);
            return true;
        } else {//Enroque corto
            for (int i = from.getY(); i < to.getY(); i++) {
                for (int j = 0; j < board.getHeight(); j++) {
                    for (int k = 0; k < board.getWidth(); k++) {
                        if (board.getCeld(j, k).getFigure() != null && !board.getCeld(j, k).getFigure().getColour().equals(board.getCeld(from.getX(), from.getY() + 3 - (7 - i)).getFigure().getColour()) && board.getCeld(j, k).getFigure().attackPuntuation(new Position(j, k), board) >= King.KING_VALUE) {
                            board.getCeld(from.getX(), from.getY()).setFigure(board.getCeld(from.getX(), from.getY() + 3 - (7 - i)).getFigure());
                            if (from.getY() + 3 - (7 - i) != from.getY()) {
                                board.getCeld(from.getX(), from.getY() + 3 - (7 - i)).setFigure(null);
                            }
                            return false;
                        }
                    }
                }
                board.getCeld(from.getX(), from.getY() + 4 - (7 - i)).setFigure(board.getCeld(from.getX(), from.getY() + 3 - (7 - i)).getFigure());
                board.getCeld(from.getX(), from.getY() + 3 - (7 - i)).setFigure(null);
            }
            for (int j = 0; j < board.getHeight(); j++) {
                for (int k = 0; k < board.getWidth(); k++) {
                    if (board.getCeld(j, k).getFigure() != null && !board.getCeld(j, k).getFigure().getColour().equals(board.getCeld(from.getX(), from.getY() + 2).getFigure().getColour()) && board.getCeld(j, k).getFigure().attackPuntuation(new Position(j, k), board) >= King.KING_VALUE) {
                        board.getCeld(from.getX(), from.getY()).setFigure(board.getCeld(from.getX(), from.getY() + 2).getFigure());
                        board.getCeld(from.getX(), from.getY() + 2).setFigure(null);
                        return false;
                    }
                }
            }
            board.getCeld(from.getX(), from.getY()).setFigure(board.getCeld(from.getX(), from.getY() + 2).getFigure());
            board.getCeld(from.getX(), from.getY() + 2).setFigure(null);
            return true;
        }
    }

    @Override
    public Figure copy() {
        Figure copy = new King(position, colour);
        copy.setFirstMove(firstMove);
        return copy;
    }

    private boolean inCheck(Position to, Board board) {
        Figure aux;
        aux = board.getCeld(to.getX(), to.getY()).getFigure();
        board.getCeld(to.getX(), to.getY()).setFigure(this);
        board.getCeld(this.position.getX(), this.position.getY()).setFigure(null);
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getCeld(i, j).getFigure() != null && !board.getCeld(i, j).getFigure().getColour().equals(this.colour) && board.getCeld(i, j).getFigure().attackPuntuation(new Position(i, j), board) >= King.KING_VALUE) {
                    board.getCeld(this.position.getX(), this.position.getY()).setFigure(this);
                    board.getCeld(to.getX(), to.getY()).setFigure(aux);
                    return true;
                }
            }
        }
        board.getCeld(this.position.getX(), this.position.getY()).setFigure(this);
        board.getCeld(to.getX(), to.getY()).setFigure(aux);
        return false;
    }
}
