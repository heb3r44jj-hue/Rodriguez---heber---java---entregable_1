public class Profesor extends Persona {

    private String codigo;
    private String especialidad;

    public Profesor(String codigo, String nombre, String apellido, int edad, String especialidad) {
        super(nombre, apellido, edad);
        this.codigo = codigo;
        this.especialidad = especialidad;
    }

    @Override
    public String obtenerInformacion() {
        return "Profesor  | Código: " + codigo +
               " | Nombre: " + getNombreCompleto() +
               " | Especialidad: " + especialidad;
    }

   
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}
