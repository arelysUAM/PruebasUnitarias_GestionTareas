package ni.edu.uam.gestiontareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

enum class FiltroTareas {
    TODAS,
    PENDIENTES,
    COMPLETADAS
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PantallaTareas()
            }
        }
    }
}

@Composable
fun PantallaTareas(modifier: Modifier = Modifier) {
    var titulo by remember { mutableStateOf("") }
    var siguienteId by remember { mutableIntStateOf(1) }
    var filtroSeleccionado by remember { mutableStateOf(FiltroTareas.TODAS) }
    var ordenarAlfabeticamente by remember { mutableStateOf(false) }

    val tareas = remember { mutableStateListOf<Tarea>() }

    val totalTareas = tareas.size
    val tareasCompletadas = tareas.count { it.completada }
    val tareasPendientes = tareas.count { !it.completada }

    val porcentajeCompletadas = if (totalTareas == 0) {
        0
    } else {
        (tareasCompletadas * 100) / totalTareas
    }

    val tareasFiltradas = when (filtroSeleccionado) {
        FiltroTareas.TODAS -> tareas
        FiltroTareas.PENDIENTES -> tareas.filter { !it.completada }
        FiltroTareas.COMPLETADAS -> tareas.filter { it.completada }
    }.let { lista ->
        if (ordenarAlfabeticamente) {
            lista.sortedBy { it.titulo.lowercase() }
        } else {
            lista
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Gestión de Tareas",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título de la tarea") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .testTag("campoTitulo")
        )

        Button(
            onClick = {
                if (titulo.isNotBlank()) {
                    tareas.add(
                        Tarea(
                            id = siguienteId,
                            titulo = titulo,
                            descripcion = "Tarea registrada desde la app",
                            completada = false
                        )
                    )
                    siguienteId++
                    titulo = ""
                }
            },
            modifier = Modifier
                .padding(top = 12.dp)
                .testTag("botonAgregar")
        ) {
            Text("Agregar")
        }

        Text(
            text = "Pendientes: $tareasPendientes",
            modifier = Modifier
                .padding(top = 16.dp)
                .testTag("contadorPendientes")
        )

        Text(
            text = "Completadas: $porcentajeCompletadas%",
            modifier = Modifier
                .padding(top = 8.dp)
                .testTag("porcentajeCompletadas")
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = filtroSeleccionado == FiltroTareas.TODAS,
                onClick = { filtroSeleccionado = FiltroTareas.TODAS },
                label = { Text("Todas") },
                modifier = Modifier.testTag("filtroTodas")
            )

            FilterChip(
                selected = filtroSeleccionado == FiltroTareas.PENDIENTES,
                onClick = { filtroSeleccionado = FiltroTareas.PENDIENTES },
                label = { Text("Pendientes") },
                modifier = Modifier.testTag("filtroPendientes")
            )

            FilterChip(
                selected = filtroSeleccionado == FiltroTareas.COMPLETADAS,
                onClick = { filtroSeleccionado = FiltroTareas.COMPLETADAS },
                label = { Text("Completadas") },
                modifier = Modifier.testTag("filtroCompletadas")
            )
        }

        OutlinedButton(
            onClick = {
                ordenarAlfabeticamente = !ordenarAlfabeticamente
            },
            modifier = Modifier
                .padding(top = 12.dp)
                .testTag("botonOrdenar")
        ) {
            Text(
                text = if (ordenarAlfabeticamente) {
                    "Orden A-Z activado"
                } else {
                    "Ordenar A-Z"
                }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .testTag("listaTareas")
        ) {
            items(tareasFiltradas, key = { it.id }) { tarea ->
                FilaTarea(
                    tarea = tarea,
                    onCompletar = {
                        val index = tareas.indexOfFirst { it.id == tarea.id }
                        if (index != -1) {
                            tareas[index] = tarea.copy(completada = !tarea.completada)
                        }
                    },
                    onEliminar = {
                        tareas.remove(tarea)
                    }
                )
            }
        }
    }
}

@Composable
fun FilaTarea(
    tarea: Tarea,
    onCompletar: () -> Unit,
    onEliminar: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .testTag("tarea_${tarea.id}"),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = tarea.titulo)

            Text(
                text = if (tarea.completada) "Completada" else "Pendiente",
                modifier = Modifier.testTag("estado_${tarea.id}")
            )
        }

        Checkbox(
            checked = tarea.completada,
            onCheckedChange = {
                onCompletar()
            },
            modifier = Modifier.testTag("check_${tarea.id}")
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = onEliminar,
            modifier = Modifier.testTag("eliminar_${tarea.id}")
        ) {
            Text("Eliminar")
        }
    }
}