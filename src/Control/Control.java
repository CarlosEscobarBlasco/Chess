package Control;

import IA.Player;
import model.Board;
import model.Game;
import UI.Viewer;
import model.figures.King;
import persistance.InitialLoader;
import persistance.file.FileMatrixStorer;

public class Control {

    private static Boolean start = false;
    private static Player white;
    private static Player black;
    private final Viewer viewer;
    private final Board board;
    private final InitialLoader loader;
    private final Game game;
    FileMatrixStorer storer;
    private static boolean validMove;//MODIFICAR!

    public Control(Board board, InitialLoader loader, Viewer viewer, Player white, Player black) {
        this.viewer = viewer;
        this.board = board;
        Control.white = white;
        Control.black = black;
        this.loader = loader;
        game = new Game(board);
        storer = new FileMatrixStorer();
    }

    public void start() {
        loader.load();
        viewer.showBoard();
        while (!start) {
            viewer.showBoard();
        }
        while (!game.isTerminal()) {
//            System.out.println(board);
            viewer.showPlayerToMove(game.getPlayer());
            validMove = game.action("White".equals(game.getPlayer()) ? white.makeDecision() : black.makeDecision());
            viewer.showBoard();
        }
        viewer.showWinner(game.getWinner());
    }

    //MODIFICAR!
    public static void setWhite(Player white) {
        Control.white = white;
    }
//MODIFICAR!

    public static void setBlack(Player black) {
        Control.black = black;
    }
//MODIFICAR!

    public static void setStart(Boolean start) {
        Control.start = start;
    }
//MODIFICAR!

    public static boolean isValidMove() {
        return validMove;
    }

}
