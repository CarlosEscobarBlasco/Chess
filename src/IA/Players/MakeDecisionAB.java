package IA.Players;

import IA.Heuristic;
import IA.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Action;
import model.Board;

public class MakeDecisionAB implements Player {

//    private int expandedNodes;
    private final Board board;
    private int depth;
    private final Heuristic heuristic;
    private final List<Action> list;
    private double resultValue;
    private int nThread;

    public MakeDecisionAB(Board board, Heuristic heuristic) {
        this.board = board;
        depth = 3;
        this.heuristic = heuristic;
        list = new ArrayList<>();
        nThread = 0;
    }

    @Override
    public Action makeDecision() {
        resultValue = Double.NEGATIVE_INFINITY;
        Board copyBoard;
        String player = board.getPlayerToMove();
        for (Action action : board.getPossibleMoves()) {
            copyBoard = board.copy(board.getPlayerToMove());
            copyBoard.move(action.getFrom(), action.getTo());
//            double value = minValue(copyBoard, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, player, depth);
            new AlphaBeta(copyBoard, player, depth, heuristic, this, action).start();
//            if (value >= resultValue) {
//                if (value > resultValue) {
//                    resultValue = value;
//                    list.clear();
//                    list.add(action);
//                } else {
//                    list.add(action);
//                }
        }
//        }
        while (true) {
            if (nThread == board.getPossibleMoves().size()) {
                nThread = 0;
//                King.setKING_VALUE(10);
                return list.get(new Random().nextInt(list.size()));
            }
        }
    }

    public synchronized void setResult(Action action, double value) {
        nThread++;
        if (value >= resultValue) {
            if (value > resultValue) {
                resultValue = value;
                list.clear();
                list.add(action);
            } else {
                list.add(action);
            }
        }
    }
//    public double maxValue(Board board, Double alfa, Double beta, String player, int depth) {
//        Board copyBoard;
//        if (depth <= 0 || board.getUtility() != -1) {
//            return heuristic.valorateBoard(board, player);
//        }
//        depth--;
//        double value;
//        for (Action action : board.getPossibleMoves()) {
//            copyBoard = board.copy(board.getPlayerToMove());
//            copyBoard.move(action.getFrom(), action.getTo());
//            value = minValue(copyBoard, alfa, beta, player, depth);
//            if (value >= beta) {
//                return beta;   // fail hard beta-cutoff
//            }
//            if (value > alfa) {
//                alfa = value; // alpha acts like max in MiniMax
//            }
//        }
//        return alfa;
//    }
//
//    public double minValue(Board board, Double alfa, Double beta, String player, int depth) {
//        Board copyBoard;
//        if (depth <= 0 || board.getUtility() != -1) {
//            return heuristic.valorateBoard(board, player);
//        }
//        depth--;
//        double value;
//        for (Action action : board.getPossibleMoves()) {
//            copyBoard = board.copy(board.getPlayerToMove());
//            copyBoard.move(action.getFrom(), action.getTo());
//            value = maxValue(copyBoard, alfa, beta, player, depth);
//            if (value <= alfa) {
//                return alfa; // fail hard alpha-cutoff
//            }
//            if (value < beta) {
//                beta = value; // beta acts like min in MiniMax
//            }
//        }
//        return beta;
//    }
//    http://chessprogramming.wikispaces.com/Alpha-Beta
}
