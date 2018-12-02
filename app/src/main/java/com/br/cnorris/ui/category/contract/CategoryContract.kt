package com.br.cnorris.ui.category.contract

import com.br.cnorris.ui.base.BaseContract

/**
 * Contrato para categorias de frases
 * @author douglas.takara
 */
class CategoryContract {

    interface View : BaseContract.View {
        fun loadDataSuccess(list: List<String>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadData()
    }
}