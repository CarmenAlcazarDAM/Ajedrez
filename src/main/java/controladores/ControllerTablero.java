package controladores;
import controladores.MenusNum.GestMATKEnum;
import modelo.*;
import utils.Util;
import vista.VistaConsola;

public class ControllerTablero {
    Tablero tab;

    public ControllerTablero(Tablero tablero) {
        this.tab = tablero;
    }

    /**
     * Crear el switch que llame a los métodos correspondientes y generar el orden de la partida, imprimir, contar puntos...;
     */
    public static boolean gestionEstadoPartida(int opcion, Tablero tablero) {
boolean seguirJugando=true;
        switch (opcion) {
            case 1:
                tablero.listarBlancas();
                break;
            case 2:
                tablero.listarNegras();
                break;
            case 3:
                VistaConsola.mostrarCabeceraEliminadasTotales();
                tablero.listarEliminadas();
                VistaConsola.imprimirLinea();
                break;
            case 4:
                System.out.println("Puntos de las fichas negras: " + tablero.obtenerPuntuacionNegras());
                break;
            case 5:
                System.out.println("Puntos de las fichas blancas: " + tablero.obtenerPuntuacionBlancas());
                break;
            case 0:
                seguirJugando=false;
        }
        return seguirJugando;
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
