package modelo;

import Interfaces.Saltadora;

public class Caballo extends Pieza implements Saltadora {

    public Caballo(Color color, String dibujo, int fila, int columna, int puntos) {
        super(color, dibujo, fila, columna, puntos);
    }

    @Override
    public void mover(int nuevaFila, int nuevaColumna) {
        if (this.comprobarMovimiento(nuevaFila, nuevaColumna)){
            this.setFila(nuevaFila);
            this.setColumna(nuevaColumna);
        }
    }

    @Override
    public boolean comprobarMovimiento(int fila, int columna) {
        int distanciaFila = this.getFila() - fila;
        int distanciaColum = this.getColumna() -  columna;
        boolean legal = false;

        if (distanciaFila < 0){
            distanciaFila = distanciaFila * -1;
        }
        if (distanciaColum < 0){
            distanciaColum = distanciaColum * -1;
        }

        if ((distanciaColum == 2 || distanciaColum == 1) & (distanciaFila == 2 || distanciaFila == 1)){
            legal = true;
        }
        return legal;
    }
}
