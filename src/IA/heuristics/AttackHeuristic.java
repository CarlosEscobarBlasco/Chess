package IA.heuristics;

import model.Board;
import IA.Heuristic;

public class AttackHeuristic implements Heuristic {

    @Override
    public double valorateBoard(Board board, String player) {
        double valoration = 0;
        boolean allyKing = false;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (board.getCeld(i, j).getFigure() != null) {
                    if (!board.getCeld(i, j).getFigure().getColour().equals(player)) {
                        valoration = valoration - board.getCeld(i, j).getFigure().getPuntuation();
                    }
                    if (board.getCeld(i, j).getFigure().getFigureName() == 'K' && board.getCeld(i, j).getFigure().getColour().equals(player)) {
                        allyKing = true;
                    }
                }
            }
        }
        return allyKing ? valoration : valoration - 300;
    }
}
