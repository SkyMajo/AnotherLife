package com.nixo.afterworklife.Common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.Stack

class AppManager private constructor() {

    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }

        activityStack!!.add(activity)
    }

    fun currentActivity(): Activity? {
        try {
            return activityStack!!.lastElement() as Activity
        } catch (var2: Exception) {
            return null
        }

    }

    fun preActivity(): Activity? {
        val index = activityStack!!.size - 2
        return if (index < 0) {
            null
        } else {
            activityStack!![index] as Activity
        }
    }

    fun finishActivity() {
        val activity = activityStack!!.lastElement() as Activity
        this.finishActivity(activity)
    }

    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack!!.remove(activity)
            activity.finish()
            activity = null
        }

    }

    fun removeActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack!!.remove(activity)
            activity = null
        }

    }

    fun finishActivity(cls: Class<*>) {
        try {
            val var2 = activityStack!!.iterator()

            while (var2.hasNext()) {
                val activity = var2.next() as Activity
                if (activity.javaClass == cls) {
                    this.finishActivity(activity)
                }
            }
        } catch (var4: Exception) {
        }

    }

    fun isCheckActivity(cls: Class<*>): Boolean {
        val var2 = activityStack!!.iterator()

        var activity: Activity
        do {
            if (!var2.hasNext()) {
                return false
            }

            activity = var2.next() as Activity
        } while (activity.javaClass != cls)

        return true
    }

    fun finishAllActivity() {
        var i = 0

        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                (activityStack!![i] as Activity).finish()
            }
            ++i
        }

        activityStack!!.clear()
    }

    fun returnToActivity(cls: Class<*>) {
        while (activityStack!!.size != 0 && (activityStack!!.peek() as Activity).javaClass != cls) {
            this.finishActivity(activityStack!!.peek() as Activity)
        }

    }

    fun isOpenActivity(cls: Class<*>): Boolean {
        if (activityStack != null) {
            var i = 0

            val size = activityStack!!.size
            while (i < size) {
                if (cls == (activityStack!!.peek() as Activity).javaClass) {
                    return true
                }
                ++i
            }
        }

        return false
    }

    fun AppExit(context: Context, isBackground: Boolean?) = try {
        this.finishAllActivity()
        val activityMgr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityMgr.restartPackage(context.packageName)
    } catch (var7: Exception) {
    } finally {
        if (!(isBackground)!!) {
            System.exit(0)
        }

    }

    companion object {


        private var activityStack: Stack<Activity>? = null
        @Volatile
        private var instance: AppManager? = null

        val appManager: AppManager?
            get() {
                if (instance == null) {
                    AppManager::class.java
                    synchronized(AppManager::class.java) {
                        if (instance == null) {
                            instance = AppManager()
                            activityStack = Stack()
                        }
                    }
                }

                return instance
            }
    }
}

