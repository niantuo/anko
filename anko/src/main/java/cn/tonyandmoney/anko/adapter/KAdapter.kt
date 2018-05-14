package cn.tonyandmoney.anko.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import cn.tonyandmoney.anko.extend.moveToPosition
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

/**
 * Created by niantuo on 2017/11/8.
 * 用kotlin 的anko去实现adapter
 */
class KAdapter<T>(init: (KAdapter<T>.() -> Unit)? = null) : BaseQuickAdapter<T, BaseViewHolder>(0) {

    private var dataBind: ((BaseViewHolder, T) -> Unit)? = null
    lateinit var itemView: (AnkoContext<Context>) -> View


    init {
        init?.invoke(this)
    }

    fun setDataBind(bindCallback: (BaseViewHolder, T) -> Unit): KAdapter<T> {
        this.dataBind = bindCallback
        return this
    }

    fun setItemView(view: AnkoContext<Context>.() -> Unit): KAdapter<T> {
        itemView = {
            it.apply {
                view(it)
            }.view
        }
        return this
    }

    override fun convert(helper: BaseViewHolder, item: T) {
        dataBind?.invoke(helper, item)
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
            createBaseViewHolder(ItemAnkoComponent().createView(AnkoContext.create(parent.context)))


    fun moveToLast() {
        val last = data.size
        recyclerView.moveToPosition(last-1)

    }

    inner class ItemAnkoComponent : AnkoComponent<Context> {
        override fun createView(ui: AnkoContext<Context>): View = itemView.invoke(ui)
    }
}