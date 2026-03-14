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
     *┌┐└┘├┬┴┼┤ │┆┊╎ ─ ╌ ┄ ┈ ╴╶ ╵ ╷
     * ┍┑┕┙┝┯┷┿┥ │ ─
     * ┎┒┖┚┠┰┸╂┨ ┃ ━
     * ┏┓┗┛┣┳┻╋┫ ┃┇┋╏ ━ ╍ ┅ ┉ ╸╺ ╹ ╻
     * ╔╗╚╝╠╦╩╬╣ ║ ═
     * ╓╖╙╜╟╥╨╫╢ ║ ═
     * ╒╕╘╛╞╤╧╪╡ │ ─
     * ╭╮╰╯ │ ─
     * ▁ ▂ ▃ ▄ ▅ ▆ ▇ █ ▉ ▊ ▋ ▌ ▍ ▎ ▏▐ ▕ ▔ ░ ▒ ▓ ▖ ▗ ▘ ▝ ▚ ▞ ▙▖▗▟▞▚▛▘▝▜▀▌▐█▕▏
     * ⦀ ⦙ ⦚ ⧘ ⧙ ⧚ ⧛
     */


    public void vistaTablero(Tablero tablero) {
        // Hecho por mi - inicio
        System.out.println("    ╔═╦═╦═╦═╦═╦═╦═╦═╗");

        for (int i = FILA - 1; i >= 0; i--) {
            System.out.print("  " + (i) + " ║");

            for (int j = 0; j < COLUMNA; j++) {
                Pieza t = tablero.obtenerPiezaEnCasilla(i, j);
                String dibujo = (t == null) ? colorCasilla(i, j) : t.getDibujo();
                System.out.print(dibujo + (j < COLUMNA - 1 ? "║" : ""));
            }

            System.out.println("║ ");
            if (i != 0) System.out.println("    ╠═╬═╬═╬═╬═╬═╬═╬═╣");
            else System.out.println("    ╚═╩═╩═╩═╩═╩═╩═╩═╝");
        }
        System.out.println("      0   1   2   3   4   5   6   7\n");
    }

    public String colorCasilla(int fila, int columna) {
        return ((fila + columna) % 2 == 0) ? "▓" : "░";
    }

    /*
     * ///////////////////////  MENUS /////////////////////////////
     */

    /**
     * Muestra el menú principal del programa
     *
     * @return opcion elegida por el usuario
     */
    public static void menuPrincipal() {


            System.out.println("╔══════════════════════════════╗");
            System.out.println("║        MENÚ PRINCIPAL        ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ Iniciar nuevo juego");
            System.out.println("   2 ▸ Cargar tablero");
            System.out.println("   0 ▸ Salir");
            System.out.println("╚══════════════════════════════╝");

    }


    /**
     * Muestra el menú principal de la partida
     *
     * @return opcion elegida para el controlador
     */
    public static int menuPartida() {

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
            int opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción entre 0 y 6", 0,6);

        return opcion;
    }


// 1 seleccionar casilla --> mover , volver (if es peon atacar) // si esta vacia que diga que esta vacia

    /**
     * Muestra el menú para seleccionar una casilla normal
     *
     * @return opcion elegida por el usuario
     */
    public static int menuSeleccionarCasilla() {

            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         MENÚ CASILLA         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ mover");
            System.out.println("   0 ▸ volver");
            System.out.println("╚══════════════════════════════╝");
           int opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción 0 o 1",0,1);

        return opcion;
    }


    /**
     * Muestra el menú para elegir una casilla
     *
     * @return opcion para el switch que usará el controlador
     */
    public static int menuSeleccionarCasillaPeon() {

            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         MENÚ CASILLA         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ mover");
            System.out.println("   2 ▸ atacar");
            System.out.println("   0 ▸ volver");
            System.out.println("╚══════════════════════════════╝");
            int opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción entre 0 y 2",0,2);

        return opcion;
    }


    /**
     * Muestra el mensaje de confirmación para reiniciar el tablero
     *
     * @return opcion elegida por el usuario
     */
    public static int mensajeReiniciar() {

            System.out.println("╔════════════════════════════════╗");
            System.out.println("║           ¿REINICIAR?          ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("   1 ▸ sí, estoy seguro de ello");
            System.out.println("   2 ▸ no, quiero volver");
            System.out.println("╚════════════════════════════════╝");
            int opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción 1 o 2", 1,2);

        return opcion;
    }


    /**
     * Muestra el mensaje de confirmación para cerrar el programa
     *
     * @return opcion elegida por el usuario
     */
    public static int mensajeFinalizar() {


            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║           ¿CERRAR PROGRAMA?          ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("   1 ▸ sí, acabemos con esto");
            System.out.println("   2 ▸ no, quiero volver a la partida");
            System.out.println("╚══════════════════════════════════════╝");
            int opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción 1 0 2",1,2);

        return opcion;
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
    public static int menuEstadoPartida() {

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
            int opcion = Util.pideEnteroRango("Introduce una opción: ", "Error, debe ser una opción entre 0 y 5",0,5);

        return opcion;
    }


    /**
     * Imprime la cabecera para mostrar las fichas eliminadas
     */
    public static void mostrarCabeceraEliminadasTotales() {

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
        System.out.println();
        System.out.println("   ✦ Equipo técnico:");
        System.out.println("   ▸ Carmen (SM)");
        System.out.println("   ▸ Antonio Jesús");
        System.out.println("   ▸ Daniel");
        System.out.println("   ▸ Kleopatra");
        System.out.println();
        System.out.println("  Gracias por usar nuestro programa.");
        System.out.println("  Esperamos verte pronto por aquí.");
        System.out.println("  Hasta pronto 🙋🏻‍♂️🙋🏻‍♀️🙋🏻‍♂️🙋🏻‍♀️");
        System.out.println("╚═══════════════════════════════════════════════╝");

    }


}