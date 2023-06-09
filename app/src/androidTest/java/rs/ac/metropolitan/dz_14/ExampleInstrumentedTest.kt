package rs.ac.metropolitan.dz_14

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import rs.ac.metropolitan.dz_14.views.InternetPermission
import rs.ac.metropolitan.dz_14.views.TabView

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val rule = createComposeRule()
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("rs.ac.metropolitan.dz_14", appContext.packageName)
    }

    @Test
    fun testStartingTab() {
        rule.setContent {
            TabView()
        }
        rule.onNode(
            hasText("IT") and hasClickAction()
        ).assertExists()
        rule.onNodeWithText("MANAGEMENT").assertExists()
    }

    @Test
    fun testClickAutoTab() {
        rule.setContent {
            TabView()
        }
        rule.onNodeWithText("MANAGEMENT").assertExists()
        rule.onNodeWithText("MANAGEMENT").performClick()
        rule.onNode(
            hasText("MANAGEMENT") and hasClickAction()
        ).assertExists()
    }

    @Test
    fun testRequestForInternet(){
        rule.setContent {
            val granted = remember {
                mutableStateOf(false)
            }
            val launcher = rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                granted.value = isGranted
            }
            InternetPermission(launcher = launcher)
        }
        rule.onNodeWithText("Internet permission not granted")
        rule.onNode(
            hasText("Request permission") and hasClickAction()
        ).assertExists()
    }
}