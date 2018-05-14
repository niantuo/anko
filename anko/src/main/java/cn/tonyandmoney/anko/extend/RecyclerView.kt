package cn.tonyandmoney.anko.extend

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by niantuo on 2018/2/8.
 * 相关
 */

fun RecyclerView.moveToPosition(position: Int) {
    if (layoutManager == null) return
    val lm = layoutManager as? LinearLayoutManager ?: return
    val firstItem = lm.findFirstVisibleItemPosition()
    val lastItem = lm.findLastVisibleItemPosition()

    when {
        position <= firstItem -> scrollToPosition(position)
        position <= lastItem -> {
            val top = getChildAt(position - firstItem).top
            scrollBy(0, top)
        }
        else -> {
            lm.scrollToPositionWithOffset(position, 0)
        }
    }
}

