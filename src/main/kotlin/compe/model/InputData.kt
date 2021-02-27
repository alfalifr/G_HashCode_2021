package compe.model

data class InputData(
    val simulDur: Int,
    val intersection: Int,
    val streets: List<Street>,
    val cars: List<Car>,
    val bonus: Int
)