
package IA;

import model.Board;

public interface Heuristic {
    public double valorateBoard(Board board,String player);
}
