package br.com.fiap.coldorbitmedtrack.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.coldorbitmedtrack.components.InfoRow
import br.com.fiap.coldorbitmedtrack.components.StatusBadge
import br.com.fiap.coldorbitmedtrack.model.DeliveryBatch
import br.com.fiap.coldorbitmedtrack.navigation.AppRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BatchDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    batch: DeliveryBatch?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(batch?.id ?: "Lote") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar") } }
            )
        }
    ) { innerPadding ->
        if (batch == null) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text("Lote não encontrado.")
            }
            return@Scaffold
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(batch.destination, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            Text(batch.route, style = MaterialTheme.typography.bodyMedium)
                        }
                        StatusBadge(batch.status)
                    }
                    InfoRow("Medicamento", batch.medicine)
                    InfoRow("Temperatura atual", batch.temperature)
                    InfoRow("Prioridade", batch.priority)
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("Observação operacional", fontWeight = FontWeight.Bold)
                    Text(batch.observation)
                }
            }

            Button(
                onClick = { navController.navigate(AppRoutes.updateStatus(batch.id)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Atualizar status")
            }
        }
    }
}
