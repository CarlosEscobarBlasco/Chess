package model;

import java.util.ArrayList;
import java.util.List;
import model.figures.King;
import model.figures.Queen;

public class Board {

    private String playerToMove;
    private int utility;
    private final Celd[][] board;

    public Board(Celd[][] board, String player) {
        this.playerToMove = player;
        this.board = board;
        iniciateBoard();
        utility = -1;
    }

    public int getWidth() {
        return board[0].length;
    }

    public int getHeight() {
        return board.length;
    }

    public Celd getCeld(int i, int j) {
        return board[i][j];
    }

    public boolean insideBounds(Position position) {
        return position.getX() >= 0 && position.getX() < board.length && position.getY() >= 0 && position.getY() < board[0].length;
    }

    public int kingDistance(Position position, String colour) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getFigure() != null) {
                    if (board[i][j].getFigure().getFigureName() == 'K' && !board[i][j].getFigure().getColour().equals(colour)) {
                        return position.euclideDistance(new Position(i, j));
                    }
                }
            }
        }
        return -1; //error (no hay rey)
    }

    private void iniciateBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Celd(null);
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < board[0].length; i++) {
            result = result + "   " + i;
        }
        result = result + "\n";
        for (int i = 0; i < board.length; i++) {
            result = result + i;
            for (int j = 0; j < board[0].length; j++) {
                if (this.getCeld(i, j).getFigure() == null) {
                    result = result + "[  ]";
                } else if ("Black".equals(this.getCeld(i, j).getFigure().getColour())) {
                    result = result + "[B" + this.getCeld(i, j).getFigure().getFigureName() + "]";  //Si son negras pone una B delante
                } else {
                    result = result + "[W" + this.getCeld(i, j).getFigure().getFigureName() + "]";  //Si son blancas pone una W delante
                }
            }
            result = result + "\n";
        }
        return result;
    }

    public String getPlayerToMove() {
        return playerToMove;
    }

    public double getUtility() {
        return utility;
    }

    private void analyzeUtility() {
        if (!searchKing(playerToMove)) {
            utility = (playerToMove.equals("White") ? 0 : 1);
        }
    }

    private boolean searchKing(String playerToMove) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getFigure() != null && board[i][j].getFigure().getFigureName() == 'K' && board[i][j].getFigure().getColour().equals(playerToMove)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean move(Position from, Position to) {
        if (insideBounds(from) && insideBounds(to) && board[from.getX()][from.getY()].getFigure() != null && board[from.getX()][from.getY()].getFigure().getColour().equals(playerToMove)) {
            //Enroque
            if (board[from.getX()][from.getY()].getFigure().getFigureName() == 'K' && board[from.getX()][from.getY()].getFigure().move(to, this)) {
                switch (King.castling(from, to, this)) {
                    case 2: //Enroque corto
                        board[from.getX()][from.getY() + 2].setFigure(board[from.getX()][from.getY()].getFigure());
                        board[from.getX()][from.getY() + 2].getFigure().setPosition(new Position(to.getX(), from.getY() + 2));
                        board[from.getX()][from.getY()].setFigure(null);
                        board[from.getX()][from.getY() + 1].setFigure(board[from.getX()][from.getY() + 3].getFigure());
                        board[from.getX()][from.getY() + 1].getFigure().setPosition(new Position(from.getX(), from.getY() + 1));
                        board[from.getX()][from.getY() + 3].setFigure(null);
                        playerToMove = "White".equals(playerToMove) ? "Black" : "White";
                        analyzeUtility();
                        board[from.getX()][from.getY() + 2].getFigure().setFirstMove(false);
                        board[from.getX()][from.getY() + 1].getFigure().setFirstMove(false);
                        return true;
                    case 1: //Enroque largo
                        board[from.getX()][from.getY() - 2].setFigure(board[from.getX()][from.getY()].getFigure());
                        board[from.getX()][from.getY() - 2].getFigure().setPosition(new Position(from.getX(), from.getY() - 2));
                        board[from.getX()][from.getY()].setFigure(null);
                        board[from.getX()][from.getY() - 1].setFigure(board[from.getX()][from.getY() - 4].getFigure());
                        board[to.getX()][from.getY() - 1].getFigure().setPosition(new Position(to.getX(), from.getY() - 1));
                        board[from.getX()][from.getY() - 4].setFigure(null);
                        playerToMove = "White".equals(playerToMove) ? "Black" : "White";
                        analyzeUtility();
                        board[from.getX()][from.getY() - 2].getFigure().setFirstMove(false);
                        board[from.getX()][from.getY() - 1].getFigure().setFirstMove(false);
                        return true;
                }
            }
            if (board[from.getX()][from.getY()].getFigure().move(to, this)) {
                board[from.getX()][from.getY()].getFigure().setFirstMove(false);
                board[to.getX()][to.getY()].setFigure(board[from.getX()][from.getY()].getFigure());
                board[to.getX()][to.getY()].getFigure().setPosition(new Position(to.getX(), to.getY()));
                board[from.getX()][from.getY()].setFigure(null);
                pawnToQueen();
                playerToMove = "White".equals(playerToMove) ? "Black" : "White";
                analyzeUtility();
                return true;
            }
        }
        return false;
    }

    public List<Action> getPossibleMoves() {
        List<Action> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getFigure() != null && board[i][j].getFigure().getColour().equals(playerToMove)) {
                    board[i][j].getFigure().possiblesMoves(list, playerToMove, this);
                }
            }
        }
        return list;
    }

    public void insert(Position position, Figure figure) {
        board[position.getX()][position.getY()].setFigure(figure);
    }

    private void pawnToQueen() {
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i].getFigure() != null && board[0][i].getFigure().getFigureName() == 'P') {
                board[0][i].setFigure(new Queen(new Position(0, i), "White"));
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[board.length - 1][i].getFigure() != null && board[board.length - 1][i].getFigure().getFigureName() == 'P') {
                board[board.length - 1][i].setFigure(new Queen(new Position(board.length - 1, i), "Black"));
            }
        }
    }

    //public Board clone(){
    public Board copy(String player) {
        Board copy = new Board(new Celd[board.length][board[0].length], player);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getFigure() != null) {
                    copy.getCeld(i, j).setFigure(board[i][j].getFigure().copy());
                }
            }
        }
        return copy;
    }

    public int numberOfRivalKingMoves(String color) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].getFigure() != null && board[i][j].getFigure().getFigureName() == 'K' && !board[i][j].getFigure().getColour().equals(color)) {
                    return board[i][j].getFigure().possiblesMoves(new ArrayList<Action>(), color, this).size();
                }
            }
        }
        return 0;
    }

//    private boolean canCastling(Position from, Position to, int castlingType) {
//        if (castlingType == 1) {
//            for (int i = from.getY(); i >= to.getY(); i--) {
//                for (int j = 0; j < board.length; j++) {
//                    for (int k = 0; k < board[0].length; k++) {
//                        if (board[j][k].getFigure() != null && board[j][k].getFigure().getColour() != board[from.getX()][from.getY() - 4 + i].getFigure().getColour() && board[j][k].getFigure().attackPuntuation(new Position(j, k), this) >= King.KING_VALUE) {
//                            board[from.getX()][from.getY()].setFigure(board[from.getX()][from.getY() - 4 + i].getFigure());
//                            if(from.getY() - 4 + i!=from.getY()) board[from.getX()][from.getY() - 4 + i].setFigure(null);
//                            return false;
//                        }
//                    }
//                }
//                board[from.getX()][from.getY() - 5 + i].setFigure(board[from.getX()][from.getY() - 4 + i].getFigure());
//                board[from.getX()][from.getY() - 4 + i].setFigure(null);
//            }
//            board[from.getX()][from.getY()].setFigure(board[from.getX()][from.getY() - 3].getFigure());
//            board[from.getX()][from.getY() - 3].setFigure(null);
//            return true;
//        } else {
//            for (int i = from.getY(); i < to.getY(); i++) {
//                for (int j = 0; j < board.length; j++) {
//                    for (int k = 0; k < board[0].length; k++) {
//                        if (board[j][k].getFigure() != null && board[j][k].getFigure().getColour() != board[from.getX()][from.getY() +3 -(7-i)].getFigure().getColour() && board[j][k].getFigure().attackPuntuation(new Position(j, k), this) >= King.KING_VALUE) {
//                            board[from.getX()][from.getY()].setFigure(board[from.getX()][from.getY() +3 -(7-i)].getFigure());
//                            //SI ESTA EN JAQUE DESDE LA 1º POSICION ELIMINAMOS EL REY POR COMPLETO, MIRAR, Y ARRIBA TAMBIEN
//                            if(from.getY() +3 -(7-i)==from.getY())board[from.getX()][from.getY() +3 -(7-i)].setFigure(null);
//                            return false;
//                        }
//                    }
//                }
//                board[from.getX()][from.getY() +4 -(7-i)].setFigure(board[from.getX()][from.getY() +3 -(7-i)].getFigure());
//                board[from.getX()][from.getY() +3 -(7-i)].setFigure(null);
//            }
//            for (int j = 0; j < board.length; j++) {
//                    for (int k = 0; k < board[0].length; k++) {
//                        if (board[j][k].getFigure() != null && board[j][k].getFigure().getColour() != board[from.getX()][from.getY() +2].getFigure().getColour() && board[j][k].getFigure().attackPuntuation(new Position(j, k), this) >= King.KING_VALUE) {
//                            board[from.getX()][from.getY()].setFigure(board[from.getX()][from.getY() +2].getFigure());
//                            //SI ESTA EN JAQUE DESDE LA 1º POSICION ELIMINAMOS EL REY POR COMPLETO, MIRAR, Y ARRIBA TAMBIEN
//                            board[from.getX()][from.getY() +2].setFigure(null);
//                            return false;
//                        }
//                    }
//                }
//            board[from.getX()][from.getY()].setFigure(board[from.getX()][from.getY() +2].getFigure());
//            board[from.getX()][from.getY() +2].setFigure(null);
//            return true;
//        }
//    }
}
