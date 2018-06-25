package cn.tonyandmoney.anko.extend

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes

/**
 * @author niantuo
 * @createdTime 2018/6/25 9:18
 *
 *
 */

@ColorInt
fun Context.getColor(@ColorRes resId: Int): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(resId, null)
    } else {
        resources.getColor(resId)
    }
}

fun Context.getColorList(@ColorRes resId: Int): ColorStateList {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColorStateList(resId, null)
    } else {
        resources.getColorStateList(resId)
    }
}