package com.example.ozimind.data

/**
 * Bu obje, şimdilik uygulamanın içinde lokal olarak tutulan
 * Ülke ve Şehir veritabanıdır. İleride gerçek bir backend'e
 * (API) bağlandığında bu obje silinip yerine internetten çekilen JSON dataları kullanılacaktır.
 */
object LocationData {
    val countryCityMap: Map<String, List<String>> = mapOf(
        "Türkiye" to listOf("İstanbul", "Ankara", "İzmir", "Bursa", "Antalya", "Adana", "Eskişehir", "Trabzon", "Gaziantep", "Konya"),
        "Amerika Birleşik Devletleri" to listOf("New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Miami", "San Francisco"),
        "Almanya" to listOf("Berlin", "Münih", "Frankfurt", "Hamburg", "Köln", "Stuttgart"),
        "İngiltere" to listOf("Londra", "Manchester", "Birmingham", "Liverpool", "Oxford"),
        "Fransa" to listOf("Paris", "Marsilya", "Lyon", "Toulouse", "Nice"),
        "İtalya" to listOf("Roma", "Milano", "Venedik", "Napoli", "Floransa"),
        "Kanada" to listOf("Toronto", "Vancouver", "Montreal", "Ottawa")
    )
    
    // Sadece ülkelerin listesini verir
    val countries: List<String> = countryCityMap.keys.toList()
    
    // Seçilen ülkeye göre şehirleri döndürür
    fun getCitiesForCountry(country: String): List<String> {
        return countryCityMap[country] ?: emptyList()
    }
}
