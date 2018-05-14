package cn.tonyandmoney.anko.ui

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatImageView
import android.view.Gravity
import android.widget.TextView
import cn.tonyandmoney.anko.R
import cn.tonyandmoney.anko.extend.appCompatImageView
import org.jetbrains.anko.*

/**
 * Created by niantuo on 2018/3/2.
 * 栏目
 */
class ItemShowView(context: Context) : _LinearLayout(context) {
    private lateinit var mLeftView: TextView
    private lateinit var mRightView: TextView
    private lateinit var mRightImageView: AppCompatImageView

    init {
        textView {
            mLeftView = this
            gravity = Gravity.CENTER_VERTICAL
            setTextColor(Color.parseColor("#999999"))
        }.lparams(0, matchParent, 1f)
        textView {
            mRightView = this
            gravity = Gravity.CENTER_VERTICAL
        }.lparams(wrapContent, matchParent)
        appCompatImageView {
            mRightImageView = this
            setImageResource(R.drawable.ic_chevron_right_black_24dp)
        }.lparams(dip(40), matchParent)

    }

    fun getTextView(): TextView = mRightView

    fun setTitleTextView(init: TextView.() -> Unit): TextView {
        mLeftView.init()
        return mLeftView
    }

    fun setRightTextView(init: TextView.() -> Unit): TextView {
        mRightView.init()
        return mRightView
    }

    fun setRightImageView(init: AppCompatImageView.() -> Unit): AppCompatImageView {
        mRightImageView.init()
        return mRightImageView
    }
}