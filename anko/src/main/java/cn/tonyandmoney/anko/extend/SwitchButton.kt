package cn.tonyandmoney.anko.extend

import android.view.ViewManager
import com.kyleduo.switchbutton.SwitchButton
import org.jetbrains.anko.custom.ankoView

/**
 * Created by niantuo on 2018/3/2.
 * 防IOS switch button
 */

inline fun ViewManager.switchButton(init: SwitchButton.() -> Unit): SwitchButton {
    return ankoView({ SwitchButton(it) }, 0, init)
}
