package info.meysam.mytvshows.utilModule.general

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding


// view visibility extensions

fun View.visible() {

    visibility = View.VISIBLE
}


fun View.gone() {

    visibility = View.GONE
}

fun View.invisible() {

    visibility = View.INVISIBLE
}

val visible = View.VISIBLE

// margin extension
fun View.margin(left: Float? = null, top: Float? = null, right: Float? = null, bottom: Float? = null) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

