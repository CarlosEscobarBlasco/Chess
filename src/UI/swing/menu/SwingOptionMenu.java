package UI.swing.menu;

//import gameoflife.ui.Swing.SwingMatrixDialog;
//import gameoflife.ui.Swing.SwingMatrixViewer;
import Control.Control;
import IA.Player;
import IA.Players.SwingHumanPlayer;
import UI.swing.SwingViewer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import model.Board;
import persistance.InitialLoader;

public class SwingOptionMenu {

    public static JMenuItem start() {
        JMenuItem exit = new JMenuItem("Start");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Control.setStart(true);
            }
        });
        return exit;
    }

    public static JMenuItem restart(final Board board) {
        JMenuItem exit = new JMenuItem("Restart");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        return exit;
    }

    public static JMenuItem undo() {
        JMenuItem exit = new JMenuItem("Undo");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Cambiar por UNDO
            }
        });
        return exit;
    }

    public static JMenuItem selectDifficulty(SwingViewer viewer, JMenu menu) {
        final SwingViewer v = viewer;
        JMenu difficulty = new JMenu("Difficulty");
        JMenuItem easy = new JMenuItem("Easy");
        JMenuItem medium = new JMenuItem("Medium");
        JMenuItem hard = new JMenuItem("Hard");

        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            }
        });
        difficulty.add(easy);
        difficulty.add(medium);
        difficulty.add(hard);
        menu.add(difficulty);
        return menu;
    }
}
