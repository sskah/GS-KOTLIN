package br.com.fiap.coldorbitmedtrack.model

data class DeliveryBatch(
    val id: String,
    val destination: String,
    val medicine: String,
    val status: String,
    val temperature: String,
    val priority: String,
    val route: String,
    val observation: String
)
