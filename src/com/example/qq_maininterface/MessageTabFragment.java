package com.example.qq_maininterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qq_slidemenu.R;

public class MessageTabFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.tab_message, container, false);
	}
	/*
	 * private ListView messagelistview;
	 * 
	 * String[] from = { "name", "id" }; // ������ListView��ʾ����ÿһ�е����� int[] to = {
	 * R.id.user_name, R.id.user_id }; // ������ListView��ʾÿһ�ж�Ӧ��list_item�пؼ���id
	 * 
	 * String[] userName = { "����", "����", "����", "����" }; // �����һ����Ҫ��ʾ������ String[]
	 * userId = { "1001", "1002", "1003", "1004" }; // ������������Ӧ��ID
	 * 
	 * ArrayList<HashMap<String, String>> list = null; HashMap<String, String>
	 * map = null;
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) {
	 * 
	 * View view = inflater.inflate(R.layout.tab_message, container, false);
	 * messagelistview = (ListView) view.findViewById(R.id.m_listView); //
	 * ����ArrayList���� list = new ArrayList<HashMap<String, String>>(); //
	 * �����ݴ�Ž�ArrayList�����У����ݰ��ŵĽṹ�ǣ�ListView��һ�����ݶ�Ӧһ��HashMap���� //
	 * HashMap������������Ϊ�����Ը��е�ֵ��ΪValue����������Ϣ��ӽ�map�У�Ȼ���ٰ�ÿһ�ж�Ӧ //
	 * ��map������ӵ�ArrayList��
	 * 
	 * for (int i = 0; i < 4; i++) { map = new HashMap<String, String>(); //
	 * Ϊ���������ָ���쳣���м��оʹ�������map���� map.put("id", userId[i]); map.put("name",
	 * userName[i]); list.add(map); }
	 * 
	 * // ����һ��SimpleAdapter���� SimpleAdapter adapter = new
	 * SimpleAdapter(getActivity(), list, R.layout.message_listview, from, to);
	 * // ����ListActivity��setListAdapter������ΪListView����������
	 * messagelistview.setAdapter(adapter); return view;
	 * 
	 * }
	 */

}
