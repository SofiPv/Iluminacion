# Iluminación | Escena 3D con Java y OpenGL

Proyecto académico desarrollado en Java para representar una escena 3D interactiva con iluminación, cámara y figuras geométricas.

El programa permite crear objetos en un entorno gráfico, modificar su posición, cambiar su color y activar efectos de luz sobre las figuras seleccionadas. Este repositorio forma parte de mi portafolio de trabajo y muestra el uso de programación orientada a objetos aplicada a gráficos 3D.

---

## Objetivo del proyecto

Aplicar conceptos de graficación computacional mediante una aplicación capaz de:

* Renderizar figuras geométricas en 3D.
* Utilizar una cámara configurable dentro de la escena.
* Aplicar iluminación sobre objetos seleccionados.
* Modificar posición, color y propiedades visuales de las figuras.
* Integrar una interfaz gráfica con controles de interacción.

---

## Tecnologías utilizadas

* Java
* Swing
* JOGL
* OpenGL
* NetBeans

---

## Estructura del repositorio

```text
Iluminacion/
├── Iluminacion/
│   ├── src/
│   │   ├── Programa.java
│   │   ├── models/
│   │   └── views/
│   ├── dist/
│   ├── nbproject/
│   ├── build.xml
│   └── manifest.mf
├── LICENSE
└── README.md
```

---

## Módulos principales

### `Programa.java`

Archivo principal del proyecto.
Configura la apariencia de la aplicación y ejecuta la ventana principal.

### `views/Iluminacion.java`

Contiene la ventana principal, los paneles de control, los botones de figuras y los eventos de teclado.

### `views/PanelIluminacion.java`

Panel encargado del renderizado OpenGL.
Dibuja la escena, configura la cámara, aplica profundidad, perspectiva e iluminación.

### `models/`

Contiene las clases que representan los elementos 3D del proyecto:

* `ModeloRedondo`
* `ModeloCubo`
* `Modelo12`
* `Modelo8`
* `Model20`
* `ModeloCamara`
* `FigureModel`
* `GlLight`

---

## Figuras disponibles

La aplicación permite crear las siguientes figuras:

* Esfera
* Cubo
* Dodecaedro
* Icosaedro
* Octaedro

Cada figura puede seleccionarse desde la interfaz para modificar su posición, color o estado de iluminación.

---

## Controles

### Movimiento de figura seleccionada

```text
W   Mover hacia arriba
S   Mover hacia abajo
A   Mover hacia la izquierda
D   Mover hacia la derecha
Q   Mover en profundidad positiva
E   Mover en profundidad negativa
```

### Cámara

```text
Shift + W/A/S/D/Q/E   Mover la cámara
Ctrl + W/A/S/D/Q/E    Cambiar el punto al que apunta la cámara
Ctrl + ↑ / ↓          Cambiar distancia de visión
```

### Propiedades visuales

```text
C   Cambiar color de la figura seleccionada
L   Activar o desactivar luz en la figura seleccionada
```

---

## Ejecución desde NetBeans

1. Abrir NetBeans.
2. Seleccionar la opción **Open Project**.
3. Abrir la carpeta:

```text
Iluminacion/Iluminacion
```

4. Ejecutar el proyecto desde el IDE.

---

## Ejecución desde archivo JAR

El proyecto incluye una versión compilada dentro de la carpeta `dist`.

Desde la terminal:

```bash
cd Iluminacion/dist
java -jar "Iluminaci_n.jar"
```

---

## Enfoque didáctico

Este proyecto permite observar de forma práctica conceptos básicos de graficación computacional:

* Sistema de coordenadas 3D.
* Movimiento de objetos.
* Cámara y perspectiva.
* Iluminación en una escena.
* Materiales y color.
* Organización de modelos mediante clases.

---

## Licencia

Este proyecto se distribuye bajo licencia MIT.
