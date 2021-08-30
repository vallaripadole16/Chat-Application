package com.example.android.chatapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userList : ArrayList<HomeActivity>
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = ArrayList()
        adapter = UserAdapter(this,userList)
    }
}