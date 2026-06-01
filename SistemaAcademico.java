import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaAcademico {

    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Profesor> profesores;
    private ArrayList<Materia> materias;

    public SistemaAcademico() {
        this.estudiantes = new ArrayList<>();
        this.profesores = new ArrayList<>();
        this.materias = new ArrayList<>();
    }

    public void registrarEstudiante() {
        System.out.println("\n--- REGISTRAR ESTUDIANTE ---");

        String matricula = Consola.leerTexto("Matrícula: ");
        if (buscarEstudiantePorMatricula(matricula) != null) {
            System.out.println("Ya existe un estudiante con esa matrícula.");
            return;
        }

        String nombre   = Consola.leerTexto("Nombre: ");
        String apellido = Consola.leerTexto("Apellido: ");
        int    edad     = Consola.leerEnteroPositivo("Edad: ");
        String carrera  = Consola.leerTexto("Carrera: ");
        LocalDate fecha = Consola.leerFecha("Fecha de inscripción (dd/mm/aaaa): ");

        estudiantes.add(new Estudiante(matricula, nombre, apellido, edad, carrera, fecha));
        System.out.println("Estudiante registrado correctamente.");
    }

    
    public void registrarProfesor() {
        System.out.println("\n--- REGISTRAR PROFESOR ---");

        String codigo = Consola.leerTexto("Código: ");
        if (buscarProfesorPorCodigo(codigo) != null) {
            System.out.println("Ya existe un profesor con ese código.");
            return;
        }

        String nombre       = Consola.leerTexto("Nombre: ");
        String apellido     = Consola.leerTexto("Apellido: ");
        int    edad         = Consola.leerEnteroPositivo("Edad: ");
        String especialidad = Consola.leerTexto("Especialidad: ");

        profesores.add(new Profesor(codigo, nombre, apellido, edad, especialidad));
        System.out.println("Profesor registrado correctamente.");
    }

   
    public void registrarMateria() {
        System.out.println("\n--- REGISTRAR MATERIA ---");

        String codigo = Consola.leerTexto("Código de la materia: ");
        if (buscarMateriaPorCodigo(codigo) != null) {
            System.out.println("Ya existe una materia con ese código.");
            return;
        }

        String nombre   = Consola.leerTexto("Nombre de la materia: ");
        int    creditos = Consola.leerEnteroPositivo("Créditos: ");

        materias.add(new Materia(codigo, nombre, creditos));
        System.out.println("Materia registrada correctamente.");
    }

    public void asignarMateriaAEstudiante() {
        System.out.println("\n--- ASIGNAR MATERIA A ESTUDIANTE ---");

        if (estudiantes.isEmpty()) { System.out.println("No hay estudiantes registrados."); return; }
        if (materias.isEmpty())    { System.out.println("No hay materias registradas.");    return; }

        String matricula = Consola.leerTexto("Matrícula del estudiante: ");
        Estudiante estudiante = buscarEstudiantePorMatricula(matricula);
        if (estudiante == null) { System.out.println("No se encontró ese estudiante."); return; }

        System.out.println("Estudiante: " + estudiante.getNombreCompleto());
        System.out.println("\nMaterias disponibles:");
        for (int i = 0; i < materias.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + materias.get(i).obtenerInformacion());
        }

        String codigoMateria = Consola.leerTexto("Código de la materia a asignar: ");
        Materia materia = buscarMateriaPorCodigo(codigoMateria);
        if (materia == null) { System.out.println("No se encontró esa materia."); return; }

        if (estudiante.asignarMateria(materia)) {
            System.out.println("Materia \"" + materia.getNombre() + "\" asignada correctamente.");
        } else {
            System.out.println("El estudiante ya está inscrito en esa materia.");
        }
    }

    public void registrarCalificacion() {
        System.out.println("\n--- REGISTRAR CALIFICACIÓN ---");

        if (estudiantes.isEmpty()) { System.out.println("No hay estudiantes registrados."); return; }

        String matricula = Consola.leerTexto("Matrícula del estudiante: ");
        Estudiante estudiante = buscarEstudiantePorMatricula(matricula);
        if (estudiante == null) { System.out.println("No se encontró ese estudiante."); return; }

        if (estudiante.getMaterias().isEmpty()) {
            System.out.println("Este estudiante no tiene materias asignadas.");
            return;
        }

        System.out.println("Estudiante: " + estudiante.getNombreCompleto());
        System.out.println("Materias del estudiante:");
        for (int i = 0; i < estudiante.getMaterias().size(); i++) {
            System.out.println("  " + (i + 1) + ". " + estudiante.getMaterias().get(i).obtenerInformacion());
        }

        String codigoMateria = Consola.leerTexto("Código de la materia: ");
        Materia materia = buscarMateriaPorCodigo(codigoMateria);
        if (materia == null) { System.out.println("No se encontró esa materia."); return; }

        double nota = Consola.leerNota("Calificación (0-100): ");
        Calificacion calificacion = new Calificacion(estudiante, materia, nota);

        if (estudiante.registrarCalificacion(calificacion)) {
            System.out.println("Calificación de " + nota + " registrada en " + materia.getNombre());
        } else {
            System.out.println("El estudiante no está inscrito en esa materia.");
        }
    }

   
    public void buscarEstudiante() {
        System.out.println("\n--- BUSCAR ESTUDIANTE ---");
        System.out.println("  1. Por matrícula");
        System.out.println("  2. Por nombre");
        System.out.print("Opción: ");
        int opcion = Consola.leerEntero();

        if (opcion == 1) {
            String matricula = Consola.leerTexto("Matrícula: ");
            Estudiante e = buscarEstudiantePorMatricula(matricula);
            if (e != null) mostrarDetallesEstudiante(e);
            else System.out.println("No se encontró ese estudiante.");

        } else if (opcion == 2) {
            String texto = Consola.leerTexto("Nombre o apellido: ");
            ArrayList<Estudiante> resultados = new ArrayList<>();
            for (Estudiante e : estudiantes) {
                if (e.getnombre().toLowerCase().contains(texto.toLowerCase()) ||
                    e.getapellido().toLowerCase().contains(texto.toLowerCase())) {
                    resultados.add(e);
                }
            }
            if (resultados.isEmpty()) {
                System.out.println("No se encontraron estudiantes con ese nombre.");
            } else {
                for (Estudiante e : resultados) mostrarDetallesEstudiante(e);
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }

    public void mostrarEstudiantes() {
        System.out.println("\n--- LISTA DE ESTUDIANTES ---");
        if (estudiantes.isEmpty()) { System.out.println("No hay estudiantes registrados."); return; }

        System.out.println("Total: " + estudiantes.size());
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println((i + 1) + ". " + estudiantes.get(i).obtenerInformacion());
        }
    }

   
    public void mostrarMaterias() {
        System.out.println("\n--- LISTA DE MATERIAS ---");
        if (materias.isEmpty()) { System.out.println("No hay materias registradas."); return; }

        System.out.println("Total: " + materias.size());
        for (int i = 0; i < materias.size(); i++) {
            System.out.println((i + 1) + ". " + materias.get(i).obtenerInformacion());
        }
    }

    
    public void mostrarReportePromedios() {
        System.out.println("\n--- REPORTE DE PROMEDIOS ---");
        if (estudiantes.isEmpty()) { System.out.println("No hay estudiantes registrados."); return; }

        for (Estudiante e : estudiantes) {
            System.out.println("\nEstudiante: " + e.getNombreCompleto() + " [" + e.getMatricula() + "]");
            if (e.getCalificaciones().isEmpty()) {
                System.out.println("  Sin calificaciones registradas.");
            } else {
                for (Calificacion c : e.getCalificaciones()) {
                    System.out.println("  " + c.obtenerInformacion());
                }
                String estado = e.aprobo() ? "APROBÓ" : "REPROBÓ";
                System.out.println("  Promedio: " + String.format("%.2f", e.calcularPromedio()) + "  ->  " + estado);
            }
        }
    }

   
    private Estudiante buscarEstudiantePorMatricula(String matricula) {
        for (Estudiante e : estudiantes) {
            if (e.getMatricula().equalsIgnoreCase(matricula)) return e;
        }
        return null;
    }

    private Profesor buscarProfesorPorCodigo(String codigo) {
        for (Profesor p : profesores) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) return p;
        }
        return null;
    }

    private Materia buscarMateriaPorCodigo(String codigo) {
        for (Materia m : materias) {
            if (m.getCodigo().equalsIgnoreCase(codigo)) return m;
        }
        return null;
    }

    private void mostrarDetallesEstudiante(Estudiante e) {
        System.out.println("\n" + e.obtenerInformacion());

        if (e.getMaterias().isEmpty()) {
            System.out.println("  Materias: (ninguna asignada)");
        } else {
            System.out.println("  Materias inscritas:");
            for (Materia m : e.getMaterias()) {
                System.out.println("    - " + m.getNombre());
            }
        }

        if (!e.getCalificaciones().isEmpty()) {
            System.out.println("  Calificaciones:");
            for (Calificacion c : e.getCalificaciones()) {
                System.out.println("    - " + c.obtenerInformacion());
            }
            System.out.println("  Promedio: " + String.format("%.2f", e.calcularPromedio()));
        }
    }
}
