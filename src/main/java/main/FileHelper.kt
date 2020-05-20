package main

import com.google.gson.Gson
import model.PeopleForBikesResult
import java.net.URL

fun getLocation(args: String): String {
    val regex = Regex(""".*?"(.*)".*""")
    val matchResult = regex.find(args)

    return matchResult?.groups?.get(1)?.value ?: return ""
}

fun getCity(args: String) : String {
    val regex = Regex("""^([^,]*),""")
    val matchResult = regex.find(args)

    return matchResult?.groups?.get(1)?.value ?: return ""
}

fun getState(args: String) : String {
    val regex = Regex(""",\s(.*)$""")
    val matchResult = regex.find(args)

    return matchResult?.groups?.get(1)?.value ?: return ""
}

fun getStateAcronym(args: String) : String? {
    val map = hashMapOf<String, String>()
    map["Alabama"] = "AL"
    map["Alaska"] = "AK"
    map["Arizona"] = "AZ"
    map["Arkansas"] = "AR"
    map["California"] = "CA"
    map["Colorado"] = "CO"
    map["Connecticut"] = "CT"
    map["Delaware"] = "DE"
    map["Florida"] = "FL"
    map["Georgia"] = "GA"
    map["Hawaii"] = "HI"
    map["Idaho"] = "ID"
    map["Illinois"] = "IL"
    map["Indiana"] = "IN"
    map["Iowa"] = "IA"
    map["Kansas"] = "KS"
    map["Kentucky"] = "KY"
    map["Louisiana"] = "LA"
    map["Maine"] = "ME"
    map["Maryland"] = "MD"
    map["Massachusetts"] = "MA"
    map["Michigan"] = "MI"
    map["Minnesota"] = "MN"
    map["Mississippi"] = "MS"
    map["Missouri"] = "MO"
    map["Montana"] = "MT"
    map["Nebraska"] = "NE"
    map["Nevada"] = "NV"
    map["New Hampshire"] = "NH"
    map["New Jersey"] = "NJ"
    map["New Mexico"] = "NM"
    map["New York"] = "NY"
    map["North Carolina"] = "NC"
    map["North Dakota"] = "ND"
    map["Ohio"] = "OH"
    map["Oklahoma"] = "OK"
    map["Oregon"] = "OR"
    map["Pennsylvania"] = "PA"
    map["Rhode Island"] = "RI"
    map["South Carolina"] = "SC"
    map["South Dakota"] = "SD"
    map["Tennessee"] = "TN"
    map["Texas"] = "TX"
    map["Utah"] = "UT"
    map["Vermont"] = "VT"
    map["Virginia"] = "VA"
    map["Washington"] = "WA"
    map["West Virginia"] = "WV"
    map["Wisconsin"] = "WI"
    map["Wyoming"] = "WY"

    return map[args]
}
