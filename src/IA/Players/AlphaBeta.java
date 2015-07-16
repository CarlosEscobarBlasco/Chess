
package IA.Players;

import IA.Heuristic;
import model.Action;
import model.Board;

public class AlphaBeta extends Thread{
    private Board board;
    private String player;
    private int depth;
    private final Heuristic heuristic;
    private MakeDecisionAB makeDecision;
    private Action action;

    public AlphaBeta(Board board,String player, int depth, Heuristic heuristic,MakeDecisionAB makeDecision,Action action) {
        this.makeDecision=makeDecision;
        this.action=action;
        this.board = board;
        this.player = player;
        this.depth = depth;
        this.heuristic=heuristic;
    }

    
    
    @Override
    public void run(){
        double value = minValue(board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, player, depth);
        makeDecision.setResult(action, value);
        
    }
    
    public double maxValue(Board board, Double alfa, Double beta, String player, int depth) {
        Board copyBoard;
        if (depth <= 0 || board.getUtility() != -1) {
            return heuristic.valorateBoard(board, player);
        }
        depth--;
        double value;
        for (Action nextAction : board.getPossibleMoves()) {
            copyBoard = board.copy(board.getPlayerToMove());
            copyBoard.move(nextAction.getFrom(), nextAction.getTo());
            value = minValue(copyBoard, alfa, beta, player, depth);
            if (value >= beta) {
                return beta;   // fail hard beta-cutoff
            }
            if (value > alfa) {
                alfa = value; // alpha acts like max in MiniMax
            }
        }
        return alfa;
    }

    public double minValue(Board board, Double alfa, Double beta, String player, int depth) {
        Board copyBoard;
        if (depth <= 0 || board.getUtility() != -1) {
            return heuristic.valorateBoard(board, player);
        }
        depth--;
        double value;
        for (Action nextAction : board.getPossibleMoves()) {
            copyBoard = board.copy(board.getPlayerToMove());
            copyBoard.move(nextAction.getFrom(), nextAction.getTo());
            value = maxValue(copyBoard, alfa, beta, player, depth);
            if (value <= alfa) {
                return alfa; // fail hard alpha-cutoff
            }
            if (value < beta) {
                beta = value; // beta acts like min in MiniMax
            }
        }
        return beta;
    }
}
