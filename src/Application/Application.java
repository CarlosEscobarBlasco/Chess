package Application;

import Control.Control;
import IA.Player;
import IA.Players.AlphaBetaPlayer;
import IA.Players.MakeDecisionAB;
import IA.Players.ConsoleHumanPlayer;
import IA.Players.MiniMaxPlayer;
import IA.Players.RandomPlayer;
import IA.Players.SwingHumanPlayer;
import IA.heuristics.AttackHeuristic;
import IA.heuristics.DefendHeuristic;
import IA.heuristics.ProHeuristic;
import IA.heuristics.ScoreDifferenceHeuristic;
import UI.console.ConsoleViewer;
import UI.swing.SwingViewer;
import model.Board;
import model.Celd;
import persistance.InitialLoader;

public class Application {

    
    public static void main(String[] args) {
        Board board = new Board(new Celd[8][8],"White");
        InitialLoader loader = new InitialLoader(board);
        swing(board,loader);
        //console(board,loader);
    }
    
    public static void swing(Board board,InitialLoader loader){
        SwingViewer viewer = new SwingViewer(board);
        Player human = new SwingHumanPlayer(viewer);
        Player random = new RandomPlayer(board);
        Player pro = new MiniMaxPlayer(board, new ProHeuristic());
        Player score = new MiniMaxPlayer(board, new ScoreDifferenceHeuristic());
        Player defend = new MiniMaxPlayer(board, new DefendHeuristic());
        Player attack = new MiniMaxPlayer(board, new AttackHeuristic());
        Player score2 = new MakeDecisionAB(board, new ScoreDifferenceHeuristic());
        Player pro2 = new MakeDecisionAB(board, new ProHeuristic());
        Control control = new Control(board, loader, viewer, human, score2);
        control.start();
    }
        
    public static void console(Board board,InitialLoader loader){
        ConsoleViewer viewer = new ConsoleViewer(board);
        Player human = new ConsoleHumanPlayer();
        Player random = new RandomPlayer(board);
        Player pro = new MiniMaxPlayer(board, new ProHeuristic());
        Player scoreD = new MiniMaxPlayer(board, new ScoreDifferenceHeuristic());
        Control control = new Control(board, loader, viewer, human, scoreD);
        control.start();
    }
}


