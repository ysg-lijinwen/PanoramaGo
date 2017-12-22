package com.quanjinggo.panoramago.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.quanjinggo.base.BaseActivity
import com.quanjinggo.panoramago.R
import com.quanjinggo.panoramago.fragment.DashboardFragment
import com.quanjinggo.panoramago.fragment.HomeFragment
import com.quanjinggo.panoramago.fragment.NotificationsFragment

/**
 * 首页框架
 */
class MainActivity : BaseActivity() {
    val homeFragment: HomeFragment by lazy { HomeFragment.newInstance() }
    val dashboardFragment: DashboardFragment by lazy { DashboardFragment.newInstance() }
    val notificationsFragment: NotificationsFragment by lazy { NotificationsFragment.newInstance() }
    val fragmentTags = arrayOf("homeFragment", "dashboardFragment", "notificationsFragment")
    lateinit var mFragment: Fragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment(homeFragment, fragmentTags[0])
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                switchFragment(dashboardFragment, fragmentTags[1])
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                switchFragment(notificationsFragment, fragmentTags[2])
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setRootView() {
        super.setRootView()
        setContentView(R.layout.activity_main)

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun initWidget() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment, fragmentTags[0]).commit()
        mFragment = homeFragment
    }

    private fun switchFragment(to: Fragment, tag: String) {
        if (mFragment !== to) {
            mFragment = to
            if (!mFragment.isAdded) {
                supportFragmentManager.beginTransaction().add(R.id.fragment_container, mFragment, tag).commit()
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, to).commit()
        }
    }
}
