package main

import java.io.File
import java.net.URL
import java.net.URLEncoder

fun main()
{
    val file = File("csv file")

    trustAllSsl()
    file.forEachLine {
        val location = getLocation(it)
        val state = getStateAcronym(getState(location))
        val city = getCity(location)

        if (city.isNotEmpty() && location.isNotEmpty()) {
            val numberOfRaces = getNumberOfRaces(city, state)
            println(numberOfRaces)
            Thread.sleep(15000)
        }
        else {
            println("-1")
        }
    }
}
// gets list of races on running in the usa
fun getNumberOfRaces(cityName: String, stateAbbreviation: String?) : Int {
    var url = "https://runningintheusa.com/race/list/%s-%s/all-dates"
    var urlWithStateAndCity = url.format(cityName.toLowerCase().replace(" ", "%20"), stateAbbreviation?.toLowerCase())

    var result = URL(urlWithStateAndCity).readText()

    val regex = Regex("""1 to \d* of (\d*)""")
    val matchResult = regex.find(result) ?: return 0

    var stringValue = matchResult?.groups?.get(1)?.value!!
    return Integer.parseInt(stringValue)
}