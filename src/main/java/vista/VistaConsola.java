package vista;

import modelo.Pieza;
import modelo.Tablero;

public class VistaConsola {
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
    // iniciar nuevo juego
    // cargar tablero
    /**
     * ------------------>
     */
    // 1 seleccionar pieza --> mover , volver (if es peon atacar)
    // 2 reiniciar tablero --> ¡Estas seguro?
    // 3 guardar tablero
    // 4 rendirse
    // 5 dar tablas
    // 6 Mostrar estado de la partida
    // 0 finalizar programa --> ¡Estas seguro? (S/N)
    /**
     *
     */
    // 1 mostrar lista de fichas blancas
    // 2 mostrar lista de fichas negras
    // 3 mostrar lista de eliminadas totales ----> fichas negras eliminadas: alfil, peon, torre ---- fichas blancas eliminadas: peon, reina
    // 4 obtener puntos de las fichas negras
    // 5 obtener puntos de las fichas blancas
    // 0 Volver


    // metodo void que compruebe si es true or false (movimiento correcto, movimiento incorrecto)

    // metodo que te diga el turno segun el parametro que le pasen, si es par dirá que mueven las negras y si es
    // impar dirá que mueven las blancas, tambien dirá el numero de turno.

}