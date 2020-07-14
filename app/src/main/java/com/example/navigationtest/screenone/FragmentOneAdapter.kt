package com.example.navigationtest.screenone

import com.example.navigationtest.DisposableHolder
import com.example.navigationtest.DisposableHolderDelegate
import com.example.navigationtest.R
import com.example.navigationtest.clicks
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.my_item_layout.view.*

class FragmentOneAdapter : GroupAdapter<GroupieViewHolder>() {
    private val onClickSubject: PublishSubject<Unit> = PublishSubject.create()
    val onClick: Observable<Unit> get() = onClickSubject

    fun setData(numberList: List<Int>) {
        numberList.forEach {
            add(MyItem(index = it))
        }
    }

    private inner class MyItem(private val index: Int) : Item<GroupieViewHolder>(), DisposableHolder by DisposableHolderDelegate() {
        override fun getLayout(): Int = R.layout.my_item_layout

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {
            with(viewHolder.itemView) {
                numListLayout.clicks().doOnNext {
                    onClickSubject.onNext(Unit)
                }.subscribe().addTo(disposables)

                itemNumber.text = index.toString()
            }
        }

        override fun unbind(viewHolder: GroupieViewHolder) {
            super.unbind(viewHolder)
            disposeAll()
        }
    }
}

