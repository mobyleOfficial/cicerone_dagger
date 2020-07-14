package com.example.navigationtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.terrakok.cicerone.*
import javax.inject.Inject

// Classe "pai" de todas as abas. Sempre que uma nova aba é adicionada, um novo descendente
// dessa classe nasce
sealed class FlowContainerFragment : Fragment(), BackButtonListener {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var cicerone: Cicerone<Router>

    val component: FlowContainerComponent? by lazy {
        context?.let {
            DaggerFlowContainerComponent
                .builder()
                .flowContainerModule((activity)?.let { fragmentActivity ->
                    FlowContainerModule(
                        fragmentActivity,
                        childFragmentManager
                    )
                })
                .build()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        component?.inject(this)

        return inflater.inflate(R.layout.fragment_flow_container, container, false)
    }

    override fun onBackPressed(): Boolean {
        return if (isAdded) {
            val childFragment = childFragmentManager.findFragmentById(R.id.flowContainer)
            childFragment != null && childFragment is BackButtonListener && childFragment.onBackPressed()
        } else
            false
    }

    override fun onPause() {
        super.onPause()
        this.navigatorHolder.removeNavigator()
    }

    override fun onResume() {
        super.onResume()
        this.navigatorHolder.setNavigator(this.navigator)
    }
}

// Fragment da primeira aba
class FragmentOneFlowContainer : FlowContainerFragment() {
    companion object {
        fun newInstance() = FragmentOneFlowContainer()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState == null) {
            cicerone.router.replaceScreen(FragmentOneScreen())
        }
    }
}

// Fragment da segunda aba
class FragmentTwoFlowContainer : FlowContainerFragment() {
    companion object {
        fun newInstance() = FragmentTwoFlowContainer()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState == null) {
            cicerone.router.replaceScreen(FragmentTwoScreen())
        }
    }
}