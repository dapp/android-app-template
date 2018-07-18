package com.dapp.template.dagger

import javax.inject.Scope

@Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.FILE)
@Scope
annotation class PerActivity