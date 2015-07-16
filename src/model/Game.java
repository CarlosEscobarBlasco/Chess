
package model;

import java.util.List;

public class Game{
    private Board board;
    private static Position from;
    private static Position to;

    public static Position getFrom() {
        return from;
    }

    public static Position getTo() {
        return to;
    }

    public Game(Board board) {
        this.board = board;
    }

    public Board getState() {
        return board;
    }
    
    public String getPlayer(){
        return board.getPlayerToMove();
    }
    public boolean isTerminal(){
        return board.getUtility() != -1;
    }
    
    public String getWinner(){
        if(isTerminal()){
            return board.getUtility() == 1 ? "White" : "Black";
        }
        return "noWinner";
    }
    public boolean action(Action action){
        from = action.getFrom();
        to = action.getTo();
        return board.move(action.getFrom(), action.getTo());
    }
    
    public List<Action> getActions(){
        return board.getPossibleMoves();
    }

}
