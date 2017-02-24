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
	 * String[] from = { "name", "id" }; // 这里是ListView显示内容每一列的列名 int[] to = {
	 * R.id.user_name, R.id.user_id }; // 这里是ListView显示每一列对应的list_item中控件的id
	 * 
	 * String[] userName = { "张三", "李四", "王五", "赵六" }; // 这里第一列所要显示的人名 String[]
	 * userId = { "1001", "1002", "1003", "1004" }; // 这里是人名对应的ID
	 * 
	 * ArrayList<HashMap<String, String>> list = null; HashMap<String, String>
	 * map = null;
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) {
	 * 
	 * View view = inflater.inflate(R.layout.tab_message, container, false);
	 * messagelistview = (ListView) view.findViewById(R.id.m_listView); //
	 * 创建ArrayList对象； list = new ArrayList<HashMap<String, String>>(); //
	 * 将数据存放进ArrayList对象中，数据安排的结构是，ListView的一行数据对应一个HashMap对象， //
	 * HashMap对象，以列名作为键，以该列的值作为Value，将各列信息添加进map中，然后再把每一列对应 //
	 * 的map对象添加到ArrayList中
	 * 
	 * for (int i = 0; i < 4; i++) { map = new HashMap<String, String>(); //
	 * 为避免产生空指针异常，有几列就创建几个map对象 map.put("id", userId[i]); map.put("name",
	 * userName[i]); list.add(map); }
	 * 
	 * // 创建一个SimpleAdapter对象 SimpleAdapter adapter = new
	 * SimpleAdapter(getActivity(), list, R.layout.message_listview, from, to);
	 * // 调用ListActivity的setListAdapter方法，为ListView设置适配器
	 * messagelistview.setAdapter(adapter); return view;
	 * 
	 * }
	 */

}
