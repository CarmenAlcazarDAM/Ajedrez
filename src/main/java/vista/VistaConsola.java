package vista;

import modelo.Pieza;
import modelo.Tablero;

public class VistaConsola {
    static final int FILA = 8;
    static final int COLUMNA = 8;

    public VistaConsola() {

    }

    /**
     * Imprime el tablero línea a línea basado en el número de fila y líneas.
     * @param tablero el nuevo tablero
     */
    public void vistaTablero(Tablero tablero){
        for (int i = FILA - 1; i >= 0; i--) {

            if (i == FILA - 1) linea("╔", "╦", "╗");
            System.out.print("║");

            for (int j = 0; j < COLUMNA; j++) {
                Pieza t = tablero.obtenerPiezaEnCasilla(i,j);

                if(t == null) System.out.print(" " + colorCasilla(i, j) + " ║");
                else System.out.print(" " + t.getDibujo() + " ║");
            }
            System.out.println();

            if (i > 0) linea("╠", "╬", "╣");
            else linea("╚", "╩", "╝");
        }
    }
    public String colorCasilla(int fila, int columna) {
        boolean color;

        if(fila % 2 == 0) color = columna % 2 == 0;
        else color = columna % 2 != 0;

        if(color) return "░";
        else  return "▓";
    }

    public void linea(String izq, String mid, String der) {
        System.out.print(izq);
        for (int i = 0; i < COLUMNA; i++) {
            System.out.print("══");
            System.out.print(i < COLUMNA - 1 ? mid : der);
        }
        System.out.println();
    }

}