package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private static List<Pieza> blancas = new ArrayList<>();
    private static List<Pieza> negras = new ArrayList<>();
    private static List<Pieza> eliminadas = new ArrayList<>();
    private static List<Pieza> posicionInicial=new ArrayList<>();

    public static List<Pieza> getBlancas() {
        return blancas;
    }

    public void setBlancas(List<Pieza> blancas) {
        Tablero.blancas = blancas;
    }

    public static List<Pieza> getNegras() {
        return negras;
    }

    public void setNegras(List<Pieza> negras) {
        Tablero.negras = negras;
    }

    public static List<Pieza> getEliminadas() {
        return eliminadas;
    }

}
