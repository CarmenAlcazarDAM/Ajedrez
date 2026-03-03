package modelo;

public class Alfil extends Pieza{

    public Alfil(Color color, String dibujo, int fila, int columna, int puntos) {
        super(color, dibujo, fila, columna, puntos);
    }

    @Override
    public void mover(int nuevaFila, int nuevaColumna) {
        int distanciaFila = nuevaFila - this.getFila();
        int distanciaColum = nuevaColumna - this.getColumna();

        if (distanciaColum < 0){
            distanciaColum = distanciaColum * -1;
        }
        if (distanciaFila < 0){
            distanciaFila = distanciaFila * -1;
        }

        if (distanciaFila == distanciaColum && distanciaColum != 0){
            this.setFila(nuevaFila);
            this.setColumna(nuevaColumna);
        } else {
            System.out.println("El movimiento es invalido");
        }

    }
}
