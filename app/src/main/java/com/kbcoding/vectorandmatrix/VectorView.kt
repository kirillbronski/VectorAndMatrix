package com.kbcoding.vectorandmatrix

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class VectorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint()

    private val vector1 = PointF()
    private val vector2 = PointF()
    val resultVector = PointF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        vector1.x = width / 2f
        vector1.y = height / 4f

        vector2.x = 50f
        vector2.y = 250f

        resultVector.x = vector1.x + vector2.x
        resultVector.y = vector1.y + vector2.y

        paint.color = Color.RED
        paint.strokeWidth = 10f

        canvas.drawLine(0f, 0f, vector1.x, vector1.y, paint)

        paint.color = Color.BLUE

        canvas.drawLine(0f, 0f, vector2.x, vector2.y, paint)

        paint.color = Color.GREEN

        canvas.drawLine(0f, 0f, resultVector.x, resultVector.y, paint)
    }
}