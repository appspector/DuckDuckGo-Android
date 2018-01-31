/*
 * Copyright (c) 2018 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.app.tabs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.duckduckgo.app.browser.R
import kotlinx.android.synthetic.main.item_tab.view.*

class TabsAdapter(private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TabsAdapter.TabViewHolder>() {

    private var tabs: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TabViewHolder {
        var inflator = LayoutInflater.from(parent!!.context)
        val root = inflator.inflate(R.layout.item_tab, parent, false)
        return TabViewHolder(root, root.favicon, root.title, root.url)
    }

    override fun getItemCount(): Int {
        return tabs.size
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        val tab = tabs[position]
        holder.title.text = tab
        holder.root.setOnClickListener {
            itemClickListener.onClicked(position)
        }

    }

    fun updateData(data: List<String>) {
        tabs = data
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClicked(position: Int)
    }

    data class TabViewHolder(
        val root: View,
        val favicon: ImageView,
        val title: TextView,
        val url: TextView
    ) : RecyclerView.ViewHolder(root)
}