package vista;

import modelo.Pieza;
import modelo.Tablero;
import utils.Util;

import java.util.Scanner;

public class VistaConsola {
    static Scanner sc = new Scanner(System.in);
    static final int FILA = 8;
    static final int COLUMNA = 8;

    /**
     * Imprime el tablero línea a línea basado en el número de fila y lieas.
     *
     * @param tablero
     */
    public static void vistaTablero(Tablero tablero) {
        for (int i = FILA - 1; i >= 0; i--) {

            if (i == FILA - 1) linea("╔", "╦", "╗", COLUMNA);
            System.out.print("║");

            for (int j = 0; j < COLUMNA; j++) {
                Pieza t = tablero.obtenerPiezaEnCasilla(i, j);

                if (t == null) System.out.printf(" %-2s ║", colorCasilla(i, j));
                else System.out.printf(" %-2s ║", t.getDibujo());
            }
            System.out.println();

            if (i > 0) linea("╠", "╬", "╣", COLUMNA);
            else linea("╚", "╩", "╝", COLUMNA);
        }
    }

    public static String colorCasilla(int fila, int columna) {
        boolean color;

        if (fila % 2 == 0) color = columna % 2 == 0;
        else color = columna % 2 != 0;

        if (color) return "░░";
        else return "▓▓";
    }

    static void linea(String izq, String mid, String der, int columnas) {
        System.out.print(izq);
        for (int i = 0; i < columnas; i++) {
            System.out.print("════");
            System.out.print(i < columnas - 1 ? mid : der);
        }
        System.out.println();
    }

    /**
     * ///////////////////////  MENUS /////////////////////////////
     */

    /**
     * Muestra el menú principal del programa
     *
     * @return opcion elegida por el usuario
     */
    public static int menuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║        MENÚ PRINCIPAL        ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ Iniciar nuevo juego");
            System.out.println("   2 ▸ Cargar tablero");
            System.out.println("   0 ▸ Salir");
            System.out.println("╚══════════════════════════════╝");
            opcion = Util.pideEntero("Introduce una opción: ", "Opcion no valida");
            return opcion;
        } while (opcion != 0);
    }


    /**
     * Muestra el menú principal de la partida
     *
     * @return opcion elegida para el controlador
     */
    public int menuPartida() {
        int opcion = 0;
        do {
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║            MENÚ PARTIDA            ║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("   1 ▸ seleccionar casilla");
            System.out.println("   2 ▸ reiniciar tablero");
            System.out.println("   3 ▸ guardar tablero");
            System.out.println("   4 ▸ rendirse");
            System.out.println("   5 ▸ declarar en tablas");
            System.out.println("   6 ▸ Mostrar estado de la partida");
            System.out.println("   0 ▸ Salir");
            System.out.println("╚════════════════════════════════════╝");
            opcion = Util.pideEntero("Introduce una opción: ", "Opcion no valida");
            return opcion;
        } while (opcion != 0);
    }


// 1 seleccionar casilla --> mover , volver (if es peon atacar) // si esta vacia que diga que esta vacia

    /**
     * Muestra el menú para seleccionar una casilla normal
     *
     * @return opcion elegida por el usuario
     */
    public int menuSeleccionarCasilla() {
        int opcion = 0;
        do {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         MENÚ CASILLA         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ elegir casilla");
            System.out.println("   0 ▸ volver");
            System.out.println("╚══════════════════════════════╝");
            opcion = Util.pideEntero("Introduce una opción: ", "Opcion no valida");
            return opcion;
        } while (opcion != 0);
    }


    /**
     * Muestra el menú para elegir una casilla
     *
     * @return opcion para el switch que usará el controlador
     */
    public int menuSeleccionarCasillaPeon() {
        int opcion = 0;
        do {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         MENÚ CASILLA         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ elegir casilla");
            System.out.println("   2 ▸ atacar");
            System.out.println("   0 ▸ volver");
            System.out.println("╚══════════════════════════════╝");
            opcion = Util.pideEntero("Introduce una opción: ", "Opcion no valida");
            return opcion;
        } while (opcion != 0);
    }


    /**
     * Muestra el mensaje de confirmación para reiniciar el tablero
     *
     * @return opcion elegida por el usuario
     */
    public int mensajeReiniciar() {
        int opcion = 0;
        do {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║           ¿REINICIAR?          ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("   1 ▸ sí, estoy seguro de ello");
            System.out.println("   2 ▸ no, quiero volver");
            System.out.println("╚════════════════════════════════╝");
            opcion = Util.pideEntero("Introduce una opción: ", "Opcion no valida");
            return opcion;
        } while (opcion != 0);
    }


    /**
     * Muestra el mensaje de confirmación para cerrar el programa
     *
     * @return opcion elegida por el usuario
     */
    public int mensajeFinalizar() {
        int opcion = 0;
        do {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║           ¿CERRAR PROGRAMA?          ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("   1 ▸ sí, acabemos con esto");
            System.out.println("   2 ▸ no, quiero volver a la partida");
            System.out.println("╚══════════════════════════════════════╝");
            opcion = Util.pideEntero("Introduce una opción: ", "Opcion no valida");
            return opcion;
        } while (opcion != 0);
    }


// comentarios originales se mantienen
// 1 mostrar lista de fichas blancas
// 2 mostrar lista de fichas negras
// 3 mostrar lista de eliminadas totales
// 4 obtener puntos de las fichas negras
// 5 obtener puntos de las fichas blancas
// 0 Volver

    /**
     * Muestra el menú de estado de la partida
     *
     * @return opcion elegida por el usuario
     */
    public int menuEstadoPartida() {
        int opcion = 0;
        do {
            System.out.println("╔═══════════════════════════════════════════════╗");
            System.out.println("║                 MENÚ DE ESTADO                ║");
            System.out.println("╠═══════════════════════════════════════════════╣");
            System.out.println("   1 ▸ mostrar lista de fichas blancas");
            System.out.println("   2 ▸ mostrar lista de fichas negras");
            System.out.println("   3 ▸ mostrar lista de eliminadas totales");
            System.out.println("   4 ▸ obtener puntos de las fichas negras");
            System.out.println("   5 ▸ obtener puntos de las fichas blancas");
            System.out.println("   0 ▸ Volver");
            System.out.println("╚═══════════════════════════════════════════════╝");
            opcion = Util.pideEntero("Introduce una opción: ", "Opcion no valida");
            return opcion;
        } while (opcion != 0);
    }


    /**
     * Imprime la cabecera para mostrar las fichas eliminadas
     */
    public void mostrarCabeceraEliminadasTotales() {

        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║            FICHAS ELIMINADAS TOTALES          ║");
        System.out.println("╠═══════════════════════════════════════════════╣");

    }


    /**
     * Imprime la línea inferior del cuadro
     */
    public static void imprimirLinea() {
        System.out.println("╚═══════════════════════════════════════════════╝");
    }


    /**
     * Muestra si el movimiento ha sido correcto o incorrecto
     *
     * @param dato resultado del movimiento
     */
    public static void movimientoCorrectoOIncorrecto(boolean dato) {
        String result = "";
        if (dato == true) {
            result = "Movimiento Correcto";
        } else {
            result = "Movimiento Incorrecto";
        }
        System.out.println(result);
    }


    /**
     * Muestra el turno actual según el número recibido
     *
     * @param turno número de turno actual
     */
    public static void turnoActual(int turno) {
        if (turno % 2 == 0) System.out.println("Turno: " + turno + ", mueven las negras");
        else {
            System.out.println("Turno: " + turno + ", mueven las blancas");
        }

    }


    /**
     * Metodo que imprime la despedida del programa
     */
    public static void mensajeFinal() {

        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║               FIN DEL PROGRAMA                ║");
        System.out.println("╠═══════════════════════════════════════════════╣");
        System.out.println("  Has decidido salir del programa.");
        System.out.println();
        System.out.println("   ✦ Equipo técnico:");
        System.out.println("   ▸ Antonio Jesus");
        System.out.println("   ▸ Daniel");
        System.out.println("   ▸ Kleopatra");
        System.out.println("   ▸ Amane (SM)");
        System.out.println();
        System.out.println("  Gracias por usar nuestro programa.");
        System.out.println("  Esperamos verte pronto por aquí.");
        System.out.println("  Hasta pronto 🙋🏻‍♂️🙋🏻‍♀️🙋🏻‍♂️🙋🏻‍♀️");
        System.out.println("╚═══════════════════════════════════════════════╝");

    }


}