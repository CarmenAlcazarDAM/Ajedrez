package modelo;

import Interfaces.Atacadora;

public class Peon extends Pieza implements Atacadora {
    private boolean primerMovimiento = true;
    private int contador = 0;

    public Peon() {
        super();
    }

    public Peon(Color color, String dibujo, int fila, int columna, int puntos) {
        super(color, dibujo, fila, columna, puntos);
    }

    @Override
    public void mover(int nuevaFila, int nuevaColumna) {
        if (this.comprobarMovimiento(nuevaFila,nuevaColumna)){
            this.setColumna(nuevaColumna);
            this.setFila(nuevaFila);
            this.primerMovimiento = false;
        }
    }

    @Override
    public boolean comprobarMovimiento(int fila, int columna) {
        int distanciaFila = getFila() - fila;
        int distanciaColum = getColumna() - columna;
        boolean legal = false;
        Tablero t = new Tablero();

        if (t.obtenerPiezaEnCasilla(fila,columna) == null){
            if (this.getColor() == Color.BLANCA){
                if (distanciaFila == -1 && distanciaColum == 0){
                    legal = true;
                }
                if (primerMovimiento && distanciaFila == -2 && distanciaColum == 0){
                    legal = true;
                }
            }else {
                if (distanciaFila == 1 && distanciaColum == 0){
                    legal = true;
                }
                if (primerMovimiento && distanciaFila == 2 && distanciaColum == 0){
                    legal = true;
                }
            }
        }
        return legal;
    }

    @Override
    public void ataque(int fila, int columna) {
        int distanciaFila = this.getFila() - fila;
        int distanciaColum = this.getColumna() - columna;

        if (this.getColor() == Color.BLANCA){
            if(distanciaFila == 1 && (distanciaColum == 1 || distanciaColum == -1)){
                this.setFila(fila);
                this.setColumna(columna);
            }
        }
        if (this.getColor() == Color.NEGRA){
            if (distanciaFila == -1 && (distanciaColum == -1 || distanciaColum == 1)){
                this.setFila(fila);
                this.setColumna(columna);
            }
        }

    }

    @Override
    public Pieza clonar() {
        Peon clon = new Peon(this.getColor(), this.getDibujo(), this.getFila(),this.getColumna(),this.getPuntos());
        clon.primerMovimiento = this.primerMovimiento;
        clon.contador = this.contador;

        return clon;
    }

    
}
