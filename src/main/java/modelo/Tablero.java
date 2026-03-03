package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private List<Pieza> blancas = new ArrayList<>();
    private List<Pieza> negras = new ArrayList<>();
    private List<Pieza> eliminadas = new ArrayList<>();
    private List<Pieza> posicionInicial=new ArrayList<>();

    public List<Pieza> getBlancas() {
        return blancas;
    }

    public void setBlancas(List<Pieza> blancas) {
        this.blancas = blancas;
    }

    public List<Pieza> getNegras() {
        return negras;
    }

    public void setNegras(List<Pieza> negras) {
        this.negras = negras;
    }

    public List<Pieza> getEliminadas() {
        return eliminadas;
    }

    public void setEliminadas(List<Pieza> eliminadas) {
        this.eliminadas = eliminadas;
    }
    public void colocarPiezas() {
        // Colocar piezas blancas
        colocarPiezasBlancas();

        // Colocar piezas negras
        colocarPiezasNegras();
    }

    private void colocarPiezasBlancas() {
        // Peones blancos en fila 1
        for (int columna = 0; columna < 8; columna++) {
            blancas.add(new Peon(Pieza.Color.Blanca, "♙", 1, columna, 0));
        }

        // Torres blancas
        blancas.add(new Torre(Pieza.Color.Blanca, "♖", 0, 0, 0));
        blancas.add(new Torre(Pieza.Color.Blanca, "♖", 0, 7, 0));

        // Caballos blancos
        blancas.add(new Caballo(Pieza.Color.Blanca, "♘", 0, 1, 0));
        blancas.add(new Caballo(Pieza.Color.Blanca, "♘", 0, 6, 0));

        // Alfiles blancos
        blancas.add(new Alfil(Pieza.Color.Blanca, "♗", 0, 2, 0));
        blancas.add(new Alfil(Pieza.Color.Blanca, "♗", 0, 5, 0));

        // Reina blanca
        blancas.add(new Reina(Pieza.Color.Blanca, "♕", 0, 3, 0));

        // Rey blanco
        blancas.add(new Rey(Pieza.Color.Blanca, "♔", 0, 4, 0));
    }

    private void colocarPiezasNegras() {
        // Peones negros en fila 6
        for (int columna = 0; columna < 8; columna++) {
            negras.add(new Peon(Pieza.Color.Negra, "♟", 6, columna, 0));
        }

        // Torres negras
        negras.add(new Torre(Pieza.Color.Negra, "♜", 7, 0, 0));
        negras.add(new Torre(Pieza.Color.Negra, "♜", 7, 7, 0));

        // Caballos negros
        negras.add(new Caballo(Pieza.Color.Negra, "♞", 7, 1, 0));
        negras.add(new Caballo(Pieza.Color.Negra, "♞", 7, 6, 0));

        // Alfiles negros
        negras.add(new Alfil(Pieza.Color.Negra, "♝", 7, 2, 0));
        negras.add(new Alfil(Pieza.Color.Negra, "♝", 7, 5, 0));

        // Reina negra
        negras.add(new Reina(Pieza.Color.Negra, "♛", 7, 3, 0));

        // Rey negro
        negras.add(new Rey(Pieza.Color.Negra, "♚", 7, 4, 0));
    }



}
