package modelo;

public class Peon extends Pieza {
    private boolean primerMovimiento = true;
    private int contador = 0;
    public Peon(Color color, String dibujo, int fila, int columna, int puntos) {
        super(color, dibujo, fila, columna, puntos);
    }

    @Override
    public void mover(int nuevaFila, int nuevaColumna) {
        if (this.comprobarMovimiento(nuevaFila,nuevaColumna)){
            this.setColumna(nuevaColumna);
            this.setFila(nuevaFila);
        }
    }

    @Override
    public boolean comprobarMovimiento(int fila, int columna) {
        int distanciaFila = getFila() - fila;
        int distanciaColum = getColumna() - columna;
        boolean legal = false;

        if (this.getColor() == Color.BLANCA){
            if (distanciaFila == 1 & distanciaColum == 0){
                legal = true;
            }
        }else {
            if (distanciaFila == -1 & distanciaColum == 0){
                legal = true;
            }
        }
        return legal;
    }
}
