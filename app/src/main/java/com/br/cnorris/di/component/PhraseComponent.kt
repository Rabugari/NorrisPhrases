package com.br.cnorris.di.component

import com.br.cnorris.di.module.PhraseModule
import com.br.cnorris.ui.phrase.NorrisPhraseActivity
import dagger.Component

/**
 * Componente para elementos relacionados à frases do Chuck
 * @author douglas.takara
 */
@Component(modules = arrayOf(PhraseModule::class))
interface PhraseComponent {

    fun inject(activity: NorrisPhraseActivity)
}