package com.br.cnorris.ui.category

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.br.cnorris.ConstantUtils
import com.br.cnorris.R
import com.br.cnorris.ui.category.fragment.CategoryListFragment

/**
 * Activity para lista de frases
 * @link - CategoryListFragment
 * @author douglas.takara
 */
class CategoryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_list)

        supportActionBar?.let {
            it.setLogo(R.mipmap.ic_chuck_avatar)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayUseLogoEnabled(true)
        }

        configureFragment()
    }

    private fun configureFragment() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.viewCategoryContainer,
                CategoryListFragment(), ConstantUtils.CategoryListFragment_TAG
            ).commit()
    }

    override fun onBackPressed() {
        finish()
    }
}