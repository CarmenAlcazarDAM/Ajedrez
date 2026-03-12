import controladores.ControllerTablero;
import modelo.Pieza;
import modelo.Tablero;
import modelo.Torre;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {

//        System.out.println("AJEDREZ");
//        int opcion = 0;
//
//        do{
//
//
//
//        }while (opcion!=0);
//
//
//
//
//
//



        //ESTO ES DE PRUEBA

        VistaConsola vistaConsola = new VistaConsola();
        ControllerTablero controladorTablero = new ControllerTablero();
        Tablero tablero = new Tablero();

        controladorTablero.iniciarTablero();
        vistaConsola.estiloTablero(tablero);
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
