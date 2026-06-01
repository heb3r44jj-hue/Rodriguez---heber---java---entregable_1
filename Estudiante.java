import java.time.LocalDate;
import java.util.ArrayList;

public class Estudiante extends Persona {

    private String matricula;
    private String carrera;
    private LocalDate fechaInscripcion;
    private ArrayList<Materia> materias;
    private ArrayList<Calificacion> calificaciones;

    public Estudiante(String matricula, String nombre, String apellido, int edad, String carrera, LocalDate fechaInscripcion) {
        super(nombre, apellido, edad);
        this.matricula = matricula;
        this.carrera = carrera;
        this.fechaInscripcion = fechaInscripcion;
        this.materias = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
    }

    @Override
    public String obtenerInformacion() {
        return "Estudiante | Matrícula: " + matricula +
               " | Nombre: " + getNombreCompleto() +
               " | Carrera: " + carrera +
               " | Inscripción: " + fechaInscripcion;
    }

    public boolean asignarMateria(Materia materia) {
        for (Materia m : materias) {
            if (m.getCodigo().equalsIgnoreCase(materia.getCodigo())) {
                return false; // Ya está inscrito en esa materia
            }
        }
        materias.add(materia);
        return true;
    }

    public boolean registrarCalificacion(Calificacion calificacion) {
        
        boolean tieneMateria = false;
        for (Materia m : materias) {
            if (m.getCodigo().equalsIgnoreCase(calificacion.getMateria().getCodigo())) {
                tieneMateria = true;
                break;
            }
        }

        if (!tieneMateria) {
            return false;
        }

       
        for (int i = 0; i < calificaciones.size(); i++) {
            if (calificaciones.get(i).getMateria().getCodigo().equalsIgnoreCase(calificacion.getMateria().getCodigo())) {
                calificaciones.set(i, calificacion);
                return true;
            }
        }

        calificaciones.add(calificacion);
        return true;
    }

    public double calcularPromedio() {
        if (calificaciones.isEmpty()) {
            return 0.0;
        }
        double suma = 0;
        for (Calificacion c : calificaciones) {
            suma += c.getNota();
        }
        return suma / calificaciones.size();
    }

    public boolean aprobo() {
        return calcularPromedio() >= 60.0;
    }

    
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public LocalDate getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDate fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }

    public ArrayList<Materia> getMaterias() { return materias; }
    public ArrayList<Calificacion> getCalificaciones() { return calificaciones; }
}
