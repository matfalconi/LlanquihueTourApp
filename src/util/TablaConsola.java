package util;

import java.util.List;

/**
 * Utilidad reutilizable para imprimir datos en consola con formato de tabla
 * (columnas alineadas y bordes), mejorando la calidad visual de la salida.
 *
 * Forma parte de la libreria personalizada del paquete 'util'.
 */
public class TablaConsola {

    /**
     * Imprime una tabla con titulo, encabezados y filas.
     * El ancho de cada columna se calcula automaticamente.
     *
     * @param titulo   texto que se muestra sobre la tabla
     * @param headers  nombres de las columnas
     * @param filas    lista de filas; cada fila es un arreglo con el mismo
     *                 numero de columnas que 'headers'
     */
    public static void imprimir(String titulo, String[] headers, List<String[]> filas) {
        int columnas = headers.length;
        int[] ancho = new int[columnas];

        // Ancho inicial segun los encabezados
        for (int i = 0; i < columnas; i++) {
            ancho[i] = headers[i].length();
        }
        // Ajusta el ancho segun el contenido de cada fila
        for (String[] fila : filas) {
            for (int i = 0; i < columnas; i++) {
                String valor = fila[i] == null ? "" : fila[i];
                if (valor.length() > ancho[i]) {
                    ancho[i] = valor.length();
                }
            }
        }

        String borde = construirBorde(ancho);
        int anchoTotal = borde.length();

        // Titulo centrado sobre la tabla
        if (titulo != null && !titulo.isEmpty()) {
            System.out.println();
            System.out.println(centrar(titulo, anchoTotal));
        }

        System.out.println(borde);
        System.out.println(construirFila(headers, ancho));
        System.out.println(borde);
        if (filas.isEmpty()) {
            System.out.println(centrar("(sin resultados)", anchoTotal));
        } else {
            for (String[] fila : filas) {
                System.out.println(construirFila(fila, ancho));
            }
        }
        System.out.println(borde);
    }

    private static String construirBorde(int[] ancho) {
        StringBuilder sb = new StringBuilder("+");
        for (int a : ancho) {
            sb.append(repetir('-', a + 2)).append('+');
        }
        return sb.toString();
    }

    private static String construirFila(String[] valores, int[] ancho) {
        StringBuilder sb = new StringBuilder("|");
        for (int i = 0; i < ancho.length; i++) {
            String valor = (i < valores.length && valores[i] != null) ? valores[i] : "";
            sb.append(' ').append(rellenar(valor, ancho[i])).append(" |");
        }
        return sb.toString();
    }

    private static String rellenar(String texto, int ancho) {
        StringBuilder sb = new StringBuilder(texto);
        while (sb.length() < ancho) {
            sb.append(' ');
        }
        return sb.toString();
    }

    private static String centrar(String texto, int ancho) {
        if (texto.length() >= ancho) {
            return texto;
        }
        int espacios = (ancho - texto.length()) / 2;
        return repetir(' ', espacios) + texto;
    }

    private static String repetir(char c, int veces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}
