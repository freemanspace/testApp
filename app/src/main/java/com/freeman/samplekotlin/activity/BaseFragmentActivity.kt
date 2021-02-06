package com.freeman.samplekotlin.activity

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.freeman.samplekotlin.R
import com.freeman.samplekotlin.activity.fragment.home.Home_Fragment
import com.freeman.samplekotlin.activity.fragment.othger.DemoAPI_Fragment
import com.freeman.samplekotlin.dialog.MsgSelDialog
import com.freeman.samplekotlin.dialog.OnDialogClickListener
import kotlinx.android.synthetic.main.activity_basefragment.*


open class BaseFragmentActivity : AppCompatActivity() {

    var nowNavType:Int = 1; //目前的nav fun

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 5.0
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) // 確認取消半透明設置。
            window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 全螢幕顯示，status bar 不隱藏，activity 上方 layout 會被 status bar 覆蓋。
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            ) // 配合其他 flag 使用，防止 system bar 改變後 layout 的變動。
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) // 跟系統表示要渲染 system bar 背景。
            window.setStatusBarColor(Color.TRANSPARENT)
        }

        setContentView(R.layout.activity_basefragment)

        btn_nav_fun1.setOnClickListener(View.OnClickListener {
            if(nowNavType != 1) {
                nowNavType = 1
                showFragment()
            }
        })
        btn_nav_fun2.setOnClickListener(View.OnClickListener {
            if(nowNavType != 2) {
                nowNavType = 2
                showFragment()
            }
        })
        btn_nav_fun3.setOnClickListener(View.OnClickListener {
            if(nowNavType != 3) {
                nowNavType = 3
                showFragment()
            }
        })
        btn_nav_fun4.setOnClickListener(View.OnClickListener {
            if(nowNavType != 4) {
                nowNavType = 4
                showFragment()
            }
        })

        //bottomNavigationView
        floatingActionButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(baseContext, "sss", Toast.LENGTH_SHORT).show()
        })

        showFragment()
    }

    fun showFragment(){
        showNavFun(nowNavType)

        when(nowNavType){
            1 -> {replaceFragment(Home_Fragment())}
            2 -> {replaceFragment(DemoAPI_Fragment())}
            3 -> {replaceFragment(DemoAPI_Fragment())}
            4 -> {replaceFragment(DemoAPI_Fragment())}
            else ->{replaceFragment(DemoAPI_Fragment())}
        }
    }

    public fun showNavFun(funNo:Int){
        img_naf_fun1.setImageResource(R.drawable.icon_nav_fun1)
        img_naf_fun2.setImageResource(R.drawable.icon_nav_fun2)
        img_naf_fun3.setImageResource(R.drawable.icon_nav_fun3)
        img_naf_fun4.setImageResource(R.drawable.icon_nav_fun4)
        when(funNo){
            1 -> {img_naf_fun1.setImageResource(R.drawable.icon_nav_fun1_on)}
            2 -> {img_naf_fun2.setImageResource(R.drawable.icon_nav_fun2_on)}
            3 -> {img_naf_fun3.setImageResource(R.drawable.icon_nav_fun3_on)}
            4 -> {img_naf_fun4.setImageResource(R.drawable.icon_nav_fun4_on)}
            else -> {}
        }
    }

    public fun addFragment(fragment: BaseFragment){
        var transcation = supportFragmentManager.beginTransaction()
        transcation.add(R.id.fragmentLayout, fragment)
        transcation.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transcation.addToBackStack(fragment.javaClass.name);
        transcation.commit()
    }

    public fun replaceFragment(fragment: BaseFragment){
        var transcation = supportFragmentManager.beginTransaction()
        transcation.replace(R.id.fragmentLayout, fragment)
        transcation.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        //transcation.addToBackStack(fragment.javaClass.name);
        transcation.commit()
    }

    public fun deleteeFragment(fragment: BaseFragment){
        var transcation = supportFragmentManager.beginTransaction()
        transcation.remove(fragment)
        transcation.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        //transcation.addToBackStack(fragment.javaClass.name);
        transcation.commit()
    }

    public fun finishFragment(){
        val count = supportFragmentManager.backStackEntryCount
        //如果tag为null，flags为0时，弹出回退栈中最上层的那个fragment。
        //如果tag为null ，flags为1时，弹出回退栈中所有fragment。
        //如果tag不为null，那就会找到这个tag所对应的fragment，flags为0时，弹出该
        //fragment以上的Fragment，如果是1，弹出该fragment（包括该fragment）以
        //上的fragment。
        supportFragmentManager.popBackStack();
    }

    /**
     * 字體大小不受影響
     * @return
     */
    override fun getResources(): Resources {
        var res:Resources = super.getResources()
        val config:Configuration = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.displayMetrics)
        return res
    }

    /**
     * 螢幕不關
     */
    fun setScreenOn(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )
    }

}