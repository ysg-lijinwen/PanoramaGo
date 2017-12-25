package com.panorama.go.bean

/**
 * Description:
 * Created by Kevin.Li on 2017-12-25.
 */
data class ListBean(
        var id: String, //    记录id
        val image: String,//    图片地址
        val url: String,//    跳转链接
        val type: String,//    跳转链接类型（0-普通链接；1-全景链接）
        val desc: String//    描述
)