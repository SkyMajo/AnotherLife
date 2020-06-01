package  com.nixo.afterworklife.Utils.Data

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.nixo.afterworklife.Common.APP


@SuppressLint("StaticFieldLeak")
object SharedUtils  {
    var spName = "locationName"
    private val KEY_USER_ACCOUNT = "account"
    private val KEY_USER_TOKEN = "token"
    private val KEY_USER_ID = "userId"
    private var context :Context? = null

    val instance: SharedPreferences
        get() = getSharedPreferences(spName)

    fun setContext(context: Context){
        this.context = context

    }


    fun getInstance(spName: String): SharedPreferences {
        return getSharedPreferences(spName)
    }

    private fun getSharedPreferences(spName: String): SharedPreferences {
        return context!!.getSharedPreferences(spName, 0)
    }

    fun getString(key: String): String {
        return instance.getString(key,"")!!
    }

    fun getSpNameString(spName: String, key: String): String? {
        return getInstance(spName).getString(key, null as String?)
    }

    fun getInt(key: String): Int {
        return instance.getInt(key, -1)
    }

    fun getSpNameInt(spName: String, key: String): Int {
        return getInstance(spName).getInt(key, -1)
    }

    fun getFloat(key: String): Float {
        return instance.getFloat(key, -1.0f)
    }

    fun getSpNameFloat(spName: String, key: String): Float {
        return getInstance(spName).getFloat(key, -1.0f)
    }

    fun getBoolean(key: String): Boolean {
        return instance.getBoolean(key, false)
    }

    fun getSpNameBoolean(spName: String, key: String): Boolean {
        return getInstance(spName).getBoolean(key, false)
    }

    fun getLong(key: String): Long {
        return instance.getLong(key, -1L)
    }

    fun getSpNameLong(spName: String, key: String): Long {
        return getInstance(spName).getLong(key, -1L)
    }

    fun putToken(token: String) {
        putString("token", token)
    }

    fun putUserId(userId: String) {
        putString("userId", userId)
    }

    fun putString(key: String, value: String) {
        val editor = instance.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun putSpNameString(spName: String, key: String, value: String) {
        val editor = getInstance(spName).edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun putInt(key: String, value: Int) {
        val editor = instance.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun putSpNameInt(spName: String, key: String, value: Int) {
        val editor = getInstance(spName).edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun putLong(key: String, value: Long) {
        val editor = instance.edit()
        editor.putLong(key, value)
        editor.commit()
    }

    fun putSpNameLong(spName: String, key: String, value: Long) {
        val editor = getInstance(spName).edit()
        editor.putLong(key, value)
        editor.commit()
    }

    fun putFloat(key: String, value: Float) {
        val editor = instance.edit()
        editor.putFloat(key, value)
        editor.commit()
    }

    fun putSpNameFloat(spName: String, key: String, value: Float) {
        val editor = getInstance(spName).edit()
        editor.putFloat(key, value)
        editor.commit()
    }

    fun putBoolean(key: String, value: Boolean) {
        val editor = instance.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun putSpNameBoolean(spName: String, key: String, value: Boolean) {
        val editor = getInstance(spName).edit()
        editor.putBoolean(key, value)
        editor.commit()
    }
    fun delete(){
        val editor = getInstance(spName).edit()
        editor.clear()
        editor.commit()
    }
}

