package com.example.navigationtest

import androidx.fragment.app.Fragment
import com.example.navigationtest.screenone.FragmentOne
import com.example.navigationtest.screentwo.FragmentTwo
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentOneScreen : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return FragmentOne.newInstance()
    }
}

class FragmentTwoScreen : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return FragmentTwo.newInstance()
    }
}