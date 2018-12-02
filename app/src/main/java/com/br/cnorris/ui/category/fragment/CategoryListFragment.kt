package com.br.cnorris.ui.category.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.br.cnorris.ConstantUtils
import com.br.cnorris.R
import com.br.cnorris.di.component.DaggerCategoryComponent
import com.br.cnorris.di.module.CategoryModule
import com.br.cnorris.ui.category.adapter.CategoryListAdapter
import com.br.cnorris.ui.category.contract.CategoryContract
import com.br.cnorris.ui.phrase.NorrisPhraseActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.frame_list_category.*
import kotlinx.android.synthetic.main.frame_list_category.view.*
import kotlinx.android.synthetic.main.loading_layout.view.*
import javax.inject.Inject

/**
 * Fragment para a lista de categorias de frases
 * @author douglas.takara
 */
open class CategoryListFragment : Fragment(), CategoryContract.View, CategoryListAdapter.onItemClickListener {

    private lateinit var rootView: View
    private lateinit var loadingChuckLayout: View
    @Inject lateinit var presenter: CategoryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependecy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.frame_list_category, container, false)
        loadingChuckLayout = rootView.lytCategoryLoading
        Glide.with(this).load(R.drawable.chuck_norris_punch).into(loadingChuckLayout.imgChuckLoader)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        presenter.loadData()
    }

    override fun itemDetail(category: String) {
        val phraseIntent = Intent(activity, NorrisPhraseActivity::class.java)
        phraseIntent.putExtra(ConstantUtils.EXTRA_CATEGORY, category)
        startActivity(phraseIntent)
    }

    override fun loadDataSuccess(list: List<String>) {
        val adapter = CategoryListAdapter(this.context!!, list, this)
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerCategory.layoutManager = layoutManager
        recyclerCategory.adapter = adapter
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            loadingChuckLayout.visibility = View.VISIBLE
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            loadingChuckLayout.visibility = View.GONE
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
        txtErrorListCategories.visibility = View.GONE
    }

    override fun showErrorMessage(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        txtErrorListCategories.text = error
        txtErrorListCategories.visibility = View.VISIBLE
    }

    private fun injectDependecy() {
        val categoryComponent = DaggerCategoryComponent.builder().categoryModule(CategoryModule()).build()
        categoryComponent.inject(this)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }
}