import controladores.ControllerPrincipal;
import controladores.ControllerTablero;
import modelo.Pieza;
import modelo.Tablero;
import modelo.Torre;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {

        ControllerPrincipal CP= new ControllerPrincipal();
        VistaConsola  vista = new VistaConsola();
        Tablero tablero = new Tablero();

        System.out.println("AJEDREZ");
        VistaConsola.menuPrincipal();
        boolean seguirJugando=true;

        tablero=CP.gestionarMenuPrincipal();
        int opcion=-1;


        while (seguirJugando){
            vista.estiloTablero(tablero);
            VistaConsola.turnoActual(tablero.getContadorTurnos());
            opcion = VistaConsola.menuPartida();
            seguirJugando=CP.gestionarMenuJuego(opcion, tablero);
        }


    }
}
