# LlanquihueTourApp

Sistema de gestión de colaboradores para la agencia de turismo **Llanquihue Tour**
(Región de Los Lagos, Chile). Corresponde a la Evaluación de la Semana 5 del curso
*Desarrollo Orientado a Objetos I*: "Organización modular y creación de una librería
personalizada".

## Descripción del sistema

La aplicación permite administrar a las personas vinculadas a la operación de la
agencia (guías, operadores y proveedores). El sistema:

- Lee los datos desde un archivo externo `.csv`.
- Los carga en una colección dinámica (`ArrayList`) en memoria.
- Valida la información ingresada y descarta los registros inválidos sin detener la
  carga, usando manejo de errores con `try-catch`.
- Muestra los registros por consola y permite búsquedas y filtros automatizados
  (por RUT, por nombre y por rol).

## Estructura del proyecto (paquetes)

```
LlanquihueTourApp/
├── data/
│   └── personas.csv          # Datos externos de los colaboradores
└── src/
    ├── model/                # Modelo de dominio (POO + composición)
    │   ├── Persona.java       # Clase base abstracta
    │   ├── Guia.java          # Hereda de Persona
    │   ├── Operador.java      # Hereda de Persona
    │   ├── Proveedor.java     # Hereda de Persona
    │   └── Contacto.java      # Objeto de composición (Persona "tiene un" Contacto)
    ├── util/                 # Utilidades reutilizables (librería personalizada)
    │   ├── ValidadorDatos.java   # Validaciones de RUT, email, texto y números
    │   ├── LectorArchivo.java    # Lectura y parsing del CSV con try-catch
    │   └── TablaConsola.java     # Impresión de tablas alineadas por consola
    ├── service/              # Lógica de negocio
    │   └── GestorPersonas.java   # Colección + búsqueda y filtrado
    └── app/                  # Punto de entrada
        └── Main.java             # Clase principal (método main)
```

### Clases implementadas

| Paquete   | Clase            | Rol |
|-----------|------------------|-----|
| `model`   | `Persona`        | Clase base abstracta con atributos comunes y composición con `Contacto`. |
| `model`   | `Contacto`       | Datos de contacto (email, teléfono, ciudad). Usado por composición. |
| `model`   | `Guia`           | Guía turístico; agrega especialidad e idiomas. |
| `model`   | `Operador`       | Operador turístico; agrega empresa y años de experiencia. |
| `model`   | `Proveedor`      | Proveedor de servicios; agrega tipo y valor del servicio. |
| `util`    | `ValidadorDatos` | Métodos estáticos de validación que lanzan excepciones. |
| `util`    | `LectorArchivo`  | Lee el CSV y construye los objetos del modelo. |
| `util`    | `TablaConsola`   | Imprime los datos en tablas alineadas con bordes. |
| `service` | `GestorPersonas` | Encapsula el `ArrayList` y ofrece búsqueda/filtrado. |
| `app`     | `Main`           | Orquesta la ejecución del programa. |

## Conceptos aplicados

- **Modularidad**: código organizado en cuatro paquetes funcionales.
- **Encapsulamiento**: todos los atributos son `private` con getters/setters.
- **Herencia**: `Guia`, `Operador` y `Proveedor` extienden a `Persona`.
- **Composición**: `Persona` contiene un objeto `Contacto`.
- **Polimorfismo**: método abstracto `getRol()` y `toString()` sobrescrito.
- **Colecciones genéricas**: `ArrayList<Persona>`.
- **Lectura de archivos** con `BufferedReader` y manejo de errores `try-catch`.

## Cómo ejecutar el programa

### Desde IntelliJ IDEA
1. Abrir el proyecto `LlanquihueTourApp`.
2. Asegurarse de que la carpeta `src` esté marcada como *Sources Root*
   (clic derecho sobre `src` → *Mark Directory as* → *Sources Root*).
3. Abrir `src/app/Main.java`.
4. Ejecutar con el botón ▶ (*Run 'Main'*).

> El programa lee `data/personas.csv` mediante una ruta relativa a la raíz del
> proyecto, que es el directorio de trabajo por defecto en IntelliJ.

### Desde la línea de comandos
```bash
# Compilar
javac -d out $(find src -name "*.java")

# Ejecutar (desde la raíz del proyecto, para que encuentre data/personas.csv)
java -cp out app.Main
```

## Datos de ejemplo

El archivo `data/personas.csv` incluye guías, operadores y proveedores. La última
línea contiene datos inválidos de forma intencional para demostrar que el sistema
los detecta, informa y omite sin interrumpir la ejecución.

## Autor

Matías Falconi — Duoc UC, Desarrollo Orientado a Objetos I.
