package aparcamiento;

public class AparcamientoException extends Exception {

    public static String VEHICULO_DENTRO = "El vehículo ya se encuentra en el aparcamiento.";
    public static String VEHICULO_FUERA = "El vehículo no se encuentra en el aparcamiento.";
    public static String APARCAMIENTO_LLENO = "El aparcamiento está lleno.";

    public AparcamientoException() {
        super("Se ha producido una excepción en la aplicación.");
    }

    public AparcamientoException(String txt) {
        super(txt); 
    }
}
