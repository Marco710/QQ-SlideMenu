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
	private ViewGroup mMenu;// �˵�����
	private ViewGroup mContent;// ��������
	private int mScreenWidth;// ��Ļ���
	private int mMenuRightPadding = 50;// �Ҳ�߾� 50dp

	private int mMenuWidth;// �˵�������

	private boolean once = false;
	private boolean isOpen = false;

	/*
	 * δʹ���Զ�������ʱ����
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		// ��ȡ�ֻ���Ļ���
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;

		// ��dpת��Ϊpx
		mMenuRightPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources()
						.getDisplayMetrics());
	}

	/*
	 * ������view�Ŀ�͸� �����Լ��Ŀ�͸�
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
	 * ������View���õ�λ�� ͨ������ƫ��������menu����
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

		case MotionEvent.ACTION_UP:// �û���ָ̧��ʱ

			int scrollx = getScrollX();// getScrollX()
										// ���ǵ�ǰview�����Ͻ������ĸ��ͼ�����Ͻǵ�X��ƫ������˵���˾����ֻ���Ļ���˵���������Ŀ��
			// ��ǰ�˵���ʾ����Ƿ����1/2����������ȫ��ʾ��С������ȫ����
			if (scrollx >= mMenuWidth / 2) {

				// ��ǰ��ͼ����ƫ����(x,y)��
				this.smoothScrollTo(mMenuWidth, 0);// ���ز˵�
				isOpen = false;
			} else {
				this.smoothScrollTo(0, 0);// ��ʾ�˵�
				isOpen = true;
			}
			return true;

		}
		return super.onTouchEvent(ev);

	}

	/*
	 * �򿪲˵�
	 */
	public void openMenu() {
		// TODO Auto-generated method stub

		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = true;

	}

	/*
	 * �رղ˵�
	 */
	public void closeMenu() {
		// TODO Auto-generated method stub

		if (!isOpen)
			return;
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen = false;
	}

	/*
	 * �л��˵�
	 */

	public void Toggle() {
		if (isOpen) {
			closeMenu();
		} else {
			openMenu();
		}
	}

	/*
	 * ��������ʱ
	 */

	/**
	 * ����1����������1.0~0.7 ���ŵ�Ч�� scale : 1.0~0.0 0.7 + 0.3 * scale
	 * 
	 * ����2���˵���ƫ������Ҫ�޸�
	 * 
	 * ����3���˵�����ʾʱ�������Լ�͸���ȱ仯 ���ţ�0.7 ~1.0 1.0 - scale * 0.3 ͸���� 0.6 ~ 1.0 0.6+ 0.4
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
		// �������Զ���������TranslationX
		ViewHelper.setTranslationX(mMenu, l);

		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);

		// ����content�����ŵ����ĵ�
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);

	}
}
