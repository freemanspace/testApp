package com.freeman.samplekotlin.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.freeman.samplekotlin.dialog.MsgDialog
import com.freeman.samplekotlin.dialog.MsgSelDialog
import com.freeman.samplekotlin.dialog.OnDialogClickListener
import kotlinx.android.synthetic.main.view_header1.*
import kotlinx.android.synthetic.main.view_loading.*

public abstract class BaseFragment : Fragment() {

    var base_isExit = false;
    var fragmentView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(getLayoutRes(), container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.initView()
    }

    override fun onStart() {
        super.onStart()
        //(activity as BaseFragmentActivity).nowFragment = this
    }

    //--------------------------------------------------------------------
    abstract protected fun getLayoutRes(): Int

    abstract protected fun initView()

    //--------------------------------------------------------------------
    protected fun showNavFun(funNo:Int){
        (activity as BaseFragmentActivity).showNavFun(funNo)
    }

    protected fun addFragment(fragment: BaseFragment){
        (activity as BaseFragmentActivity).addFragment(fragment)
    }

    protected fun replaceFragment(fragment: BaseFragment){
        (activity as BaseFragmentActivity).replaceFragment(fragment)
    }

    protected fun deleteeFragment(fragment: BaseFragment){
        (activity as BaseFragmentActivity).deleteeFragment(fragment)
    }

    protected fun finish(){
        (activity as BaseFragmentActivity).finishFragment()
    }

    /**
     * //設定是否back離開app,default is false
     * @param isExit
     */
    fun setIsExit(isExit: Boolean){
        base_isExit = isExit
    }

    /**
     * 螢幕不關
     */
    fun setScreenOn(){
        (context as BaseFragmentActivity).setScreenOn()
    }

    /**
     * 顯示toast
     * @param msg
     */
    fun showToast(msg: String){
        try{
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        } catch (e: Exception){
        }
    }

    /**
     * 顯示loading
     */
    fun showLoading(){
        if(base_ry_loading!=null) {
            base_ry_loading?.visibility = View.VISIBLE
        }
    }

    /**
     * 關閉loading
     */
    fun closeLoading(){
        if(base_ry_loading!=null) {
            base_ry_loading?.visibility = View.GONE
        }
    }

    /**
     * 是否正在顯示loading
     */
    fun isLoading():Boolean {
        if(base_ry_loading?.visibility==View.VISIBLE){
            return true
        }
        return false
    }

    /**
     * 顯示msg dialog
     * @param msg
     * @param onDialogClickListener
     */
    open fun showMsg(msg: String?, onDialogClickListener: OnDialogClickListener?) {
        var msgDialog = MsgDialog(context!!, "", msg, "OK", onDialogClickListener, null)
        msgDialog.show()
    }

    /**
     * 顯示msg select dialog
     * @param msg
     * @param onDialogClickListener
     */
    open fun showMsgSel(msg: String?, onDialogClickListener: OnDialogClickListener?) {
        var msgSelectDialog =
                MsgSelDialog(context!!, "", msg, "NO", "OK", onDialogClickListener, null)
        msgSelectDialog.show()
    }

    //----------------------------------header------------------------------------------------------------
    open fun setHeaderTitle(title: String?) {
        if (base_tv_header != null) {
            base_tv_header?.text = title
        }
    }
}

//用來偵測activity onkey dowen
interface onFragmentKeyDown {
    fun onKeyDown(keyCode: Int, event: KeyEvent?):Boolean
}