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



    /**
     * Crear el switch que llame a los métodos correspondientes y generar el orden de la partida, imprimir, contar puntos...;
     *
     * @param opcion introduction pro el usuario
     */
    public static void gestionEstadoPartida(int opcion) {// ToDo
        while (true) {

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
