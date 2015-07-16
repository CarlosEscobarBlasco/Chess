package UI.swing;

import Control.Control;
import UI.Viewer;
import UI.swing.menu.SwingFileMenu;
import UI.swing.menu.SwingOptionMenu;
import UI.swing.menu.SwingPlayerMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import model.Action;
import model.Board;
import model.Game;
import model.Position;
import model.figures.King;

public class SwingViewer extends JFrame implements Viewer {

    private Position position;
    private boolean pressed = false;
    private final JButton[][] bBoard;
    private final Board board;
    private final JLabel playerLabel = new JLabel();
    private final Color lightBrown = new Color(255, 200, 109);
    private final Color darkBrown = new Color(167, 93, 44);

    public SwingViewer(Board board) {
        this.board = board;
        pressed = false;
        this.bBoard = new JButton[board.getHeight()][board.getWidth()];
        this.setTitle("Chess");
        this.setSize(590, 650);//  590, 650||300, 495 || 420, 605
        this.setLocationRelativeTo(null);
//        this.setResizable(false);
        createButtonsMatrix();
        createComponents();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void showBoard() {
        Position wKing = null;
        Position bKing = null;
        for (int i = 0; i < bBoard.length; i++) {
            for (int j = 0; j < bBoard[0].length; j++) {
                if (board.getCeld(i, j).getFigure() == null) {
                    bBoard[i][j].setIcon(null);
                } else {
                    switch (board.getCeld(i, j).getFigure().getFigureName()) {
                        case 'P':
                            bBoard[i][j].setIcon("White".equals(board.getCeld(i, j).getFigure().getColour()) ? new ImageIcon("img//whitepawn.png") : new ImageIcon("img//blackpawn.png"));
                            break;
                        case 'K':
                            bBoard[i][j].setIcon("White".equals(board.getCeld(i, j).getFigure().getColour()) ? new ImageIcon("img//whiteking.png") : new ImageIcon("img//blackking.png"));
                            if (board.getCeld(i, j).getFigure().getColour() == "White") {
                                wKing = new Position(i, j);
                            } else {
                                bKing = new Position(i, j);
                            }
                            break;
                        case 'B':
                            bBoard[i][j].setIcon("White".equals(board.getCeld(i, j).getFigure().getColour()) ? new ImageIcon("img//whitebishop.png") : new ImageIcon("img//blackbishop.png"));
                            break;
                        case 'Q':
                            bBoard[i][j].setIcon("White".equals(board.getCeld(i, j).getFigure().getColour()) ? new ImageIcon("img//whitequeen.png") : new ImageIcon("img//blackqueen.png"));
                            break;
                        case 'R':
                            bBoard[i][j].setIcon("White".equals(board.getCeld(i, j).getFigure().getColour()) ? new ImageIcon("img//whiterook.png") : new ImageIcon("img//blackrook.png"));
                            break;
                        case 'H':
                            bBoard[i][j].setIcon("White".equals(board.getCeld(i, j).getFigure().getColour()) ? new ImageIcon("img//whiteknight.png") : new ImageIcon("img//blackknight.png"));
                            break;
                    }
                }
                bBoard[i][j].setBackground((i + j) % 2 == 0 ? lightBrown : darkBrown);
            }
        }
        if (Control.isValidMove()) { //En azul el ultimo movimiento, ¿poner en opcion?
            //MODIFICAR!
            bBoard[Game.getFrom().getX()][Game.getFrom().getY()].setBackground(Color.BLUE);
            bBoard[Game.getTo().getX()][Game.getTo().getY()].setBackground(Color.BLUE);
        }
        for (int i = 0; i < bBoard.length; i++) {
            for (int j = 0; j < bBoard[0].length; j++) {
                if (board.getCeld(i, j).getFigure() != null && board.getCeld(i, j).getFigure().attackPuntuation(new Position(i, j), board) >= King.KING_VALUE) {
                    bBoard[i][j].setBackground(Color.RED);
                    if (board.getCeld(i, j).getFigure().getColour() == "White") {
                        bBoard[bKing.getX()][bKing.getY()].setBackground(Color.RED);
//                        bBoard[bKing.getX()][bKing.getY()].setForeground(checkRed);
                    } else {
                        bBoard[wKing.getX()][wKing.getY()].setBackground(Color.RED);
                    }
                }
            }
        }
    }

    public Position getPosition() {
        System.out.print("");
        return position;
    }

    private void createButtonsMatrix() {
        for (int i = 0; i < bBoard.length; i++) {
            for (int j = 0; j < bBoard[0].length; j++) {
                final int ii = i, jj = j;
                final JButton button = new JButton();
                button.setPreferredSize(new Dimension(65, 65));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) { //MODIFICAR!
                        position = new Position(ii, jj);
                        if(board.getCeld(ii, jj).getFigure() != null && board.getCeld(ii, jj).getFigure().getColour() == board.getPlayerToMove()){
                            List<Action> list = new ArrayList<>();
                            for (Action action : board.getCeld(ii, jj).getFigure().possiblesMoves(list, board.getPlayerToMove(), board)) {
                                //bBoard[action.getFrom().getX()][action.getFrom().getY()].setBackground(possibleMoveBlue);
                                bBoard[action.getTo().getX()][action.getTo().getY()].setBackground(Color.CYAN);
                                if (board.getCeld(action.getTo().getX(), action.getTo().getY()).getFigure() != null && board.getCeld(action.getTo().getX(), action.getTo().getY()).getFigure().getColour()!= board.getPlayerToMove()){
                                    bBoard[action.getTo().getX()][action.getTo().getY()].setBackground(Color.GREEN);
                                }
                            }
                        }
                        pressed = true;
                    }
                });
                bBoard[i][j] = button;
            }
        }
    }

    public boolean waitForInput() {
        if (pressed) {
            pressed = false;
            return true;
        }
        return false;
    }

    private JPanel createMatrixPanel() {
        JPanel panel = new JPanel();
        for (int i = 0; i < bBoard.length; i++) {
            for (int j = 0; j < bBoard[0].length; j++) {
                panel.add(bBoard[i][j]);
            }
        }
        return panel;
    }

    @Override
    public void showPlayerToMove(String player) {
        playerLabel.setText(player + " turn");
    }

    private void createComponents() {
        this.add(createMenu(), BorderLayout.NORTH);
        this.add(createMatrixPanel());
        this.add(createPlayerTurnPanel(), BorderLayout.SOUTH);
    }

    private JPanel createPlayerTurnPanel() {
        JPanel panel = new JPanel();
        panel.add(playerLabel);
        return panel;
    }

    @Override
    public void showWinner(String winner) {
        playerLabel.setText("The winner is: " + winner);
    }

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
//        menuBar.add(createOptionsMenu());
        menuBar.add(createPlayersMenu());
        menuBar.add(SwingOptionMenu.start());
        return menuBar;
    }

    private JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        menu.add(SwingFileMenu.createStoreSubMenu(board));
        menu.add(SwingFileMenu.createLoadSubMenu(board));
        return menu;
    }

    private JMenu createOptionsMenu() {
        JMenu menu = new JMenu("Option");
        menu.add(SwingOptionMenu.undo());
        menu.add(SwingOptionMenu.selectDifficulty(this, menu));
        menu.add(SwingOptionMenu.restart(board));
        return menu;
    }

    private JMenu createPlayersMenu() {
        JMenu menu = new JMenu("Player");
        menu.add(SwingPlayerMenu.selectWhitePlayer(board, menu, this));
        menu.add(SwingPlayerMenu.selectBlackPlayer(board, menu, this));
        return menu;
    }
}
