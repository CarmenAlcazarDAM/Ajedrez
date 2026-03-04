package modelo;

public class Reina extends Pieza{

    public Reina(Color color, String dibujo, int fila, int columna, int puntos) {
        super(color, dibujo, fila, columna, puntos);
    }

    @Override
    public boolean comprobarMovimiento(int fila, int columna) {
        return false;
    }

    @Override
    public void mover(int nuevaFila, int nuevaColumna) {
        int distanciaFila = nuevaFila - this.getFila();
        int distanciaColum = nuevaColumna - this.getColumna();


        
    }
}
