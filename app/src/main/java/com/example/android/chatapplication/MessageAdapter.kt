package com.example.android.chatapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

class MessageAdapter(val context : Context, val messageList:ArrayList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVED = 1
    val ITEM_SENT = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1){
            //inflate received
            val view : View = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceivedViewHolder(view)
        }
        else{
            //inflate sent
            val view : View = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SentViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currMessage = messageList[position]
        if(holder.javaClass == SentViewHolder::class.java) {
            // do stuff for sent view holder
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currMessage.message
        }else{
            //do stuff for received view holder
            val viewHolder = holder as ReceivedViewHolder
            holder.receivedMessage.text = currMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        var currMessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currMessage.senderId)){
            return ITEM_SENT
        }
        else{
            return ITEM_RECEIVED
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }


    class SentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }
    class ReceivedViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val receivedMessage = itemView.findViewById<TextView>(R.id.txt_received_message)
    }


}