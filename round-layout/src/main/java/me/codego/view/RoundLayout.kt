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
class RoundLayout(context: Context, attributeSet: AttributeSet? = null) : ConstraintLayout(context, attributeSet) {

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

    override fun draw(canvas: Canvas) {
        canvas.apply {
            save()
            clipPath(mClipPath)
            super.draw(this)
            if (shouldDrawStroke()) {
                drawPath(mClipPath, mPathPaint)
            }
            restore()
        }
    }

    private fun shouldDrawStroke() = mStrokeWidth > 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        resetClipPath()
    }

    /**
     * 修改圆角大小
     */
    fun setRadius(topLeftRadius: Float, topRightRadius: Float, bottomLeftRadius: Float, bottomRightRadius: Float) {
        mRadii[0] = topLeftRadius
        mRadii[1] = topLeftRadius
        mRadii[2] = topRightRadius
        mRadii[3] = topRightRadius
        mRadii[4] = bottomLeftRadius
        mRadii[5] = bottomLeftRadius
        mRadii[6] = bottomRightRadius
        mRadii[7] = bottomRightRadius

        resetClipPath()

        postInvalidate()
    }

    private fun resetClipPath() {
        mClipPath.apply {
            reset()
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                addRoundRect(0f, 0f, width.toFloat(), height.toFloat(), mRadii, Path.Direction.CW)
            } else {
                addRoundRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), mRadii, Path.Direction.CW)
            }
        }
    }

    /**
     * 修改描边宽度
     */
    fun setStrokeWidth(width: Float) {
        mStrokeWidth = width

        postInvalidate()
    }

    /**
     * 修改描边颜色
     */
    fun setStrokeColor(color: Int) {
        mStrokeColor = color

        postInvalidate()
    }

    /**
     * 修改描边
     */
    fun setStroke(color: Int, width: Float) {
        mStrokeWidth = width
        mStrokeColor = color

        postInvalidate()
    }

}