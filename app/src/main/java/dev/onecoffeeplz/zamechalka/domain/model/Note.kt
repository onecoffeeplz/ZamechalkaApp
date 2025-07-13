package dev.onecoffeeplz.zamechalka.domain.model

data class Note(
    val id: Int? = null,
    val name: String,
    val path: String,
    val duration: Long,
    val text: String? = null,
    val tags: String? = null,
    val createdAt: Long,
)
