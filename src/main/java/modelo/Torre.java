package modelo;

public class Torre extends Pieza{

    public Torre(Color color, String dibujo, int fila, int columna, int puntos) {
        super(color, dibujo, fila, columna, puntos);
    }

    @Override
    public void mover(int nuevaFila, int nuevaColumna) {

        if (nuevaFila == this.getFila() || nuevaColumna == this.getColumna()){
            this.setFila(nuevaFila);
            this.setColumna(nuevaColumna);
        } else {
            System.out.println("El movimiento es invalido");
        }
    }
}
