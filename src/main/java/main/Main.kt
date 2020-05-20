package main

import java.net.URL
import com.google.gson.Gson
import model.PeopleForBikesCityResult
import model.PeopleForBikesResult
import java.io.File

fun main() {
    val file = File("csv file")

    file.forEachLine {
        val location = getLocation(it)
        val state = getStateAcronym(getState(location))
        val city = getCity(location)

        if (city.isNotEmpty() && location.isNotEmpty()) {
            val id = getId(city, state)
            println(id?.let { it1 -> getNetworkScore(it1) })
            Thread.sleep(15000)
        }
        else {
           println("-1")
        }
    }
}

fun getNetworkScore(id: Long) : Double {
    val url = "https://cityratings.peopleforbikes.org/wp-json/cityratings/v1/ratings/%d"
    val urlWithId = url.format(id)

    val json = URL(urlWithId).readText()
    val result = Gson().fromJson(json, PeopleForBikesCityResult::class.java)

    return result.network.toDouble()
}

fun getId(cityName: String, state: String?) : Long? {
    val cityFirstWordRegex = Regex("""^(\w*)""")
    val matchResult = cityFirstWordRegex.find(cityName)
    val cityFirstWord = matchResult?.groups?.get(1)?.value

    val url = "https://cityratings.peopleforbikes.org/wp-json/cityratings/v1/search/%s/ALL"
    val urlWithCity = String.format(url, cityFirstWord)

    val json = URL(urlWithCity).readText()
    val results: Array<PeopleForBikesResult>? = Gson().fromJson(json, Array<PeopleForBikesResult>::class.java) ?: return null

    results?.forEach {
        val cityIsSame = it.name == cityName
        val stateIsSame = it.state == state

        if(cityIsSame && stateIsSame)
        {
            return it.id
        }
    }

    return null
}