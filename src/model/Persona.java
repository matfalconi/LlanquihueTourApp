package model;

/**
 * Clase base abstracta para todas las personas vinculadas a la operacion
 * de la agencia (guias, operadores y proveedores).
 *
 * Aplica buenas practicas de POO:
 *  - Atributos privados.
 *  - Constructor, getters y setters.
 *  - Metodo toString().
 *  - COMPOSICION: cada Persona "tiene un" objeto Contacto.
 */
public abstract class Persona {

    private String rut;
    private String nombre;
    private Contacto contacto; // Composicion

    public Persona(String rut, String nombre, Contacto contacto) {
        this.rut = rut;
        this.nombre = nombre;
        this.contacto = contacto;
    }

    /**
     * Cada subclase define como se describe su rol dentro de la agencia.
     */
    public abstract String getRol();

    /**
     * Detalle resumido y especifico de cada rol, usado para mostrar
     * una columna compacta en las tablas por consola.
     */
    public abstract String getDetalle();

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (RUT: %s) -> %s",
                getRol(), nombre, rut, contacto);
    }
}
