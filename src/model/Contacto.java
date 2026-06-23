package model;

/**
 * Clase de valor que representa la informacion de contacto de una persona.
 * Se usa por COMPOSICION dentro de la clase Persona (una Persona "tiene un" Contacto).
 */
public class Contacto {

    private String email;
    private String telefono;
    private String ciudad;

    public Contacto(String email, String telefono, String ciudad) {
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return ciudad + " | " + email + " | " + telefono;
    }
}
