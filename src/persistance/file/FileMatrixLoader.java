package persistance.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import model.Board;
import model.Position;
import model.figures.Bishop;
import model.figures.King;
import model.figures.Knight;
import model.figures.Pawn;
import model.figures.Queen;
import model.figures.Rook;

public class FileMatrixLoader {

    public void load(Board board, String name) {
        File file;
        switch (name) { // Eleige el tipo de loader
            case "state1":
                file = new File("State\\state1.txt");
                loadState(board, file);
                break;
            case "state2":
                file = new File("State\\state2.txt");
                loadState(board, file);
                break;
            case "state3":
                file = new File("State\\state3.txt");
                loadState(board, file);
                break;
        }
    }

    private void loadState(Board board, File file) {
        int i;
        int lines = 0;
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(fileReader);
            String line;
            while ((line = buffer.readLine()) != null) {
                i = 0;
                while (i < line.length() - 1) {
                    switch (line.charAt(i + 1)) {
                        case 'P':
                            board.getCeld(lines, Math.round(i / 4)).setFigure(new Pawn(new Position(lines, Math.round(i / 4)), line.charAt(i) == 'W' ? "White" : "Black"));
                            break;
                        case 'K':
                            board.getCeld(lines, Math.round(i / 4)).setFigure(new King(new Position(lines, Math.round(i / 4)), line.charAt(i) == 'W' ? "White" : "Black"));
                            break;
                        case 'Q':
                            board.getCeld(lines, Math.round(i / 4)).setFigure(new Queen(new Position(lines, Math.round(i / 4)), line.charAt(i) == 'W' ? "White" : "Black"));
                            break;
                        case 'R':
                            board.getCeld(lines, Math.round(i / 4)).setFigure(new Rook(new Position(lines, Math.round(i / 4)), line.charAt(i) == 'W' ? "White" : "Black"));
                            break;
                        case 'B':
                            board.getCeld(lines, Math.round(i / 4)).setFigure(new Bishop(new Position(lines, Math.round(i / 4)), line.charAt(i) == 'W' ? "White" : "Black"));
                            break;
                        case 'H':
                            board.getCeld(lines, Math.round(i / 4)).setFigure(new Knight(new Position(lines, Math.round(i / 4)), line.charAt(i) == 'W' ? "White" : "Black"));
                            break;
                        case ' ':
                            board.getCeld(lines, Math.round(i / 4)).setFigure(null);
                            break;
                    }
                    i++;
                }
                lines++;
            }
        } catch (IOException ex) {
        }
    }

}
