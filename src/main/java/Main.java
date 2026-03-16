import controladores.ControllerPrincipal;
import controladores.ControllerTablero;
import modelo.Pieza;
import modelo.Tablero;
import modelo.Torre;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {

        ControllerPrincipal cp = new ControllerPrincipal();
        VistaConsola vista = new VistaConsola();
        Tablero tablero = new Tablero();


        System.out.println("AJEDREZ");
        boolean seguirJugando = true;

        try {
            VistaConsola.menuPrincipal();
            tablero=cp.gestionarMenuPrincipal();

        } catch (RuntimeException e) {
            System.out.println("Error:" + e.getMessage());
        }

        int opcion = -1;


        while (seguirJugando) {
            vista.vistaTablero(tablero);
            VistaConsola.turnoActual(tablero.getContadorTurnos());
            opcion = VistaConsola.menuPartida();
            seguirJugando = cp.gestionarMenuJuego(opcion, tablero);
        }
    }
}
