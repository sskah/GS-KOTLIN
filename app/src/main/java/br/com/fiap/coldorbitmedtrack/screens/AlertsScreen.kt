package br.com.fiap.coldorbitmedtrack.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.coldorbitmedtrack.model.FieldAlert

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    alerts: List<FieldAlert>,
    onAcknowledge: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Alertas críticos") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar") } }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(alerts) { alert ->
                AlertCard(alert = alert, onAcknowledge = { onAcknowledge(alert.id) })
            }
        }
    }
}

@Composable
fun AlertCard(alert: FieldAlert, onAcknowledge: () -> Unit) {
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
                    Text(alert.title, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Text(alert.batchId, style = MaterialTheme.typography.bodySmall)
                }
                Text(
                    text = alert.severity,
                    color = if (alert.severity == "Crítico") Color(0xFFB3261E) else Color(0xFF6D4C41),
                    fontWeight = FontWeight.Bold
                )
            }

            Text(alert.description)

            if (alert.acknowledged) {
                Text("Ciência confirmada", color = Color(0xFF2E7D32), fontWeight = FontWeight.SemiBold)
            } else {
                Button(onClick = onAcknowledge, modifier = Modifier.fillMaxWidth()) {
                    Text("Confirmar ciência do alerta")
                }
            }
        }
    }
}
