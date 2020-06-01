package om.nixo.afterworklife.Utils.RecyclerView

import android.view.View
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView


/**
 * @author Nixo
 */
class BaseHolder(item : View) : RecyclerView.ViewHolder(item){

    //复用View,省去getView.findViewById
    private val mViews = SparseArrayCompat<View>()


    /**
     * 通过id绑定View控件
     * 通过as可以获取到泛型具体类型不需要再强转
     */
    fun<V:View> getView(id :Int):V{
        var view = mViews[id]
        if (view == null) {
            view = itemView.findViewById(id)
            mViews.put(id,view)
        }
        return view as V
    }
}