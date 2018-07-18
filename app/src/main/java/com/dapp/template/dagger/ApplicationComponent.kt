package com.dapp.template.dagger

import com.dapp.template.TemplateApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidInjectionModule::class),
    (ApplicationModule::class),
    (InjectorsModule::class)])
interface ApplicationComponent {
    fun inject(target: TemplateApplication)
}