
package persistance;

import model.figures.Pawn;
import model.figures.King;
import model.figures.Queen;
import model.figures.Rook;
import model.figures.Bishop;
import model.Board;
import model.Position;
import model.figures.Knight;

public class InitialLoader {
    private final Board board;

    public InitialLoader(Board board) {
        this.board = board;
    }
    public void load(){
        if(board.getWidth()==6){//8x6
            load8x6();
        }else if(board.getWidth()==4){//6x4
            mediumLoad();
        }else if(board.getWidth()==8){//8x8
            load8x8();
        }else{//3x3
            smallLoad();
        }
    }
    private void smallLoad(){
        board.getCeld(0, 0).setFigure( new King(new Position(0, 0), "Black"));
        board.getCeld(2, 2).setFigure( new King(new Position(2, 2), "White"));
    }
    
    private void mediumLoad(){
        board.getCeld(1, 0).setFigure( new Pawn(new Position(1, 0), "Black"));
        board.getCeld(1, 1).setFigure( new Pawn(new Position(1, 1), "Black"));
        board.getCeld(1, 2).setFigure( new Pawn(new Position(1, 2), "Black"));
        board.getCeld(1, 3).setFigure( new Pawn(new Position(1, 3), "Black"));
        board.getCeld(0, 0).setFigure( new King(new Position(0, 0), "Black"));
        board.getCeld(0, 1).setFigure( new Queen(new Position(0, 1), "Black"));
        board.getCeld(0, 2).setFigure( new Bishop(new Position(0, 2), "Black"));
        board.getCeld(0, 3).setFigure( new Rook(new Position(0, 3), "Black"));
        
        board.getCeld(4, 0).setFigure( new Pawn(new Position(4, 0), "White"));
        board.getCeld(4, 1).setFigure( new Pawn(new Position(4, 1), "White"));
        board.getCeld(4, 2).setFigure( new Pawn(new Position(4, 2), "White"));
        board.getCeld(4, 3).setFigure( new Pawn(new Position(4, 3), "White"));
        board.getCeld(5, 0).setFigure( new Rook(new Position(5, 0), "White"));
        board.getCeld(5, 1).setFigure( new Bishop(new Position(5, 1), "White"));
        board.getCeld(5, 2).setFigure( new Queen(new Position(5, 2), "White"));
        board.getCeld(5, 3).setFigure( new King(new Position(5, 3), "White"));
        
    }
    private void load8x6(){
        board.getCeld(1, 0).setFigure( new Pawn(new Position(1, 0), "Black"));
        board.getCeld(1, 1).setFigure( new Pawn(new Position(1, 1), "Black"));
        board.getCeld(1, 2).setFigure( new Pawn(new Position(1, 2), "Black"));
        board.getCeld(1, 3).setFigure( new Pawn(new Position(1, 3), "Black"));
        board.getCeld(1, 4).setFigure( new Pawn(new Position(1, 4), "Black"));
        board.getCeld(1, 5).setFigure( new Pawn(new Position(1, 5), "Black"));
        board.getCeld(0, 0).setFigure( new Rook(new Position(0, 0), "Black"));
        board.getCeld(0, 1).setFigure( new Bishop(new Position(0, 1), "Black"));
        board.getCeld(0, 2).setFigure( new King(new Position(0, 2), "Black"));
        board.getCeld(0, 3).setFigure( new Queen(new Position(0, 3), "Black"));
        board.getCeld(0, 4).setFigure( new Bishop(new Position(0, 4), "Black"));
        board.getCeld(0, 5).setFigure( new Rook(new Position(0, 5), "Black"));
        
        board.getCeld(7, 0).setFigure( new Rook(new Position(7, 0), "White"));
        board.getCeld(7, 1).setFigure( new Bishop(new Position(7, 1), "White"));
        board.getCeld(7, 2).setFigure( new Queen(new Position(7, 2), "White"));
        board.getCeld(7, 3).setFigure( new King(new Position(7, 3), "White"));
        board.getCeld(7, 4).setFigure( new Bishop(new Position(7, 4), "White"));
        board.getCeld(7, 5).setFigure( new Rook(new Position(7, 5), "White"));
        board.getCeld(6, 0).setFigure( new Pawn(new Position(6, 0), "White"));
        board.getCeld(6, 1).setFigure( new Pawn(new Position(6, 1), "White"));
        board.getCeld(6, 2).setFigure( new Pawn(new Position(6, 2), "White"));
        board.getCeld(6, 3).setFigure( new Pawn(new Position(6, 3), "White"));
        board.getCeld(6, 4).setFigure( new Pawn(new Position(6, 4), "White"));
        board.getCeld(6, 5).setFigure( new Pawn(new Position(6, 5), "White"));
    }

    private void load8x8() {
        //Peones negros
        board.getCeld(1, 0).setFigure( new Pawn(new Position(1, 0), "Black"));
        board.getCeld(1, 1).setFigure( new Pawn(new Position(1, 1), "Black"));
        board.getCeld(1, 2).setFigure( new Pawn(new Position(1, 2), "Black"));
        board.getCeld(1, 3).setFigure( new Pawn(new Position(1, 3), "Black"));
        board.getCeld(1, 4).setFigure( new Pawn(new Position(1, 4), "Black"));
        board.getCeld(1, 5).setFigure( new Pawn(new Position(1, 5), "Black"));
        board.getCeld(1, 6).setFigure( new Pawn(new Position(1, 6), "Black"));
        board.getCeld(1, 7).setFigure( new Pawn(new Position(1, 7), "Black"));
        //Figuras negras
        board.getCeld(0, 0).setFigure( new Rook(new Position(0, 0), "Black"));
        board.getCeld(0, 2).setFigure( new Bishop(new Position(0, 2), "Black"));
        board.getCeld(0, 1).setFigure( new Knight(new Position(0, 1), "Black"));
        board.getCeld(0, 3).setFigure( new Queen(new Position(0, 3), "Black"));
        board.getCeld(0, 4).setFigure( new King(new Position(0, 4), "Black"));
        board.getCeld(0, 6).setFigure( new Knight(new Position(0, 6), "Black"));
        board.getCeld(0, 5).setFigure( new Bishop(new Position(0, 5), "Black"));
        board.getCeld(0, 7).setFigure( new Rook(new Position(0, 7), "Black"));
        
        //Figuras blancas
        board.getCeld(7, 0).setFigure( new Rook(new Position(7, 0), "White"));
        board.getCeld(7, 2).setFigure( new Bishop(new Position(7, 2), "White"));
        board.getCeld(7, 1).setFigure( new Knight(new Position(7, 1), "White"));
        board.getCeld(7, 3).setFigure( new Queen(new Position(7, 3), "White"));
        board.getCeld(7, 4).setFigure( new King(new Position(7, 4), "White"));
        board.getCeld(7, 6).setFigure( new Knight(new Position(7, 6), "White"));
        board.getCeld(7, 5).setFigure( new Bishop(new Position(7, 5), "White"));
        board.getCeld(7, 7).setFigure( new Rook(new Position(7, 7), "White"));
        //Peones blancos
        board.getCeld(6, 0).setFigure( new Pawn(new Position(6, 0), "White"));
        board.getCeld(6, 1).setFigure( new Pawn(new Position(6, 1), "White"));
        board.getCeld(6, 2).setFigure( new Pawn(new Position(6, 2), "White"));
        board.getCeld(6, 3).setFigure( new Pawn(new Position(6, 3), "White"));
        board.getCeld(6, 4).setFigure( new Pawn(new Position(6, 4), "White"));
        board.getCeld(6, 5).setFigure( new Pawn(new Position(6, 5), "White"));
        board.getCeld(6, 6).setFigure( new Pawn(new Position(6, 6), "White"));
        board.getCeld(6, 7).setFigure( new Pawn(new Position(6, 7), "White"));
    }
}
