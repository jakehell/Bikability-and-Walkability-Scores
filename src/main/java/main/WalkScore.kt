package main

import java.io.File
import java.net.URL
import java.net.URLEncoder
import java.security.GeneralSecurityException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


fun main() {
    val file = File("csv file")

    trustAllSsl()
    file.forEachLine {
        val location = getLocation(it)
        val state = getStateAcronym(getState(location))
        val city = getCity(location)

        if (city.isNotEmpty() && location.isNotEmpty()) {
            println(getWalkScore(city, state));
            Thread.sleep(15000)
        }
        else {
            println("-1")
        }
    }
}

fun getWalkScore(cityName: String, stateAcronym: String?): String {
    var url = "https://www.walkscore.com/%s/%s"

    var urlWithCity = String.format(url, stateAcronym, URLEncoder.encode(cityName, "utf-8"))
    var result = URL(urlWithCity).readText()

    val regex = Regex("""has an average Walk Score of (\d*)""")
    val matchResult = regex.find(result) ?: return ""

    return matchResult?.groups?.get(1)?.value!!;
}
