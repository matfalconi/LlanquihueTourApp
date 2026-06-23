package app;

import model.Persona;
import service.GestorPersonas;
import util.LectorArchivo;

import java.util.List;


public class Main {

    
    private static final String RUTA_DATOS = "data/personas.csv";

    public static void main(String[] args) {
        encabezado();

        
        System.out.println("  Cargando datos desde: " + RUTA_DATOS);
        LectorArchivo lector = new LectorArchivo();
        List<Persona> personas = lector.cargarPersonas(RUTA_DATOS);

        
        GestorPersonas gestor = new GestorPersonas();
        gestor.agregarTodas(personas);
        System.out.println("  Registros cargados correctamente: " + gestor.cantidad());

        
        mostrarResumen(gestor);

        
        gestor.mostrarTodas();

        
        gestor.mostrarTabla("FILTRO POR ROL: GUIAS", gestor.filtrarPorRol("GUIA"));
        gestor.mostrarTabla("FILTRO POR ROL: PROVEEDORES", gestor.filtrarPorRol("PROVEEDOR"));

        
        String rutBuscado = "12345678-9";
        seccion("BUSQUEDA POR RUT: " + rutBuscado);
        Persona encontrada = gestor.buscarPorRut(rutBuscado);
        if (encontrada != null) {
            System.out.println("  Encontrado -> " + encontrada);
        } else {
            System.out.println("  No se encontro una persona con ese RUT.");
        }

        
        String nombreBuscado = "Carla";
        gestor.mostrarTabla("BUSQUEDA POR NOMBRE QUE CONTIENE: '" + nombreBuscado + "'",
                gestor.buscarPorNombre(nombreBuscado));

        pie();
    }

    private static void mostrarResumen(GestorPersonas gestor) {
        seccion("RESUMEN POR ROL");
        System.out.printf("   %-12s : %d%n", "Guias", gestor.contarPorRol("GUIA"));
        System.out.printf("   %-12s : %d%n", "Operadores", gestor.contarPorRol("OPERADOR"));
        System.out.printf("   %-12s : %d%n", "Proveedores", gestor.contarPorRol("PROVEEDOR"));
        System.out.printf("   %-12s : %d%n", "TOTAL", gestor.cantidad());
    }

    private static void encabezado() {
        System.out.println("==============================================================");
        System.out.println("||             LLANQUIHUE TOUR  -  Region de Los Lagos      ||");
        System.out.println("||              Sistema de Gestion de Colaboradores         ||");
        System.out.println("==============================================================");
    }

    private static void seccion(String titulo) {
        System.out.println();
        System.out.println(">> " + titulo);
        System.out.println("--------------------------------------------------------------");
    }

    private static void pie() {
        System.out.println();
        System.out.println("==============================================================");
        System.out.println("||                  Fin de la ejecucion                     ||");
        System.out.println("==============================================================");
    }
}
