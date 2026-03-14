import controladores.ControllerTablero;
import modelo.Pieza;
import modelo.Tablero;
import modelo.Torre;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        VistaConsola vistaConsola = new VistaConsola();
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


        //--------------------------------------------


    }
}
