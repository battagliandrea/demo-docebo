package com.demodocebo.test.injection.scopes

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by andrea on 26/08/2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class PresenterScope