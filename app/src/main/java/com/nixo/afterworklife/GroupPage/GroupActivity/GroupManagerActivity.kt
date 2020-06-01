package com.nixo.afterworklife.GroupPage.GroupActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyf.immersionbar.ImmersionBar
import com.nixo.afterworklife.ActivitePage.Activity.ActiviteManagerActivity
import com.nixo.afterworklife.Common.APP
import com.nixo.afterworklife.Common.AppManager
import com.nixo.afterworklife.Common.BaseActivity
import com.nixo.afterworklife.GroupPage.GroupAdapter.GroupUserAdapter
import com.nixo.afterworklife.GroupPage.GroupManagerData.GroupUser.DataItem
import com.nixo.afterworklife.GroupPage.GroupManagerData.GroupUser.Users
import com.nixo.afterworklife.GroupPage.GroupPresent.GroupManagerPresent
import com.nixo.afterworklife.MainPage.Acivity.MainActivity
import com.nixo.afterworklife.MainPage.Acivity.MessageInfoActivity
import com.nixo.afterworklife.R
import com.nixo.afterworklife.UserPage.View.FansActivity
import com.nixo.afterworklife.Utils.ConfirmAnnoumeDialog
import com.nixo.afterworklife.Utils.ConfirmDialog
import com.nixo.afterworklife.Utils.ConfirmTextDialog
import com.nixo.afterworklife.Utils.Ext.click
import com.nixo.afterworklife.Utils.ToastUtils
import com.yqjr.superviseapp.utils.dialog.AlertUtils
import kotlinx.android.synthetic.main.activity_group_manager.*
import kotlinx.android.synthetic.main.item_fans.*

class GroupManagerActivity : BaseActivity<GroupManagerPresent>() ,ConfirmTextDialog.ConfirmExitOnClickListener,
    ConfirmAnnoumeDialog.ConfirmOnClickListener {
    override fun confirm(string: String) {
        presenter.onAnnouncementGroups(groupId,string)
    }

    var dialog:ConfirmAnnoumeDialog? = null

    override fun onLayout(): Int = R.layout.activity_group_manager
    var groupId = 0
    override fun onState() {
        ImmersionBar.with(this)
//            .transparentStatusBar()  //透明状态栏，不写默认透明色
            .transparentNavigationBar()
            .statusBarDarkFont(true)
            .barColor(R.color.color_white)
            .init()

        AlertUtils.showProgress(false,this)
        groupId = intent.getIntExtra("group_id", 0)
        presenter.isAdmin(groupId)
        presenter.getGroupUser(groupId)
        initClick()

    }




    fun initClick(){
        ll_title.click{
            finish()
        }
        ll_creat_activite.click {
            var bundle = Bundle()
            bundle.putInt("groupId",groupId)
            bundle.putString("premsioons",premsioons)
            paramerAction(ActiviteManagerActivity::class.java,bundle)
        }
        ll_person.click {
            var bundle = Bundle()
            bundle.putString("startType","group_person")
            bundle.putInt("groupId",groupId)
            paramerAction(FansActivity::class.java,bundle)
        }

    }

    override fun onViewStateResotre(saveInstanceState: Bundle?) {}

    override fun onDestory() {}
    fun UserSuccess(data: MutableList<DataItem>?) {
        srv_person.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        if(data!!.size == 0){
            ll_person_content.visibility = View.GONE
            civ_no_content.visibility = View.VISIBLE
//            DataItem(0,0,0,"",0,Users(0,0,"null",""))
            var arrayList = ArrayList<DataItem>()
            srv_person.adapter = GroupUserAdapter(this,R.layout.item_group_user,arrayList)
        }else{
            civ_no_content.visibility = View.GONE
            ll_person_content.visibility = View.VISIBLE
        }
        srv_person.adapter = GroupUserAdapter(this,R.layout.item_group_user,data!!)

    }


    private var premsioons = ""

    fun checkPremisson(administor: String) {
        AlertUtils.dismissProgress()
        //holder：群主，visitor：游客，member：普通成员，administor：管理员
        premsioons = administor
            when(premsioons){
                "holder"->{
                    tv_enter.text = "解散社团"
                    tv_group_edit.click {
                        var bundle = Bundle()
                        bundle.putInt("id",groupId)
                        bundle.putString("startType","edit")
                        paramerAction(CreateGroupActivity::class.java,bundle)
                    }
                    ll_request.click {
                        var bundle = Bundle()
                        bundle.putString("startType","group")
                        bundle.putInt("id",groupId)
                        paramerAction(MessageInfoActivity::class.java,bundle)
                    }
                    tv_group_announcement.click {
                        dialog =  ConfirmAnnoumeDialog(this).setConfirmOnClickListener(this)
                        dialog!!.show()
                    }
                    tv_enter.click {
                        ConfirmTextDialog(this,"您确认解散该社团吗？").setConfirExitmOnClickListener(this).show()
                    }
                }
                "administor"->{
                    tv_enter.text = "退出社团"
                    tv_group_edit.click {
                        var bundle = Bundle()
                        bundle.putInt("id",groupId)
                        bundle.putString("startType","edit")
                        paramerAction(CreateGroupActivity::class.java,bundle)
                    }
                    ll_request.click {

                    }
                    tv_group_announcement.click {
                        dialog =  ConfirmAnnoumeDialog(this).setConfirmOnClickListener(this)
                        dialog!!.show()
                    }
                    tv_enter.click {
                        ConfirmTextDialog(this,"您确认退出该社团吗？").setConfirExitmOnClickListener(this).show()
                    }
                }
                "visitor"->{
                    tv_enter.text = "加入社团"
                    tv_group_edit.click {
                      ToastUtils.newToastCenter(this,"您尚未拥有该权限！")
                    }
                    tv_group_announcement.click {
                        ToastUtils.newToastCenter(this,"您尚未拥有该权限！")
                    }
                    tv_enter.click {
                        ConfirmTextDialog(this,"您确认加入该社团吗？").setConfirExitmOnClickListener(this).show()
                    }
                }
                "member"->{
                    tv_enter.text = "退出社团"
                    tv_group_announcement.click {
                        ToastUtils.newToastCenter(this,"您尚未拥有该权限！")
                    }
                    tv_group_edit.click {
                        ToastUtils.newToastCenter(this,"您尚未拥有该权限！")
                    }
                    tv_enter.click {
                        ConfirmTextDialog(this,"您确认退出该社团吗？").setConfirExitmOnClickListener(this).show()
                    }
                }


        }

    }
    //加入解散退出的dialog回调
    override fun confirmExit(string: String) {
            when(premsioons){
                "holder"->{
                   presenter.deleteGroup(groupId)
                }
                "administor"->{
                    presenter.deleteGroup(groupId)
                    finish()
                }
                "visitor"->{
                    presenter.onJoinS(groupId,"")
                    finish()
                }
                "member"->{
                    presenter.deleteGroup(groupId)
                }
            }

    }

    fun onSuccessJoin() {
        presenter.isAdmin(groupId)
    }

    fun deleteSuccess() {
        AppManager.appManager!!.finishAllActivity()
        Action(MainActivity::class.java)
    }

    fun onAnnoumceSuccess() {
        ToastUtils.newToastCenter(this,"发布成功")
        dialog!!.dismiss()
    }

}


