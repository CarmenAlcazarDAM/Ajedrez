package modelo;

public class Peon extends Pieza {
    private boolean primerMovimiento = true;

    public Peon(Color color, String dibujo, int fila, int columna, int puntos) {
        super(color, dibujo, fila, columna, puntos);
    }

    @Override
    public void mover(int nuevaFila, int nuevaColumna) {
        int distanciaFila = nuevaFila - this.getFila();
        int distanciaColum = nuevaColumna - this.getColumna();

        boolean legal = false;

        if (this.getColor() == Color.Blanca) {
            if (distanciaFila == 1 & distanciaColum == 0) {
                legal = true;
            }
        } else {
            if (distanciaFila == -1 & distanciaColum == 0) {
                legal = true;
            }
        }

        if (legal){
            this.setFila(nuevaFila);
            this.setColumna(nuevaColumna);
        } else {
            System.out.println("El movimiento es invalido");
        }
    }
}
