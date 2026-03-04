package modelo;

public class Torre extends Pieza{

    public Torre(Color color, String dibujo, int fila, int columna, int puntos) {
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
        int distanciaFila = this.getFila() - fila;
        int distanciaColum = this.getColumna() - columna;
        boolean legal = false;

        if (distanciaColum == 0 || distanciaFila == 0){
            legal = true;
        }
        return legal;
    }
}
