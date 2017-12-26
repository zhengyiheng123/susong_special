package com.xyd.susong.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * 圆形图片
 * 
 * @author zxl
 *
 */

public class CircleImageView extends ImageView {
	/**
	 * 绘图的Paint
	 */
	private Paint mBitmapPaint;
	/**
	 * 圆角的半径
	 */
	private int mRadius;
	/**
	 * 用于缩小放大
	 */
	private Matrix mMatrix;
	/**
	 * 渲染图像，使用图像为绘制图形着色
	 */
	private BitmapShader mBitmapShader;
	/**
	 * view的宽度
	 */
	private int mWidth;

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mMatrix = new Matrix();
		mBitmapPaint = new Paint();
		mBitmapPaint.setAntiAlias(true);
	}

	public CircleImageView(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
		mRadius = mWidth / 2;
		setMeasuredDimension(mWidth, mWidth);
	}

	/**
	 * 初始化BitmapShader
	 */
	private void initShader() {
		Drawable drawable = getDrawable();
		if (drawable == null) {
			return;
		}

		Bitmap bmp = BitmapUtil.drawableToBitamp(drawable);
		// 将bmp作为着色器，就是在指定区域内绘制bmp
		mBitmapShader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
		float scale = 1.0f;
		// 拿到bitmap宽或高的小值
		int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
		// 获得尺寸
		scale = mWidth * 1.0f / bSize;
		// shader的变换矩阵，我们这里主要用于放大或者缩小
		mMatrix.setScale(scale, scale);
		// 设置变换矩阵
		mBitmapShader.setLocalMatrix(mMatrix);
		// 设置shader
		mBitmapPaint.setShader(mBitmapShader);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (getDrawable() == null) {
			return;
		}
		initShader();
		// 画圆
		canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);

	}

}
