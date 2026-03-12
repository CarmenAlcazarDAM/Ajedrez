package vista;

import modelo.Pieza;
import modelo.Tablero;
import utils.Util;

import java.util.Scanner;

public class VistaConsola {
    static Scanner sc=new Scanner(System.in);
    static final int FILA = 8;
    static final int COLUMNA = 8;

    /**
     * Imprime el tablero línea a línea basado en el número de fila y lieas.
     * @param tablero
     */
    public static void vistaTablero(Tablero tablero){
        for (int i = FILA - 1; i >= 0; i--) {

             if (i == FILA - 1) linea("╔", "╦", "╗", COLUMNA);
            System.out.print("║");

            for (int j = 0; j < COLUMNA; j++) {
                Pieza t = tablero.obtenerPiezaEnCasilla(i,j);

                if(t == null) System.out.printf(" %-2s ║", colorCasilla(i,j));
                else System.out.printf(" %-2s ║", t.getDibujo());
            }
            System.out.println();

            if (i > 0) linea("╠", "╬", "╣", COLUMNA);
            else linea("╚", "╩", "╝", COLUMNA);
        }
    }
    public static String colorCasilla(int fila, int columna) {
        boolean color;

        if(fila % 2 == 0) color = columna % 2 == 0;
        else color = columna % 2 != 0;

        if(color) return "░░";
        else  return "▓▓";
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
     * //////  MENUS ///////
     */

public static int menuPrincipal() {
    int opcion=0;
    do {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║        MENÚ PRINCIPAL        ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("   1 ▸ Iniciar nuevo juego");
        System.out.println("   2 ▸ Cargar tablero");
        System.out.println("   0 ▸ Salir");
        System.out.println("╚══════════════════════════════╝");
        opcion= Util.pideEntero("Introduce una opción: ","Opcion no valida");
        return opcion;
    } while (opcion!=0);
}
    /**
     * ------------------>
     */
    // 1 seleccionar casilla --> mover , volver (if es peon atacar) // si esta vacia que diga que esta vacia
    // 2 reiniciar tablero --> ¡Estas seguro?
    // 3 guardar tablero
    // 4 rendirse
    // 5 dar tablas
    // 6 Mostrar estado de la partida
    // 0 finalizar programa --> ¡Estas seguro? (S/N)
    /**
     *
     */
    public int menuPartida(){
        int opcion=0;
        do {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         MENÚ PARTIDA         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ seleccionar casilla");
            System.out.println("   2 ▸ reiniciar tablero");
            System.out.println("   3 ▸ guardar tablero");
            System.out.println("   4 ▸ rendirse");
            System.out.println("   5 ▸ declarar en tablas");
            System.out.println("   6 ▸ Mostrar estado de la partida");
            System.out.println("   0 ▸ Salir");
            System.out.println("╚══════════════════════════════╝");
            opcion= Util.pideEntero("Introduce una opción: ","Opcion no valida");
            return opcion;
        } while (opcion!=0);
    }
    // 1 seleccionar casilla --> mover , volver (if es peon atacar) // si esta vacia que diga que esta vacia
    public int menuSeleccionarCasilla(){
        int opcion=0;
        do {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         MENÚ CASILLA         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ elegir casilla");
            System.out.println("   0 ▸ volver");
            System.out.println("╚══════════════════════════════╝");
            opcion= Util.pideEntero("Introduce una opción: ","Opcion no valida");
            return opcion;
        } while (opcion!=0);
    }

    /**
     * Muestra el menú para elegir una casilla
     * @return opcion para el switch que usará el controlador
     */
    public int menuSeleccionarCasillaPeon(){
        int opcion=0;
        do {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║         MENÚ CASILLA         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ elegir casilla");
            System.out.println("   2 ▸ atacar");
            System.out.println("   0 ▸ volver");
            System.out.println("╚══════════════════════════════╝");
            opcion= Util.pideEntero("Introduce una opción: ","Opcion no valida");
            return opcion;
        } while (opcion!=0);
    }
    public int mensajeReiniciar(){
        int opcion=0;
        do {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║          ¿REINICIAR?         ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ sí, estoy seguro de ello");
            System.out.println("   2 ▸ no, quiero volver");
            System.out.println("╚══════════════════════════════╝");
            opcion= Util.pideEntero("Introduce una opción: ","Opcion no valida");
            return opcion;
        } while (opcion!=0);
    }

    public int mensajeFinalizar(){
        int opcion=0;
        do {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║       ¿CERRAR PROGRAMA?      ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("   1 ▸ sí, acabemos con esto");
            System.out.println("   2 ▸ no, quiero volver a la partida");
            System.out.println("╚══════════════════════════════╝");
            opcion= Util.pideEntero("Introduce una opción: ","Opcion no valida");
            return opcion;
        } while (opcion!=0);
    }



    // 1 mostrar lista de fichas blancas
    // 2 mostrar lista de fichas negras
    // 3 mostrar lista de eliminadas totales ----> fichas negras eliminadas: alfil, peon, torre ---- fichas blancas eliminadas: peon, reina
    // 4 obtener puntos de las fichas negras
    // 5 obtener puntos de las fichas blancas
    // 0 Volver


    // metodo void que compruebe si es true or false (movimiento correcto, movimiento incorrecto)
    public static void movimientoCorrectoOIncorrecto(boolean dato){
        String result="";
        if (dato==true){
            result="Movimiento Correcto";
        }else {
            result="Movimiento Incorrecto";
        }
        System.out.println(result);
    }

    // metodo que te diga el turno segun el parametro que le pasen, si es par dirá que mueven las negras y si es
    public static void turnoActual(int turno){
        if (turno%2==0) System.out.println("Turno: "+ turno +", mueven las negras");
        else {
            System.out.println("Turno: "+ turno +", mueven las blancas");
        }

    }
//imprimir despedida al salir del programa


}