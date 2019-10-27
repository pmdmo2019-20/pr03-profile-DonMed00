package es.iessaladillo.pedrojoya.profile.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

// Is any activity available to use the intent.
fun isActivityAvailable(ctx: Context, intent: Intent): Boolean {
    val packageManager = ctx.applicationContext.packageManager
    val appList = packageManager.queryIntentActivities(
        intent,
        PackageManager.MATCH_DEFAULT_ONLY
    )
    return appList.size > 0
}

fun newViewUriIntent(uri: Uri): Intent {
    return Intent(Intent.ACTION_VIEW, uri)
}

fun newEmailIntent(email: String): Intent {
    return Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
}

fun newDialIntent(phoneNumber: String): Intent {
    return Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.trim { it <= ' ' }))
}

fun newSearchInMapIntent(text: String): Intent {
    return Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$text"))
}

