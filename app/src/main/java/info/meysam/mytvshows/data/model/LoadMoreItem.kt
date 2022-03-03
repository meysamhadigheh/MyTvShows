package info.meysam.mytvshows.data.model

import info.meysam.hivaadapter.ItemBinder
import info.meysam.hivaadapter.ItemHolder
import info.meysam.mytvshows.R

class LoadMoreItem : ItemBinder {


    override fun getResourceId(): Int {

        return R.layout.item_loading
    }

    override fun bindToHolder(p0: ItemHolder?, p1: Any?) {
    }


}