import controladores.ControllerPrincipal;
import modelo.Tablero;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {

        ControllerPrincipal cp = new ControllerPrincipal();
        VistaConsola vista = new VistaConsola();
        Tablero tablero = new Tablero();


        VistaConsola.imprimirEnVista("AJEDREZ");
        boolean seguirJugando = true;


        try {
            VistaConsola.menuPrincipal();
            tablero = cp.gestionarMenuPrincipal();

        } catch (RuntimeException e) {
            VistaConsola.imprimirEnVista("Error:" + e.getMessage());
        }


        if (tablero != null) {


            int opcion = -1;


            while (seguirJugando) {
                vista.vistaTablero(tablero);
                VistaConsola.turnoActual(tablero.getContadorTurnos());
                opcion = VistaConsola.menuPartida();
                seguirJugando = cp.gestionarMenuJuego(opcion, tablero);
            }
        }
    }
}
