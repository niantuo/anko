package cn.tonyandmoney.anko.extend

import android.support.v7.widget.AppCompatImageView
import android.view.ViewManager
import cn.tonyandmoney.anko.ui.ItemShowView
import org.jetbrains.anko.custom.ankoView

/**
 * Created by niantuo on 2018/3/2.
 * 自动以控件
 */
inline fun ViewManager.itemShowView(init: ItemShowView.() -> Unit): ItemShowView {
    return ankoView({ ItemShowView(it) }, 0, init)
}

inline fun ViewManager.appCompatImageView(init: AppCompatImageView.() -> Unit): AppCompatImageView {
    return ankoView({ AppCompatImageView(it) }, 0, init)
}
