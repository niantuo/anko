package cn.tonyandmoney.anko.extend

import android.widget.TextView
import org.jetbrains.anko.internals.AnkoInternals

/**
 * 相关textView的一些自定义扩展方法
 */

inline var TextView.rightDrawableResId: Int
    get() = AnkoInternals.noGetter()
    set(value) {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, value, 0)
    }


inline var TextView.colorResId: Int
    get() = AnkoInternals.noGetter()
    set(value) {
        setTextColor(resources.getColor(value))
    }