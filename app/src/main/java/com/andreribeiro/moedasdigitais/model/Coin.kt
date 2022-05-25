package com.andreribeiro.moedasdigitais.model

data class Coin(
    val name: String,
    val sigla: String,
    val price: String
)

fun mockCoin(): List<Coin> {
    return listOf(
        Coin(
            "Bitcoin",
            "BTC",
            "$25.000,99"
        ),
        Coin(
            "Real",
            "R$",
            "R$25.000,99"
        ),
        Coin(
            "Etherium",
            "ETH",
            "$35.000,99"
        ),
        Coin(
            "DogCoin",
            "DGC",
            "$55.000,99"
        ),
        Coin(
            "Mineriro",
            "MNR",
            "$56.000,99"
        ),
        Coin(
            "Mineriro",
            "MNR",
            "$56.000,99"
        ),
        Coin(
            "Mineriro",
            "MNR",
            "$56.000,99"
        ),
        Coin(
            "Mineriro",
            "MNR",
            "$56.000,99"
        ),
        Coin(
            "Mineriro",
            "MNR",
            "$56.000,99"
        ),
        Coin(
            "Mineriro",
            "MNR",
            "$56.000,99"
        )

    )
}
