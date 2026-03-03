package controladores;

import modelo.*;

public class ControllerTablero {

    public void colocarPiezas() {
        // Colocar piezas blancas
        colocarPiezasBlancas();

        // Colocar piezas negras
        colocarPiezasNegras();
    }

    private void colocarPiezasBlancas() {
        // Peones blancos en fila 1
        for (int columna = 0; columna < 8; columna++) {
            Tablero.getBlancas().add(new Peon(Pieza.Color.Blanca, "♙", 1, columna, 1));
        }

        // Torres blancas
        Tablero.getBlancas().add(new Torre(Pieza.Color.Blanca, "♖", 0, 0, 5));
        Tablero.getBlancas().add(new Torre(Pieza.Color.Blanca, "♖", 0, 7, 5));

        // Caballos blancos
        Tablero.getBlancas().add(new Caballo(Pieza.Color.Blanca, "♘", 0, 1, 3));
        Tablero.getBlancas().add(new Caballo(Pieza.Color.Blanca, "♘", 0, 6, 3));

        // Alfiles blancos
        Tablero.getBlancas().add(new Alfil(Pieza.Color.Blanca, "♗", 0, 2, 3));
        Tablero.getBlancas().add(new Alfil(Pieza.Color.Blanca, "♗", 0, 5, 3));

        // Reina blanca
        Tablero.getBlancas().add(new Reina(Pieza.Color.Blanca, "♕", 0, 3, 9));

        // Rey blanco
        Tablero.getBlancas().add(new Rey(Pieza.Color.Blanca, "♔", 0, 4, 100));
    }

    private void colocarPiezasNegras() {
        // Peones negros en fila 6
        for (int columna = 0; columna < 8; columna++) {
            Tablero.getNegras().add(new Peon(Pieza.Color.Negra, "♟", 6, columna, 1));
        }

        // Torres negras
        Tablero.getNegras().add(new Torre(Pieza.Color.Negra, "♜", 7, 0, 5));
        Tablero.getNegras().add(new Torre(Pieza.Color.Negra, "♜", 7, 7, 5));

        // Caballos negros
        Tablero.getNegras().add(new Caballo(Pieza.Color.Negra, "♞", 7, 1, 3));
        Tablero.getNegras().add(new Caballo(Pieza.Color.Negra, "♞", 7, 6, 3));

        // Alfiles negros
        Tablero.getNegras().add(new Alfil(Pieza.Color.Negra, "♝", 7, 2, 3));
        Tablero.getNegras().add(new Alfil(Pieza.Color.Negra, "♝", 7, 5, 3));

        // Reina negra
        Tablero.getNegras().add(new Reina(Pieza.Color.Negra, "♛", 7, 3, 9));

        // Rey negro
        Tablero.getNegras().add(new Rey(Pieza.Color.Negra, "♚", 7, 4, 100));
    }
}
