package com.br.cnorris.di.component

import com.br.cnorris.CNorrisApp
import com.br.cnorris.di.module.ApplicationModule
import dagger.Component

/**
 * Componente para a aplicação
 * @author douglas.takara
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: CNorrisApp)
}