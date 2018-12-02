package com.br.cnorris.ui.phrase.contract

import com.br.cnorris.model.NorrisPhrase
import com.br.cnorris.ui.base.BaseContract

/**
 * Contrato para a frase
 * @author douglas.takara
 */
class PhraseContract {

    interface View:BaseContract.View {
        fun loadSuccess(phrase:NorrisPhrase)
    }

    interface Presenter:BaseContract.Presenter<View>{
        fun loadMessage(category:String)
    }
}