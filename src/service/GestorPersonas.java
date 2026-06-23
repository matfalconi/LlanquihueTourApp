package service;

import model.Persona;
import util.TablaConsola;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que administra la coleccion de personas de la agencia.
 * Encapsula un ArrayList y ofrece operaciones de:
 *  - almacenamiento,
 *  - recorrido / visualizacion,
 *  - busqueda por RUT o nombre,
 *  - filtrado por rol.
 */
public class GestorPersonas {

    private final List<Persona> personas;

    public GestorPersonas() {
        this.personas = new ArrayList<>();
    }

    public void agregar(Persona persona) {
        if (persona != null) {
            personas.add(persona);
        }
    }

    public void agregarTodas(List<Persona> lista) {
        if (lista != null) {
            personas.addAll(lista);
        }
    }

    public int cantidad() {
        return personas.size();
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    /** Muestra todos los registros por consola en formato de tabla. */
    public void mostrarTodas() {
        mostrarTabla("LISTADO COMPLETO DE COLABORADORES", personas);
    }

    /** Muestra una lista de personas como tabla alineada con un titulo. */
    public void mostrarTabla(String titulo, List<Persona> lista) {
        String[] headers = {"N", "ROL", "NOMBRE", "RUT", "CIUDAD", "TELEFONO", "DETALLE"};
        List<String[]> filas = new ArrayList<>();
        int n = 1;
        for (Persona p : lista) {
            filas.add(new String[]{
                    String.valueOf(n++),
                    p.getRol(),
                    p.getNombre(),
                    p.getRut(),
                    p.getContacto().getCiudad(),
                    p.getContacto().getTelefono(),
                    p.getDetalle()
            });
        }
        TablaConsola.imprimir(titulo, headers, filas);
    }

    /** Cuenta cuantas personas tienen el rol indicado. */
    public int contarPorRol(String rol) {
        return filtrarPorRol(rol).size();
    }

    /** Busca una persona por su RUT exacto. Devuelve null si no existe. */
    public Persona buscarPorRut(String rut) {
        for (Persona p : personas) {
            if (p.getRut().equalsIgnoreCase(rut)) {
                return p;
            }
        }
        return null;
    }

    /** Busca personas cuyo nombre contenga el texto indicado. */
    public List<Persona> buscarPorNombre(String texto) {
        List<Persona> resultado = new ArrayList<>();
        String t = texto.toLowerCase();
        for (Persona p : personas) {
            if (p.getNombre().toLowerCase().contains(t)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /** Filtra las personas segun su rol (GUIA, OPERADOR, PROVEEDOR). */
    public List<Persona> filtrarPorRol(String rol) {
        List<Persona> resultado = new ArrayList<>();
        for (Persona p : personas) {
            if (p.getRol().equalsIgnoreCase(rol)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
