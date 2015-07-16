package IA.Players;

import IA.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Action;
import model.Board;
import IA.Heuristic;

public class MiniMaxPlayer implements Player {

    private int expandedNodes;
    private final Board board;
    private final int depth;
    private final Heuristic heuristic;
    private final List<Action> list;

    public MiniMaxPlayer(Board board, Heuristic heuristic) {
        this.board = board;
        expandedNodes = 0;
        depth = 3;
        this.heuristic = heuristic;
        list = new ArrayList<>();
    }

    @Override
    public Action makeDecision() {
        expandedNodes = 0;
        double resultValue = Double.NEGATIVE_INFINITY;
        Board copyBoard;
        String player = board.getPlayerToMove();
        for (Action action : board.getPossibleMoves()) {
            copyBoard = board.copy(board.getPlayerToMove());
            copyBoard.move(action.getFrom(), action.getTo());
            double value = minValue(copyBoard, player, depth);
            if (value >= resultValue) {
                if (value > resultValue) {
                    resultValue=value;
                    list.clear();
                    list.add(action);
                } else {
                    list.add(action);
                }
            }
        }
        return list.get(new Random().nextInt(list.size()));
    }

    public double maxValue(Board board, String player, int depth) {
        expandedNodes++;
        Board copyBoard;
        if (depth == 0 || board.getUtility() != -1) {
            return heuristic.valorateBoard(board, player);
        }
        double value = Double.NEGATIVE_INFINITY;
        depth--;
        for (Action action : board.getPossibleMoves()) {
            copyBoard = board.copy(board.getPlayerToMove());
            copyBoard.move(action.getFrom(), action.getTo());
            value = Math.max(value,minValue(copyBoard, player, depth));
        }
        return value;
    }

    public double minValue(Board board, String player, int depth) {
        expandedNodes++;
        Board copyBoard;
        if (board.getUtility() != -1 || depth <= 0) {
            return heuristic.valorateBoard(board, player);
        }
        double value = Double.POSITIVE_INFINITY;
        depth--;
        for (Action action : board.getPossibleMoves()) {
            copyBoard = board.copy(board.getPlayerToMove());
            copyBoard.move(action.getFrom(), action.getTo());
            value = Math.min(value, maxValue(copyBoard, player, depth));
        }
        return value;
    }
}
