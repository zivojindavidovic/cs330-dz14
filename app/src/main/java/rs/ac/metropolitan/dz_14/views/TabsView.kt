package rs.ac.metropolitan.dz_14.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun TabView() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("IT", "MANAGEMENT", "DESIGN")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> {
                tabIndex = 0
               Text(text = "IT")
            }
            1 -> {
                tabIndex = 1
                Text(text = "MANAGEMENT")
            }
            2 -> {
                tabIndex = 2
                Text(text = "DESIGN")
            }
        }
    }
}