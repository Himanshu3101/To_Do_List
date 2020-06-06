package com.calculator.app

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPrefs(val context: Context) {

    private val sharedPrefFile = "ToDoShrdPref"

    fun saveList(todoList: ArrayList<String>) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("todoList", Gson().toJson(todoList))
        editor.apply()
        editor.commit()
    }

    fun getList(): ArrayList<String> {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val todoList = sharedPreferences.getString("todoList", "")
        var itemlist = arrayListOf<String>()
        if (todoList != null) {
            if (!todoList.isEmpty()) {
                val groupListType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
                itemlist = Gson().fromJson(todoList, groupListType)
            }
        }
        return itemlist
    }

    fun clearPreferences() {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}