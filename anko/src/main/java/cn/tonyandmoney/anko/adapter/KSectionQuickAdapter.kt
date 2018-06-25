package cn.tonyandmoney.anko.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.SectionEntity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

/**
 * Created by niantuo on 2017/12/4.
 * 分组那种
 */
class KSectionQuickAdapter<T> : BaseSectionQuickAdapter<SectionEntity<T>,
        BaseViewHolder>(0, 0, mutableListOf()) {

    private var dataBind: ((BaseViewHolder, T) -> Unit)? = null
    private var headDataBind: ((BaseViewHolder, SectionEntity<T>) -> Unit)? = null

    private lateinit var mSectionHeaderView: (AnkoContext<Context>) -> View
    private lateinit var mEntityItemView: (AnkoContext<Context>) -> View


    fun setEntityDataBind(callback: (BaseViewHolder, T) -> Unit): KSectionQuickAdapter<T> {
        this.dataBind = callback
        return this
    }

    fun setHeaderDataBind(callback: (BaseViewHolder, SectionEntity<T>) -> Unit): KSectionQuickAdapter<T> {
        this.headDataBind = callback
        return this
    }

    fun setHeaderItemView(view: AnkoContext<Context>.() -> Unit): KSectionQuickAdapter<T> {
        mSectionHeaderView = {
            it.apply {
                view(it)
            }.view
        }
        return this
    }

    fun setEntityItemView(ankoView: AnkoContext<Context>.() -> Unit): KSectionQuickAdapter<T> {
        mEntityItemView = {
            it.apply {
                ankoView(it)
            }.view
        }
        return this
    }


    override fun convert(helper: BaseViewHolder, item: SectionEntity<T>) {
        dataBind?.invoke(helper, item.t)
    }

    override fun convertHead(helper: BaseViewHolder, item: SectionEntity<T>) {
        headDataBind?.invoke(helper, item)
    }

    override fun onCreateDefViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view: View = object : AnkoComponent<Context> {
            override fun createView(ui: AnkoContext<Context>): View {
                return when (viewType) {
                    SECTION_HEADER_VIEW -> mSectionHeaderView.invoke(ui)
                    else -> mEntityItemView.invoke(ui)
                }
            }
        }.createView(AnkoContext.create(parent.context))

        return createBaseViewHolder(view)
    }
}