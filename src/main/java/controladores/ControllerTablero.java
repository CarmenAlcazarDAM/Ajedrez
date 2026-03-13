package controladores;

import controladores.MenusNum.GestEstadoPartida;
import controladores.MenusNum.GestMATKEnum;
import modelo.*;
import vista.VistaConsola;

import java.util.Scanner;

public class ControllerTablero {
    private final static VistaConsola vista = new VistaConsola();
    private final Tablero tab;
    Scanner sc = new Scanner(System.in); // Medida temporal

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
        vista.menuEstadoPartida();// ToDo
        GestEstadoPartida opcion = GestEstadoPartida.gestEstadoFromIndex((readInt(GestEstadoPartida.values().length)));
        switch (opcion) {
            case MOSTRAR_FICHAS_BLANCAS:
                tab.listarBlancas();
                break;
            case MOSTRAR_FICHAS_NEGRAS:
                tab.listarNegras();
                break;
            case ELIMINADAS:
                tab.listarEliminadas();
                vista.mostrarCabeceraEliminadasTotales();
                break;
            case PUNTOS_FICHAS_BLANCAS:
                System.out.println("Puntos de las fichas blancas: " + tab.obtenerPuntuacionBlancas());
                break;
            case PUNTOS_FICHAS_NEGRAS:
                System.out.println("Puntos de las fichas negras: " + tab.obtenerPuntuacionNegras());
                break;
            case VOLVER:
                return;
        }
    }
    public void gestionarMovimientosAtaques() {
        vista.menuSeleccionarCasilla();
        GestMATKEnum opcion = GestMATKEnum.gestorMATKFromIndex(readInt(GestMATKEnum.values().length));
        switch (opcion) {
            case MOVER:
                moverP();
                break;
            case VOLVER:
                return;
        }
    }

    // MEDIDAS TEMPORALES HASTA QUE ESTE HECHA LA LÓGICA
    // ############### CONTIENE SPOILERS #####################

    /**
     * Validamos el destino, pero no devuelve nada, solo imprime un mensaje
     * usamos el metodo movimiento correcto para imprimir el mensaje
     * Movemos la pieza si es true.
     */
    public void moverP() { // Medida temporal debe gestionarse en CONTROLADOR PRINCIPAL
        Pieza pOrigen = getPiezaOrigen();
        if (pOrigen == null) return;

        //Pedir por pantalla las coordenadas de destino
        int filaDestino = pedirCoordenadas("Introduce la fila de destino: ");
        int columnaDestino = pedirCoordenadas("Introduce la columna de destino: ");

        if (filaDestino == pOrigen.getFila() && columnaDestino == pOrigen.getColumna()) return;
        if (!validarMovimiento(pOrigen, filaDestino, columnaDestino)) return;
        if (!destinoValido(pOrigen, filaDestino, columnaDestino)) return;

        esPeon(pOrigen, filaDestino, columnaDestino);

        vista.estiloTablero(tab);
    }

    private void esPeon(Pieza pOrigen, int filaDestino, int columnaDestino) { // Medida Temporal debe gestionarse en PIEZA
        if (pOrigen instanceof Peon peon) {
            if (esAtaquePeon(peon, filaDestino, columnaDestino)) peon.ataque(filaDestino, columnaDestino);
            else peon.mover(filaDestino, columnaDestino);
        } else pOrigen.mover(filaDestino, columnaDestino);
    }

    private Pieza getPiezaOrigen() { // Medida Temporal debe gestionarse en Pieza/Utils
        // Pedir por pantalla las coordenadas de la pieza
        int filaPieza = pedirCoordenadas("Introduce la fila de origen: ");
        int columnaPieza = pedirCoordenadas("Introduce la columna de origen: ");

        Pieza pOrigen = tab.obtenerPiezaEnCasilla(filaPieza, columnaPieza);
        if (pOrigen == null) {
            System.out.println("No hay pieza en la casilla de origen.");
            return null;
        }
        return pOrigen;
    }

    public int pedirCoordenadas(String mensaje){ // Medida temporal, debe gestionarse en Utils
        System.out.print(mensaje);
        return sc.nextInt();
    }

    private boolean validarMovimiento(Pieza pOrigen, int dFila, int dCol) { // Medida temporal, debe gestionarse en PIEZA
        boolean valido = pOrigen.validarDestino(dFila, dCol, tab);
        vista.movimientoCorrectoOIncorrecto(valido);
        return valido;
    }

    private boolean destinoValido(Pieza pOrigen, int dFila , int dCol) { // Medida temporal, debe gestionarse en CONTROLADORPRINCIPAL
        Pieza pDestino = tab.obtenerPiezaEnCasilla(dFila, dCol);

        if (pDestino != null) {
            if (pDestino.getColor() == pOrigen.getColor()){
                System.out.println("La casilla de destino ya está ocupada por una pieza aliada.");
                return false;
            } else return true;
        }
        return true;
    }

    private boolean esAtaquePeon(Peon peon, int filaDestino, int columnaDestino) { // Medida temporal, debe gestionarse en PEON
        int avanceFila;

        if (peon.getColor() == Pieza.Color.BLANCA) avanceFila = 1;
        else avanceFila = -1;

        boolean filaCorrecta = peon.getFila() + avanceFila == filaDestino;
        boolean columnaCorrecta = Math.abs(peon.getColumna() - columnaDestino) == 1;

        return filaCorrecta && columnaCorrecta;
    }

    public int readInt(int max) { // Medida Temporal
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("Numero invalido. Vuelve a introducir un numero.");
                sc.nextLine();
            } else {
                int num = sc.nextInt();
                sc.nextLine();
                if (num >= 0 && num <= max) return num;
                else System.out.println("Numero fuera de rango. Vuelve a introducir un numero.");
            }
        }
    }
}
