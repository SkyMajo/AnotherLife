package com.nixo.afterworklife.MainPage.Fragement

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.jerey.loglib.Klog
import com.nixo.afterworklife.Common.BaseFragment
import com.nixo.afterworklife.GroupPage.GroupActivity.AllGroupActivity
import com.nixo.afterworklife.GroupPage.GroupActivity.CreateGroupActivity
import com.nixo.afterworklife.MainPage.Adapter.GroupHotReviewAdapter
import com.nixo.afterworklife.MainPage.Adapter.GroupJoinReviewAdapter
import com.nixo.afterworklife.MainPage.Adapter.LocationGroupAdapter
import com.nixo.afterworklife.MainPage.Data.Data
import com.nixo.afterworklife.MainPage.Data.GroupData.LocationDataItem
import com.nixo.afterworklife.MainPage.Present.GroupPresent
import com.nixo.afterworklife.R
import com.nixo.afterworklife.Utils.Data.SharedUtils
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Welcome.View.Activity.LoginActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.yqjr.superviseapp.utils.GpsUtil
import com.yqjr.superviseapp.utils.dialog.CommonDialog
import kotlinx.android.synthetic.main.fragment_group.*
import kotlinx.android.synthetic.main.include_no_content_big.*


class GroupFragment : BaseFragment<GroupPresent>() , XRecyclerView.LoadingListener{
    override fun onLoadMore() {

    }
    var lat = ""
    var lng = ""
    override fun onRefresh() {
        presenter.getLocationGroupData("$lng","$lat")
    }

    private var rxPermissions: RxPermissions? = null



    override fun setLayoutParame(): Int = R.layout.fragment_group

    @SuppressLint("MissingPermission")
    override fun onState() {

        if(SharedUtils.getString("token").isEmpty()){
            ll_unlogin.visibility = View.VISIBLE
            ll_review.visibility = View.GONE
            ll_unlogin.click { Action(LoginActivity::class.java) }
        }else{
            ll_unlogin.visibility = View.GONE
            ll_review.visibility = View.VISIBLE
            presenter.getGroupJoinData()
        }
        rxPermissions = RxPermissions(this)
        requestPermissionLocations()
//        rv_location.setLoadingListener(this)

        tv_all.click {
            Action(AllGroupActivity::class.java)
        }
        rl_all.click {
            Action(AllGroupActivity::class.java)
        }

    }





    @SuppressLint("CheckResult", "MissingPermission")
    fun requestPermissionLocations() {
        rxPermissions!!.requestEachCombined(Manifest.permission.ACCESS_FINE_LOCATION)
            .subscribe { permission ->
                if (permission.granted) {
                    val isopen = GpsUtil.isOPen(activity!!)
                    if (isopen) {
                        // 获取位置服务
                        val serviceName = Context.LOCATION_SERVICE
                        // 调用getSystemService()方法来获取LocationManager对象
                        var locationManager = activity!!.getSystemService(serviceName) as LocationManager?
                        // 指定LocationManager的定位方法
                        val provider = LocationManager.GPS_PROVIDER
                        val provider2 = LocationManager.NETWORK_PROVIDER
                        // 调用getLastKnownLocation()方法获取当前的位置信息
                        val location2 = locationManager!!.getLastKnownLocation(provider2)
                        val location = locationManager!!.getLastKnownLocation(provider)
                        when {
                            location != null -> {
                                //获取纬度
                                val lat = location.latitude
                                //获取经度
                                val lng = location.longitude
                                this.lat = lat.toString()
                                this.lng = lng.toString()
                                SharedUtils.putString("address","$lat,$lng")
                                presenter.getLocationGroupData("$lng","$lat")
                            }
                            location2 != null -> {
                                //获取纬度
                                val lat = location2!!.latitude
                                //获取经度
                                val lng = location2!!.longitude
                                this.lat = lat.toString()
                                this.lng = lng.toString()
                                SharedUtils.putString("address","$lat,$lng")
                                presenter.getLocationGroupData("$lng","$lat")
                            }
                            else -> presenter.getLocationGroupData("121.43693436596678","31.025808460214524")
                        }

//                          查询附近社团
                        SharedUtils.putString("location_isOpen","1")
                        Klog.e("Nixo --- 权限isOpen",isopen)
                    } else {
                        presenter.getLocationGroupData("121.43693436596678","31.025808460214524")
                        SharedUtils.putString("location_isOpen","0")
                        Klog.e("Nixo --- 权限isOpen",isopen)
//                        showCommonDialog()
                    }
                }
            }
    }

    /**
     * 提示框
     */
    private fun showCommonDialog() {
        val commonDialog = CommonDialog(activity!!)
        commonDialog.show()
        commonDialog.setOnDialogListener(object : CommonDialog.OnDialogListener {
            override fun onDetermine(vararg msg: String) {
                commonDialog.dismiss()
                //跳转GPS设置界面
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }

        })
    }

    override fun onResume() {
        super.onResume()
        val isopen = GpsUtil.isOPen(activity!!)
//        if (isopen){
//
//        }
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Klog.e("Nixo","显示隐藏")
    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}



    fun onInitCreateList(data: MutableList<LocationDataItem>) {
        if(tv_max_size == null || rv_group_review == null){
            return
        }
        tv_max_size.text = data.size.toString()
        if(data.size == 0){
            ll_no_josin.visibility = View.VISIBLE
        }else{
            rv_group_review.layoutManager = LinearLayoutManager(activity!!).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
            rv_group_review.adapter = GroupHotReviewAdapter(activity!!,
                com.nixo.afterworklife.R.layout.item_group_review,data)
        }

    }

    fun onLocationSuccess(data: MutableList<LocationDataItem>) {
        Klog.e("dataSize --> Nixo --->",data.size)
//        rv_location.refreshComplete()
        if(rv_location != null){
            if(data.size == 0){
                if(ll_no_content != null){
                    ll_no_content.visibility = View.VISIBLE
                }
                if(rv_location != null){
                    rv_location.visibility = View.GONE
                }
            }else{
                if(ll_no_content != null){
                    ll_no_content.visibility = View.GONE
                }
                if(rv_location != null){
                    rv_location.visibility = View.VISIBLE
                }
                rv_location.layoutManager = LinearLayoutManager(activity).also {
                    it.orientation = LinearLayoutManager.VERTICAL
                }
                rv_location.adapter = LocationGroupAdapter(activity!!,
                    R.layout.item_group,data,0)

            }
        }



    }

}