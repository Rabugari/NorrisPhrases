package com.br.cnorris.ui.phrase.contract

import com.br.cnorris.api.ApiCNorrisInterface
import com.br.cnorris.model.NorrisPhrase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Presenter para frases do Chuck
 * @author douglas.takara
 */
class PhrasePresenter: PhraseContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiCNorrisInterface = ApiCNorrisInterface.create()
    private lateinit var view: PhraseContract.View

    override fun subscribe() {}

    override fun unsubscribe() = subscriptions.clear()

    override fun attach(view: PhraseContract.View) {
        this.view = view
    }

    override fun loadMessage(category: String) {
        var subscribe = api.getPhrase(category).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({phrase: NorrisPhrase ->
                view.showProgress(true)
                view.loadSuccess(phrase)
                view.showProgress(false)
            }, { _->
                val errorMessage = "Erro ao carregar a frase"
                view.showProgress(false)
                view.showErrorMessage(errorMessage)
            })
        subscriptions.add(subscribe)
    }
}