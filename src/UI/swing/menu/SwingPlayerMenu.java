package UI.swing.menu;


import Control.Control;
import IA.Players.MakeDecisionAB;
import IA.Players.MiniMaxPlayer;
import IA.Players.RandomPlayer;
import IA.Players.SwingHumanPlayer;
import UI.swing.SwingViewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import model.Board;
import IA.heuristics.ProHeuristic;
import IA.heuristics.ScoreDifferenceHeuristic;

public class SwingPlayerMenu {

    public static JMenuItem selectBlackPlayer(final Board board, JMenu menu,SwingViewer viewer) {
        final SwingViewer v = viewer;
        JMenu whitePlayer = new JMenu("Black");
        JMenuItem human = new JMenuItem("Human");
        JMenuItem random = new JMenuItem("Random");
        JMenuItem scoreDifference = new JMenuItem("Score Difference");
        JMenuItem pro = new JMenuItem("Pro SD");
        
        human.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Control.setBlack(new SwingHumanPlayer(v));
            }
        });
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Control.setBlack(new RandomPlayer(board));
            }
        });
        scoreDifference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Control.setBlack(new MakeDecisionAB(board, new ScoreDifferenceHeuristic()));
            }
        });
        pro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Control.setBlack(new MakeDecisionAB(board, new ProHeuristic()));
            }
        });
        whitePlayer.add(human);
        whitePlayer.add(random);
        whitePlayer.add(scoreDifference);
        whitePlayer.add(pro);

        menu.add(whitePlayer);
        return menu;
    }
    public static JMenuItem selectWhitePlayer(final Board board, JMenu menu,SwingViewer viewer) {
        final SwingViewer v = viewer;
        JMenu blackPlayer = new JMenu("White");
        JMenuItem human = new JMenuItem("Human");
        JMenuItem random = new JMenuItem("Random");
        JMenuItem scoreDifference = new JMenuItem("Score Difference");
        JMenuItem pro = new JMenuItem("Pro SD");
        
        human.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Control.setWhite(new SwingHumanPlayer(v));
            }
        });
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Control.setWhite(new RandomPlayer(board));
            }
        });
        scoreDifference.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Control.setWhite(new MakeDecisionAB(board, new ScoreDifferenceHeuristic()));
            }
        });
        pro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Control.setWhite(new MakeDecisionAB(board, new ProHeuristic()));
            }
        });
        blackPlayer.add(human);
        blackPlayer.add(random);
        blackPlayer.add(scoreDifference);
        blackPlayer.add(pro);

        menu.add(blackPlayer);
        return menu;
    }
}
