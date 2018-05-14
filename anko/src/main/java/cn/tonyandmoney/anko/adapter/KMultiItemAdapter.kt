package cn.tonyandmoney.anko.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import cn.tonyandmoney.anko.extend.moveToPosition
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

/**
 * Created by niantuo on 2017/11/8.
 * 试着用kotlin 的anko来写这个布局
 */
class KMultiItemAdapter<T : MultiItemEntity>(init: (KMultiItemAdapter<T>.() -> Unit)? = null)
    : BaseMultiItemQuickAdapter<T, BaseViewHolder>(mutableListOf()) {


    private var dataBind: ((BaseViewHolder, T) -> Unit)? = null
    private val mItemViews: MutableMap<Int, ((AnkoContext<Context>) -> View)> = mutableMapOf()

    init {
        init?.invoke(this)
    }

    fun scrollToPosition(position: Int) {
        recyclerView.moveToPosition(position)
    }

    fun getPositionByEntity(entity: T): Int {
        return mData?.indexOf(entity) ?: -1
    }

    fun firstOrNull(predicate: (T) -> Boolean): T? {
        return mData.orEmpty().firstOrNull { predicate(it) }
    }

    fun indexOfFirst(predicate: (T) -> Boolean): Int {
        return mData.indexOfFirst(predicate)
    }

    fun setDataBind(bindCallback: (BaseViewHolder, T) -> Unit): KMultiItemAdapter<T> {
        this.dataBind = bindCallback
        return this
    }

    fun addItemView(viewType: Int, view: AnkoContext<Context>.() -> Unit): KMultiItemAdapter<T> {
        mItemViews.put(viewType, {
            it.apply { view(it) }.view
        })
        return this
    }

    override fun convert(helper: BaseViewHolder, item: T) {
        dataBind?.invoke(helper, item)
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = ItemAnkoComponent(viewType).createView(AnkoContext.create(parent.context))
        return createBaseViewHolder(view)
    }

    fun setEmptyView(context: Context, view: (AnkoContext<Context>) -> View) {
        val anko = object : AnkoComponent<Context> {
            override fun createView(ui: AnkoContext<Context>): View = view.invoke(ui)
        }
        emptyView = anko.createView(AnkoContext.create(context))
    }


    inner class ItemAnkoComponent(val viewType: Int) : AnkoComponent<Context> {
        override fun createView(ui: AnkoContext<Context>): View {
            val viewFun: (AnkoContext<Context>) -> View = mItemViews[viewType] ?: throw RuntimeException("未找打类型：$viewType  对应的view，请先添加。")
            return viewFun.invoke(ui)
        }
    }
}