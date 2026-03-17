package modelo;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="tablero")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tablero {
    private  List<Pieza> blancas = new ArrayList<>();
    private  List<Pieza> negras = new ArrayList<>();
    private  List<Pieza> eliminadas = new ArrayList<>();
    private  List<Pieza> posicionInicial = new ArrayList<>();
    private  int contadorTurnos=1;

    /* ///////////////// CONSTRUCTOR VACÍO POR DEFECTO ///////////////// */
    public Tablero() {

    }

    public  List<Pieza> getBlancas() {
        return blancas;
    }

    public void setBlancas(List<Pieza> blancas) {
        this.blancas = blancas;
    }

    public  List<Pieza> getNegras() {
        return negras;
    }

    public void setNegras(List<Pieza> negras) {
        this.negras = negras;
    }

    public  List<Pieza> getEliminadas() {
        return eliminadas;
    }

    public int getContadorTurnos() {
        return contadorTurnos;
    }

    public void setContadorTurnos(int contadorTurnos) {
        this.contadorTurnos = contadorTurnos;
    }


    /**
     * Elimina todas las piezas de los arrays
     */
    public  void vaciarPiezas() {
        blancas.clear();
        negras.clear();
        eliminadas.clear();
    }

    /**
     * Coloca todas las piezas en el tablero
     */
    public  void colocarPiezas() {
        // Colocar piezas blancas
        colocarPiezasBlancas();

        // Colocar piezas negras
        colocarPiezasNegras();
    }

    /**
     * Crea y configura todas las piezas blancas.
     */
    public  void colocarPiezasBlancas() {
        // Peones blancos en fila 1
        for (int columna = 0; columna < 8; columna++) {
            blancas.add(new Peon(Pieza.Color.BLANCA, "♙", 1, columna, 1));
        }

        // Torres blancas
        blancas.add(new Torre(Pieza.Color.BLANCA, "♖", 0, 0, 5));
        blancas.add(new Torre(Pieza.Color.BLANCA, "♖", 0, 7, 5));

        // Caballos blancos
        blancas.add(new Caballo(Pieza.Color.BLANCA, "♘", 0, 1, 3));
        blancas.add(new Caballo(Pieza.Color.BLANCA, "♘", 0, 6, 3));

        // Alfiles blancos
        blancas.add(new Alfil(Pieza.Color.BLANCA, "♗", 0, 2, 3));
        blancas.add(new Alfil(Pieza.Color.BLANCA, "♗", 0, 5, 3));

        // Reina blanca
        blancas.add(new Reina(Pieza.Color.BLANCA, "♕", 0, 3, 9));

        // Rey blanco
        blancas.add(new Rey(Pieza.Color.BLANCA, "♔", 0, 4, 100));
    }

    /**
     * Crea y configura todas las piezas negras.
     */
    private void colocarPiezasNegras() {
        // Peones negros en fila 6
        for (int columna = 0; columna < 8; columna++) {
            negras.add(new Peon(Pieza.Color.NEGRA, "♟", 6, columna, 1));
        }

        // Torres negras
        negras.add(new Torre(Pieza.Color.NEGRA, "♜", 7, 0, 5));
        negras.add(new Torre(Pieza.Color.NEGRA, "♜", 7, 7, 5));

        // Caballos negros
        negras.add(new Caballo(Pieza.Color.NEGRA, "♞", 7, 1, 3));
        negras.add(new Caballo(Pieza.Color.NEGRA, "♞", 7, 6, 3));

        // Alfiles negros
        negras.add(new Alfil(Pieza.Color.NEGRA, "♝", 7, 2, 3));
        negras.add(new Alfil(Pieza.Color.NEGRA, "♝", 7, 5, 3));

        // Reina negra
        negras.add(new Reina(Pieza.Color.NEGRA, "♛", 7, 3, 9));

        // Rey negro
        negras.add(new Rey(Pieza.Color.NEGRA, "♚", 7, 4, 100));
    }

    /**
     * Inicializa el tablero para una nueva partida. Limpia el estado previo de los arrays, vuelve a colocar
     * todas las piezas en sus casillas iniciales y reinicia el contador
     */
    public void iniciarTablero() {
        vaciarPiezas();
        colocarPiezas();
        setContadorTurnos(1);
    }

    /**
     * Método que verifica si una casilla está ocupada
     *
     * @param nuevaFila    fila que se recibe para verificar si la casilla está en la fila
     * @param nuevaColumna columna que se recibe para verificar si la casilla está en la columna
     * @return null si está vacio o la pieza concreta que ocupe la casilla
     */
    public Pieza obtenerPiezaEnCasilla(int nuevaFila, int nuevaColumna) {
        for (Pieza p : blancas) {
            if (p.getFila() == nuevaFila && p.getColumna() == nuevaColumna) {
                return p;
            }
        }
        for (Pieza p : negras) {
            if (p.getFila() == nuevaFila && p.getColumna() == nuevaColumna) {
                return p;
            }
        }
        return null;

    }

    /**
     * Método que devuelve true si encuentra una ficha en el camino indicado
     *
     * @param filaOrigen     forma la posicion de origen junto con columnaOrigen
     * @param columnaOrigen  forma la posicion de origen junto con filaOrigen
     * @param filaDestino    forma la posicion de destino junto con columnaDestino
     * @param columnaDestino forma la posicion de destino junto con filaDestino
     * @return true si encuentra una pieza intermedia, false si no encuentra ninguna pieza intermedia
     */
    public boolean hayPiezaIntermedia(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {

        int filaDireccion = Integer.compare(filaDestino, filaOrigen);
        int columnaDireccion = Integer.compare(columnaDestino, columnaOrigen);
        int filaActual = filaOrigen + filaDireccion;
        int columnaActual = columnaOrigen + columnaDireccion;


        while (filaActual != filaDestino || columnaActual != columnaDestino) {
            if (obtenerPiezaEnCasilla(filaActual, columnaActual) != null) {
                return true;
            }
            filaActual += filaDireccion;
            columnaActual += columnaDireccion;
        }

        return false;
    }

    /**
     * Método que devuelve impreso el contenido del ArrayList blancas
     */
    public void listarBlancas() {
        System.out.println("\nListado de BLANCAS:");
        for (Pieza p : blancas) {
            System.out.println(p.toString());

        }
    }

    /**
     * Método que devuelve impreso el contenido del ArrayList negras
     */
    public void listarNegras() {
        System.out.println("\nListado de NEGRAS:");
        for (Pieza p : negras) {
            System.out.println(p.toString());

        }
    }

    /**
     * Método que devuelve impreso el contenido del ArrayList eliminadas
     */
    public void listarEliminadas() {
        System.out.println("\nELIMINADAS:");
        if (eliminadas.size() == 0) {
            System.out.println("Actualmente no hay piezas eliminadas");
        } else {
            for (Pieza p : eliminadas) {
                System.out.println(p.toString());

            }
        }
    }

    /**
     * Metodo que devuelve la suma total de las piezas blancas vivas
     * @return devuelve la suma total
     */
    public int obtenerPuntuacionBlancas(){
        int suma = 0;
        for (Pieza p : blancas){
            suma += p.getPuntos();
        }
        return suma;
    }

    /**
     * Metodo que devuelve la suma total de las piezas negras vivas
     * @return devuelve la suma total
     */
    public int obtenerPuntuacionNegras(){
        int suma = 0;
        for (Pieza p : negras){
            suma += p.getPuntos();
        }
        return suma;
    }

    /**
     * Metodo que inserta una pieza segun el color
     * @param p requiere de una pieza para poder insertarla
     */
    public void insertarPiezaenCasilla(Pieza p){
        if (p.getColor() == Pieza.Color.BLANCA){
            blancas.add(p);
        } else {
            negras.add(p);
        }
    }

    /**
     * Metodo que clona un tablero
     * @param t tablero del que se quiere sacar una copia
     * @return devuelve la copia del tablero
     */
    public Tablero clonarTablero (Tablero t){
        Tablero copia = new Tablero();
        copia.vaciarPiezas();

        copia.setContadorTurnos(t.getContadorTurnos());

        for (Pieza p : t.blancas) {
            copia.insertarPiezaenCasilla(p);
        }

        for (Pieza p : t.negras) {
            copia.insertarPiezaenCasilla(p);
        }

        return copia;
    }

    /**
     * Me traigo el tablero y el color de las piezas por parametro, hago un Arraylist de las piezas aliadas y enemigas
     * Luego determino si estamos jugando como blancas o como negras.
     * Busco el rey entre los aliados. ...
     * Luego compruebo si entre las piezas enemigas hay alguna que pueda mover a la posicion en la qu ese encuentra el rey aliado*
     *
     * @param tablero el tablero
     * @param color   el color de la pieza
     * @return si hay hay o no jaque
     */
    public boolean comprobarJaque(Tablero tablero, Pieza.Color color) {
        List<Pieza> enemiga;
        List<Pieza> aliadas;
        Pieza rey = null;

        if (color == Pieza.Color.BLANCA) {
            aliadas = getBlancas();
            enemiga = getNegras();
        } else {
            aliadas = getNegras();
            enemiga = getBlancas();
        }

        for (Pieza pieza : aliadas) if (pieza instanceof Rey) rey = pieza;

        if (rey == null) throw new RuntimeException("Rey no encontrado");

        for (Pieza pieza : enemiga) if (pieza.puedeMover(rey.getFila(), rey.getColumna(), tablero)) return true;

        return false;
    }

    /**
     * Elimina la pieza del array de su color correspondiente y la lleva al array de eliminadas
     * @param victima --> pieza que va a ser modificada y llevada al array de eliminadas
     */
    public void matarPieza(Pieza victima){

        if(victima!=null){
                eliminadas.add(victima);
            if(victima.getColor() == Pieza.Color.BLANCA){
                blancas.remove(victima);
            }else{
                negras.remove(victima);
            }

        }

    }

}

