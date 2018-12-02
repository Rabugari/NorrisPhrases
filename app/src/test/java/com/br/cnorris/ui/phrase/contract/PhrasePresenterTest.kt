package com.br.cnorris.ui.phrase.contract

import com.br.cnorris.api.ApiCNorrisInterface
import com.br.cnorris.model.NorrisPhrase
import com.br.cnorris.ui.phrase.NorrisPhraseActivity
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class PhrasePresenterTest {
    private val TIME_WAITING_ANSWER_SERVER: kotlin.Long = 5000
    private val CATEGORY_EXPLICITY = "explicit"

    lateinit var view: NorrisPhraseActivity
    lateinit var presenter: PhrasePresenter
    var phrase: NorrisPhrase? = null

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        var api = ApiCNorrisInterface.create()
        api.getPhrase(CATEGORY_EXPLICITY).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ phrase: NorrisPhrase ->
                this.phrase = phrase
            }, { error ->
                this.phrase = null
            })

        view = Mockito.mock(NorrisPhraseActivity::class.java)
        presenter = PhrasePresenter()
        presenter.attach(view)
    }

    @Test
    fun testFlowLoadingPhrase() {
        presenter.loadMessage(CATEGORY_EXPLICITY)
        Thread.sleep(TIME_WAITING_ANSWER_SERVER)
        Mockito.verify(view).showProgress(true)
        // Como a cada requisição o retorno é diferente, não comparo
        //Mockito.verify(view).loadSuccess(this.phrase!!)
        Mockito.verify(view).showProgress(false)
    }

}