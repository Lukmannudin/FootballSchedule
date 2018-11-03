package com.lukmannudin.assosiate.footballclubschedule.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName





class TeamDetail{
    @SerializedName("teamSchedule")
    @Expose
    private var idTeam: String? = null
    @SerializedName("idSoccerXML")
    @Expose
    private var idSoccerXML: String? = null
    @SerializedName("intLoved")
    @Expose
    private var intLoved: String? = null
    @SerializedName("strTeam")
    @Expose
    private var strTeam: String? = null
    @SerializedName("strTeamShort")
    @Expose
    private var strTeamShort: Any? = null
    @SerializedName("strAlternate")
    @Expose
    private var strAlternate: String? = null
    @SerializedName("intFormedYear")
    @Expose
    private var intFormedYear: String? = null
    @SerializedName("strSport")
    @Expose
    private var strSport: String? = null
    @SerializedName("strLeague")
    @Expose
    private var strLeague: String? = null
    @SerializedName("idLeague")
    @Expose
    private var idLeague: String? = null
    @SerializedName("strDivision")
    @Expose
    private var strDivision: Any? = null
    @SerializedName("strManager")
    @Expose
    private var strManager: String? = null
    @SerializedName("strStadium")
    @Expose
    private var strStadium: String? = null
    @SerializedName("strKeywords")
    @Expose
    private var strKeywords: String? = null
    @SerializedName("strRSS")
    @Expose
    private var strRSS: String? = null
    @SerializedName("strStadiumThumb")
    @Expose
    private var strStadiumThumb: String? = null
    @SerializedName("strStadiumDescription")
    @Expose
    private var strStadiumDescription: String? = null
    @SerializedName("strStadiumLocation")
    @Expose
    private var strStadiumLocation: String? = null
    @SerializedName("intStadiumCapacity")
    @Expose
    private var intStadiumCapacity: String? = null
    @SerializedName("strWebsite")
    @Expose
    private var strWebsite: String? = null
    @SerializedName("strFacebook")
    @Expose
    private var strFacebook: String? = null
    @SerializedName("strTwitter")
    @Expose
    private var strTwitter: String? = null
    @SerializedName("strInstagram")
    @Expose
    private var strInstagram: String? = null
    @SerializedName("strDescriptionEN")
    @Expose
    private var strDescriptionEN: String? = null
    @SerializedName("strDescriptionDE")
    @Expose
    private var strDescriptionDE: Any? = null
    @SerializedName("strDescriptionFR")
    @Expose
    private var strDescriptionFR: Any? = null
    @SerializedName("strDescriptionCN")
    @Expose
    private var strDescriptionCN: Any? = null
    @SerializedName("strDescriptionIT")
    @Expose
    private var strDescriptionIT: Any? = null
    @SerializedName("strDescriptionJP")
    @Expose
    private var strDescriptionJP: Any? = null
    @SerializedName("strDescriptionRU")
    @Expose
    private var strDescriptionRU: Any? = null
    @SerializedName("strDescriptionES")
    @Expose
    private var strDescriptionES: Any? = null
    @SerializedName("strDescriptionPT")
    @Expose
    private var strDescriptionPT: Any? = null
    @SerializedName("strDescriptionSE")
    @Expose
    private var strDescriptionSE: Any? = null
    @SerializedName("strDescriptionNL")
    @Expose
    private var strDescriptionNL: Any? = null
    @SerializedName("strDescriptionHU")
    @Expose
    private var strDescriptionHU: Any? = null
    @SerializedName("strDescriptionNO")
    @Expose
    private var strDescriptionNO: Any? = null
    @SerializedName("strDescriptionIL")
    @Expose
    private var strDescriptionIL: Any? = null
    @SerializedName("strDescriptionPL")
    @Expose
    private var strDescriptionPL: Any? = null
    @SerializedName("strGender")
    @Expose
    private var strGender: String? = null
    @SerializedName("strCountry")
    @Expose
    private var strCountry: String? = null
    @SerializedName("strTeamBadge")
    @Expose
    private var strTeamBadge: String? = null
    @SerializedName("strTeamJersey")
    @Expose
    private var strTeamJersey: String? = null
    @SerializedName("strTeamLogo")
    @Expose
    private var strTeamLogo: String? = null
    @SerializedName("strTeamFanart1")
    @Expose
    private var strTeamFanart1: String? = null
    @SerializedName("strTeamFanart2")
    @Expose
    private var strTeamFanart2: String? = null
    @SerializedName("strTeamFanart3")
    @Expose
    private var strTeamFanart3: String? = null
    @SerializedName("strTeamFanart4")
    @Expose
    private var strTeamFanart4: String? = null
    @SerializedName("strTeamBanner")
    @Expose
    private var strTeamBanner: String? = null
    @SerializedName("strYoutube")
    @Expose
    private var strYoutube: String? = null
    @SerializedName("strLocked")
    @Expose
    private var strLocked: String? = null

    fun getIdTeam(): String? {
        return idTeam
    }

    fun setIdTeam(idTeam: String) {
        this.idTeam = idTeam
    }

    fun getIdSoccerXML(): String? {
        return idSoccerXML
    }

    fun setIdSoccerXML(idSoccerXML: String) {
        this.idSoccerXML = idSoccerXML
    }

    fun getIntLoved(): String? {
        return intLoved
    }

    fun setIntLoved(intLoved: String) {
        this.intLoved = intLoved
    }

    fun getStrTeam(): String? {
        return strTeam
    }

    fun setStrTeam(strTeam: String) {
        this.strTeam = strTeam
    }

    fun getStrTeamShort(): Any? {
        return strTeamShort
    }

    fun setStrTeamShort(strTeamShort: Any) {
        this.strTeamShort = strTeamShort
    }

    fun getStrAlternate(): String? {
        return strAlternate
    }

    fun setStrAlternate(strAlternate: String) {
        this.strAlternate = strAlternate
    }

    fun getIntFormedYear(): String? {
        return intFormedYear
    }

    fun setIntFormedYear(intFormedYear: String) {
        this.intFormedYear = intFormedYear
    }

    fun getStrSport(): String? {
        return strSport
    }

    fun setStrSport(strSport: String) {
        this.strSport = strSport
    }

    fun getStrLeague(): String? {
        return strLeague
    }

    fun setStrLeague(strLeague: String) {
        this.strLeague = strLeague
    }

    fun getIdLeague(): String? {
        return idLeague
    }

    fun setIdLeague(idLeague: String) {
        this.idLeague = idLeague
    }

    fun getStrDivision(): Any? {
        return strDivision
    }

    fun setStrDivision(strDivision: Any) {
        this.strDivision = strDivision
    }

    fun getStrManager(): String? {
        return strManager
    }

    fun setStrManager(strManager: String) {
        this.strManager = strManager
    }

    fun getStrStadium(): String? {
        return strStadium
    }

    fun setStrStadium(strStadium: String) {
        this.strStadium = strStadium
    }

    fun getStrKeywords(): String? {
        return strKeywords
    }

    fun setStrKeywords(strKeywords: String) {
        this.strKeywords = strKeywords
    }

    fun getStrRSS(): String? {
        return strRSS
    }

    fun setStrRSS(strRSS: String) {
        this.strRSS = strRSS
    }

    fun getStrStadiumThumb(): String? {
        return strStadiumThumb
    }

    fun setStrStadiumThumb(strStadiumThumb: String) {
        this.strStadiumThumb = strStadiumThumb
    }

    fun getStrStadiumDescription(): String? {
        return strStadiumDescription
    }

    fun setStrStadiumDescription(strStadiumDescription: String) {
        this.strStadiumDescription = strStadiumDescription
    }

    fun getStrStadiumLocation(): String? {
        return strStadiumLocation
    }

    fun setStrStadiumLocation(strStadiumLocation: String) {
        this.strStadiumLocation = strStadiumLocation
    }

    fun getIntStadiumCapacity(): String? {
        return intStadiumCapacity
    }

    fun setIntStadiumCapacity(intStadiumCapacity: String) {
        this.intStadiumCapacity = intStadiumCapacity
    }

    fun getStrWebsite(): String? {
        return strWebsite
    }

    fun setStrWebsite(strWebsite: String) {
        this.strWebsite = strWebsite
    }

    fun getStrFacebook(): String? {
        return strFacebook
    }

    fun setStrFacebook(strFacebook: String) {
        this.strFacebook = strFacebook
    }

    fun getStrTwitter(): String? {
        return strTwitter
    }

    fun setStrTwitter(strTwitter: String) {
        this.strTwitter = strTwitter
    }

    fun getStrInstagram(): String? {
        return strInstagram
    }

    fun setStrInstagram(strInstagram: String) {
        this.strInstagram = strInstagram
    }

    fun getStrDescriptionEN(): String? {
        return strDescriptionEN
    }

    fun setStrDescriptionEN(strDescriptionEN: String) {
        this.strDescriptionEN = strDescriptionEN
    }

    fun getStrDescriptionDE(): Any? {
        return strDescriptionDE
    }

    fun setStrDescriptionDE(strDescriptionDE: Any) {
        this.strDescriptionDE = strDescriptionDE
    }

    fun getStrDescriptionFR(): Any? {
        return strDescriptionFR
    }

    fun setStrDescriptionFR(strDescriptionFR: Any) {
        this.strDescriptionFR = strDescriptionFR
    }

    fun getStrDescriptionCN(): Any? {
        return strDescriptionCN
    }

    fun setStrDescriptionCN(strDescriptionCN: Any) {
        this.strDescriptionCN = strDescriptionCN
    }

    fun getStrDescriptionIT(): Any? {
        return strDescriptionIT
    }

    fun setStrDescriptionIT(strDescriptionIT: Any) {
        this.strDescriptionIT = strDescriptionIT
    }

    fun getStrDescriptionJP(): Any? {
        return strDescriptionJP
    }

    fun setStrDescriptionJP(strDescriptionJP: Any) {
        this.strDescriptionJP = strDescriptionJP
    }

    fun getStrDescriptionRU(): Any? {
        return strDescriptionRU
    }

    fun setStrDescriptionRU(strDescriptionRU: Any) {
        this.strDescriptionRU = strDescriptionRU
    }

    fun getStrDescriptionES(): Any? {
        return strDescriptionES
    }

    fun setStrDescriptionES(strDescriptionES: Any) {
        this.strDescriptionES = strDescriptionES
    }

    fun getStrDescriptionPT(): Any? {
        return strDescriptionPT
    }

    fun setStrDescriptionPT(strDescriptionPT: Any) {
        this.strDescriptionPT = strDescriptionPT
    }

    fun getStrDescriptionSE(): Any? {
        return strDescriptionSE
    }

    fun setStrDescriptionSE(strDescriptionSE: Any) {
        this.strDescriptionSE = strDescriptionSE
    }

    fun getStrDescriptionNL(): Any? {
        return strDescriptionNL
    }

    fun setStrDescriptionNL(strDescriptionNL: Any) {
        this.strDescriptionNL = strDescriptionNL
    }

    fun getStrDescriptionHU(): Any? {
        return strDescriptionHU
    }

    fun setStrDescriptionHU(strDescriptionHU: Any) {
        this.strDescriptionHU = strDescriptionHU
    }

    fun getStrDescriptionNO(): Any? {
        return strDescriptionNO
    }

    fun setStrDescriptionNO(strDescriptionNO: Any) {
        this.strDescriptionNO = strDescriptionNO
    }

    fun getStrDescriptionIL(): Any? {
        return strDescriptionIL
    }

    fun setStrDescriptionIL(strDescriptionIL: Any) {
        this.strDescriptionIL = strDescriptionIL
    }

    fun getStrDescriptionPL(): Any? {
        return strDescriptionPL
    }

    fun setStrDescriptionPL(strDescriptionPL: Any) {
        this.strDescriptionPL = strDescriptionPL
    }

    fun getStrGender(): String? {
        return strGender
    }

    fun setStrGender(strGender: String) {
        this.strGender = strGender
    }

    fun getStrCountry(): String? {
        return strCountry
    }

    fun setStrCountry(strCountry: String) {
        this.strCountry = strCountry
    }

    fun getStrTeamBadge(): String? {
        return strTeamBadge
    }

    fun setStrTeamBadge(strTeamBadge: String) {
        this.strTeamBadge = strTeamBadge
    }

    fun getStrTeamJersey(): String? {
        return strTeamJersey
    }

    fun setStrTeamJersey(strTeamJersey: String) {
        this.strTeamJersey = strTeamJersey
    }

    fun getStrTeamLogo(): String? {
        return strTeamLogo
    }

    fun setStrTeamLogo(strTeamLogo: String) {
        this.strTeamLogo = strTeamLogo
    }

    fun getStrTeamFanart1(): String? {
        return strTeamFanart1
    }

    fun setStrTeamFanart1(strTeamFanart1: String) {
        this.strTeamFanart1 = strTeamFanart1
    }

    fun getStrTeamFanart2(): String? {
        return strTeamFanart2
    }

    fun setStrTeamFanart2(strTeamFanart2: String) {
        this.strTeamFanart2 = strTeamFanart2
    }

    fun getStrTeamFanart3(): String? {
        return strTeamFanart3
    }

    fun setStrTeamFanart3(strTeamFanart3: String) {
        this.strTeamFanart3 = strTeamFanart3
    }

    fun getStrTeamFanart4(): String? {
        return strTeamFanart4
    }

    fun setStrTeamFanart4(strTeamFanart4: String) {
        this.strTeamFanart4 = strTeamFanart4
    }

    fun getStrTeamBanner(): String? {
        return strTeamBanner
    }

    fun setStrTeamBanner(strTeamBanner: String) {
        this.strTeamBanner = strTeamBanner
    }

    fun getStrYoutube(): String? {
        return strYoutube
    }

    fun setStrYoutube(strYoutube: String) {
        this.strYoutube = strYoutube
    }

    fun getStrLocked(): String? {
        return strLocked
    }

    fun setStrLocked(strLocked: String) {
        this.strLocked = strLocked
    }
}