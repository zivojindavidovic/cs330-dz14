package rs.ac.metropolitan.dz_14.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import rs.ac.metropolitan.dz_14.data.Activity
import rs.ac.metropolitan.dz_14.data.Company

@Composable
fun CompanyListPage(vm: AppViewModel, paddingValues: PaddingValues) {
    val companies = vm.companies.observeAsState(emptyList())
    LaunchedEffect(vm.loadCompanies()) {
    }
    Column {
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(companies.value) { companies ->
                CompanyRow(companies)
            }
        }
    }
}

@Composable
fun CompanyRow(company: Company) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { print("Company: $company") }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = company.logo,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(250.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = company.title,
                    fontSize = 18.sp
                )
                Text(
                    text = "Company Activity: ${company.activity}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun TabScreen(vm: AppViewModel, paddingValues: PaddingValues) {
    val tabs = listOf("IT", "MANAGEMENT", "DESIGN")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = vm.tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = vm.tabIndex == index,
                    onClick = { vm.tabIndex = index }
                )
            }
        }
        when (vm.tabIndex) {
            0 -> {
                vm.setTabActivity(Activity.IT)
                CompanyListPage(vm, paddingValues)
            }

            1 -> {
                vm.setTabActivity(Activity.MANAGEMENT)
                CompanyListPage(vm, paddingValues)
            }

            2 -> {
                vm.setTabActivity(Activity.DESIGN)
                CompanyListPage(vm, paddingValues)
            }
        }
    }
}