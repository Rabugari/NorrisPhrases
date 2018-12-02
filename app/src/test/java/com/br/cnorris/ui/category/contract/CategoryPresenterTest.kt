package com.br.cnorris.ui.category.contract

import com.br.cnorris.api.ApiCNorrisInterface
import com.br.cnorris.ui.category.fragment.CategoryListFragment
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

class CategoryPresenterTest {

    private val TIME_WAITING_ANSWER_SERVER: kotlin.Long = 5000

    lateinit var view: CategoryListFragment
    lateinit var presenter: CategoryPresenter
    lateinit var categories:List<String>

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        var api = ApiCNorrisInterface.create()
        api.getCategories().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<String> ->
                categories=list
            }, { error ->
                categories= listOf()
            })

        view = Mockito.mock(CategoryListFragment::class.java)
        presenter = CategoryPresenter()
        presenter.attach(view)
    }

    @Test
    fun testFlowLoadingCategories() {
        presenter.loadData()
        Thread.sleep(TIME_WAITING_ANSWER_SERVER)
        verify(view).showProgress(true)
        verify(view).loadDataSuccess(categories)
        verify(view).showProgress(false)
    }
}