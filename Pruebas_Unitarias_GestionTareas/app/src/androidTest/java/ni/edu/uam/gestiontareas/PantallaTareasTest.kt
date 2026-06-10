package ni.edu.uam.gestiontareas

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class PantallaTareasTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun campoEntrada_aceptaTextoCorrectamente() {
        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Estudiar pruebas")

        composeTestRule
            .onNodeWithText("Estudiar pruebas")
            .assertExists()
    }

    @Test
    fun agregarTarea_apareceEnPantalla() {
        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Comprar cuaderno")

        composeTestRule
            .onNodeWithTag("botonAgregar")
            .performClick()

        composeTestRule
            .onNodeWithText("Comprar cuaderno")
            .assertExists()
    }

    @Test
    fun botonAgregar_respondeAlClickCorrectamente() {
        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Realizar práctica")

        composeTestRule
            .onNodeWithText("Agregar")
            .performClick()

        composeTestRule
            .onNodeWithText("Realizar práctica")
            .assertExists()
    }

    @Test
    fun eliminarTarea_desapareceDeLaLista() {
        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Tarea para eliminar")

        composeTestRule
            .onNodeWithTag("botonAgregar")
            .performClick()

        composeTestRule
            .onNodeWithText("Tarea para eliminar")
            .assertExists()

        composeTestRule
            .onNodeWithTag("eliminar_1")
            .performClick()

        composeTestRule
            .onAllNodesWithText("Tarea para eliminar")
            .assertCountEquals(0)
    }

    @Test
    fun mostrarPendientes_cantidadEsCorrecta() {
        composeTestRule
            .onNodeWithTag("contadorPendientes")
            .assertTextEquals("Pendientes: 0")

        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Primera tarea")

        composeTestRule
            .onNodeWithTag("botonAgregar")
            .performClick()

        composeTestRule
            .onNodeWithTag("contadorPendientes")
            .assertTextEquals("Pendientes: 1")
    }

    @Test
    fun completarTarea_actualizaContadorPendientes() {
        composeTestRule
            .onNodeWithTag("campoTitulo")
            .performTextInput("Completar informe")

        composeTestRule
            .onNodeWithTag("botonAgregar")
            .performClick()

        composeTestRule
            .onNodeWithTag("contadorPendientes")
            .assertTextEquals("Pendientes: 1")

        composeTestRule
            .onNodeWithTag("check_1")
            .performClick()

        composeTestRule
            .onNodeWithTag("contadorPendientes")
            .assertTextEquals("Pendientes: 0")
    }
}