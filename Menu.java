public class Menu {

    private SistemaAcademico sistema;

    public Menu() {
        this.sistema = new SistemaAcademico();
    }

    private void mostrarMenu() {
        System.out.println("\n====================================");
        System.out.println("   SISTEMA DE GESTIÓN ACADÉMICA");
        System.out.println("====================================");
        System.out.println("  1.  Registrar estudiante");
        System.out.println("  2.  Registrar profesor");
        System.out.println("  3.  Registrar materia");
        System.out.println("  4.  Asignar materia a estudiante");
        System.out.println("  5.  Registrar calificación");
        System.out.println("  6.  Buscar estudiante");
        System.out.println("  7.  Mostrar estudiantes");
        System.out.println("  8.  Mostrar materias");
        System.out.println("  9.  Mostrar reporte de promedios");
        System.out.println("  10. Salir");
        System.out.println("====================================");
    }

    public void ejecutar() {
        System.out.println("Bienvenido al Sistema de Gestión Académica.");

        int opcion;
        do {
            mostrarMenu();
            opcion = Consola.leerOpcionMenu(1, 10);

            switch (opcion) {
                case 1:  sistema.registrarEstudiante();       
                break;
                case 2:  sistema.registrarProfesor();        
                break;
                case 3:  sistema.registrarMateria();          
                break;
                case 4:  sistema.asignarMateriaAEstudiante(); 
                break;
                case 5:  sistema.registrarCalificacion();     
                break;
                case 6:  sistema.buscarEstudiante();          
                break;
                case 7:  sistema.mostrarEstudiantes();        
                break;
                case 8:  sistema.mostrarMaterias();           
                break;
                case 9:  sistema.mostrarReportePromedios();   
                break;
                case 10: System.out.println("Cerrando el sistema."); 
                break;
            }

            if (opcion != 10) {
                Consola.pausar();
            }

        } while (opcion != 10);
    }
}
