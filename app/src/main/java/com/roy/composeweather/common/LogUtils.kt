package com.roy.composeweather.common

import android.util.Log
import com.roy.composeweather.BuildConfig
import com.roy.composeweather.MainApp

/**
 * 日志工具单例类
 */
object LogUtils {
    private const val LIMIT_LENGTH = 3 * 1024
    private const val APP_NAME = MainApp.APP_NAME
    var enableLog = BuildConfig.DEBUG

    fun v(content: String, tag: String = APP_NAME) {
        if (!enableLog) return
        splitLogCheck(content, tag, throwable = null) { _content, _tag, _ ->
            Log.v(_tag, _content)
        }
    }

    fun d(content: String, tag: String = APP_NAME) {
        if (!enableLog) return
        splitLogCheck(content, tag, throwable = null) { _content, _tag, _ ->
            Log.d(_tag, _content)
        }
    }

    fun i(content: String, tag: String = APP_NAME) {
        if (!enableLog) return
        splitLogCheck(content, tag, throwable = null) { _content, _tag, _ ->
            Log.i(_tag, _content)
        }
    }

    fun w(content: String, tag: String = APP_NAME) {
        if (!enableLog) return
        splitLogCheck(content, tag, throwable = null) { _content, _tag, _ ->
            Log.w(_tag, _content)
        }
    }

    fun e(content: String, tag: String = APP_NAME, throwable: Throwable? = null) {
        if (!enableLog) return
        splitLogCheck(content, tag, throwable) { _content, _tag, _throwable ->
            if (throwable == null) {
                Log.e(_tag, _content)
                return@splitLogCheck
            }
            Log.e(_tag, _content, _throwable)
        }
    }

    private fun splitLogCheck(
        content: String,
        tag: String,
        throwable: Throwable? = null,
        log: (content: String, tag: String, throwable: Throwable?) -> Unit
    ) {
        var msg = content
        if (msg.length <= LIMIT_LENGTH) { // 小于分段限制的直接打印
            log(msg, tag, throwable)
        } else { // 超过限制的循环分段打印
            while (msg.length > LIMIT_LENGTH) {
                val subMsg = msg.substring(0, LIMIT_LENGTH)
                msg = msg.replace(subMsg, "")
                log(msg, tag, throwable)
            }
        }
    }

}