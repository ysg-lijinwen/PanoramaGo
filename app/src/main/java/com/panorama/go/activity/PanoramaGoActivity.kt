package com.panorama.go.activity

import android.graphics.Bitmap
import android.os.Looper
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.panorama.base.BaseActivity
import com.panorama.go.R
import com.panorama.go.util.DTListDialog
import com.player.data.panoramas.Hotspot
import com.player.data.panoramas.PanoramaData
import com.player.panoplayer.*
import com.player.panoplayer.plugin.VideoPlugin
import com.player.panoplayer.plugin.WVideoPlugin
import kotlinx.android.synthetic.main.activity_panorama_go.*
import tv.danmaku.ijk.media.player.IjkMediaPlayer
import tv.danmaku.ijk.media.player.StatisticsData
import java.util.*

class PanoramaGoActivity : BaseActivity(), IPanoPlayerListener, IPanoPlayerVideoPluginListener,
        IPanoPlayerHotpotListener, IScreenShot, SeekBar.OnSeekBarChangeListener {
    private var panoplayer_renderer: PanoPlayer? = null
    private var playerStatus: PanoPlayer.PanoVideoPluginStatus = PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_STOP
    private var isSeekBarDragging: Boolean = false
    private var vplugin: Plugin? = null
    private var panoplayerurl: PanoPlayerUrl = PanoPlayerUrl()
    private var menuDialog: DTListDialog? = null
    private val PanoPlayer_Template = "<DetuVr> " + "<settings init=\"pano1\" initmode=\"default\" enablevr=\"true\" title=\"\"/>" + "<scenes> " + "<scene name=\"pano1\" title=\"\" thumburl=\"\" >" + "<preview url=\"%s\" type=\"CUBESTRIP\" />" + "<image type = \"%s\" url =\"%s\" device = \"0\" />" + "<view fovmin='110' fovmax='170' gyroEnable=\"false\"/>" + "</scene>" + "</scenes>" + "</DetuVr>"

    override fun setRootView() {
        super.setRootView()
        setContentView(R.layout.activity_panorama_go)
    }

    override fun PanoPlayOnLoading() {
        Log.i("ceshi", "PanoPlayOnLoading on MainActivity,isMainThread:" + (Looper.getMainLooper() == Looper.myLooper()))
    }

    override fun PanoPlayOnEnter(p0: PanoramaData?) {
        Log.d("PanoPlay", "PanoPlayOnEnter")
    }

    override fun PanoPlayOnLoaded() {
        // 用于初始化视频的各种参数
        val plugin = panoplayer_renderer?.curPlugin
        if (plugin != null && (plugin is VideoPlugin || plugin is WVideoPlugin)) {
            videolay.visibility = View.VISIBLE
        } else {
            videolay.visibility = View.GONE
        }
    }

    override fun PanoPlayOnLeave(p0: PanoramaData?) {
        Log.d("PanoPlay", "PanoPlayOnLeave")
    }

    override fun PanoPlayOnError(p0: PanoPlayer.PanoPlayerErrorCode?) {
        Toast.makeText(context, p0?.name, Toast.LENGTH_SHORT).show()
    }

    override fun PluginVideoOnStatisticsChanged(p0: StatisticsData?) {
    }

    override fun PluginVideoOnInit() {
        // 用于初始化视频的各种参数
        val plugin = panoplayer_renderer?.curPlugin
        if (plugin != null && (plugin is VideoPlugin || plugin is WVideoPlugin)) {
            if (plugin is VideoPlugin) {
                vplugin = plugin
                //videoPlugin.setOption(PlayerOption.OPT_CATEGORY_PLAYER, "haha", 1);
                (vplugin as VideoPlugin).setLogLevel(IjkMediaPlayer.IJK_LOG_SILENT)
                videolay.visibility = View.VISIBLE
            } else if (plugin is WVideoPlugin) {
                vplugin = plugin
                (vplugin as WVideoPlugin).setLogLevel(IjkMediaPlayer.IJK_LOG_VERBOSE)
            }
        }
    }

    override fun PluginVideoOnSeekFinished() {
        Log.d("PanoPlay", "PluginVideoOnSeekFinished")
    }

    override fun PluginVideoOnProgressChanged(p0: Int, p1: Int, p2: Int) {
        Log.d("PanoPlay", "PluginVideoOnProgressChanged:" + p0)

        if (!isSeekBarDragging) {
            sb_progress.max = p2
            sb_progress.secondaryProgress = p1
            sb_progress.progress = p0
        }

        lable2.text = formatDuring(p2.toLong())
        lable1.text = formatDuring(p0.toLong())
    }

    override fun PluginVideOnPlayerError(p0: PanoPlayer.PanoPlayerErrorStatus?, p1: String?) {
        Log.d("PanoPlay", "PluginVideOnPlayerError" + p1)
    }

    override fun PluginVideoOnStatusChanged(p0: PanoPlayer.PanoVideoPluginStatus?) {
        playerStatus = p0!!
        when (p0) {
            PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_PAUSE -> {
                btn_play?.post({ btn_play.text = "播放" })
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to pause")
            }
            PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_STOP -> {
                btn_play.post({ btn_play.text = "停止" })
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to stop")
                sb_progress.progress = 0
            }
            PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_PLAYING -> {
                btn_play.post({ btn_play.text = "暂停" })
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to play")
            }
            PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_FINISH ->
                // videoplugin.release();
                Log.d("PanoPlay", "PluginVideoOnStatusChanged to FINISH")
            PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_BUFFER_EMPTY -> Log.d("PanoPlay", "PluginVideoOnStatusChanged to BUFFER_EMPTY")
            PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_HW_TO_AVCODEC -> Log.d("PanoPlay", "硬解失败了要切换为软解")
            else -> Log.d("PanoPlay", "PluginVideoOnStatusChanged to UNPREPARED;")
        }
    }

    override fun PanoPlayOnTapAfterHotPot(p0: Hotspot?, p1: String?) {
        Log.d("PanoPlay", "PanoPlayOnTapAfterHotPot")
    }

    override fun PanoPlayOnTapBeforeHotPot(p0: Hotspot?) {
        Log.d("PanoPlay", "PanoPlayOnTapBeforeHotPot")
    }

    override fun onGetScreenShot(p0: Bitmap?) {
        Log.d("PanoPlay", "shot image")
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (seekBar?.id == R.id.sb_progress && !isSeekBarDragging && fromUser) {
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        if (seekBar?.id == R.id.sb_progress) {
            isSeekBarDragging = true
        }
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        if (seekBar!!.id == R.id.sb_progress) {
            isSeekBarDragging = false
        }
        if (vplugin is VideoPlugin) {
            (vplugin as VideoPlugin).seekTo(seekBar.progress)
        } else if (vplugin is WVideoPlugin) {
            (vplugin as WVideoPlugin).seekTo(seekBar.progress)
        }
    }

    override fun initWidget() {
        super.initWidget()
        sb_Brightness.setOnSeekBarChangeListener(this)
        sb_Contrast.setOnSeekBarChangeListener(this)
        sb_Saturation.setOnSeekBarChangeListener(this)

        panoplayer_renderer = glview.render
        panoplayer_renderer?.listener = this
        panoplayer_renderer?.videoPluginListener = this
        panoplayer_renderer?.hotpotListener = this
        panoplayer_renderer?.gyroEnable = true
        panoplayer_renderer?.setGestureEnable(true)
        playPhoto.performClick()
        panoplayer_renderer?.setOnClickPanoViewListener { _, _ ->
            val menuLay = menu_layer
            if (menuLay.visibility == View.VISIBLE) {
                menuLay.visibility = View.INVISIBLE
            } else {
                menuLay.visibility = View.VISIBLE
            }
        }
        panoplayer_renderer?.setOnLongClickPanoViewListener { _, _ -> }
        videolay.visibility = View.GONE
        menuDialog = DTListDialog(context)
        menuDialog!!.setTitle("请选择功能")

        menuDialog!!.setTitle("请选择功能").setItems(arrayOf("平面模式", "VR模式_左右", "小行星模式", "全景模式", "鱼眼模式", "半球模式", "VR模式_上下", "截屏"), { _, _, position ->
            when (position) {
                0 -> {
                    panoplayer_renderer?.gyroEnable = false
                    panoplayer_renderer?.viewMode = ViewMode.VIEWMODE_PLANE // 原始图像
                }
                1 -> {
                    panoplayer_renderer?.gyroEnable = false
                    panoplayer_renderer?.viewMode = ViewMode.VIEWMODE_VR_HORIZONTAL // vr
                    panoplayer_renderer?.setVLookAt(0f)
                    panoplayer_renderer?.setHLookAt(0f)
                }
                2 -> {
                    panoplayer_renderer?.gyroEnable = false
                    // 模式渐变动画
                    panoplayer_renderer?.setAnimationViewMode(ViewMode.VIEWMODE_LITTLEPLANET, 120f, -90f, 0f, -0.999f, 0.001f)// 小行�?
                }

                3 -> {
                    panoplayer_renderer?.gyroEnable = false
                    // 模式渐变动画
                    panoplayer_renderer?.setAnimationViewMode(ViewMode.VIEWMODE_DEF, 65f, 0f, 0f, 0f, 0.1f)// 全景
                }

                4 -> {
                    panoplayer_renderer?.gyroEnable = false
                    panoplayer_renderer?.setHLookAt(0f)
                    panoplayer_renderer?.setVLookAt(0f)
                    panoplayer_renderer?.setFov(53f)
                    panoplayer_renderer?.setFovMax(66f)
                    panoplayer_renderer?.setFovMin(35f)
                    panoplayer_renderer?.viewMode = ViewMode.VIEWMODE_FISHEYE
                }

                5 -> {
                    panoplayer_renderer?.gyroEnable = false
                    // 模式渐变动画
                    panoplayer_renderer?.setAnimationViewMode(ViewMode.VIEWMODEL_SPHERE, 113f, 26f, 0f, -1.2f, 0.34f) // 半球
                }
                6 -> {
                    // VR——上下
                    panoplayer_renderer?.gyroEnable = false
                    panoplayer_renderer?.viewMode = ViewMode.VIEWMODE_VR_VERTICAL // vr
                    panoplayer_renderer?.setVLookAt(0f)
                    panoplayer_renderer?.setHLookAt(0f)
                }
                7 -> {
                }

                else -> {
                }
            }// 截屏
            menuDialog?.dismiss()
        })

        playPhoto.setOnClickListener {
            val imgPath = "assets://pano.jpeg"
            if (!TextUtils.isEmpty(inputUrl.text.toString())) {
                panoplayerurl.setXmlUrl(inputUrl.text.toString())
                panoplayer_renderer?.Play(panoplayerurl)
            } else {
                val xmlstring = String.format(PanoPlayer_Template, "", "sphere", imgPath)
                panoplayerurl.setXmlContent(xmlstring)
                panoplayer_renderer?.Play(panoplayerurl)
            }
        }

        playVideo.setOnClickListener {
            var path = inputUrl.text.toString()
            //path = "mnt/sdcard/bug.mp4";
            //path = "rtmp://pili-live-rtmp.live.detu.com/detulive/sqr222";
            path = "rtsp://192.168.42.1/tmp/SD0/DCIM/161206000/182420AB.MP4"
            path = "rtmp://pili-live-rtmp.live.detu.com/detulive/9527"
            path = "mnt/sdcard/360.MP4"
            path = "mnt/sdcard/2017_0427_200045_014.MP4"
            path = "mnt/sdcard/4k.MP4"
            //path = "mnt/sdcard/video-103729.mp4";
            //path = "rtmp://222.211.65.243/live/S23811315370860655221";
            //path = "rtsp://192.168.42.1/live";
//            path = "http://media.qicdn.detu.com/@/70955075-5571-986D-9DC4-450F13866573/2016-05-19/573d15dfa19f3-2048x1024.m3u8"//sdk提供
            path = "assets://video01.mp4"//本地视频
//            path = "assets://video02.mov"//本地视频

            if (TextUtils.isEmpty(path)) {
                panoplayerurl.setXmlUrl("http://www.detu.com/ajax/pano/xml/159891")
            } else {
                val xmlstring = String.format(PanoPlayer_Template, "", "video", path)
                panoplayerurl.setXmlContent(xmlstring)
            }
            val value = OptionValue(OptionType.OPT_CATEGORY_CODEC, "hw_decoder", "true")
            val values = ArrayList<OptionValue>(1)
            values.add(value)
            panoplayer_renderer?.play(panoplayerurl, values)
        }
        btMenu.setOnClickListener {
            menuDialog?.show()
        }

        btn_play.setOnClickListener {
            when (playerStatus) {
                PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_PAUSE -> {
                    if (vplugin is VideoPlugin) {
                        (vplugin as VideoPlugin).start()
                    } else if (vplugin is WVideoPlugin) {
                        (vplugin as WVideoPlugin).start()
                    }
                    Log.e("", "click btn_play to start")
                }
                PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_STOP -> {
                    if (vplugin is VideoPlugin) {
                        (vplugin as VideoPlugin).start()
                    } else if (vplugin is WVideoPlugin) {
                        (vplugin as WVideoPlugin).start()
                    }
                    Log.e("", "click btn_play to start")
                }
                PanoPlayer.PanoVideoPluginStatus.VIDEO_STATUS_PLAYING -> {
                    if (vplugin is VideoPlugin) {
                        (vplugin as VideoPlugin).pause()
                    } else if (vplugin is WVideoPlugin) {
                        (vplugin as WVideoPlugin).pause()
                    }
                    Log.e("", "click btn_play to pause")
                }
                else -> {
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        glview.onResume()
    }

    override fun onPause() {
        super.onPause()
        glview.onPause()
    }

    override fun initData() {
        super.initData()
    }

    override fun initListener() {
        super.initListener()
    }

    fun formatDuring(mss: Long): String {
        val days = mss / (1000 * 60 * 60 * 24)
        val hours = mss % (1000 * 60 * 60 * 24) / (1000 * 60 * 60) + days * 24
        val minutes = mss % (1000 * 60 * 60) / (1000 * 60)
        val seconds = mss % (1000 * 60) / 1000

        var HH = if (hours > 0) hours.toString() else "00"
        var mm = if (minutes > 0) minutes.toString() else "00"
        var ss = if (seconds > 0) seconds.toString() else "00"

        HH = if (HH.length == 1) "0" + HH else HH
        mm = if (mm.length == 1) "0" + mm else mm
        ss = if (ss.length == 1) "0" + ss else ss
        return "$HH : $mm : $ss"
    }
}
