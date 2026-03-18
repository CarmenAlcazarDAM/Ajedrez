package modelo;

public class Torre extends Pieza{
public Torre(){
    super();

}
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
        boolean legal = false;

        if (fila == 0 || columna == 0){
            legal = true;
        }
        return legal;
    }

    @Override
    public Pieza clonar() {
        return new Torre(this.getColor(),this.getDibujo(),this.getFila(),this.getColumna(),this.getPuntos());
    }
}
