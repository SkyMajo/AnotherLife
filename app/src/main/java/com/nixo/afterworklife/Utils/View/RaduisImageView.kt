package com.nixo.afterworklife.Utils.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import android.view.View

import androidx.appcompat.widget.AppCompatImageView

internal class RaduisImageView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : AppCompatImageView(context, attrs, defStyleAttr) {
    var width: Float = 0.toFloat()
    var height: Float = 0.toFloat()

    constructor(context: Context) : this(context, null) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init(context, attrs)
    }

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        width = getWidth().toFloat()
        height = getHeight().toFloat()
    }

    override fun onDraw(canvas: Canvas) {
//        if (width >= 20 && height > 20) {
            val path = Path()
            //四个圆角
            path.moveTo(20f, 0f)
            path.lineTo(width - 20, 0f)

            path.quadTo(width, 0f, width, 20f)
            path.lineTo(width, height - 20)

            path.quadTo(width, height, width - 20, height)
            path.lineTo(20f, height)

            path.quadTo(0f, height, 0f, height - 20)
            path.lineTo(0f, 20f)

            path.quadTo(0f, 0f, 20f, 0f)

            canvas.clipPath(path)
//        }
        super.onDraw(canvas)
    }

}
