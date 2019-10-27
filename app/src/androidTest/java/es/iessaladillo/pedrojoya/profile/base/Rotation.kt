package es.iessaladillo.pedrojoya.profile.base

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider

object Rotation {

    fun rotateScreen(activity: Activity) {
        val orientation = ApplicationProvider.getApplicationContext<Context>()
            .resources.configuration.orientation
        activity.requestedOrientation = if (orientation == Configuration.ORIENTATION_PORTRAIT)
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        else
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

}
