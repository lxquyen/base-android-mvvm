package com.lxquyen.sample.common.di.component

import com.lxquyen.sample.common.di.module.*
import com.lxquyen.sample.presentation.fragment.EditUserFragment
import com.lxquyen.sample.presentation.fragment.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CommonModule::class,
        MapperModule::class,
        RemoteSourceModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(mainFragment: MainFragment)
    fun inject(editUserFragment: EditUserFragment)
}