package modelo;

public class Rey extends Pieza{

    public Rey(Color color, String dibujo, int fila, int columna, int puntos) {
        super(color, dibujo, fila, columna, puntos);
    }

    @Override
    public void mover(int nuevaFila, int nuevaColumna) {
        if (this.comprobarMovimiento(nuevaFila,nuevaColumna)){
            this.setFila(nuevaFila);
            this.setColumna(nuevaColumna);
        }
    }

    @Override
    public boolean comprobarMovimiento(int fila, int columna) {
        int distanciaFila = getFila() - fila;
        int distanciaColum = getColumna() - columna;
        boolean legal = true;

        if (distanciaFila < 0){
            distanciaFila = distanciaFila * -1;
        }
        if (distanciaColum < 0){
            distanciaColum = distanciaColum * -1;
        }

        if (distanciaFila >= 2 || distanciaColum >= 2){
            legal = false;
        }

        return legal;
    }
}
