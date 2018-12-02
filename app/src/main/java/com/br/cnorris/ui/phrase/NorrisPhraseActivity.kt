package com.br.cnorris.ui.phrase

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.br.cnorris.ConstantUtils
import com.br.cnorris.R
import com.br.cnorris.di.component.DaggerPhraseComponent
import com.br.cnorris.di.module.PhraseModule
import com.br.cnorris.model.NorrisPhrase
import com.br.cnorris.ui.phrase.contract.PhraseContract
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_norris_phrase.*
import kotlinx.android.synthetic.main.loading_layout.view.*
import javax.inject.Inject

/**
 * Activity para as frases do Chuck
 * @author douglas.takara
 */
class NorrisPhraseActivity : AppCompatActivity(), PhraseContract.View {

    @Inject
    lateinit var presenter: PhraseContract.Presenter

    lateinit var loadingChuckLayout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_norris_phrase)
        injectDepedency()

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowCustomEnabled(true)
        }
        presenter.attach(this)

        loadIntent()
    }

    private fun loadIntent() {
        val category = intent.extras.get(ConstantUtils.EXTRA_CATEGORY) as String
        if (category.isNullOrEmpty()) {
            showErrorMessage(getString(R.string.not_found_category))
        } else {
            presenter.loadMessage(category)
            loadingChuckLayout = lytPhraseLoading
            Glide.with(this).load(R.drawable.chuck_norris_punch).into(loadingChuckLayout.imgChuckLoader)
        }
    }

    override fun loadSuccess(phrase: NorrisPhrase) {
        Picasso.get().load(phrase.iconUrl).into(imgPhraseCNorris)

        if (phrase.category != null) {
            val category = phrase.category.first()
            txtCategoryChosen.text = category.capitalize()
        } else {
            txtCategoryChosen.text = getString(R.string.explicit)
        }

        txtCNorrisPhrase.text = "\"${phrase.value}\""

        txtCNorrisPhraseLink.text = getString(R.string.visit_site)
        txtCNorrisPhraseLink.movementMethod = LinkMovementMethod.getInstance()
        txtCNorrisPhraseLink.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
            browserIntent.setData((Uri.parse(phrase.url)))
            startActivity(browserIntent)
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            loadingChuckLayout.visibility = View.VISIBLE
            lytCardView.visibility = View.INVISIBLE
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            loadingChuckLayout.visibility = View.GONE
            lytCardView.visibility = View.VISIBLE
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
        txtErrorLoadPhrase.visibility = View.GONE
    }

    override fun showErrorMessage(error: String) {
        txtErrorLoadPhrase.text = error
        txtErrorLoadPhrase.visibility = View.VISIBLE
        lytCardView.visibility = View.INVISIBLE
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun injectDepedency() {
        val phraseComponent = DaggerPhraseComponent.builder().phraseModule(PhraseModule(this)).build()
        phraseComponent.inject(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
