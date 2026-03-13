package controladores;

import controladores.MenusNum.GestEstadoPartida;
import controladores.MenusNum.GestMATKEnum;
import modelo.*;
import vista.VistaConsola;

public class ControllerTablero {
    int filaPieza, columnaPieza, filaDestino, columnaDestino;
    private final static VistaConsola vista = new VistaConsola();
    private final Pieza pOrigen;
    private final Pieza pDestino;
    private final Tablero tab;

    public ControllerTablero(Tablero tablero) {
        this.pOrigen = tablero.obtenerPiezaEnCasilla(filaPieza, columnaPieza);
        this.pDestino = tablero.obtenerPiezaEnCasilla(filaDestino, columnaDestino);
        this.tab = tablero;
    }

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
        while (true) {
            vista.menuEstadoPartida();
            switch (GestEstadoPartida.gestEstadoFromIndex(opcion)) {
                case MOSTRAR_FICHAS_BLANCAS:
                    Tablero.listarBlancas();
                    break;
                case MOSTRAR_FICHAS_NEGRAS:
                    Tablero.listarNegras();
                    break;
                case ELIMINADAS:
                    Tablero.listarEliminadas();
                    vista.mostrarCabeceraEliminadasTotales();
                    break;
                case PUNTOS_FICHAS_BLANCAS:
                    int puntosBlancas = Tablero.obtenerPuntuacionBlancas();
                    // mensaje para imprimir los puntos ToDo
                    break;
                case PUNTOS_FICHAS_NEGRAS:
                    int puntoNegras = Tablero.obtenerPuntuacionNegras();
                    // mensaje para imprimir los puntos ToDo
                    break;
                case VOLVER:
                    return;
            }
        }

    }

    public void gestionarMovimientosAtaques(int opcion) {
        while (true) {
            esPeonMensaje();
            switch (GestMATKEnum.gestorMATKFromIndex(opcion)) {
                case MOVER -> moverP();
                case ATACAR -> esPeon();
                case VOLVER -> {
                    return;
                }
            }
        }

    }

    /**
     * Validamos el destino, pero no devuelve nada, solo imprime un mensaje
     * usamos el metodo movimiento correcto para imprimir el mensaje
     * Movemos la pieza si es true.
     */
    public void moverP() {
        pOrigen.validarDestino(filaDestino, columnaDestino, tab);
        VistaConsola.movimientoCorrectoOIncorrecto(pDestino == null);

        if (pDestino == null) pOrigen.mover(filaDestino, columnaDestino);
        VistaConsola.estiloTablero(tab);
    }

    /**
     * Comprobamos que la pieza sea un peon.
     * Si es peon, realiza el ataque en la fila columna destino.
     * si no, imprime un mensaje.
     */
    public void esPeon() {
        if (pOrigen instanceof Peon peon) peon.ataque(filaDestino, columnaDestino);
        else System.out.println("[!] Solo el [ peon ] puede realizar esta acción.");
    }

    public void esPeonMensaje() {
        if (pOrigen instanceof Peon) vista.menuSeleccionarCasillaPeon();
        else vista.menuSeleccionarCasilla();
    }
}
