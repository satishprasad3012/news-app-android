package com.satish.android.networktest.model.enums

/**
 *
 * @author satish
 * 27/03/2019
 *
 * **/

import android.support.annotation.StringDef

object NetworkStatusType {

    const val SUCCESS = "ok"
    const val ERROR="error"

    @StringDef(SUCCESS, ERROR)
    @Retention(AnnotationRetention.SOURCE)
    annotation class NetworkStatusTypeDef
}