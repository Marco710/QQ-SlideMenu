package com.example.qq_slidemenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

public class SlidingMenu extends HorizontalScrollView {

	private LinearLayout mWapper;
	private ViewGroup mMenu;// 菜单区域
	private ViewGroup mContent;// 内容区域
	private int mScreenWidth;// 屏幕宽度
	private int mMenuRightPadding = 50;// 右侧边距 50dp

	private int mMenuWidth;// 菜单区域宽度

	private boolean once = false;
	private boolean isOpen = false;

	/*
	 * 未使用自定义属性时调用
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		// 获取手机屏幕宽度
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;

		// 把dp转化为px
		mMenuRightPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources()
						.getDisplayMetrics());
	}

	/*
	 * 设置子view的宽和高 设置自己的宽和高
	 */

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		if (!once) {
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth
					- mMenuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;

			once = true;

		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/*
	 * 决定子View放置的位置 通过设置偏移量，将menu隐藏
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

		super.onLayout(changed, l, t, r, b);

		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub

		int action = ev.getAction();
		switch (action) {

		case MotionEvent.ACTION_UP:// 用户手指抬起时

			int scrollx = getScrollX();// getScrollX()
										// 就是当前view的左上角相对于母视图的左上角的X轴偏移量，说白了就是手机屏幕左侧菜单隐藏区域的宽度
			// 当前菜单显示宽度是否大于1/2，大于则完全显示，小于则完全隐藏
			if (scrollx >= mMenuWidth / 2) {

				// 当前视图内容偏移至(x,y)处
				this.smoothScrollTo(mMenuWidth, 0);// 隐藏菜单
				isOpen = false;
			} else {
				this.smoothScrollTo(0, 0);// 显示菜单
				isOpen = true;
			}
			return true;

		}
		return super.onTouchEvent(ev);

	}

	/*
	 * 打开菜单
	 */
	public void openMenu() {
		// TODO Auto-generated method stub

		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = true;

	}

	/*
	 * 关闭菜单
	 */
	public void closeMenu() {
		// TODO Auto-generated method stub

		if (!isOpen)
			return;
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen = false;
	}

	/*
	 * 切换菜单
	 */

	public void Toggle() {
		if (isOpen) {
			closeMenu();
		} else {
			openMenu();
		}
	}

	/*
	 * 滚动发生时
	 */

	/**
	 * 区别1：内容区域1.0~0.7 缩放的效果 scale : 1.0~0.0 0.7 + 0.3 * scale
	 * 
	 * 区别2：菜单的偏移量需要修改
	 * 
	 * 区别3：菜单的显示时有缩放以及透明度变化 缩放：0.7 ~1.0 1.0 - scale * 0.3 透明度 0.6 ~ 1.0 0.6+ 0.4
	 * * (1- scale) ;
	 * 
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);

		float scale = l * 1.0f / mMenuWidth; // 1 ~ 0
		float rightScale = 0.7f + 0.3f * scale;
		float leftScale = 1.0f - scale * 0.3f;
		float leftAlpha = 0.6f + 0.4f * (1 - scale);
		// 调用属性动画，设置TranslationX
		ViewHelper.setTranslationX(mMenu, l);

		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);

		// 设置content的缩放的中心点
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);

	}
}
