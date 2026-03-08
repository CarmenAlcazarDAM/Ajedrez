package modelo;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
    private static List<Pieza> blancas = new ArrayList<>();
    private static List<Pieza> negras = new ArrayList<>();
    private static List<Pieza> eliminadas = new ArrayList<>();
    private static List<Pieza> posicionInicial = new ArrayList<>();

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
     *
     * @param nuevaFila    fila que se recibe para verificar si la casilla está en la fila
     * @param nuevaColumna columna que se recibe para verificar si la casilla está en la columna
     * @return null si está vacio o la pieza concreta que ocupe la casilla
     */
    public Pieza obtenerPiezaEnCasilla(int nuevaFila, int nuevaColumna) {
        for (Pieza p : blancas) {
            if (p.getFila() == nuevaFila && p.getColumna() == nuevaColumna) {
                return p;
            }
        }
        for (Pieza p : negras) {
            if (p.getFila() == nuevaFila && p.getColumna() == nuevaColumna) {
                return p;
            }
        }
        return null;

    }

    /**
     * metodo que devuelve true si encuentra una ficha en el camino indicado
     *
     * @param filaOrigen     forma la posicion de origen junto con columnaOrigen
     * @param columnaOrigen  forma la posicion de origen junto con filaOrigen
     * @param filaDestino    forma la posicion de destino junto con columnaDestino
     * @param columnaDestino forma la posicion de destino junto con filaDestino
     * @return true si encuentra una pieza intermedia, false si no encuentra ninguna pieza intermedia
     */
    public boolean hayPiezaIntermedia(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

        int filaDireccion = Integer.compare(filaDestino, filaOrigen);
        int columnaDireccion = Integer.compare(columnaDestino, columnaOrigen);
        int filaActual = filaOrigen + filaDireccion;
        int columnaActual = columnaOrigen + columnaDireccion;


        while (filaActual != filaDestino + filaDireccion || columnaActual != columnaDestino + columnaDireccion) {
            if (obtenerPiezaEnCasilla(filaActual, columnaActual) != null) {
                return true;
            }
            filaActual += filaDireccion;
            columnaActual += columnaDireccion;
        }

        return false;
    }

    /**
     * Método que devuelve impreso el contenido del ArrayList blancas
     */
    public void listarBlancas() {
        System.out.println("\nListado de BLANCAS:");
        for (Pieza p : blancas) {
            System.out.println(p.toString());

        }
    }

    /**
     * Método que devuelve impreso el contenido del ArrayList negras
     */
    public void listarNegras() {
        System.out.println("\nListado de NEGRAS:");
        for (Pieza p : negras) {
            System.out.println(p.toString());

        }
    }

    /**
     * Método que devuelve impreso el contenido del ArrayList eliminadas
     */
    public void listarEliminadas() {
        System.out.println("\nELIMINADAS:");
        if (eliminadas.size() == 0) {
            System.out.println("Actualmente no hay piezas eliminadas");
        } else {
            for (Pieza p : eliminadas) {
                System.out.println(p.toString());

            }
        }
    }

    public int obtenerPuntuacionBlancas(){
        int suma = 0;
        for (Pieza p : blancas){
            suma += p.getPuntos();
        }
        return suma;
    }

    public int obtenerPuntuacionNegras(){
        int suma = 0;
        for (Pieza p : negras){
            suma += p.getPuntos();
        }
        return suma;
    }

}

