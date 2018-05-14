package cn.tonyandmoney.anko.adapter

import android.content.Context
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

/**
 * Created by niantuo on 2017/12/4.
 * 用anko方式设置空的view
 */
fun <T, K : BaseViewHolder> BaseQuickAdapter<T, K>.setKEmptyView(context: Context, view: (AnkoContext<Context>).() -> Unit) {
    val anko = object : AnkoComponent<Context> {
        override fun createView(ui: AnkoContext<Context>): View {
            return ui.apply {
                view(ui)
            }.view
        }
    }
    emptyView = anko.createView(AnkoContext.create(context))
}