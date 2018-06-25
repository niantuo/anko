package cn.tonyandmoney.anko.rx

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * @author niantuo
 * @createdTime 2018/6/25 10:48
 *
 *  这个
 *
 */

class FlatSubscribe<T>(private val observable: Observable<T>) : Observer<T> {

    private var error: ((Throwable) -> Unit)? = null
    private var complete: (() -> Unit)? = null
    private var next: ((T) -> Unit)? = null
    private var subscribe: ((Disposable) -> Unit)? = null


    fun doOnError(callback: (Throwable) -> Unit): FlatSubscribe<T> {
        this.error = callback
        return this
    }

    fun doOnComplete(callback: () -> Unit): FlatSubscribe<T> {
        this.complete = callback
        return this
    }

    fun doOnNext(callback: (T) -> Unit): FlatSubscribe<T> {
        this.next = callback
        return this
    }

    fun doOnSubscribe(callback: (Disposable) -> Unit): FlatSubscribe<T> {
        this.subscribe = callback
        return this
    }

    override fun onComplete() {
        complete?.invoke()
    }

    override fun onError(e: Throwable) {
        error?.invoke(e)
    }

    override fun onNext(t: T) {
        next?.invoke(t)
    }

    override fun onSubscribe(d: Disposable) {
        subscribe?.invoke(d)
    }

    fun subscribe() {
        observable.subscribe(this)
    }
}

class FlatSingleSubscribe<T>(val single:Single<T>):SingleObserver<T>{


    private var error: ((Throwable) -> Unit)? = null
    private var next: ((T) -> Unit)? = null
    private var subscribe: ((Disposable) -> Unit)? = null


    fun doOnError(callback: (Throwable) -> Unit): FlatSingleSubscribe<T> {
        this.error = callback
        return this
    }
    fun doOnNext(callback: (T) -> Unit): FlatSingleSubscribe<T> {
        this.next = callback
        return this
    }

    fun doOnSubscribe(callback: (Disposable) -> Unit): FlatSingleSubscribe<T> {
        this.subscribe = callback
        return this
    }

    override fun onSubscribe(d: Disposable) {
        subscribe?.invoke(d)
    }

    override fun onError(e: Throwable) {
        error?.invoke(e)
    }

    override fun onSuccess(t: T) {
        next?.invoke(t)
    }

    fun subscribe() {
        single.subscribe(this)
    }
}


fun <T> Observable<T>.flatSubscribe():FlatSubscribe<T>{
    return FlatSubscribe(this)
}

fun <T> Single<T>.flatSubscribe():FlatSingleSubscribe<T>{
    return FlatSingleSubscribe(this)
}
