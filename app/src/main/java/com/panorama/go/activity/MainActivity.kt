package com.panorama.go.activity

import com.panorama.base.BaseActivity
import com.panorama.go.R
import com.panorama.go.fragment.HomeFragment

/**
 * 首页框架
 */
class MainActivity : BaseActivity() {
    val homeFragment: HomeFragment by lazy { HomeFragment.newInstance() }

    override fun setRootView() {
        super.setRootView()
        setContentView(R.layout.activity_main)

//        val navigation = findViewById(R.id.navigation) as BottomNavigationView
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun initWidget() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment, "homeFragment").commit()
//        mFragment = homeFragment
    }

//    private fun switchFragment(to: Fragment, tag: String) {
//        if (mFragment !== to) {
//            mFragment = to
//            if (!mFragment.isAdded) {
//                supportFragmentManager.beginTransaction().add(R.id.fragment_container, mFragment, tag).commit()
//            }
//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.replace(R.id.fragment_container, to).commit()
//        }
//    }

//    val dashboardFragment: DashboardFragment by lazy { DashboardFragment.newInstance() }
//    val notificationsFragment: NotificationsFragment by lazy { NotificationsFragment.newInstance() }
//    val fragmentTags = arrayOf("homeFragment", "dashboardFragment", "notificationsFragment")
//    lateinit var mFragment: Fragment

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                switchFragment(homeFragment, fragmentTags[0])
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                switchFragment(dashboardFragment, fragmentTags[1])
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                switchFragment(notificationsFragment, fragmentTags[2])
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

}
