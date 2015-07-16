package IA.Players;

import IA.Heuristic;
import IA.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Action;
import model.Board;

public class AlphaBetaPlayer implements Player {

//    private int expandedNodes;
    private final Board board;
    private int depth;
    private final Heuristic heuristic;
    private final List<Action> list;

    public AlphaBetaPlayer(Board board, Heuristic heuristic) {
        this.board = board;
        depth = 3;
        this.heuristic = heuristic;
        list = new ArrayList<>();
    }

    @Override
    public Action makeDecision() {
        double resultValue = Double.NEGATIVE_INFINITY;
        Board copyBoard ;
        String player = board.getPlayerToMove();
        ActionValue value = maxValue(board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, player, depth, new ActionValue(null,Double.NEGATIVE_INFINITY));
//        for (Action action : board.getPossibleMoves()) {
//            copyBoard = board.copy(board.getPlayerToMove());
//            copyBoard.move(action.getFrom(), action.getTo());
//            double value = minValue(copyBoard, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, player, depth);
//            if (value >= resultValue) {
//                if (value > resultValue) {
//                    resultValue = value;
//                    list.clear();
//                    list.add(action);
//                } else {
//                    list.add(action);
//                }
//            }
//        }
        return value.getAction();
    }

    public ActionValue maxValue(Board board, Double alfa, Double beta, String player, int depth, ActionValue value) {
        Board copyBoard;
        if (depth <= 0 || board.getUtility() != -1) {
            value.setValue(heuristic.valorateBoard(board, player));
            return value;
        }
        depth--;
        for (Action action : board.getPossibleMoves()) {
            copyBoard = board.copy(board.getPlayerToMove());
            copyBoard.move(action.getFrom(), action.getTo());
            value = minValue(copyBoard, alfa, beta, player, depth, value);
            if (value.getValue() >= beta) {
                value.setValue(beta);
                return value;   // fail hard beta-cutoff
            }
            if (value.getValue() > alfa) {
                value.setAction(action);
                alfa = value.getValue(); // alpha acts like max in MiniMax
            }
        }
        value.setValue(alfa);
        return value;
    }

    public ActionValue minValue(Board board, Double alfa, Double beta, String player, int depth, ActionValue value) {
        Board copyBoard;
        if (depth <= 0 || board.getUtility() != -1) {
            value.setValue(heuristic.valorateBoard(board, player));
            return value;
        }
        depth--;
        for (Action action : board.getPossibleMoves()) {
            copyBoard = board.copy(board.getPlayerToMove());
            copyBoard.move(action.getFrom(), action.getTo());
            value = maxValue(copyBoard, alfa, beta, player, depth, value);
            if (value.getValue() <= alfa) {
                value.setValue(alfa);
                return value; // fail hard alpha-cutoff
            }
            if (value.getValue() < beta) {
                value.setAction(action);
                beta = value.getValue(); // beta acts like min in MiniMax
            }
        }
        value.setValue(beta);
        return value;
    }
//    http://chessprogramming.wikispaces.com/Alpha-Beta
}