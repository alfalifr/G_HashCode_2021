package compe.model

/**
 * [timeRemaining] shows time remaining for car with [carId]
 * to reach the end of street with [streetId].
 * [timeRemaining] shows 0 if car [carId] reaches the end of street [streetId] and stops.
 */
data class CarPosition(
    val carId: Int,
    val streetId: Int,
    val timeRemaining: Int
)