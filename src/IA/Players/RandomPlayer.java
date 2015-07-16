
package IA.Players;

import IA.Player;
import java.util.List;
import java.util.Random;
import model.Action;
import model.Board;

public class RandomPlayer implements Player{
    private Board board;

    public RandomPlayer(Board board) {
        this.board = board;
    }
    @Override
    public Action makeDecision() {
        List<Action> list;
        list=board.getPossibleMoves();
        try {
            Thread.sleep(600);
        } catch (InterruptedException ex) {
        }
        return list.get(new Random().nextInt(list.size()));
    }

}