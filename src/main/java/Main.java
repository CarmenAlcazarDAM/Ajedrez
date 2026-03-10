import controladores.ControllerTablero;
import modelo.Pieza;
import modelo.Tablero;
import modelo.Torre;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {

        //ESTO ES DE PRUEBA

        VistaConsola vistaConsola = new VistaConsola();
        ControllerTablero controladorTablero = new ControllerTablero();
        Tablero tablero = new Tablero();

        ControllerTablero.iniciarTablero();
        vistaConsola.vistaTablero(tablero);

        Pieza torre = new Torre(Pieza.Color.BLANCA, "♖", 0, 0, 5);
        System.out.println(torre.toString());

//        tablero.listarBlancas();
//        tablero.listarNegras();
//        tablero.listarEliminadas();

        controladorTablero.gestionarMovimientosAtaques(1, torre,2, 2, tablero);
        for (int i = 1; i < 6; i++) {
            controladorTablero.gestionEstadoPartida(i);
        }


        vistaConsola.vistaTablero(tablero);


        //--------------------------------------------


    }
}
