package UI.console;

import UI.Viewer;
import model.Board;

public class ConsoleViewer implements Viewer{

    Board board;

    public ConsoleViewer(Board board) {
        this.board = board;
    }

    @Override
    public void showBoard() {
        Control.Control.setStart(true);
        System.out.println(board);
    }


    @Override
    public void showPlayerToMove(String player) {
        System.out.println("Mueven las "+player);
    }

    @Override
    public void showWinner(String winner) {
        System.out.println("Ganaron las "+ winner);
    }
}
