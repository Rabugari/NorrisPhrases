package com.br.cnorris.di.component

import com.br.cnorris.di.module.CategoryModule
import com.br.cnorris.ui.category.fragment.CategoryListFragment
import dagger.Component

/**
 * Componente para elementos relacionados à categoria de frases
 * @author douglas.takara
 */
@Component(modules = arrayOf(CategoryModule::class))
interface CategoryComponent {

    fun inject(categoryListFragment: CategoryListFragment)
}