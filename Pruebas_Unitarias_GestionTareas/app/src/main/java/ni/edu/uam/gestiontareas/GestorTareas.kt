package ni.edu.uam.gestiontareas

class GestorTareas {

    private val tareas = mutableListOf<Tarea>()

    fun agregarTarea(tarea: Tarea) {
        tareas.add(tarea)
    }

    fun eliminarTarea(id: Int) {
        tareas.removeIf { it.id == id }
    }

    fun completarTarea(id: Int) {
        val indice = tareas.indexOfFirst { it.id == id }

        if (indice != -1) {
            val tareaActual = tareas[indice]
            tareas[indice] = tareaActual.copy(completada = true)
        }
    }

    fun obtenerTodas(): List<Tarea> {
        return tareas
    }

    fun obtenerPendientes(): List<Tarea> {
        return tareas.filter { !it.completada }
    }

    fun contarPendientes(): Int {
        return tareas.count { !it.completada }
    }
}