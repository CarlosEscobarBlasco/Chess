package model.figures;

import java.util.List;
import model.Action;
import model.Board;
import model.Figure;
import model.Position;

public class Queen implements Figure {

    private Position position;
    private final String colour;

    public Queen(Position posittion, String colour) {
        this.position = posittion;
        this.colour = colour;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public double getPuntuation() {
        return 9;
    }

    @Override
    public char getFigureName() {
        return 'Q';
    }

    @Override
    public boolean move(Position position, Board board) {
        if (board.insideBounds(position)) {
            switch (kindOfMove(position)) { // Elige el tipo de movimiento
                case 0:
                    return false;
                case 1:
                    return bishopMove(position, board);
                case 2:
                    return rookMove(position, board);
            }
        }
        return false;
    }

    @Override
    public double attackPuntuation(Position position, Board board) {
        return rookAttackPuntuation(position, board) + bishopAttackPuntuation(position, board);
    }

    private int kindOfMove(Position position) {
        if (position.getX() == this.position.getX()) {
            return 2;//movimiento de torre
        } else if (position.getY() == this.position.getY()) {
            return 2;//movimiento de torre
        } else if ((this.position.getX() > position.getX() && this.position.getY() > position.getY()) || (this.position.getX() < position.getX() && this.position.getY() < position.getY())) {
            if (this.position.getX() - this.position.getY() == position.getX() - position.getY()) {
                return 1; //movimiento del alfil
            }
        } else {
            if (this.position.getX() + this.position.getY() == position.getX() + position.getY()) {
                return 1; //movimiento del alfil
            }
        }
        return 0; //no es un movimiento valido
    }

    private boolean rookMove(Position position, Board board) {
        if (board.getCeld(position.getX(), position.getY()).getFigure() != null && board.getCeld(position.getX(), position.getY()).getFigure().getColour().equals(colour)) {
            return false; //no come a sus aliados
        }
        if (board.insideBounds(position) && rookValidMove(position)) {
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

    private int rookAttackPuntuation(Position position, Board board) {
        int result = 0;
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

    private boolean rookValidMove(Position position) {
        if (position.getX() == this.position.getX()) {
            return true;
        } else if (position.getY() == this.position.getY()) {
            return true;
        }
        return false;
    }


    private boolean bishopMove(Position position, Board board) {
        if (board.getCeld(position.getX(), position.getY()).getFigure() != null && board.getCeld(position.getX(), position.getY()).getFigure().getColour().equals(colour)) {
            return false; //no come a sus aliados
        }
        if (board.insideBounds(position) && bishopValidMove(position)) {
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

    public double bishopAttackPuntuation(Position position, Board board) {//MODIFICAR
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

    private boolean bishopValidMove(Position position) {
        if ((this.position.getX() > position.getX() && this.position.getY() > position.getY()) || (this.position.getX() < position.getX() && this.position.getY() < position.getY())) {
            return this.position.getX() - this.position.getY() == position.getX() - position.getY();
        } else {
            return this.position.getX() + this.position.getY() == position.getX() + position.getY();
        }
    }


    @Override
    public List<Action> possiblesMoves(List<Action> list, String colour, Board board) {
        //Rook
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
        for (int i = position.getY() - 1; i >= 0; i--) {
            if (move(new Position(position.getX(), i), board)) {
                list.add(new Action(position, new Position(position.getX(), i)));
            }
        }
        //Bishop
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

    //No necesario
    @Override
    public void setFirstMove(boolean b) {
    }
    
    @Override
    public boolean getFirstMove() {
        return false;
    }

    @Override
    public Figure copy() {
        Figure copy = new Queen(position, colour);
        return copy;
    }

}
