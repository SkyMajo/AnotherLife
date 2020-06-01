package com.nixo.afterworklife.Utils.RecyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import om.nixo.afterworklife.Utils.RecyclerView.BaseHolder

abstract class BaseAdapter<T>(private val context : Context, private val layoutRes : Int
                              , val mDataList : MutableList<T>) : RecyclerView.Adapter<BaseHolder>() {

    /**
     * 使用lateinit保证初始化
     */
//    private lateinit var mClickListener : onItemClick
//    private lateinit var mLongClickListener : onLongItemClick
//
//    fun setOnItemClickListener(mClickListener : onItemClick){
//        this.mClickListener = mClickListener
//    }
//    fun setOnLongItemClickListener(mLongClickListener : onLongItemClick){
//        this.mLongClickListener = mLongClickListener
//    }

    override fun onBindViewHolder(p0: BaseHolder, p1: Int) {
        initView(p0, p1)
        //设置点击回调
//        if (mClickListener != null) {
//            p0.itemView.setOnClickListener {
//                mClickListener.onItemCallBack(p1)
//            }
//        }
//       if (mLongClickListener != null){
//           p0.itemView.setOnLongClickListener { mLongClickListener.onItemCallBack(p1) }
//       }
    }

    /**
     * 通过layout的id生成ViewHolder对象
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return BaseHolder(LayoutInflater.from(context).inflate(layoutRes, parent, false))
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    /**
     * 用来给具体Adapter实现逻辑的抽象方法
     */
    abstract fun initView(p0: BaseHolder, position: Int)


    /**
     * 添加一项数据
     * item:添加的数据
     * position:默认为最后一项
     */
    fun addData(item: T, position: Int = mDataList.size) {
        mDataList.add(position, item)
        notifyDataSetChanged()
    }

    /**
     * 添加数据
     * listData：添加的数据
     * isDelete：是否删除原来的数据
     */
    fun addDataList(listData: MutableList<T>, isDelete: Boolean) {
        if (isDelete) {
            mDataList.clear()
        }
        mDataList.addAll(listData)
        notifyDataSetChanged()
    }

    /**
     * 删除指定项数据
     * position:从0开始
     */
    fun deletePositionData(position: Int) {
        // 防止position越界
        if (position >= 0 && position < mDataList.size) {
            mDataList.remove(mDataList[position])
            notifyDataSetChanged()
        } else {
            Log.d("taonce", "delete item failed, position error!")
        }
    }

    /**
     * 删除所有数据
     */
    fun deleteAllData(){
        mDataList.removeAll(mDataList)
        notifyDataSetChanged()
    }

    /**
     * item的点击事件
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    /**
     * item的长按事件
     */
    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int): Boolean
    }
}