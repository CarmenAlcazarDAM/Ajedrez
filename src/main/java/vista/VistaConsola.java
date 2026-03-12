package vista;

import modelo.Pieza;
import modelo.Tablero;

public class VistaConsola {
    static final int FILA = 8;
    static final int COLUMNA = 8;

    public static void estiloTablero(Tablero tablero) {
        System.out.println("""
                \n┌┐└┘├┬┴┼┤ │┆┊╎ ─ ╌ ┄ ┈ ╴╶ ╵ ╷
                \n┍┑┕┙┝┯┷┿┥ │ ─
                \n┎┒┖┚┠┰┸╂┨ ┃ ━
                \n┏┓┗┛┣┳┻╋┫ ┃┇┋╏ ━ ╍ ┅ ┉ ╸╺ ╹ ╻
                \n╔╗╚╝╠╦╩╬╣ ║ ═
                \n╓╖╙╜╟╥╨╫╢ ║ ═
                \n╒╕╘╛╞╤╧╪╡ │ ─
                \n╭╮╰╯ │ ─
                
                """);

        vistaTablero0(tablero, "    ┌─┬─┬─┬─┬─┬─┬─┬─┐", " │", "│", "│ ", "    ├─┼─┼─┼─┼─┼─┼─┼─┤", "    └─┴─┴─┴─┴─┴─┴─┴─┘");
        vistaTablero0(tablero, "     ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▖" + "\n    ┌─┬─┬─┬─┬─┬─┬─┬─┐▌", " │", "│", "│▌ ", "    ├─┼─┼─┼─┼─┼─┼─┼─┤▌", "    └─┴─┴─┴─┴─┴─┴─┴─┘▘");
        vistaTablero0(tablero, "    ╔═╦═╦═╦═╦═╦═╦═╦═╗", " ║", "║", "║ ", "    ╠═╬═╬═╬═╬═╬═╬═╬═╣", "    ╚═╩═╩═╩═╩═╩═╩═╩═╝");
        vistaTablero0(tablero, "    ╔═╤═╤═╤═╤═╤═╤═╤═╗", " ║", "┃", "║ ", "    ╟─╋─╋─╋─╋─╋─╋─╋─╢", "    ╚═╧═╧═╧═╧═╧═╧═╧═╝");
        vistaTablero0(tablero, "    ╔━┳━┳━┳━┳━┳━┳━┳━╗", " ┃", "┃", "┃ ", "    ┣━╋━╋━╋━╋━╋━╋━╋━┫", "    ╚━┻━┻━┻━┻━┻━┻━┻━╝");
        vistaTablero0(tablero, "    ╔═╤═╤═╤═╤═╤═╤═╤═╗", " ║", "│", "║ ", "    ╟─┼─┼─┼─┼─┼─┼─┼─╢", "    ╚═╧═╧═╧═╧═╧═╧═╧═╝");
        vistaTablero0(tablero, "    ╓─┬─┬─┬─┬─┬─┬─┬─╖", " ║", "│", "║ ", "    ╟─┼─┼─┼─┼─┼─┼─┼─╢", "    ╙─┴─┴─┴─┴─┴─┴─┴─╜");
        vistaTablero0(tablero, "    ┌─┬─┬─┬─┬─┬─┬─┬─┐", " │", "│", "│ ", "    ╞═╪═╪═╪═╪═╪═╪═╪═╡", "    └─┴─┴─┴─┴─┴─┴─┴─┘");
        }

    public static void vistaTablero0(Tablero tablero, String top, String a, String b, String c, String mid, String bot) {
        System.out.println(top); // Borde superior

        for (int i = FILA - 1; i >= 0; i--) {
            System.out.print("  " + (i) + a);

            for (int j = 0; j < COLUMNA; j++) {
                Pieza t = tablero.obtenerPiezaEnCasilla(i, j);
                String dibujo = (t == null) ? colorCasilla(i, j) : t.getDibujo();
                System.out.print(dibujo + (j < COLUMNA - 1 ? b : ""));
            }

            System.out.println(c);
            if (i != 0) System.out.println(mid);
            else System.out.println(bot);
        }
        System.out.println("      0   1   2   3   4   5   6   7\n");
    }

    public static String colorCasilla(int fila, int columna) {
        return ((fila + columna) % 2 == 0) ? "▓" : "░";
    }

}