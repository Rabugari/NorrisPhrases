package com.br.cnorris.api

import com.br.cnorris.BuildConfig
import com.br.cnorris.model.NorrisPhrase
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api para consumir os serviços de Chuck Norris
 * @author douglas.takara
 */
interface ApiCNorrisInterface {

    /**
     * Recebe uma coletania de categoria de frases do chuck
     * @return - lista de categorias
     */
    @GET(CATEGORIES)
    fun getCategories(): Observable<List<String>>

    /**
     * Recebe uma frase de acordo com uma categoria
     * @param category - Categoria escolhida
     * @return - @link NorrisPhrase - frase do chuck
     */
    @GET(RANDOM)
    fun getPhrase(@Query(CATEGORY) category: String): Observable<NorrisPhrase>

    companion object Factory {
        const val CATEGORIES: String = "categories"
        const val CATEGORY: String = "category"
        const val RANDOM: String = "random"

        /**
         * Factory para construir chamadas à API
         */
        fun create(): ApiCNorrisInterface {
            val retrofit = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_URL)
                .build()
            return retrofit.create(ApiCNorrisInterface::class.java)
        }
    }
}
