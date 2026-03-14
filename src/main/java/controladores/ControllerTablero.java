package controladores;

import controladores.MenusNum.GestEstadoPartida;
import controladores.MenusNum.GestMATKEnum;
import modelo.*;
import utils.Util;
import vista.VistaConsola;

public class ControllerTablero {
    Tablero tab;

    public ControllerTablero(Tablero tablero) {
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
     */
    public void gestionEstadoPartida() {
        VistaConsola.menuEstadoPartida();// ToDo
        switch (GestEstadoPartida.getGestEstadoPartida()) {
            case MOSTRAR_FICHAS_BLANCAS:
                Tablero.listarBlancas();
                break;
            case MOSTRAR_FICHAS_NEGRAS:
                Tablero.listarNegras();
                break;
            case ELIMINADAS:
                VistaConsola.mostrarCabeceraEliminadasTotales();
                Tablero.listarEliminadas();
                VistaConsola.imprimirLinea();
                break;
            case PUNTOS_FICHAS_NEGRAS:
                System.out.println("Puntos de las fichas negras: " + Tablero.obtenerPuntuacionNegras());
                break;
            case PUNTOS_FICHAS_BLANCAS:
                System.out.println("Puntos de las fichas blancas: " + Tablero.obtenerPuntuacionBlancas());
                break;
            case VOLVER:
                return;
        }
    }

    public void gestionarMovimientosAtaques() {
        Pieza pOrigen = mostrarMenuSegunPieza();
        if (pOrigen == null) return;

        switch (GestMATKEnum.getGestMATKEnum()) {
            case MOVER:
                ControllerPrincipal.mover(pOrigen, tab);
                break;
            case ATACAR:
                if (pOrigen instanceof Peon) ControllerPrincipal.atacaPeon(pOrigen, tab);
                else System.out.println("Solo los peones pueden usar la opción atacar.");
                break;
            case VOLVER:
                return;
        }
    }

    // MEDIDAS TEMPORALES HASTA QUE ESTE HECHA LA LÓGICA
    // ############### CONTIENE SPOILERS #####################
    private Pieza getPiezaOrigen() { // Medida Temporal debe gestionarse en Pieza/Utils
        // Pedir por pantalla las coordenadas de la pieza
        int filaPieza = Util.pideEnteroRango("Introduce la fila de origen: ", "Error, debe ser entre 0 y 7", 0, 7);
        int columnaPieza = Util.pideEnteroRango("Introduce la columna de origen: ", "Error, debe ser entre 0 y 7", 0, 7);

        Pieza pOrigen = tab.obtenerPiezaEnCasilla(filaPieza, columnaPieza);

        if (pOrigen == null) System.out.println("No hay pieza en la casilla de origen.");

        return pOrigen;
    }

    private Pieza mostrarMenuSegunPieza() {
        Pieza pOrigen = getPiezaOrigen();
        if (pOrigen == null) return null;
        if (pOrigen instanceof Peon) VistaConsola.menuSeleccionarCasillaPeon();
        else VistaConsola.menuSeleccionarCasilla();
        return pOrigen;
    }
}
