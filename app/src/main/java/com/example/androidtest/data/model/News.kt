package com.example.androidtest.data.model

import com.google.gson.annotations.SerializedName

data class News (
    @SerializedName("id_documento")
    var id_documento: String,
    @SerializedName("chapeu")
    var chapeu: String,
    @SerializedName("titulo")
    var titulo: String,
    @SerializedName("linha_fina")
    var linha_fina: String,
    @SerializedName("data_hora_publicacao")
    var publi: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("imagem")
    var imagen: String,
    @SerializedName("source")
    var source: String
)