package br.com.fiap.coldorbitmedtrack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.coldorbitmedtrack.components.SummaryCard
import br.com.fiap.coldorbitmedtrack.model.DeliveryBatch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    batches: List<DeliveryBatch>
) {
    val criticalCount = batches.count { it.status == "Crítico" }
    val deliveredCount = batches.count { it.status == "Entregue" }

    Scaffold(
        topBar = { TopAppBar(title = { Text("ColdOrbit MedTrack") }) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Operador de campo",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "O ColdOrbit MedTrack conecta o operador de campo ao controle de lotes médicos sensíveis à temperatura. A tela inicial concentra as ações mais importantes do turno: acompanhar entregas, verificar alertas, consultar o perfil operacional e registrar atualizações de rota.",
                style = MaterialTheme.typography.bodyLarge
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SummaryCard(
                    title = "Lotes do dia",
                    value = batches.size.toString(),
                    description = "em acompanhamento",
                    modifier = Modifier.weight(1f)
                )
                SummaryCard(
                    title = "Críticos",
                    value = criticalCount.toString(),
                    description = "exigem atenção",
                    modifier = Modifier.weight(1f)
                )
            }

            SummaryCard(
                title = "Entregas concluídas",
                value = deliveredCount.toString(),
                description = "lotes finalizados sem pendência",
                modifier = Modifier.fillMaxWidth()
            )

            SummaryCard(
                title = "Operador ativo",
                value = "OP-204",
                description = "turno da manhã em campo",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("deliveries") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text("Ver entregas")
            }

            Button(
                onClick = { navController.navigate("alerts") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text("Ver alertas")
            }

            Button(
                onClick = { navController.navigate("profile") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Text("Meu perfil")
            }
        }
    }
}
