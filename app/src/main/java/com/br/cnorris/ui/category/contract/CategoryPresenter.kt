package com.br.cnorris.ui.category.contract

import com.br.cnorris.api.ApiCNorrisInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Presenter para lista de categorias de mensagens
 * @author douglas.takara
 */
class CategoryPresenter: CategoryContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiCNorrisInterface = ApiCNorrisInterface.create()
    lateinit var view: CategoryContract.View

    override fun subscribe() {}

    override fun unsubscribe() = subscriptions.clear()

    override fun attach(view: CategoryContract.View) {
        this.view = view
    }

    override fun loadData() {
        var subscribe = api.getCategories().subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe({list:List<String> ->
                 view.showProgress(true)
                 view.loadDataSuccess(list)
                 view.showProgress(false)
             }, { error ->
                    val errorMessage = "Erro ao carregar as categorias"
                    view.showProgress(false)
                    view.showErrorMessage(errorMessage)
             })
        subscriptions.add(subscribe)
    }
}