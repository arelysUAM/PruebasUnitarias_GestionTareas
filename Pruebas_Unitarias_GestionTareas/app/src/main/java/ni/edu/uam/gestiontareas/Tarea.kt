package ni.edu.uam.gestiontareas

data class Tarea(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val completada: Boolean = false
)