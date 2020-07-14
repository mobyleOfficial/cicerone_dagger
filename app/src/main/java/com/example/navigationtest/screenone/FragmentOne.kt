package com.example.navigationtest.screenone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.navigationtest.*
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.frament_one.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FragmentOne : Fragment(), DisposableHolder by DisposableHolderDelegate(), BackButtonListener {
    private lateinit var numbersAdapter: FragmentOneAdapter
    private val numberList: List<Int> =
        listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)

    @Inject
    lateinit var cicerone: Cicerone<Router>

    private val component: FragmentOneComponent? by lazy {
        DaggerFragmentOneComponent
            .builder()
            .flowContainerComponent((parentFragment as? FlowContainerFragment)?.component)
            .build()
    }

    companion object {
        fun newInstance(): FragmentOne = FragmentOne()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        component?.inject(this)
        return inflater.inflate(R.layout.frament_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        numbersAdapter = FragmentOneAdapter()
        numberListRecyclerView.layoutManager = GridLayoutManager(context, 1)
        numberListRecyclerView.adapter = numbersAdapter
        numbersAdapter.setData(numberList)

        numbersAdapter.onClick.doOnNext {
            cicerone.router.navigateTo(FragmentTwoScreen())
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