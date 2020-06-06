package com.calculator.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_todo.*

class ToDoActivity : AppCompatActivity(), View.OnClickListener {

    private var sp: SharedPrefs? = null
    var itemlist = ArrayList<String>()
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        sp = SharedPrefs(this)
        itemlist = sp!!.getList()
        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_multiple_choice
            , itemlist
        )
        listView.adapter = adapter
        add.setOnClickListener(this)
        clear.setOnClickListener(this)
        delete.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        Log.d("kjasdhflajs", v.toString())
        when (v!!.id) {
            add.id -> {
                itemlist.add(editText.text.toString())
                sp!!.saveList(itemlist)
                listView.adapter = adapter
                adapter!!.notifyDataSetChanged()
                editText.text.clear()
            }
            clear.id -> {
                sp!!.clearPreferences()
                itemlist.clear()
                adapter!!.notifyDataSetChanged()
            }
            delete.id -> {
                val position: SparseBooleanArray = listView.checkedItemPositions
                val count = listView.count
                var item = count - 1
                while (item >= 0) {
                    if (position.get(item)) {
                        adapter!!.remove(itemlist.get(item))
                    }
                    item--
                }
                sp!!.saveList(itemlist)
                position.clear()
                adapter!!.notifyDataSetChanged()
            }
        }
    }
}
