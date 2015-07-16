package IA.Players;

import IA.Player;
import model.Action;
import model.Position;
import UI.swing.SwingViewer;

public class SwingHumanPlayer implements Player {

    private final SwingViewer viewer;

    public SwingHumanPlayer(SwingViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public Action makeDecision() {
        Position from, to;
        while (!viewer.waitForInput()) {};
        from = viewer.getPosition();
        while (!viewer.waitForInput()) {};
        to = viewer.getPosition();
        return new Action(from,to);
    }

}
