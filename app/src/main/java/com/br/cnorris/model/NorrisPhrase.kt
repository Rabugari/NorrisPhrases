package com.br.cnorris.model

import com.google.gson.annotations.SerializedName

/**
 * Dominio para frase CNorris
 * @author douglas.takara
 */
data class NorrisPhrase(val id:String,
                        val value:String,
                        val url:String,
                        @SerializedName("icon_url") val iconUrl:String,
                        val category: List<String>?)