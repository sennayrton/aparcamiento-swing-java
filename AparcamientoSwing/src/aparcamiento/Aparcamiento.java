package aparcamiento;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Aparcamiento {

    private static ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    private final static int cantidad = 20;
    private static int numero = 0;

    public static ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public static String intoducirVehiculo(Vehiculo v) {
        try {
            if (vehiculos.contains(v)) {//si esta dentro
                throw new AparcamientoException(AparcamientoException.VEHICULO_DENTRO);
            } else if (numero == cantidad) {
                throw new AparcamientoException(AparcamientoException.APARCAMIENTO_LLENO);
            } else {
                numero++;
                vehiculos.add(v);
                return "Vehículo con matrícula " + v.getMatricula() + " aparcado";
            }
        } catch (AparcamientoException ae) {
            return ae.toString();
        }

    }

    public static String sacarVehiculo(String matricula) {
        try {
            boolean dentro = false;
            Vehiculo ve = null;
            for (Vehiculo v : vehiculos) {
                if (v.getMatricula().equals(matricula)) {
                    ve = v;
                    dentro = true;
                }
            }
            if (!dentro) {//no esta dentro
                throw new AparcamientoException(AparcamientoException.VEHICULO_FUERA);
            } else {
                double precio = ve.calcularImporte();
                numero--;
                vehiculos.remove(ve);
                return "Vehículo con matrícula " + ve.getMatricula() + " retirado con precio: " + precio + " €";
            }
        } catch (AparcamientoException ae) {
            return ae.toString();
        }
    }

    public static void cargarDatos() {
        try {
            //Lectura de los objetos
            FileInputStream istreamPer = new FileInputStream("copiasegApar.dat");
            ObjectInputStream oisPer = new ObjectInputStream(istreamPer);
            vehiculos = (ArrayList<Vehiculo>) oisPer.readObject();
            istreamPer.close();
        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error de clase no encontrada: " + cnfe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//fin cargarDatos

    public static void guardarDatos() {
        try {
            //Si hay datos los guardamos...
            if (!vehiculos.isEmpty()) {
                /**
                 * **** Serialización de los objetos *****
                 */
                //Serialización
                FileOutputStream ostreamPer = new FileOutputStream("copiasegApar.dat");
                ObjectOutputStream oosPer = new ObjectOutputStream(ostreamPer);
                oosPer.writeObject(vehiculos);
                ostreamPer.close();
            } else {
                System.out.println("Error: No hay datos...");
            }

        } catch (IOException ioe) {
            System.out.println("Error de IO: " + ioe.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }//fin guardarDatos
}
