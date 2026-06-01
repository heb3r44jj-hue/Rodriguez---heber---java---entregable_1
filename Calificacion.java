public class Calificacion {
    
    private Estudiante estudiante;
    private Materia materia;
    private double nota;

    public Calificacion(Estudiante estudiante, Materia materia, double nota) {
        this.estudiante = estudiante;
        this.materia = materia;
        this.nota = nota;
    }

    public String obtenerInformacion() {
        return "Estudiante: " + materia.getNombre() +
               " ||Nota: " + String .format("%.2f", nota);
    }

    public Estudiante getEstudiante() { return estudiante; }
    public Materia getMateria() { return materia; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

}
