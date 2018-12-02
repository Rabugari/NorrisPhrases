package com.br.cnorris.ui.base

/**
 * Base para todos os outros contratos
 * @author douglas.takara
 */
class BaseContract {

    interface Presenter<in T>{
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {
        fun showProgress(show:Boolean)
        fun showErrorMessage(error:String)
    }
}