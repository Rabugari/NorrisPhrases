package com.br.cnorris

import android.app.Application
import com.br.cnorris.di.component.ApplicationComponent
import com.br.cnorris.di.component.DaggerApplicationComponent
import com.br.cnorris.di.module.ApplicationModule

/**
 * Classe para gerenciar a facilitar o uso da Application do android
 * @author douglas.takara
 */
class CNorrisApp : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        setup()
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    companion object {
        lateinit var instance: CNorrisApp private set
    }
}