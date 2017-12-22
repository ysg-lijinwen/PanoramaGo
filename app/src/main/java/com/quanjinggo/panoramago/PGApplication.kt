package com.quanjinggo.panoramago

import com.player.panoplayer.PanoPlayer
import com.quanjinggo.base.BaseApplication

/**
 * Description:
 * Created by Kevin.Li on 2017-12-14.
 */
class PGApplication: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        PanoPlayer.init(this)
    }
}