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
    @SerializedName("credito")
    var credito: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("imagem")
    var imagem: String,
    @SerializedName("imagem_credito")
    var imagem_credito: String,
    @SerializedName("source")
    var source: String
)