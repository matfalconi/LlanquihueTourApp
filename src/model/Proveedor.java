package model;

/**
 * Proveedor de servicios (transporte, alimentacion, alojamiento, etc.).
 * Hereda de Persona y agrega el tipo y valor del servicio.
 */
public class Proveedor extends Persona {

    private String tipoServicio;
    private int valorServicio; // en pesos chilenos

    public Proveedor(String rut, String nombre, Contacto contacto,
                     String tipoServicio, int valorServicio) {
        super(rut, nombre, contacto);
        this.tipoServicio = tipoServicio;
        this.valorServicio = valorServicio;
    }

    @Override
    public String getRol() {
        return "PROVEEDOR";
    }

    @Override
    public String getDetalle() {
        return tipoServicio + " - " + formatearValor();
    }

    /** Devuelve el valor con separador de miles, ej: $120.000 */
    public String formatearValor() {
        return "$" + String.format("%,d", valorServicio).replace(',', '.');
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(int valorServicio) {
        this.valorServicio = valorServicio;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Servicio: " + tipoServicio
                + " | Valor: " + formatearValor();
    }
}
