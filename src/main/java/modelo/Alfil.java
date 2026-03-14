package modelo;

public class Alfil extends Pieza{
    public Alfil(){super();}
    public Alfil(Color color, String dibujo, int fila, int columna, int puntos) {
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
        int distanciaColum = this.getColumna() - columna;
        boolean legal = false;

        if (distanciaFila < 0){
            distanciaFila = distanciaFila * -1;
        }
        if (distanciaColum < 0){
            distanciaColum = distanciaColum * -1;
        }

        if (distanciaFila == distanciaColum){
            legal = true;
        }
        return legal;
    }

    @Override
    public Pieza clonar() {
        return new Alfil(this.getColor(),this.getDibujo(),this.getFila(),this.getColumna(),this.getPuntos());
    }
}
