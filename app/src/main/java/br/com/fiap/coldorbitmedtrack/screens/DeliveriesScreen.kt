package br.com.fiap.coldorbitmedtrack.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.coldorbitmedtrack.components.InfoRow
import br.com.fiap.coldorbitmedtrack.components.StatusBadge
import br.com.fiap.coldorbitmedtrack.model.DeliveryBatch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveriesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    batches: List<DeliveryBatch>
) {
    var selectedFilter by remember { mutableStateOf("Todos") }
    val filters = listOf("Todos", "Em rota", "Crítico", "Pendente", "Entregue")
    val filteredBatches = if (selectedFilter == "Todos") batches else batches.filter { it.status == selectedFilter }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Entregas em rota") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar") } },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(filters) { filter ->
                    FilterChip(
                        selected = selectedFilter == filter,
                        onClick = { selectedFilter = filter },
                        label = { Text(filter) }
                    )
                }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filteredBatches) { batch ->
                    DeliveryBatchCard(batch = batch) {
                        navController.navigate("batchDetail/${batch.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun DeliveryBatchCard(batch: DeliveryBatch, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(batch.id, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Text(batch.destination, style = MaterialTheme.typography.bodyMedium)
                }
                StatusBadge(batch.status)
            }
            InfoRow(label = "Medicamento", value = batch.medicine)
            InfoRow(label = "Temperatura", value = batch.temperature)
            InfoRow(label = "Prioridade", value = batch.priority)
        }
    }
}
