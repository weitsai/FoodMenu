package tw.android.Menu_07;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class GraphicalInterfaceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graphical_interface);

		findViewById();
		setGraphicalListView();
		setGraphicalGridView();
	}

	/**
	 * refresh data when Resume
	 * 	*/
	public void onResume() {
		super.onResume();
		mMyAdapter.notifyDataSetChanged();
	}

	/**
	 * GridView custom content
	 * */
	private class MyAdapter extends BaseAdapter {
		public int getCount() {
			return mealName[getNowListViewInt()].length;
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			// LayoutInflater不会的参照我的 Android 高手进阶教程(五)
			convertView = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.graphical_gridview, null);

			ImageView graphicalGridViewImageView = (ImageView) convertView
					.findViewById(R.id.graphicalGridViewImageView);
			graphicalGridViewImageView
					.setImageResource(mealIcon[getNowListViewInt()][position]);

			TextView graphicalGridViewTextViewMealCount = (TextView) convertView
					.findViewById(R.id.graphicalGridViewTextViewMealCount);
			if (mealCount[getNowListViewInt()][position] == 0)
				graphicalGridViewTextViewMealCount.setText("");
			else
				graphicalGridViewTextViewMealCount.setText(Integer
						.toString(mealCount[getNowListViewInt()][position]));

			for (int i = 0; i < arrtmp.size(); i++) {
				if (arrtmp.get(i).get("food_name")
						.equals(mealName[getNowListViewInt()][position])) {
					graphicalGridViewTextViewMealCount.setText(arrtmp.get(i)
							.get("food_number"));
					if (graphicalGridViewTextViewMealCount.getText().toString() == "0")
						graphicalGridViewTextViewMealCount.setText("");
				}
			}

			TextView graphicalGridViewTextViewMealName = (TextView) convertView
					.findViewById(R.id.graphicalGridViewTextViewMealName);
			graphicalGridViewTextViewMealName
					.setText(mealName[getNowListViewInt()][position]);

			return convertView;
		}

	}

	/**
	 * set Graphical GridView onClickListener
	 * */
	public void setGraphicalGridView() {

		grahpicalGridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {

				Intent it = new Intent();
				it.setClass(GraphicalInterfaceActivity.this,
						GraphicalEveryMealActivity.class);
				Bundle tmp = new Bundle();

				tmp.putString("clickMealName", ((TextView) v
						.findViewById(R.id.graphicalGridViewTextViewMealName))
						.getText().toString());

				if (((TextView) v
						.findViewById(R.id.graphicalGridViewTextViewMealCount))
						.getText().toString().equals(""))
					tmp.putString("clickMealCount", "1");
				else
					tmp.putString(
							"clickMealCount",
							((TextView) v
									.findViewById(R.id.graphicalGridViewTextViewMealCount))
									.getText().toString());

				it.putExtras(tmp);
				startActivity(it);
			}
		});
	}

	/**
	 * set Graphical ListView content and onClickListener
	 * */
	public void setGraphicalListView() {
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;

		for (int i = 0; i < mealGroup.length; i++) {
			if (i == mealGroup.length - 1) {
				map = new HashMap<String, String>();
				map.put("groupText", mealGroup[i]);
				map.put("dividingLine", "");
				listItem.add(map);
			} else {
				map = new HashMap<String, String>();
				map.put("groupText", mealGroup[i]);
				map.put("dividingLine", R.drawable.dividing_line + "");
				listItem.add(map);
			}
		}

		SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(),
				listItem, R.layout.graphical_listview, new String[] {
						"groupText", "dividingLine" }, new int[] {
						R.id.graphicalListViewTextView,
						R.id.graphicalListViewImageView });

		grahpicalListView.setAdapter(mSchedule);

		grahpicalListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				setNowListViewInt(position);
				mMyAdapter.notifyDataSetChanged();
			}
		});
	}

	/**
	 * set nowListViewInt
	 * */
	public void setNowListViewInt(int nowListViewInt) {
		this.nowListViewInt = nowListViewInt;
	}

	/**
	 * get nowListViewInt
	 * */
	public int getNowListViewInt() {
		return nowListViewInt;
	}

	/**
	 * go CheckoutActivity
	 * */
	public void goCheckoutActivity(View v) {
		int orderTotalPrice = 0;
		Intent it = new Intent();
		Bundle bundle = new Bundle();
		Bundle bundleT = new Bundle();
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < arrtmp.size(); i++) {
			for (int j = 0; j < mealName.length; j++) {
				for (int k = 0; k < mealName[j].length; k++) {
					if (mealName[j][k] == arrtmp.get(i).get("food_name")) {
						mealCount[j][k] = Integer.parseInt(arrtmp.get(i).get(
								"food_number"));
					}
				}
			}
		}

		for (int i = 0; i < mealName.length; i++) {
			for (int j = 0; j < mealName[i].length; j++) {
				if (mealCount[i][j] > 0) {
					HashMap<String, String> myOrderMealData = new HashMap<String, String>();
					myOrderMealData.put("orderMealName", "名稱：" + mealName[i][j]);
					myOrderMealData.put("orderMealCount",
							"數量：" + Integer.toString(mealCount[i][j]));
					myOrderMealData.put("orderMealPrice",
							"單價：" + Integer.toString(mealPrice[i][j]));
					myOrderMealData.put("orderMealTotal",
							"小計：" + Integer.toString((mealPrice[i][j] * mealCount[i][j])));
					listItem.add(myOrderMealData);
					orderTotalPrice += mealCount[i][j] * mealPrice[i][j];
				}
			}
		}

		if (orderTotalPrice > 0) {
			bundleT.putString("allMealTotalPrice", Integer.toString(orderTotalPrice));
			it.putExtras(bundleT);
			bundle.putSerializable("allMyOrderMealData", listItem);
			it.putExtras(bundle);
			it.setClass(GraphicalInterfaceActivity.this, CheckOutActivity.class);
			startActivity(it);
		} else {
			it.setClass(GraphicalInterfaceActivity.this, NoOrderActivity.class);
			startActivity(it);

		}
	}

	/**
	 * go last Activity
	 * */
	public void goLastActivity(View v) {
		arrtmp.clear();
		GraphicalInterfaceActivity.this.finish();
	}

	/**
	 * no answer when you click back
	 * */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return (false);
		} else
			return super.onKeyDown(keyCode, msg);
	}

	private int nowListViewInt = 0;
	private String[] mealGroup = { "湯品", "開胃料理", "經典料理", "義大利麵", "飯類", "比薩",
			"經典主菜", "甜點", "軟性飲料", "酒精飲料" };
	String[][] mealName = {
			{ "酥皮南瓜濃湯", "蛤肉巧達湯", "泰式海鮮湯", "義大利野菇酥皮濃湯", "酥皮玉米濃湯", "匈牙利牛肉湯",
					"蛤肉巧達湯-大湯", "玉米濃湯-大湯", "泰式海鮮湯-大湯", "匈牙利牛肉湯-大湯", "南瓜濃湯-午餐湯",
					"洋蔥清湯-午餐湯", "玉米濃湯-午餐湯", "素湯(素食可)" },
			{ "香煎雞肉佐義大利葡萄醋", "和風香酥鮮魚", "韓式辣味牛肉手捲", "沙嗲豬肉手捲", "香酥辣味透抽", "辣味烤雞翅",
					"烤田螺", "蘿勒醬雞肉", "炸薯條香草風味", "季節優格水果", "蒜香烤雞肉", "凱薩沙拉",
					"午餐沙拉-凱薩沙拉", "午餐沙拉-火腿生菜沙拉", "午餐沙拉-嫩煎雞肉沙拉", "午餐沙拉-季節鮮炒時蔬" },
			{ "香烤豬肉捲", "起士焗烤蟹肉", "蒜烤牛肉鮑菇", "煙燻鮭魚沙拉", "古拉爵沙拉", "鮮烤透抽", "義式生牛肉",
					"白醬野菇起士鍋", "沙嗲活菌豬串燒", "綠咖哩起士烤鮮魚", "培根起士焗蘑菇" },
			{ "海鮮蕃茄湯義大利麵", "培根青花菜義大利麵", "蔬菜蕃茄義大利麵(素食可)", "拿坡里蝦仁義大利麵",
					"杏鮑菇牛肉義大利麵", "辣味雞肉義大利麵", "明太子透抽義大利麵", "蘿勒蔬菜義大利麵",
					"蒜香白酒蛤蜊義大利麵", "瑞典香腸肉醬焗斜管麵", "海鮮焗烤斜管麵", "野菇雞肉焗斜管麵",
					"蛤蜊蕃茄湯扁麵", "奶油鮭魚鮮蝦扁麵", "奶油蔬菜扁麵(素食可)", "烏賊墨扁麵" },
			{ "青醬透抽鮮焗飯", "米蘭牛肉焗飯", "西班牙海鮮烤飯", "蕃茄雞肉焗飯", "烏賊墨燉飯", "拿坡里珍珠蝦球焗飯" },
			{ "菲力牛肉比薩", "辣味鮪魚比薩", "野菇總匯青醬比薩", "明太子透抽比薩", "咖哩豬肉比薩", "辣味香腸比薩",
					"健康蔬菜比薩(素食可)", "鯷魚比薩", "總匯比薩", "培根比薩", "義式臘腸比薩",
					"藍紋起士蜂蜜蘋果比薩", "瑪格麗特比薩", "夏威夷比薩", "照燒雞肉比薩", "培根+照燒雞肉比薩",
					"培根+藍紋起士蜂蜜蘋果比薩", "照燒雞肉+藍紋起士蜂蜜蘋果比薩" },
			{ "義式經典牛排", "米蘭炭烤牛排", "起士烤明蝦", "海陸拼盤", "義式香草烤雞" },
			{ "巧克力栗子蛋糕", "藍紋起士蛋糕", "水果珠寶盒(素食可)", "百香奶酪", "香烤蘋果佐優格慕斯(素食可)",
					"布蘭妮蛋糕(素食可)", "法式香草焦糖布蕾(素食可)", "蘭姆葡萄乾起士蛋糕(素食可)", "濃縮咖啡奶霜杯",
					"提拉米蘇(素食可)", "蜜桃白巧克力聖代杯(素食可)", "下午茶餅乾總匯", "情人節限定甜點" },
			{ "金桔檸檬茶", "冰咖啡", "炭火焙煎咖啡", "暖暖生薑檸檬茶", "洋甘菊花茶", "密柚蘋果茶", "橙香伯爵奶茶",
					"冰桂花釀密花茶", "冰繽紛水果茶", "冰焦糖奶茶", "冰咖啡拿鐵", "咖啡拿鐵", "大吉嶺水果茶",
					"冰咖啡摩卡", "冰薄荷拿鐵", "卡布奇諾", "焦糖瑪其哈朵", "熱咖啡摩卡", "熱薄荷拿鐵",
					"熱奶茶-午餐飲料", "熱紅茶-午餐飲料", "冰紅茶-午餐飲料", "冰奶茶-午餐飲料" },
			{ "佛羅里達", "冰山", "蜜桃天堂", "愛爾蘭奶酒", "羅馬之夜", "白蘭地酸甜雞尾酒", "熱情加勒比海",
					"朝日啤酒(瓶)", "百威啤酒(瓶)", "麒麟啤酒(瓶)", "海尼根啤酒(瓶)", "Orion生啤酒(罐)",
					"CL-鶴湖卡伯納蘇維翁紅酒(杯)", "CL-鶴湖夏多娜白酒(杯)", "CR-加州卡伯納蘇維翁紅酒(杯)",
					"CR-加州夏多娜白酒", "CL-鶴湖卡伯納蘇維翁紅酒(瓶)", "CL-鶴湖夏多娜白酒(瓶)",
					"CR-加州卡伯納蘇維翁紅酒(瓶)", "CR-加州下多白酒(瓶)" } };
	int[][] mealCount = {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	int[][] mealPrice = {
			{ 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40 },
			{ 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 },
			{ 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60 },
			{ 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120,
					120, 120, 120 },
			{ 120, 120, 120, 120, 120, 120 },
			{ 190, 190, 190, 190, 190, 190, 190, 190, 190, 190, 190, 190, 190,
					190, 190, 190, 190, 190 },
			{ 370, 370, 370, 370, 370 },
			{ 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50 },
			{ 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
					50, 50, 50, 50, 50, 50, 50 },
			{ 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70, 70,
					70, 70, 70, 70 } };
	private int[][] mealIcon = {
			{ R.drawable.icon_soup_00, R.drawable.icon_soup_01,
					R.drawable.icon_soup_02, R.drawable.icon_soup_03,
					R.drawable.icon_soup_04, R.drawable.icon_soup_05,
					R.drawable.icon_soup_06, R.drawable.icon_soup_07,
					R.drawable.icon_soup_08, R.drawable.icon_soup_09,
					R.drawable.icon_soup_10, R.drawable.icon_soup_11,
					R.drawable.icon_soup_12, R.drawable.icon_soup_13,
					R.drawable.icon_soup_14 },
			{ R.drawable.icon_appetizer_00, R.drawable.icon_appetizer_01,
					R.drawable.icon_appetizer_02, R.drawable.icon_appetizer_03,
					R.drawable.icon_appetizer_04, R.drawable.icon_appetizer_05,
					R.drawable.icon_appetizer_06, R.drawable.icon_appetizer_07,
					R.drawable.icon_appetizer_08, R.drawable.icon_appetizer_09,
					R.drawable.icon_appetizer_10, R.drawable.icon_appetizer_11,
					R.drawable.icon_appetizer_12, R.drawable.icon_appetizer_13,
					R.drawable.icon_appetizer_14, R.drawable.icon_appetizer_15 },
			{ R.drawable.icon_classic_00, R.drawable.icon_classic_01,
					R.drawable.icon_classic_02, R.drawable.icon_classic_03,
					R.drawable.icon_classic_04, R.drawable.icon_classic_05,
					R.drawable.icon_classic_06, R.drawable.icon_classic_07,
					R.drawable.icon_classic_08, R.drawable.icon_classic_09,
					R.drawable.icon_classic_10 },
			{ R.drawable.icon_pasta_00, R.drawable.icon_pasta_01,
					R.drawable.icon_pasta_02, R.drawable.icon_pasta_03,
					R.drawable.icon_pasta_04, R.drawable.icon_pasta_05,
					R.drawable.icon_pasta_06, R.drawable.icon_pasta_07,
					R.drawable.icon_pasta_08, R.drawable.icon_pasta_09,
					R.drawable.icon_pasta_10, R.drawable.icon_pasta_11,
					R.drawable.icon_pasta_12, R.drawable.icon_pasta_13,
					R.drawable.icon_pasta_14, R.drawable.icon_pasta_15 },
			{ R.drawable.icon_rice_00, R.drawable.icon_rice_01,
					R.drawable.icon_rice_02, R.drawable.icon_rice_03,
					R.drawable.icon_rice_04, R.drawable.icon_rice_05 },
			{ R.drawable.icon_pizza_00, R.drawable.icon_pizza_01,
					R.drawable.icon_pizza_02, R.drawable.icon_pizza_03,
					R.drawable.icon_pizza_04, R.drawable.icon_pizza_05,
					R.drawable.icon_pizza_06, R.drawable.icon_pizza_07,
					R.drawable.icon_pizza_08, R.drawable.icon_pizza_09,
					R.drawable.icon_pizza_10, R.drawable.icon_pizza_11,
					R.drawable.icon_pizza_12, R.drawable.icon_pizza_13,
					R.drawable.icon_pizza_14, R.drawable.icon_pizza_15,
					R.drawable.icon_pizza_16, R.drawable.icon_pizza_17 },
			{ R.drawable.icon_maincourse_00, R.drawable.icon_maincourse_01,
					R.drawable.icon_maincourse_02,
					R.drawable.icon_maincourse_03,
					R.drawable.icon_maincourse_04 },
			{ R.drawable.icon_dessert_00, R.drawable.icon_dessert_01,
					R.drawable.icon_dessert_02, R.drawable.icon_dessert_03,
					R.drawable.icon_dessert_04, R.drawable.icon_dessert_05,
					R.drawable.icon_dessert_06, R.drawable.icon_dessert_07,
					R.drawable.icon_dessert_08, R.drawable.icon_dessert_09,
					R.drawable.icon_dessert_10, R.drawable.icon_dessert_11,
					R.drawable.icon_dessert_12 },
			{ R.drawable.icon_drink_00, R.drawable.icon_drink_01,
					R.drawable.icon_drink_02, R.drawable.icon_drink_03,
					R.drawable.icon_drink_04, R.drawable.icon_drink_05,
					R.drawable.icon_drink_06, R.drawable.icon_drink_07,
					R.drawable.icon_drink_08, R.drawable.icon_drink_09,
					R.drawable.icon_drink_10, R.drawable.icon_drink_11,
					R.drawable.icon_drink_12, R.drawable.icon_drink_13,
					R.drawable.icon_drink_14, R.drawable.icon_drink_15,
					R.drawable.icon_drink_16, R.drawable.icon_drink_17,
					R.drawable.icon_drink_18, R.drawable.icon_drink_19,
					R.drawable.icon_drink_20, R.drawable.icon_drink_21,
					R.drawable.icon_drink_22, R.drawable.icon_drink_23,
					R.drawable.icon_drink_24, R.drawable.icon_drink_25,
					R.drawable.icon_drink_26, R.drawable.icon_drink_27,
					R.drawable.icon_drink_28, R.drawable.icon_drink_29,
					R.drawable.icon_drink_30, R.drawable.icon_drink_31,
					R.drawable.icon_drink_32, R.drawable.icon_drink_33,
					R.drawable.icon_drink_34, R.drawable.icon_drink_35,
					R.drawable.icon_drink_36, R.drawable.icon_drink_37,
					R.drawable.icon_drink_38, R.drawable.icon_drink_39,
					R.drawable.icon_drink_40, R.drawable.icon_drink_41 },
			{ R.drawable.icon_liquor_00, R.drawable.icon_liquor_01,
					R.drawable.icon_liquor_02, R.drawable.icon_liquor_03,
					R.drawable.icon_liquor_04, R.drawable.icon_liquor_05,
					R.drawable.icon_liquor_06, R.drawable.icon_liquor_07,
					R.drawable.icon_liquor_08, R.drawable.icon_liquor_09,
					R.drawable.icon_liquor_10, R.drawable.icon_liquor_11,
					R.drawable.icon_liquor_12, R.drawable.icon_liquor_13,
					R.drawable.icon_liquor_14, R.drawable.icon_liquor_15,
					R.drawable.icon_liquor_16, R.drawable.icon_liquor_17,
					R.drawable.icon_liquor_18, R.drawable.icon_liquor_19 } };

	/**
	 * findViewById
	 * */
	public void findViewById() {
		mMyAdapter = new MyAdapter();
		grahpicalGridView = (GridView) findViewById(R.id.graphicalGridView);
		grahpicalGridView.setAdapter(mMyAdapter);
		grahpicalListView = (ListView) findViewById(R.id.graphicalListView);
	}

	public static ArrayList<HashMap<String, String>> arrtmp = new ArrayList<HashMap<String, String>>();
	private MyAdapter mMyAdapter;
	private ListView grahpicalListView;
	private GridView grahpicalGridView;
}
