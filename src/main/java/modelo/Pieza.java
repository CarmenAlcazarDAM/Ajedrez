package modelo;

import Interfaces.Saltadora;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;


@XmlRootElement(name="pieza")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Pieza {

    public enum Color {
        BLANCA,
        NEGRA
    }

    private Color color;
    private String dibujo;
    private int fila;
    private int columna;
    private int puntos;

    public abstract void mover(int nuevaFila, int nuevaColumna);

    public abstract boolean comprobarMovimiento(int fila, int columna);

    //Constructor por defecto necesario para serializar en JaxB
    public Pieza(){}

    public Pieza(Color color, String dibujo, int fila, int columna, int puntos) {
        filaValida(fila);
        columnaValida(columna);

        this.color = color;
        this.dibujo = dibujo;
        this.fila = fila;
        this.columna = columna;
        this.puntos=puntos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getDibujo() {
        return dibujo;
    }

    public void setDibujo(String dibujo) {
        this.dibujo = dibujo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        filaValida(fila);
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }



    public void setColumna(int columna) {
        columnaValida(columna);
        this.columna = columna;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName().toUpperCase()+"] " + this.dibujo + " Posición: ("+ this.fila
                + "," + this.columna+") | Puntos: " + this.puntos;
    }

    /*-------------------------------------*/

    /**
     * Método que valida si el número de fila introducido es correcto
     * @param fila --> número de fila a validar
     */
    private void filaValida(int fila) {
        if (fila < 0 || fila > 7) {
            throw new IllegalArgumentException("La posición debe estar entre 0 y 7");
        }
    }

    /**
     * Método que valida si el número de columna introducido es correcto
     * @param columna --> número de columna a validar
     */
    private void columnaValida(int columna) {
        if (columna < 0 || columna > 7) {
            throw new IllegalArgumentException("La posición debe estar entre 0 y 7");
        }
    }

    /*-------------------------------------*/

    //Una pieza será igual que otra si tiene el mismo color y puntos
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pieza pieza = (Pieza) o;
        return puntos == pieza.puntos && color == pieza.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, puntos);
    }


    /*-------------------------------------*/

    /**
     * Método que valida si la pieza seleccionada puede moverse a la casilla de destino introducida,
     * comprobando si hay una pieza del mismo color o es el rey
     * @param nuevaFila --> número de fila donde está la casilla de destino seleccionada
     * @param nuevaColumna --> número de columna donde está la casilla de destino seleccionada
     * @param tablero --> clase donde se encuentran todas las casillas
     */
    public void validarDestino(int nuevaFila, int nuevaColumna, Tablero tablero){
        Pieza piezaEnCasillaDestino = tablero.obtenerPiezaEnCasilla(nuevaFila, nuevaColumna);

            if(piezaEnCasillaDestino!=null && piezaEnCasillaDestino.getColor()==this.color){
                throw new IllegalArgumentException("No puedes matar una pieza de tu propio color.");
            }

            if(piezaEnCasillaDestino instanceof Rey){
                throw new IllegalArgumentException("No se permite capturar al Rey.");
            }

    }

    /**
     * Método que comprueba si una pieza puede ser movida a una casilla de destino si la casilla está vacía o no hay pieza
     * del mismo color, si el movmiento es legal o si no hay una pieza intermedia en caso de no ser una pieza saltadora
     * @param nuevaFila --> número de fila donde está la casilla de destino seleccionada
     * @param nuevaColumna --> número de columna donde está la casilla de destino seleccionada
     * @param tablero --> clase donde se encuentran todas las casillas
     * @return --> devuelve true si puede moverse a la casilla de destino, true si no puede moverse
     */
    public boolean puedeMover(int nuevaFila, int nuevaColumna, Tablero tablero){
        Pieza piezaEnCasillaDestino = tablero.obtenerPiezaEnCasilla(nuevaFila, nuevaColumna);

        if(piezaEnCasillaDestino!=null && piezaEnCasillaDestino.getColor()==this.color){return false;}

        if(!comprobarMovimiento(nuevaFila,nuevaColumna)){ return false;}

        if(!(this instanceof Saltadora)){
           if(tablero.hayPiezaIntermedia(this.fila, this.columna, nuevaFila,nuevaColumna)){return false;}
        }
        return true;
    }

}
