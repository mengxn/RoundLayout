package me.codego.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import me.codego.roundlayout.R

/**
 * 圆角背景
 * @author mengxn
 * @date 2020/6/10
 */
open class RoundLayout(context: Context, attributeSet: AttributeSet? = null) : ConstraintLayout(context, attributeSet) {

    private val mRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
    private var mClipPath: Path = Path()

    private var mStrokeWidth = 0f
    private var mStrokeColor = 0
    private val mPathPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = mStrokeWidth * 2
            color = mStrokeColor
        }
    }

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RoundLayout)
        for (i in 0 until typedArray.indexCount) {
            when (val attr = typedArray.getIndex(i)) {
                R.styleable.RoundLayout_round_radius -> {
                    val radius = typedArray.getDimensionPixelSize(attr, 0).toFloat()
                    for (index in mRadii.indices) {
                        mRadii[index] = radius
                    }
                }
                R.styleable.RoundLayout_round_topLeftRadius -> {
                    val radius = typedArray.getDimensionPixelSize(attr, 0).toFloat()
                    mRadii[0] = radius
                    mRadii[1] = radius
                }
                R.styleable.RoundLayout_round_topRightRadius -> {
                    val radius = typedArray.getDimensionPixelSize(attr, 0).toFloat()
                    mRadii[2] = radius
                    mRadii[3] = radius
                }
                R.styleable.RoundLayout_round_bottomRightRadius -> {
                    val radius = typedArray.getDimensionPixelSize(attr, 0).toFloat()
                    mRadii[4] = radius
                    mRadii[5] = radius
                }
                R.styleable.RoundLayout_round_bottomLeftRadius -> {
                    val radius = typedArray.getDimensionPixelSize(attr, 0).toFloat()
                    mRadii[6] = radius
                    mRadii[7] = radius
                }
                R.styleable.RoundLayout_round_stroke_width -> {
                    mStrokeWidth = typedArray.getDimensionPixelSize(attr, 0).toFloat()
                }
                R.styleable.RoundLayout_round_stroke_color -> {
                    mStrokeColor = typedArray.getColor(attr, Color.WHITE)
                }
                else -> {
                }
            }

        }
        typedArray.recycle()
        if (shouldDrawStroke()) {
            setWillNotDraw(false)
        }
    }

    override fun draw(canvas: Canvas?) {
        canvas?.apply {
            save()
            clipPath(mClipPath)
            super.draw(this)
            if (shouldDrawStroke()) {
                drawPath(mClipPath, mPathPaint)
            }
            restore()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mClipPath.reset()
        mClipPath.addRoundRect(RectF(0f, 0f, w.toFloat(), h.toFloat()), mRadii, Path.Direction.CW)
    }

    private fun shouldDrawStroke() = mStrokeWidth > 0

}