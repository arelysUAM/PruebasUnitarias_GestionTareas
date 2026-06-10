package ni.edu.uam.gestiontareas

import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

class GestorTareasTest {

    @Test
    fun agregarTarea_incrementaListaEnUno() {
        val gestor = GestorTareas()
        val tarea = Tarea(
            id = 1,
            titulo = "Estudiar Kotlin",
            descripcion = "Repasar pruebas unitarias"
        )

        gestor.agregarTarea(tarea)

        Assert.assertEquals(1, gestor.obtenerTodas().size)
    }

    @Test
    fun eliminarTarea_desapareceDeLaLista() {
        val gestor = GestorTareas()
        val tarea = Tarea(
            id = 1,
            titulo = "Hacer tarea",
            descripcion = "Completar práctica"
        )

        gestor.agregarTarea(tarea)
        gestor.eliminarTarea(1)

        Assert.assertTrue(gestor.obtenerTodas().isEmpty())
    }

    @Test
    fun completarTarea_cambiaEstadoACompletada() {
        val gestor = GestorTareas()
        val tarea = Tarea(
            id = 1,
            titulo = "Leer guía",
            descripcion = "Leer documento de práctica"
        )

        gestor.agregarTarea(tarea)
        gestor.completarTarea(1)

        val tareaCompletada = gestor.obtenerTodas().first()

        Assert.assertTrue(tareaCompletada.completada)
    }

    @Test
    fun contarTareasPendientes_retornaValorCorrecto() {
        val gestor = GestorTareas()

        gestor.agregarTarea(
            Tarea(
                id = 1,
                titulo = "Tarea pendiente",
                descripcion = "Debe seguir pendiente"
            )
        )

        gestor.agregarTarea(
            Tarea(
                id = 2,
                titulo = "Tarea completada",
                descripcion = "Debe completarse"
            )
        )

        gestor.completarTarea(2)

        Assert.assertEquals(1, gestor.contarPendientes())
    }

    @Test
    fun listaVacia_retornaCeroPendientes() {
        val gestor = GestorTareas()

        Assert.assertEquals(0, gestor.contarPendientes())
    }

    @Test
    fun obtenerPendientes_noIncluyeTareasCompletadas() {
        val gestor = GestorTareas()

        gestor.agregarTarea(
            Tarea(
                id = 1,
                titulo = "Pendiente",
                descripcion = "Sin completar"
            )
        )

        gestor.agregarTarea(
            Tarea(
                id = 2,
                titulo = "Completada",
                descripcion = "Ya completada"
            )
        )

        gestor.completarTarea(2)

        val pendientes = gestor.obtenerPendientes()

        Assert.assertEquals(1, pendientes.size)
        Assert.assertFalse(pendientes.any { it.completada })
    }

    @Test
    fun pruebaIntencionalFallida_contadorIncorrecto() {
        val gestor = GestorTareas()

        gestor.agregarTarea(
            Tarea(
                id = 1,
                titulo = "Tarea pendiente",
                descripcion = "Esta tarea no está completada"
            )
        )

        assertEquals(1, gestor.contarPendientes())
    }
}