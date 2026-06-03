package br.com.fiap.coldorbitmedtrack.navigation

object AppRoutes {
    const val HOME = "home"
    const val DELIVERIES = "deliveries"
    const val BATCH_DETAIL = "batchDetail/{batchId}"
    const val UPDATE_STATUS = "updateStatus/{batchId}"
    const val ALERTS = "alerts"
    const val PROFILE = "profile"

    fun batchDetail(batchId: String) = "batchDetail/$batchId"
    fun updateStatus(batchId: String) = "updateStatus/$batchId"
}
