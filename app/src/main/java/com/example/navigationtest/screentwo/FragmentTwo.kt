package com.example.navigationtest.screentwo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigationtest.*
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_two.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FragmentTwo : Fragment(), DisposableHolder by DisposableHolderDelegate(), BackButtonListener{

    @Inject
    lateinit var cicerone: Cicerone<Router>

    private val component: FragmentTwoComponent by lazy {
        DaggerFragmentTwoComponent
            .builder()
            .flowContainerComponent((parentFragment as? FlowContainerFragment)?.component)
            .build()
    }

    companion object {
        fun newInstance(): FragmentTwo = FragmentTwo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        component.inject(this)
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionButton.clicks().doOnNext {
            cicerone.router.navigateTo(FragmentOneScreen())
        }.subscribe().addTo(disposables)
    }

    override fun onBackPressed(): Boolean {
        cicerone.router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeAll()
    }
}