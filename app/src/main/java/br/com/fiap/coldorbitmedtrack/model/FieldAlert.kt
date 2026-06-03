package br.com.fiap.coldorbitmedtrack.model

data class FieldAlert(
    val id: String,
    val batchId: String,
    val title: String,
    val description: String,
    val severity: String,
    val acknowledged: Boolean = false
)
