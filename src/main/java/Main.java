import controladores.ControllerTablero;
import modelo.Pieza;
import modelo.Tablero;
import modelo.Torre;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        VistaConsola vistaConsola = new VistaConsola();

        ControllerPrincipal cp = new ControllerPrincipal();
        VistaConsola vista = new VistaConsola();
        Tablero tablero = new Tablero();
        ControllerTablero controladorTablero = new ControllerTablero(tablero);
        controladorTablero.iniciarTablero();
        vistaConsola.vistaTablero(tablero);

        System.out.println("AJEDREZ");
        int opcion = 0;
        do{

        }while (true);

        controladorTablero.gestionEstadoPartida();
        controladorTablero.gestionarMovimientosAtaques();

        //ESTO ES DE PRUEBA


//
//        Pieza torre = new Torre(Pieza.Color.BLANCA, "♖", 0, 0, 5);
//        System.out.println(torre.toString());
//
//        tablero.listarBlancas();
//        tablero.listarNegras();
//        tablero.listarEliminadas();

        System.out.println("AJEDREZ");
        boolean seguirJugando = true;


        try {
            VistaConsola.menuPrincipal();
            cp.gestionarMenuPrincipal();

        } catch (NullPointerException e) {
            System.out.println("Error:" + e.getMessage());

        }

        int opcion = -1;


        while (seguirJugando) {
            vista.estiloTablero(tablero);
            VistaConsola.turnoActual(tablero.getContadorTurnos());
            opcion = VistaConsola.menuPartida();
            seguirJugando = cp.gestionarMenuJuego(opcion, tablero);
        }


    }
}
