package com.localiza.jdc.composeprototypes.ui.screen.data

import androidx.annotation.DrawableRes
import com.localiza.jdc.composeprototypes.R

sealed class Planet(
    val name: String,
    @DrawableRes val imageRes: Int,
    val shortDescription: String,
    val longDescription: String,
    val number: Int
) {
    data object Mercury : Planet(
        name = "Mercury",
        imageRes = R.drawable.ic_mercury,
        shortDescription = "Tiny inferno, closest to the Sun with wild temperature swings.",
        longDescription = "Tiny inferno, closest to the Sun with wild temperature swings. Scorched by day, freezing by night, it has no atmosphere.",
        number = 1,
    )

    data object Venus : Planet(
        name = "Venus",
        imageRes = R.drawable.ic_venus,
        shortDescription = "Burning beauty, hottest planet with a thick, toxic atmosphere.",
        longDescription = "Burning beauty, hottest planet with a thick, toxic atmosphere. A runaway greenhouse effect traps heat, making it hotter than Mercury.",
        number = 2,
    )

    data object Earth : Planet(
        name = "Earth",
        imageRes = R.drawable.ic_earth,
        shortDescription = "Our oasis, the only known planet to support life with 75% of water.",
        longDescription = "Our oasis, the only known planet to support life. A blue marble with liquid water, diverse life forms, and a protective atmosphere.",
        number = 3,
    )

    data object Mars : Planet(
        name = "Mars",
        imageRes = R.drawable.ic_mars,
        shortDescription = "Rusty neighbor, cold and dry with a thin atmosphere and ancient water.",
        longDescription = "Rusty neighbor, cold and dry with a thin atmosphere and hints of ancient water. Reddish dust covers the surface, with polar ice caps and potential for past life.",
        number = 4,
    )

    data object Jupiter : Planet(
        name = "Jupiter",
        imageRes = R.drawable.ic_jupiter,
        shortDescription = "Giant king, swirling atmosphere with a giant storm bigger than Earth.",
        longDescription = "Giant king, swirling atmosphere with a giant storm bigger than Earth (Great Red Spot). Made mostly of gas, it has a strong gravity and numerous moons.",
        number = 5,
    )

    data object Saturn : Planet(
        name = "Saturn",
        imageRes = R.drawable.ic_saturn,
        shortDescription = "Ringed wonder, famous for its icy rings and beautiful form.",
        longDescription = "Ringed wonder, famous for its stunning icy rings and beautiful form. A gas giant with a tilted axis and a complex system of moons.",
        number = 6,
    )

    data object Neptune : Planet(
        name = "Neptune",
        imageRes = R.drawable.ic_neptune,
        shortDescription = "Distant wanderer, farthest planet with supersonic winds and a dark blue hue.",
        longDescription = "Distant wanderer, farthest planet with supersonic winds and a dark blue hue. An ice giant with the strongest winds in the solar system and a dark blue methane atmosphere.",
        number = 7,
    )

    data object Pluto : Planet(
        name = "Pluto",
        imageRes = R.drawable.ic_pluto,
        shortDescription = "Icy dwarf, once a planet, cold and small with moons.",
        longDescription = "Icy dwarf, once a planet, cold and small with moons. A small, icy world beyond Neptune, rich in frozen nitrogen and methane, with its own moons.",
        number = 8,
    )

}

val planetList = listOf(
    Planet.Mercury,
    Planet.Venus,
    Planet.Earth,
    Planet.Mars,
    Planet.Jupiter,
    Planet.Saturn,
    Planet.Neptune,
    Planet.Pluto
)
