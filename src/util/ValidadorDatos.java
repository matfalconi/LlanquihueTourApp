package util;

/**
 * Utilidades de validacion basica de datos ingresados.
 * Lanza IllegalArgumentException cuando un dato no es valido,
 * lo que permite manejar errores con try-catch al cargar registros.
 */
public class ValidadorDatos {

    /** Verifica que un texto no sea nulo ni vacio. */
    public static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo '" + campo + "' no puede estar vacio.");
        }
        return valor.trim();
    }

    /** Valida un RUT chileno con formato simple (numeros, guion y digito verificador). */
    public static String validarRut(String rut) {
        String r = validarTexto(rut, "rut");
        if (!r.matches("\\d{7,8}-[0-9kK]")) {
            throw new IllegalArgumentException("RUT con formato invalido: " + rut);
        }
        return r;
    }

    /** Valida que el email contenga '@' y un punto. */
    public static String validarEmail(String email) {
        String e = validarTexto(email, "email");
        if (!e.matches("^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Email invalido: " + email);
        }
        return e;
    }

    /** Convierte a entero y valida que no sea negativo. */
    public static int validarEnteroNoNegativo(String valor, String campo) {
        try {
            int n = Integer.parseInt(valor.trim());
            if (n < 0) {
                throw new IllegalArgumentException("El campo '" + campo + "' no puede ser negativo.");
            }
            return n;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El campo '" + campo + "' debe ser numerico: " + valor);
        }
    }
}
