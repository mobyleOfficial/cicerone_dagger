package com.example.navigationtest.screenone

import com.example.navigationtest.FlowContainerComponent
import com.example.navigationtest.PerScreen
import dagger.Component

@PerScreen
@Component(dependencies = [FlowContainerComponent::class])
interface FragmentOneComponent {
    fun inject(fragmentOneFragment: FragmentOne)
}