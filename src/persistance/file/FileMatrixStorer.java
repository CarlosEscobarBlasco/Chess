package persistance.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.Board;

public class FileMatrixStorer {

    public void store(Board board, String name) {
        File file;
        switch (name) { // Elige el tipo de storer
            case "state1":
                file = new File("State\\state1.txt");
                storeState(board, file);
                break;
            case "state2":
                file = new File("State\\state2.txt");
                storeState(board, file);
                break;
            case "state3":
                file = new File("State\\state3.txt");
                storeState(board, file);
                break;
        }
    }

    private void storeState(Board board, File file) {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (board.getCeld(i, j).getFigure() == null) {
                        printWriter.print("[  ]");
                    } else {
                        printWriter.print("[" + board.getCeld(i, j).getFigure().getColour().charAt(0) + board.getCeld(i, j).getFigure().getFigureName() + "]");
                    }
                }
                printWriter.println("");
            }

        } catch (IOException ex) {
        } finally {
            try {
                if (null != fileWriter) {
                    fileWriter.close();
                }
            } catch (Exception e) {
            }
        }
    }

}
