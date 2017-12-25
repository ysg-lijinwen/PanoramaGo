package com.panorama.go

import com.panorama.base.BaseApplication
import com.player.panoplayer.PanoPlayer

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