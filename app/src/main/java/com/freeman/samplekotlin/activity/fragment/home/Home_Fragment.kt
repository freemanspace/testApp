package com.freeman.samplekotlin.activity.fragment.home

import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.freeman.samplekotlin.R
import com.freeman.samplekotlin.activity.BaseFragment
import com.freeman.samplekotlin.activity.fragment.home.page.ListPage
import com.freeman.samplekotlin.activity.onFragmentKeyDown
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_header1.*

class Home_Fragment : BaseFragment(), onFragmentKeyDown {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun initView(){
        setHeaderTitle("Api Demo")

        setIsExit(true)

        asyncImageHeader.setTransType(1)
        asyncImageHeader.setImageRes(R.drawable.icon_startbucks,0,0,false)

        viewpager.apply {
            //isUserInputEnabled = false
            adapter = HomeViewpagerAdapter(this@Home_Fragment)
        }

        TabLayoutMediator(tabLayout,viewpager){tab, position ->
            when(position){
                0 -> tab.setText("容幣紀錄")
                1 -> tab.setText("兌換券")
                else -> tab.setText("")
            }
            viewpager.currentItem = tab.position
        }.attach()
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            if(base_isExit){

            } else{
                return false
            }
            return true
        }
        return false
    }


    class HomeViewpagerAdapter(fa:Fragment) : FragmentStateAdapter(fa){
        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            when(position){
                0 -> { return ListPage()
                }
                1 -> { return ListPage()
                }
                else ->{ return ListPage()
                }
            }
        }
    }

}