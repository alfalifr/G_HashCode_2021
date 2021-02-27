package compe.model

/**
 * [start], [end] for intersection.
 */
data class Street(
    val name: String,
    val id: Int,
    val start: Int,
    val end: Int,
    val duration: Int,
    val isGreen: Boolean = false
){
    override fun hashCode(): Int = id
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Street

        if (id != other.id) return false

        return true
    }
}