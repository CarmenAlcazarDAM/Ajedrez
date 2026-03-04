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
    public static void vaciarPiezas() {
        blancas.clear();
        negras.clear();
        eliminadas.clear();
    }

    /**
     * metodo que verifica si una casilla está ocupada
     * @param nuevaFila fila que se recibe para verificar si la casilla está en la fila
     * @param nuevaColumna columna que se recibe para verificar si la casilla está en la columna
     * @return null si está vacio o la pieza concreta que ocupe la casilla
     */
    public Pieza obtenerPiezaEnCasilla(int nuevaFila,int nuevaColumna){
        for ( Pieza p : blancas ) {
            if (p.getFila() == nuevaFila && p.getColumna() == nuevaColumna) {
                return p;
            }
        }
        for ( Pieza p : negras ) {
            if (p.getFila() == nuevaFila && p.getColumna() == nuevaColumna) {
                return p;
            }
        }
        return null;

    }


    public boolean hayPiezaIntermedia(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino){
        boolean hayPieza=false;
        for (Pieza p : blancas){
            if (p.getFila()+p.getColumna()==obtenerPiezaEnCasilla(filaDestino,columnaDestino).getColumna()+obtenerPiezaEnCasilla(filaDestino,columnaDestino).getFila())return true;
            if ((p.getFila()+1 + p.getColumna()+1 == filaOrigen+1 + columnaOrigen+1) && ){

            }


        }
        return hayPieza;

    }




}

