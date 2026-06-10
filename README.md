# Gestión de Tareas - Pruebas Unitarias y de Interfaz

## Descripción

Este proyecto consiste en una aplicación Android desarrollada con **Kotlin** y **Jetpack Compose** para gestionar tareas pendientes.

La aplicación permite registrar tareas, visualizarlas en una lista, marcarlas como completadas, eliminarlas y consultar la cantidad de tareas pendientes. Además, incluye mejoras adicionales como filtros, ordenamiento alfabético y porcentaje de tareas completadas.

## Objetivo

Implementar una aplicación de gestión de tareas y validar su funcionamiento mediante pruebas unitarias y pruebas de interfaz con Jetpack Compose.

## Tecnologías utilizadas

* Kotlin
* Android Studio
* Jetpack Compose
* Material 3
* JUnit
* Compose UI Testing
* Gradle

## Estructura principal del proyecto

```text
app/
 └── src/
     ├── main/
     │   └── java/ni/edu/uam/gestiontareas/
     │       ├── MainActivity.kt
     │       ├── Tarea.kt
     │       └── GestorTareas.kt
     ├── test/
     │   └── java/ni/edu/uam/gestiontareas/
     │       └── GestorTareasTest.kt
     └── androidTest/
         └── java/ni/edu/uam/gestiontareas/
             └── PantallaTareasTest.kt
```

## Funcionalidades implementadas

* Registrar nuevas tareas.
* Mostrar todas las tareas registradas.
* Marcar tareas como completadas.
* Eliminar tareas.
* Mostrar cantidad total de tareas pendientes.
* Filtrar tareas completadas.
* Filtrar tareas pendientes.
* Ordenar tareas alfabéticamente.
* Mostrar porcentaje de tareas completadas.

## Modelo de datos

La aplicación utiliza una clase llamada `Tarea`, que representa cada tarea registrada.

Atributos principales:

* `id`: identificador único de la tarea.
* `titulo`: nombre de la tarea.
* `descripcion`: descripción breve.
* `completada`: estado de la tarea.

## Lógica de negocio

La clase `GestorTareas` administra las operaciones principales de la lista de tareas.

Métodos principales:

* `agregarTarea()`
* `eliminarTarea()`
* `completarTarea()`
* `obtenerTodas()`
* `obtenerPendientes()`
* `contarPendientes()`

## Pruebas unitarias

Las pruebas unitarias se encuentran en:

```text
app/src/test/
```

Estas pruebas validan la lógica de negocio de la aplicación.

Casos de prueba implementados:

* Agregar una tarea incrementa la lista en uno.
* Eliminar una tarea hace que desaparezca de la lista.
* Completar una tarea cambia su estado a completada.
* Contar tareas pendientes retorna el valor correcto.
* Una lista vacía retorna cero pendientes.
* Obtener tareas pendientes no incluye tareas completadas.

## Pruebas de interfaz

Las pruebas de interfaz se encuentran en:

```text
app/src/androidTest/
```

Estas pruebas validan el comportamiento visual de la aplicación usando Jetpack Compose Testing.

Casos de prueba implementados:

* El campo de entrada acepta texto correctamente.
* El botón Agregar responde al clic.
* Una tarea agregada aparece en pantalla.
* Una tarea eliminada desaparece de la lista.
* El contador de pendientes muestra la cantidad correcta.
* Al completar una tarea, el contador de pendientes se actualiza.
* Porcentaje de tareas completadas.

## Autor

Material elaborado como práctica académica para el aprendizaje de pruebas unitarias y pruebas de interfaz en aplicaciones Android con Jetpack Compose.
