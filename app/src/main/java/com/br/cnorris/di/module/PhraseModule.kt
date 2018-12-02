package com.br.cnorris.di.module

import android.app.Activity
import com.br.cnorris.api.ApiCNorrisInterface
import com.br.cnorris.ui.phrase.contract.PhraseContract
import com.br.cnorris.ui.phrase.contract.PhrasePresenter
import dagger.Module
import dagger.Provides

/**
 * Modulo para a frases do Chuck
 * @author douglas.takara
 */
@Module
class PhraseModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): PhraseContract.Presenter {
        return PhrasePresenter()
    }

    @Provides
    fun providerApiService(): ApiCNorrisInterface {
        return ApiCNorrisInterface.create()
    }
}