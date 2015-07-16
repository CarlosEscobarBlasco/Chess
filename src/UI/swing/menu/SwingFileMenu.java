package UI.swing.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import model.Board;
import persistance.file.FileMatrixLoader;
import persistance.file.FileMatrixStorer;


public class SwingFileMenu {
    public static JMenu createStoreSubMenu(final Board board){
        final FileMatrixStorer storer = new FileMatrixStorer();
        JMenu subMenuStore = new JMenu("Save Matrix");
        JMenuItem store = new JMenuItem("Slot 1");
        JMenuItem store2 = new JMenuItem("Slot 2");
        JMenuItem store3 = new JMenuItem("Slot 3");
        
        store.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                storer.store(board, "state1");
            }
        });
        store2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                storer.store(board, "state2");
            }
        });
        store3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                storer.store(board, "state3");
            }
        });
        
        subMenuStore.add(store);
        subMenuStore.add(store2);
        subMenuStore.add(store3);
        return subMenuStore;
    }
    
    public static JMenu createLoadSubMenu(final Board board){
        final FileMatrixLoader loader = new FileMatrixLoader();
        JMenu subMenuLoad = new JMenu("Load Matrix");
        JMenuItem load = new JMenuItem("Slot 1");
        JMenuItem load2 = new JMenuItem("Slot 2");
        JMenuItem load3 = new JMenuItem("Slot 3");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                loader.load(board, "state1");
            }
        });
        load2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                loader.load(board, "state2");
            }
        });
        load3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                loader.load(board, "state3");
            }
        });
        subMenuLoad.add(load);
        subMenuLoad.add(load2);
        subMenuLoad.add(load3);
        return subMenuLoad;
        
    }
}
