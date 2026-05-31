public abstract class Persona {
    private String nombre;
    private String apellido;
    private int edad;

    public Persona(String nombre, String apellido, int edad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public abstract String obtenerInformacion();

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public String getnombre() { return nombre; }
    public void setnombre(String nombre) { this.nombre = nombre; }
    
    public String getapellido(){ return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
}