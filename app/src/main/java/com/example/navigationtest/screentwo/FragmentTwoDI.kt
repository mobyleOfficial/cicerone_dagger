package com.example.navigationtest.screentwo

import com.example.navigationtest.FlowContainerComponent
import com.example.navigationtest.PerScreen
import dagger.Component

@PerScreen
@Component(dependencies = [FlowContainerComponent::class])
interface FragmentTwoComponent {
    fun inject(fragmentTwoFragment: FragmentTwo)
}