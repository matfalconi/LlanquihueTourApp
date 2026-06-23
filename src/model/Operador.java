package model;

/**
 * Operador turistico asociado a la agencia.
 * Hereda de Persona y agrega datos de su empresa.
 */
public class Operador extends Persona {

    private String empresa;
    private int aniosExperiencia;

    public Operador(String rut, String nombre, Contacto contacto,
                    String empresa, int aniosExperiencia) {
        super(rut, nombre, contacto);
        this.empresa = empresa;
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public String getRol() {
        return "OPERADOR";
    }

    @Override
    public String getDetalle() {
        return empresa + " - " + aniosExperiencia + " anios exp.";
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Empresa: " + empresa
                + " | Experiencia: " + aniosExperiencia + " anios";
    }
}
