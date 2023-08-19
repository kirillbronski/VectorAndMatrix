package com.kbcoding.vectorandmatrix

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class MatrixView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val p = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 3f
        flags = Paint.ANTI_ALIAS_FLAG
    }

    private val path = Path()
    private val pathDst = Path()
    private val matrix1 = Matrix()

    private val rectfBounds: RectF = RectF()
    private val rectfDst: RectF = RectF()
    private val matrix: Matrix = Matrix()


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawARGB(80, 102, 204, 255)
        path.reset()

        rectToRectMatrix(canvas)

    }

    private fun rectToRectMatrix(canvas: Canvas) {

        rectfDst.set(500f, 50f, 800f, 150f)

        path.addCircle(200f, 100f, 50f, Path.Direction.CW)
        path.addCircle(200f, 225f, 75f, Path.Direction.CW)
        path.addCircle(200f, 400f, 100f, Path.Direction.CW)

        pathDst.reset()

        // снеговик
        p.color = Color.BLUE
        canvas.drawPath(path, p)

        // граница снеговика
        path.computeBounds(rectfBounds, true)
        p.color = Color.GREEN
        canvas.drawRect(rectfBounds, p)

        // START
        // рамка
        p.color = Color.BLACK
        canvas.drawRect(rectfDst, p)
        // перобразование
        matrix.reset()
        matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.START)
        path.transform(matrix, pathDst)
        // снеговик
        p.color = Color.BLUE
        canvas.drawPath(pathDst, p)

        rectfDst.offset(0f, 150f)

        // CENTER
        // рамка
        p.color = Color.BLACK
        canvas.drawRect(rectfDst, p)
        // перобразование
        matrix.reset()
        matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.CENTER)
        path.transform(matrix, pathDst)
        // снеговик
        p.color = Color.BLUE
        canvas.drawPath(pathDst, p)

        rectfDst.offset(0f, 150f)

        // END
        // рамка
        p.color = Color.BLACK
        canvas.drawRect(rectfDst, p)
        // перобразование
        matrix.reset()
        matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.END)
        path.transform(matrix, pathDst)
        // снеговик
        p.color = Color.BLUE
        canvas.drawPath(pathDst, p)

        rectfDst.offset(0f, 150f)

        // FILL
        // рамка
        p.color = Color.BLACK
        canvas.drawRect(rectfDst, p)
        // преобразование
        matrix.reset()
        matrix.setRectToRect(rectfBounds, rectfDst, Matrix.ScaleToFit.FILL)
        path.transform(matrix, pathDst)
        // снеговик
        p.color = Color.BLUE
        canvas.drawPath(pathDst, p)
    }

    private fun rotateScaleTranslateMatrix(canvas: Canvas) {
        val p1 = PointF(300f, 300f)
        val rectF = RectF(200f, 200f, 400f, 400f)
        val rectFDst = RectF()

        path.addRect(rectF, Path.Direction.CW)
        p.color = Color.BLACK
        canvas.drawPath(path, p)

        matrix1.reset()
        matrix1.setRotate(45f, p1.x, p1.y)
        matrix1.postScale(1.2f, 0.8f, 300f, 300f)
        matrix1.postTranslate(400f, 0f)

        path.transform(matrix1)

        p.color = Color.GREEN
        canvas.drawPath(path, p)

        matrix1.mapRect(rectFDst, rectF)
        p.color = Color.BLUE
        canvas.drawRect(rectFDst, p)
    }

    private fun postSkewMatrix(canvas: Canvas) {
        val p1 = PointF(500f, 200f)
        val p2 = PointF(500f, 0f)

        path.addRect(200f, 200f, 400f, 400f, Path.Direction.CW)
        p.color = Color.BLACK
        canvas.drawPath(path, p)
        canvas.drawLine(300f, 200f, 1000f, 200f, p)
        canvas.drawLine(200f, 200f, 200f, 1000f, p)

        p.color = Color.GREEN

        // перемещение на 300 вправо
        // и наклон по вертикали на 0.5
        // точка наклона - слева
        matrix1.reset()
        matrix1.setTranslate(300f, 0f)
        matrix1.postSkew(0.0f, 0.5f, p1.x, p1.y)
        path.transform(matrix1, pathDst)
        canvas.drawPath(pathDst, p)
        canvas.drawCircle(p1.x, p1.y, 5f, p)

        // перемещение на 600 вправо
        // и наклон по вертикали на 0.5
        // точка наклона - справа
        matrix1.reset();
        matrix1.setTranslate(600f, 0f);
        matrix1.postSkew(0.0f, 0.5f, 1000f, 200f)
        path.transform(matrix1, pathDst);
        canvas.drawPath(pathDst, p);
        canvas.drawCircle(1000f, 200f, 5f, p)

        p.setColor(Color.BLUE);

        // перемещение на 300 вниз
        // и наклон по горизонтали на 0.5
        // точка наклона - сверху
        matrix1.reset();
        matrix1.setTranslate(0f, 300f);
        matrix1.postSkew(0.5f, 0.0f, 200f, 500f)
        path.transform(matrix1, pathDst);
        canvas.drawPath(pathDst, p);
        canvas.drawCircle(200f, 500f, 5f, p)

        // перемещение на 600 вниз
        // и наклон по горизонтали на 0.5
        // точка наклона - снизу
        matrix1.reset();
        matrix1.setTranslate(0f, 600f)
        matrix1.postSkew(0.5f, 0.0f, 200f, 1000f)
        path.transform(matrix1, pathDst);
        canvas.drawPath(pathDst, p);
        canvas.drawCircle(200f, 1000f, 5f, p)

    }

    private fun setMatrix(canvas: Canvas) {
        val p1 = PointF(400f, 200f)
        val p2 = PointF(500f, 0f)

        p.color = Color.BLACK
        canvas.drawCircle(p1.x, p1.y, 10f, p)

        path.addRect(300f, 100f, 500f, 300f, Path.Direction.CW)
        canvas.drawPath(path, p)

        matrix1.reset()
        matrix1.setRotate(45f, p1.x, p1.y)
        matrix1.postTranslate(p2.x, p2.y)

        path.transform(matrix1, pathDst)

        p.color = Color.GREEN
        canvas.drawPath(pathDst, p)

        matrix1.reset()
        matrix1.setRotate(45f, p1.x, p1.y)
        matrix1.preTranslate(p2.x, p2.y)

        path.transform(matrix1, pathDst)

        p.color = Color.RED
        canvas.drawPath(pathDst, p)
    }

    private fun rotateMatrix(canvas: Canvas) {
        val p = PointF(600f, 400f)

        path.addRect(300f, 150f, 450f, 200f, Path.Direction.CW)
        path.addRect(350f, 100f, 400f, 250f, Path.Direction.CW)
        path.addCircle(375f, 125f, 5f, Path.Direction.CW)

        this.p.color = Color.GREEN
        canvas.drawPath(path, this.p)

        matrix1.reset()
        matrix1.setRotate(120f, p.x, p.y)

        path.transform(matrix1)

        this.p.color = Color.BLUE
        canvas.drawPath(path, this.p)

        this.p.color = Color.BLACK
        canvas.drawCircle(p.x, p.y, 5f, this.p)
    }

    private fun scaleMatrix(canvas: Canvas) {
        path.addRect(300f, 150f, 450f, 200f, Path.Direction.CW)
        path.addRect(350f, 100f, 400f, 250f, Path.Direction.CW)

        p.color = Color.GREEN
        canvas.drawPath(path, p)

        matrix1.reset()
        matrix1.setScale(2f, 2.5f, 375f, 100f)

        path.transform(matrix1)

        p.color = Color.BLUE
        canvas.drawPath(path, p)

        p.color = Color.BLACK
        canvas.drawCircle(375f, 100f, 5f, p)
    }

    private fun translateMatrix(canvas: Canvas) {
        path.addRect(300f, 150f, 450f, 200f, Path.Direction.CW)
        path.addRect(350f, 100f, 400f, 250f, Path.Direction.CW)

        p.color = Color.GREEN
        canvas.drawPath(path, p)

        matrix1.reset()
        matrix1.setTranslate(300f, 200f)

        path.transform(matrix1)
        p.color = Color.BLUE

        canvas.drawPath(path, p)
    }

    private fun affineTransformation(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = 100f

        canvas.drawCircle(centerX, centerY, radius, p)

        matrix1.reset()

        // Пример масштабирования по обеим осям в 1.5 раза
        matrix1.setScale(1.5f, 1.5f)

        // Пример перемещения на 50 пикселей вправо и 100 пикселей вниз
        matrix1.postTranslate(50f, 100f)

        p.color = Color.RED
        // Применяем преобразование к центру круга
        val point = floatArrayOf(centerX, centerY)
        matrix1.mapPoints(point)

        // Рисуем преобразованный круг
        canvas.drawCircle(point[0], point[1], radius, p)
    }
}