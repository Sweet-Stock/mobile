package sweet.apisweetstore.integration

data class NutritionalFactsResponseAPI(
    val id: Int? = 0,
    val calories: Int? = 0,
    val sodium: Double? = 0.0,
    val sugars: Double? = 0.0,
    val protein: Double = 0.0,
    val fat: Double? = 0.0,
    val weight: Double? = 0.0,
    val gluten: Double? = 0.0,
)