
package IA.heuristics;

import model.Board;
import IA.Heuristic;
import model.Position;
import model.figures.King;

public class ProHeuristic implements Heuristic{

    @Override
    public double valorateBoard(Board board, String player) {
        double valoration=0;
        double val=0;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if(board.getCeld(i, j).getFigure()!=null){
                    //Cuenta la diferencia de figuras
                    if(board.getCeld(i, j).getFigure().getColour().equals(player)){
                        valoration = valoration + board.getCeld(i, j).getFigure().getPuntuation()*10;
                    }else{
                        valoration = valoration - board.getCeld(i, j).getFigure().getPuntuation()*10;
                    }
                    //Favorece dar jaque
                    if(board.getCeld(i, j).getFigure().getColour().equals(player)){
                        if(board.getCeld(i, j).getFigure().attackPuntuation(new Position(i,j), board)>=King.KING_VALUE){
                            valoration+=(((8+board.numberOfRivalKingMoves(player)*(-1))/7)+0.7);
                        }
                    }
                    //Favorece que el rey tuyo no suba
                    if(board.getCeld(i, j).getFigure().getColour().equals(player)&&board.getCeld(i, j).getFigure().getFigureName()=='K'){
                        if("White".equals(player)){
                            valoration+=i*0.09;
                        }else{
                            valoration+=i*(-0.09);
                        }
                    }
                    //Favorece que los peones suban
                    if(board.getCeld(i, j).getFigure().getColour().equals(player)&&board.getCeld(i, j).getFigure().getFigureName()=='P'){
                        if("Black".equals(player)){
                            valoration+=i*0.12;
                        }else{
                            valoration+=i*(-0.12);
                        }
                    }
                }
            }
        }
        valoration = valoration + board.getPossibleMoves().size()*0.15;
        return valoration;
    }

}
