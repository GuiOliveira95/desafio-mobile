package com.guioliveiraapps.fulllab.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Specification : Serializable {

    @SerializedName("Peso e Dimensões")
    var pesoEDimensoes: Array<String>? = null

    @SerializedName("Outros Detalhes")
    var outrosDetalhes: Array<String>? = null

    @SerializedName("Category")
    var category: String? = null

    @SerializedName("Marca")
    var marca: Array<String>? = null

    @SerializedName("Garantia do Fabricante")
    var garantiaDoFabricante: Array<String>? = null

    @SerializedName("Modelo")
    var modelo: Array<String>? = null

    @SerializedName("Composicao")
    var composicao: Array<String>? = null

    @SerializedName("Cor")
    var cor: Array<String>? = null

    @SerializedName("Informacoes Importantes")
    var informacoesImportantes: Array<String>? = null

    @SerializedName("Potência")
    var potencia: Array<String>? = null

    @SerializedName("Itens Inclusos")
    var itensInclusos: Array<String>? = null

    @SerializedName("Banner do Menu")
    var bannerDoMenu: Array<String>? = null

}
