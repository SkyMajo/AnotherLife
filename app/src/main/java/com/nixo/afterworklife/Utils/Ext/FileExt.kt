package com.nixo.afterworklife.Utils.Ext

import android.util.Log
import java.io.File


private const val TAG = "FileExt"


fun File.ensureDir():Boolean{
    try{
        // 如果这个路径不是文件夹，我们就去看看是不是File
        if(isDirectory){
            //如果是File文件，删掉
            if(isFile) {
                delete()
            }
        }else{
            //然后我们重新创建文件夹
            return mkdir()
        }
    } catch (e:Exception){
        Log.w(TAG , e.message)
    }
    //如果都不是 返回false
    return false
}