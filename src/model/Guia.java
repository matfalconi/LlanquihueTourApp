package model;

/**
 * Guia turistico de la agencia Llanquihue Tour.
 * Hereda de Persona y agrega atributos propios de su rol.
 */
public class Guia extends Persona {

    private String especialidad; // ej. rutas gastronomicas, paseos lacustres
    private String idiomas;      // ej. "Espanol|Ingles"

    public Guia(String rut, String nombre, Contacto contacto,
                String especialidad, String idiomas) {
        super(rut, nombre, contacto);
        this.especialidad = especialidad;
        this.idiomas = idiomas;
    }

    @Override
    public String getRol() {
        return "GUIA";
    }

    @Override
    public String getDetalle() {
        return especialidad + " (" + idiomas + ")";
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Especialidad: " + especialidad
                + " | Idiomas: " + idiomas;
    }
}
