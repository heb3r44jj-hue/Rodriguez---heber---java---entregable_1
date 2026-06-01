import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {

    private static Scanner scanner = new Scanner(System.in);

   
    public static String leerTexto(String mensaje) {
        String valor;
        do {
            System.out.print(mensaje);
            valor = scanner.nextLine().trim();
            if (valor.isEmpty()) {
                System.out.println("  Este campo no puede estar vacío dale de nuevo.");
            }
        } while (valor.isEmpty());
        return valor;
    }

   
    public static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("  Ingrese un número válido: ");
            }
        }
    }

    
    public static int leerEnteroPositivo(String mensaje) {
        int valor;
        do {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor <= 0) {
                    System.out.println("  El valor debe ser mayor que 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un número entero válido");
                valor = -1;
            }
        } while (valor <= 0);
        return valor;
    }

    
    public static double leerNota(String mensaje) {
        double nota;
        do {
            System.out.print(mensaje);
            try {
                nota = Double.parseDouble(scanner.nextLine().trim());
                if (nota < 0 || nota > 100) {
                    System.out.println("  La nota debe estar entre 0 y 100.");
                    nota = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un número válido (ejemplo: 85 o 72.5).");
                nota = -1;
            }
        } while (nota < 0 || nota > 100);
        return nota;
    }

    
    public static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;
        do {
            System.out.print(mensaje);
            try {
                fecha = LocalDate.parse(scanner.nextLine().trim(), formato);
            } catch (DateTimeParseException e) {
                System.out.println("  Formato incorrecto. Use dd/mm/aaaa (ejemplo: 15/03/2024).");
            }
        } while (fecha == null);
        return fecha;
    }

    
    public static int leerOpcionMenu(int min, int max) {
        int opcion;
        do {
            System.out.print("Seleccione una opción (" + min + "-" + max + "): ");
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                if (opcion < min || opcion > max) {
                    System.out.println("  Opción fuera de rango. Elija entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("  Ingrese un número válido.");
                opcion = -1;
            }
        } while (opcion < min || opcion > max);
        return opcion;
    }

   
    public static void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
