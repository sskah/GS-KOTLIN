package br.com.fiap.coldorbitmedtrack.repository

import br.com.fiap.coldorbitmedtrack.model.DeliveryBatch
import br.com.fiap.coldorbitmedtrack.model.FieldAlert

fun getMockBatches(): List<DeliveryBatch> {
    return listOf(
        DeliveryBatch(
            id = "CO-1024",
            destination = "Base Clínica Norte",
            medicine = "Vacinas criogênicas",
            status = "Em rota",
            temperature = "-72°C",
            priority = "Alta",
            route = "Rota Polar 03",
            observation = "Manter cadeia fria estável até o recebimento."
        ),
        DeliveryBatch(
            id = "CO-1025",
            destination = "Hospital Orbital Sul",
            medicine = "Amostras biológicas",
            status = "Crítico",
            temperature = "-61°C",
            priority = "Crítica",
            route = "Rota Equatorial 01",
            observation = "Temperatura acima do limite recomendado."
        ),
        DeliveryBatch(
            id = "CO-1026",
            destination = "Unidade Remota Marte Lab",
            medicine = "Kits de insulina",
            status = "Pendente",
            temperature = "-68°C",
            priority = "Média",
            route = "Rota Interior 07",
            observation = "Aguardando confirmação do operador."
        ),
        DeliveryBatch(
            id = "CO-1027",
            destination = "Posto Médico Vale Azul",
            medicine = "Plasma sintético",
            status = "Entregue",
            temperature = "-70°C",
            priority = "Baixa",
            route = "Rota Urbana 04",
            observation = "Entrega concluída sem desvio térmico."
        )
    )
}

fun getMockAlerts(): List<FieldAlert> {
    return listOf(
        FieldAlert(
            id = "AL-01",
            batchId = "CO-1025",
            title = "Desvio de temperatura",
            description = "Lote CO-1025 registrou -61°C. Verifique o contêiner imediatamente.",
            severity = "Crítico"
        ),
        FieldAlert(
            id = "AL-02",
            batchId = "CO-1026",
            title = "Confirmação pendente",
            description = "O lote CO-1026 ainda não recebeu validação de saída pelo operador.",
            severity = "Atenção"
        ),
        FieldAlert(
            id = "AL-03",
            batchId = "CO-1024",
            title = "Janela de entrega próxima",
            description = "Entrega prevista em menos de 30 minutos. Prepare o protocolo de recebimento.",
            severity = "Médio"
        )
    )
}
