package util;

import model.Contacto;
import model.Guia;
import model.Operador;
import model.Persona;
import model.Proveedor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LectorArchivo {

    private static final String SEPARADOR = ";";

    public List<Persona> cargarPersonas(String ruta) {
        List<Persona> personas = new ArrayList<>();
        int numeroLinea = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                linea = linea.trim();

                // Ignora lineas vacias y la cabecera comentada con '#'
                if (linea.isEmpty() || linea.startsWith("#")) {
                    continue;
                }

                try {
                    Persona p = construirPersona(linea);
                    personas.add(p);
                } catch (IllegalArgumentException e) {
                    System.out.println("  [AVISO] Linea " + numeroLinea
                            + " omitida: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo leer el archivo '" + ruta
                    + "': " + e.getMessage());
        }

        return personas;
    }

    private Persona construirPersona(String linea) {
        String[] c = linea.split(SEPARADOR);
        if (c.length < 8) {
            throw new IllegalArgumentException("Cantidad de columnas insuficiente (" + c.length + ").");
        }

        String tipo = ValidadorDatos.validarTexto(c[0], "tipo").toUpperCase();
        String rut = ValidadorDatos.validarRut(c[1]);
        String nombre = ValidadorDatos.validarTexto(c[2], "nombre");
        String email = ValidadorDatos.validarEmail(c[3]);
        String telefono = ValidadorDatos.validarTexto(c[4], "telefono");
        String ciudad = ValidadorDatos.validarTexto(c[5], "ciudad");

        Contacto contacto = new Contacto(email, telefono, ciudad);

        switch (tipo) {
            case "GUIA":
                String especialidad = ValidadorDatos.validarTexto(c[6], "especialidad");
                String idiomas = ValidadorDatos.validarTexto(c[7], "idiomas");
                return new Guia(rut, nombre, contacto, especialidad, idiomas);

            case "OPERADOR":
                String empresa = ValidadorDatos.validarTexto(c[6], "empresa");
                int anios = ValidadorDatos.validarEnteroNoNegativo(c[7], "aniosExperiencia");
                return new Operador(rut, nombre, contacto, empresa, anios);

            case "PROVEEDOR":
                String tipoServicio = ValidadorDatos.validarTexto(c[6], "tipoServicio");
                int valor = ValidadorDatos.validarEnteroNoNegativo(c[7], "valorServicio");
                return new Proveedor(rut, nombre, contacto, tipoServicio, valor);

            default:
                throw new IllegalArgumentException("Tipo de persona desconocido: " + tipo);
        }
    }
}
