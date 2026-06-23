package app;

import model.Persona;
import service.GestorPersonas;
import util.LectorArchivo;

import java.util.List;

/**
 * Clase principal del sistema de gestion de personas de Llanquihue Tour.
 *
 * Flujo de ejecucion:
 *  1. Lee los datos desde un archivo CSV externo.
 *  2. Los carga en una coleccion (ArrayList) a traves del GestorPersonas.
 *  3. Muestra todos los registros por consola en formato de tabla.
 *  4. Aplica busquedas y filtros automatizados.
 */
public class Main {

    // Ruta relativa a la raiz del proyecto (directorio de trabajo en IntelliJ).
    private static final String RUTA_DATOS = "data/personas.csv";

    public static void main(String[] args) {
        encabezado();

        // 1. Carga de datos desde archivo externo
        System.out.println("  Cargando datos desde: " + RUTA_DATOS);
        LectorArchivo lector = new LectorArchivo();
        List<Persona> personas = lector.cargarPersonas(RUTA_DATOS);

        // 2. Almacenamiento en la coleccion
        GestorPersonas gestor = new GestorPersonas();
        gestor.agregarTodas(personas);
        System.out.println("  Registros cargados correctamente: " + gestor.cantidad());

        // 3. Resumen por rol
        mostrarResumen(gestor);

        // 4. Visualizacion de todos los registros (tabla ordenada)
        gestor.mostrarTodas();

        // 5a. Filtrado por rol
        gestor.mostrarTabla("FILTRO POR ROL: GUIAS", gestor.filtrarPorRol("GUIA"));
        gestor.mostrarTabla("FILTRO POR ROL: PROVEEDORES", gestor.filtrarPorRol("PROVEEDOR"));

        // 5b. Busqueda por RUT
        String rutBuscado = "12345678-9";
        seccion("BUSQUEDA POR RUT: " + rutBuscado);
        Persona encontrada = gestor.buscarPorRut(rutBuscado);
        if (encontrada != null) {
            System.out.println("  Encontrado -> " + encontrada);
        } else {
            System.out.println("  No se encontro una persona con ese RUT.");
        }

        // 5c. Busqueda por nombre
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
