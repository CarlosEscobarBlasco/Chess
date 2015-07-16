
package IA.Players;

import IA.Player;
import java.util.Scanner;
import model.Action;
import model.Board;
import model.Position;

public class ConsoleHumanPlayer implements Player{

    @Override
    public Action makeDecision() {
        String from, to;
        Scanner leer = new Scanner(System.in);
        while(true){
        System.out.println("Inserte la posicion de la figura a mover");
            from = leer.nextLine();
            if (from.length() != 3) {
                System.out.println("Inserte correctamente la direccion");
            } else {
                System.out.println("Inserte la posicion destino");
                to = leer.nextLine();
                if (to.length() != 3) {
                    System.out.println("Inserte correctamente la direccion");
                } else {
                    return new Action(new Position(from),new Position(to));
                }
            }
        }
    }

}
