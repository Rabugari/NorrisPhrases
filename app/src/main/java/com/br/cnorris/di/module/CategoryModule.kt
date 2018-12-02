package com.br.cnorris.di.module

import com.br.cnorris.api.ApiCNorrisInterface
import com.br.cnorris.ui.category.contract.CategoryContract
import com.br.cnorris.ui.category.contract.CategoryPresenter
import dagger.Module
import dagger.Provides

/**
 * Modulo para a categoria de frases
 * @author douglas.takara
 */
@Module
class CategoryModule {

    @Provides
    fun providePresenter(): CategoryContract.Presenter {
        return CategoryPresenter()
    }

    @Provides
    fun providerApiService(): ApiCNorrisInterface {
        return ApiCNorrisInterface.create()
    }
}