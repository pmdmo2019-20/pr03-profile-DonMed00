package es.iessaladillo.pedrojoya.profile.utils

import android.util.Patterns

fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPhone(): Boolean = Patterns.PHONE.matcher(this).matches()

fun String.isValidUrl(): Boolean = Patterns.WEB_URL.matcher(this).matches()
