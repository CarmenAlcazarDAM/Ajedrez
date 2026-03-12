package controladores;

import controladores.MenusNum.GestEstadoPartida;
import controladores.MenusNum.GestMATKEnum;
import modelo.*;

public class ControllerTablero {

    public static void colocarPiezas() {
        // Colocar piezas blancas
        colocarPiezasBlancas();

        // Colocar piezas negras
        colocarPiezasNegras();
    }

    private static void colocarPiezasBlancas() {
        // Peones blancos en fila 1
        for (int columna = 0; columna < 8; columna++) {
            Tablero.getBlancas().add(new Peon(Pieza.Color.BLANCA, "♙", 1, columna, 1));
        }

        // Torres blancas
        Tablero.getBlancas().add(new Torre(Pieza.Color.BLANCA, "♖", 0, 0, 5));
        Tablero.getBlancas().add(new Torre(Pieza.Color.BLANCA, "♖", 0, 7, 5));

        // Caballos blancos
        Tablero.getBlancas().add(new Caballo(Pieza.Color.BLANCA, "♘", 0, 1, 3));
        Tablero.getBlancas().add(new Caballo(Pieza.Color.BLANCA, "♘", 0, 6, 3));

        // Alfiles blancos
        Tablero.getBlancas().add(new Alfil(Pieza.Color.BLANCA, "♗", 0, 2, 3));
        Tablero.getBlancas().add(new Alfil(Pieza.Color.BLANCA, "♗", 0, 5, 3));

        // Reina blanca
        Tablero.getBlancas().add(new Reina(Pieza.Color.BLANCA, "♕", 0, 3, 9));

        // Rey blanco
        Tablero.getBlancas().add(new Rey(Pieza.Color.BLANCA, "♔", 0, 4, 100));
    }

    private static void colocarPiezasNegras() {
        // Peones negros en fila 6
        for (int columna = 0; columna < 8; columna++) {
            Tablero.getNegras().add(new Peon(Pieza.Color.NEGRA, "♟", 6, columna, 1));
        }

        // Torres negras
        Tablero.getNegras().add(new Torre(Pieza.Color.NEGRA, "♜", 7, 0, 5));
        Tablero.getNegras().add(new Torre(Pieza.Color.NEGRA, "♜", 7, 7, 5));

        // Caballos negros
        Tablero.getNegras().add(new Caballo(Pieza.Color.NEGRA, "♞", 7, 1, 3));
        Tablero.getNegras().add(new Caballo(Pieza.Color.NEGRA, "♞", 7, 6, 3));

        // Alfiles negros
        Tablero.getNegras().add(new Alfil(Pieza.Color.NEGRA, "♝", 7, 2, 3));
        Tablero.getNegras().add(new Alfil(Pieza.Color.NEGRA, "♝", 7, 5, 3));

        // Reina negra
        Tablero.getNegras().add(new Reina(Pieza.Color.NEGRA, "♛", 7, 3, 9));

        // Rey negro
        Tablero.getNegras().add(new Rey(Pieza.Color.NEGRA, "♚", 7, 4, 100));
    }

    public static void iniciarTablero() {
        Tablero.vaciarPiezas();
        controladores.ControllerTablero.colocarPiezas();
    }

    /**
     * Crear el switch que llame a los métodos correspondientes y generar el orden de la partida, imprimir, contar puntos...;
     *
     * @param opcion introduction pro el usuario
     */
    public static void gestionEstadoPartida(int opcion) {// ToDo
        switch (GestEstadoPartida.gestEstadoFromIndex(opcion)) {
            //Llamar para que imprima el menu ToDo
            case MOSTRAR_FICHAS_BLANCAS:
                Tablero.listarBlancas(); //necesito que sea estático o que este dentro de un metodo de estatico
                break;
            case MOSTRAR_FICHAS_NEGRAS:
                Tablero.listarNegras(); //necesito que sea estático o que este dentro de un metodo de estatico
                break;
            case ELIMINADAS:
                Tablero.listarEliminadas();//necesito que sea estático o que este dentro de un metodo de estatico
                break;
            case PUNTOS_FICHAS_BLANCAS:
                int puntosBlancas = Tablero.obtenerPuntuacionBlancas();//necesito que sea estático o que este dentro de un metodo de estatico
                // mensaje para imprimir los puntos ToDo
                break;
            case PUNTOS_FICHAS_NEGRAS:
                int puntoNegras = Tablero.obtenerPuntuacionNegras();//necesito que sea estático o que este dentro de un metodo de estatico
                // mensaje para imprimir los puntos ToDo
                break;
            case VOLVER:
                break; //menuDeJuego;
        }
    }

    public void gestionarMovimientosAtaques(int opcion, int filaPieza, int columnaPieza, int filaDestino, int columnaDestino, Tablero tablero) {
        switch (GestMATKEnum.gestorMATKFromIndex(opcion)) {
            //Llamar para que imprima el menu ToDo
            case MOVER:
                moverP(filaPieza, columnaPieza, filaDestino, columnaDestino, tablero);
                break;
            case ATACAR:
                esPeon(filaPieza, columnaPieza, filaDestino, columnaDestino, tablero);
                break;
            case VOLVER:
                break; //menuDeJuego;
        }
    }

    /**
     * Obtenemos la la pieza que de la casilla que ha introducido el usuario (en realidad la que metemos como parametro).
     * Validamos el destino, pero no devuelve nada, solo imprime un mensaje
     * Movemos la pieza.
     *
     * @param filaPieza    fila que introduzca el usuario
     * @param columnaPieza columna que introduzca el usuario
     * @param nuevaFila    fila del destino de la pieza
     * @param nuevaColumna columna del destino de la pieza
     * @param t            tablero
     */
    public void moverP(int filaPieza, int columnaPieza, int nuevaFila, int nuevaColumna, Tablero t) {
        Pieza p = t.obtenerPiezaEnCasilla(filaPieza, columnaPieza);
        p.validarDestino(nuevaFila, nuevaColumna, t);
        p.mover(nuevaFila, nuevaColumna);
    }

    /**
     * Obtenemos la la pieza que de la casilla que ha introducido el usuario (en realidad la que metemos como parametro).
     * comprobamos que la pieza sea un peon.
     * Si es peon, realiza el ataque en la fila columna destino.
     * sino, imprime un mensaje.
     *
     * @param filaPieza
     * @param columnaPieza
     * @param filaDestino
     * @param columnaDestino
     * @param t
     */
    public void esPeon(int filaPieza, int columnaPieza, int filaDestino, int columnaDestino, Tablero t) {
        Pieza p = t.obtenerPiezaEnCasilla(filaPieza, columnaPieza);
        if (p instanceof Peon peon) {
            peon.ataque(filaDestino, columnaDestino);
        } else System.out.println("Solo el peon puede realizar esta acción.");
    }


}
