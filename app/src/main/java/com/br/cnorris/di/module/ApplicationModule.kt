package com.br.cnorris.di.module

import android.app.Application
import com.br.cnorris.CNorrisApp
import com.br.cnorris.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Modulo para a aplicação
 * @author douglas.takara
 */
@Module
class ApplicationModule(private val cNorrisApp: CNorrisApp) {

    /**
     * Prove uma instancia da aplicação
     */
    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return cNorrisApp
    }
}