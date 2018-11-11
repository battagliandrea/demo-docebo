package com.demodocebo.test.ui.utils

import android.os.Build
import android.text.Html
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.security.NoSuchAlgorithmException


/**
 * Returns an html-stripped String
 */
fun String.toSpanned() : String {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        return Html.fromHtml(this).toString()
    }
}


/**
 * @param inputStream
 * @return the inputStream in input converted in a String object
 */
fun InputStream.getString(): String {

    var br: BufferedReader? = null
    val sb = StringBuilder()

    try {
        br = BufferedReader(InputStreamReader(this))
        var line = br.readLine()
        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }

    } catch (e: IOException) {
        throw e
    } finally {
        if (br != null) {
            try {
                br.close()
            } catch (e: IOException) {
                throw e
            }
        }
    }

    return sb.toString()
}


/**
 * Calculate MD% from a string.
 */
fun String.calculateMd5(): String {
    try {
        // Create MD5 Hash
        val digest = java.security.MessageDigest.getInstance("MD5")
        digest.update(toByteArray())
        val messageDigest = digest.digest()

        // Create Hex String
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF.and(aMessageDigest.toInt()))
            while (h.length < 2)
                h = "0" + h
            hexString.append(h)
        }
        return hexString.toString()

    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}


