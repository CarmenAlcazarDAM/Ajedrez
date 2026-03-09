package controladores;

import modelo.Pieza;
import modelo.Rey;
import modelo.Tablero;

import java.util.List;

public class JaqueAlRey {
    /**
     * Me traigo el tablero y el color de las piezas por parametro, hago un Arraylist de las piezas aliadas y enemigas
     * Luego determino si estamos jugando como blancas o como negras.
     * Busco el rey entre los aliados.
     * Luego compruebo si entre las piezas enemigas hay alguna que pueda mover a la posicion en la qu ese encuentra el rey aliado
     *
     * @param tablero
     * @param color
     * @return
     */
    public static boolean comprobarJaque(Tablero tablero, Pieza.Color color) {
        List<Pieza> enemiga;
        List<Pieza> aliadas;
        Pieza rey = null;

        if (color == Pieza.Color.BLANCA) {
            aliadas = Tablero.getBlancas();
            enemiga = Tablero.getNegras();
        } else {
            aliadas = Tablero.getNegras();
            enemiga = Tablero.getBlancas();
        }

        for (Pieza pieza : aliadas) if (pieza instanceof Rey) rey = pieza;

        if (rey == null)throw new RuntimeException("Rey no encontrado");

        for (Pieza pieza : enemiga) if(pieza.puedeMover(rey.getFila(), rey.getColumna(), tablero))return true;

        return false;
    }


}
