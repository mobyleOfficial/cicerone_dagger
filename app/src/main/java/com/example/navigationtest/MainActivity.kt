package com.example.navigationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val fragmentOneTag: String = "fragmentOneTag"
        private const val fragmentTwoTag: String = "fragmentTwoTag"
    }

    private var activeFragmentTag: String? = fragmentOneTag

    private val fragmentOneOneFlowContainer: FragmentOneFlowContainer by lazy {
        supportFragmentManager.findFragmentByTag(fragmentOneTag) as? FragmentOneFlowContainer ?: FragmentOneFlowContainer.newInstance()
    }

    private val fragmentTwoTwoFlowContainer: FragmentTwoFlowContainer by lazy {
        supportFragmentManager.findFragmentByTag(fragmentTwoTag) as? FragmentTwoFlowContainer ?: FragmentTwoFlowContainer.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavActions()

        supportFragmentManager.beginTransaction().hide(fragmentTwoTwoFlowContainer).show(fragmentOneOneFlowContainer).commit()
        activeFragmentTag = fragmentOneOneFlowContainer.tag
    }


    override fun onBackPressed() {
        val currentFragmentMoviesFlow = (supportFragmentManager.findFragmentByTag(activeFragmentTag) as? BackButtonListener)
        currentFragmentMoviesFlow?.let {
            if (!it.onBackPressed())
                finish()
        }
    }

    private fun setupNavActions(){
        supportFragmentManager.beginTransaction()
            .add(R.id.mainFlowContainer, fragmentOneOneFlowContainer, fragmentOneTag)
            .add(R.id.mainFlowContainer, fragmentTwoTwoFlowContainer, fragmentTwoTag)
            .commitNow()

        //Cliques na bottomNavigation
        bottomNavigationView.setOnNavigationItemSelectedListener { onNavigationItemSelected(it) }
    }

    //Navegação na bottomNavigation, retorna true se o item foi selecionado, false cc
    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction().hide(fragmentTwoTwoFlowContainer).show(fragmentOneOneFlowContainer).commit()
                activeFragmentTag = fragmentOneOneFlowContainer.tag
                true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction().hide(fragmentOneOneFlowContainer).show(fragmentTwoTwoFlowContainer).commit()
                activeFragmentTag = fragmentTwoTwoFlowContainer.tag
                true
            }
            else -> {
                false
            }
        }
    }
}