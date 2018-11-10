package com.demodocebo.test.injection.scopes

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

@Scope
@Retention (RetentionPolicy.RUNTIME)
annotation class FragmentScope
