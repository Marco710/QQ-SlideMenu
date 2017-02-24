package com.example.qq_maininterface;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qq_slidemenu.R;
import com.example.qq_slidemenu.view.SlidingMenu;

public class MainActivity extends FragmentActivity implements
		View.OnClickListener {

	private TextView textView, textView2, textView3, title;
	private ImageView imageView;
	private int currentIndex = 0;
	private ArrayList<Fragment> fragmentArrayList;
	private Fragment mCurrentFrgment;
	private SlidingMenu menu;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		menu = (SlidingMenu) findViewById(R.id.id_menu);
		title = (TextView) findViewById(R.id.title);
		
		initView();
		initFragment();

	}

	private void initFragment() {
		// TODO Auto-generated method stub
		fragmentArrayList = new ArrayList<Fragment>(3);
		fragmentArrayList.add(new MessageTabFragment());
		fragmentArrayList.add(new ContactsTabFragment());
		fragmentArrayList.add(new InterractionTabFragment());

		//textView.setSelected(true);
		changeTab(0);
	}

	private void initView() {

		textView = (TextView) findViewById(R.id.id_tv_chat);
		textView.setOnClickListener(this);
		textView.setTag(0);

		textView2 = (TextView) findViewById(R.id.id_tv_friend);
		textView2.setOnClickListener(this);
		textView2.setTag(1);

		textView3 = (TextView) findViewById(R.id.id_tv_contact);
		textView3.setOnClickListener(this);
		textView3.setTag(2);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		changeTab((Integer) v.getTag());
		resetTextView();
		switch ((Integer) v.getTag()) {
		case 0:
			textView.setTextColor(Color.parseColor("#008000"));
			title.setText("��Ϣ");
			break;

		case 1:

			textView2.setTextColor(Color.parseColor("#008000"));
			title.setText("��ϵ��");
			break;
		case 2:

			textView3.setTextColor(Color.parseColor("#008000"));
			title.setText("��̬");
			break;
		}

	}

	private void changeTab(int index) {
		currentIndex = index;
		//textView.setSelected(index == 0);
		//textView2.setSelected(index == 1);
		//textView3.setSelected(index == 2);

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// �жϵ�ǰ��Fragment�Ƿ�Ϊ�գ���Ϊ��������
		if (null != mCurrentFrgment) {
			ft.hide(mCurrentFrgment);
		}
		// �ȸ���Tag��FragmentTransaction�����ȡ֮ǰ��ӵ�Fragment
		Fragment fragment = getSupportFragmentManager().findFragmentByTag(
				fragmentArrayList.get(currentIndex).getClass().getName());

		if (null == fragment) {
			// ��fragmentΪ�գ���֮ǰδ��Ӵ�Fragment����Ӽ�����ȡ��
			fragment = fragmentArrayList.get(index);
		}
		mCurrentFrgment = fragment;

		// �жϴ�Fragment�Ƿ��Ѿ���ӵ�FragmentTransaction������
		if (!fragment.isAdded()) {
			ft.add(R.id.fragment, fragment, fragment.getClass().getName());
		} else {
			ft.show(fragment);
		}
		ft.commit();
	}

	protected void resetTextView() {
		// TODO Auto-generated method stub
		textView.setTextColor(Color.BLACK);
		textView2.setTextColor(Color.BLACK);
		textView3.setTextColor(Color.BLACK);
	}

	public void ToggleMenu(View view) {
		menu.Toggle();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
