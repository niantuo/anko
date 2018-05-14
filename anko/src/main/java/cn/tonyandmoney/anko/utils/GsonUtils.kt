package cn.tonyandmoney.anko.utils

import com.google.gson.Gson

/**
 * @author niantuo
 * @createdTime 2018/4/27 16:25
 *
 *
 */

object GsonUtils {
    val GSON = Gson()

    fun toJson(any: Any?): String {
        if (any == null) return GSON.toJson(Any())
        return GSON.toJson(any)
    }

    inline fun <reified T : Any> fromJson(json: String): T? {
        return try {
            GSON.fromJson<T>(json, T::class.java)
        } catch (e: Exception) {
            null
        }
    }
}
