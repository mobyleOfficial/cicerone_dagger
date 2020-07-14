package com.example.navigationtest

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@Module
class FlowContainerModule(
    private val frameActivity: FragmentActivity,
    private val fragmentManager: FragmentManager
) {
    @Provides
    @PerFlow
    fun getNavigator(): Navigator {
        return SupportAppNavigator(frameActivity, fragmentManager, R.id.flowContainer)
    }

    @Provides
    @PerFlow
    fun getCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @PerFlow
    fun getNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @PerFlow
    fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router

}

@Component(modules = [FlowContainerModule::class])
@PerFlow
interface FlowContainerComponent {
    fun getCicerone(): Cicerone<Router>
    fun getNavigationHolder(): NavigatorHolder
    fun getRouter(): Router
    fun inject(flowContainerFragment: FlowContainerFragment)
}