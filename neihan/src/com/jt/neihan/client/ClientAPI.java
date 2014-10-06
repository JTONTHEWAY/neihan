package com.jt.neihan.client;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jt.test.TestActivity;

/**
 * 所有和服务器接口对接的方法全部在这个类里面
 * 
 * @author Administrator
 * 
 */
public class ClientAPI {

	public ClientAPI() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取内涵段子的列表
	 * 
	 * @param queue
	 *            Volley 请求的队列
	 * @param categoryType
	 *            要获取的参数类型
	 * @param itemCount
	 *            服务器一次传回来的条目数
	 * @param responseListener
	 *            用于获取段子列表的回调接口，任何使用getlist方法时，此参数用于更新段子列表
	 * @param minTime
	 *            用于分页加载数据，或者是下拉刷新时用，代表的上一次服务器返回的时间信息
	 * @see TestActivity#CATEGORY_TEXT
	 * @see TestActivity#CATEGORY_IMGE
	 */
	public static void getList(RequestQueue queue, int categoryType,
			int itemCount, long minTime,
			Response.Listener<String> responseListener) {
		String CATEGORY_LIST_API = "http://ic.snssdk.com/2/essay/zone/category/data/";
		String categoryParam = "category_id=" + categoryType;// 分类参数,根据类型获取不同数据
		String countParam = "count=" + itemCount;//
		String deviceTypeParam = "device_type=KFTT";
		String openUDID = "openudid=b90ca6a3a19a78d6";
		String url = CATEGORY_LIST_API
				+ "?"
				+ categoryParam
				+ "&"
				+ countParam
				+ "&"
				+ deviceTypeParam
				+ "&"
				+ openUDID
				+ "&level=6&iid=2337593504&device_id=2757969807&ac=wifi&channel=wandoujia&aid=7&app_name=joke_essay&version_code=302&device_platform=android&os_api=15&os_version=4.0.3";
		if (minTime > 0) {
			url = url + "&min_time=" + minTime;
		}
		queue.add(new StringRequest(Request.Method.GET, url, responseListener,
				new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub

					}
				}));
	}

	public static void getComment(RequestQueue queue, long groupid, int offset,
			Response.Listener<String> listener) {
		String COMMENT_API = "http://isub.snssdk.com/2/data/get_essay_comments/";
		String groupIdParam = "group_id=" + groupid;
		String offsetParam = "offset=" + offset;
		String url = COMMENT_API
				+ "?"
				+ groupIdParam
				+ "&"
				+ offsetParam
				+ "&count=20&iid=2337593504&device_id=2757969807&ac=wifi&channel=wandoujia&aid=7&app_name=joke_essay&version_code=302&device_platform=android&device_type=KFTT&os_api=15&os_version=4.0.3&openudid=b90ca6a3a19a78d6";
	
		queue.add(new StringRequest(Request.Method.GET, url, listener,
				new ErrorListener() {
	
					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
	
					}
				}));
	}

}
