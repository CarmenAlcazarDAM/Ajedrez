package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private List<Pieza> blancas = new ArrayList<>();
    private List<Pieza> negras = new ArrayList<>();
    private List<Pieza> eliminadas = new ArrayList<>();

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

}
