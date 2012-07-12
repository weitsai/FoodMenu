package tw.android.Menu_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class LiteralInterfaceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.literal_interface);

		findViewById();
		setLiteralSpinner();
	}

	/**
	 * ListView custom content
	 * */
	private class MyAdapter extends BaseAdapter {
		public int getCount() {
			return mealName[getNowSpinnerInt()].length;
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {

			convertView = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.literal_listview, null);

			TextView literalListViewTextView = (TextView) convertView
					.findViewById(R.id.literalListViewTextView);
			literalListViewTextView
					.setText(mealName[getNowSpinnerInt()][position]);

			final TextView literalListViewTextViewMealCount = (TextView) convertView
					.findViewById(R.id.literalListViewTextViewMealCount);
			if (mealCount[getNowSpinnerInt()][position] < 1) {
				literalListViewTextViewMealCount.setText("");
			} else {
				literalListViewTextViewMealCount.setText(Integer
						.toString(mealCount[getNowSpinnerInt()][position]));
			}

			TextView literalListViewTextViewMealPrice = (TextView) convertView
					.findViewById(R.id.literalListViewTextViewMealPrice);
			literalListViewTextViewMealPrice.setText(Integer
					.toString(mealPrice[getNowSpinnerInt()][position]));
			// add one count of meal
			Button addMealCount = ((Button) convertView
					.findViewById(R.id.addMealCount));

			addMealCount.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					mealCount[getNowSpinnerInt()][position]++;
					literalListViewTextViewMealCount.setText(Integer
							.toString(mealCount[getNowSpinnerInt()][position]));
				}
			});
			// sub one count of meal
			Button sub = ((Button) convertView.findViewById(R.id.subMealCount));

			sub.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					mealCount[getNowSpinnerInt()][position]--;
					if (mealCount[getNowSpinnerInt()][position] < 1) {
						mealCount[getNowSpinnerInt()][position] = 0;
						literalListViewTextViewMealCount.setText("");
					} else {
						literalListViewTextViewMealCount.setText(Integer
								.toString(mealCount[getNowSpinnerInt()][position]));
					}
				}
			});

			return convertView;
		}

	}

	/**
	 * set Literal Spinner content and onClickListener
	 * */
	public void setLiteralSpinner() {

		List<String> str = new ArrayList<String>();
		for (int i = 0; i < mealGroup.length; i++) {
			str.add(mealGroup[i]);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.literal_spinner, str);

		literalSpinner.setAdapter(adapter);
		literalSpinner.setOnItemSelectedListener(literalSpinnerSelected);
	}

	/**
	 * Spinner of listener which one is select now
	 * */
	private Spinner.OnItemSelectedListener literalSpinnerSelected = new Spinner.OnItemSelectedListener() {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			setNowSpinnerInt(arg2);
			mMyAdapter.notifyDataSetChanged();
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}

	};

	/**
	 * set nowSpinnerInt
	 * */
	public void setNowSpinnerInt(int nowSpinnerInt) {
		this.nowSpinnerInt = nowSpinnerInt;
	}

	/**
	 * get nowSpinnerInt
	 * */
	public int getNowSpinnerInt() {
		return nowSpinnerInt;
	}

	/**
	 * go last Activity
	 * */
	public void goLastActivity(View v) {
		for (int i = 0; i < mealCount.length; i++) {
			for (int j = 0; j < mealCount[i].length; j++) {
				mealCount[i][j] = 0;
			}
		}
		LiteralInterfaceActivity.this.finish();

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
		for (int i = 0; i < mealCount.length; i++) {
			for (int j = 0; j < mealCount[i].length; j++) {
				if (mealCount[i][j] > 0) {
					HashMap<String, String> myOrderMealData = new HashMap<String, String>();
					myOrderMealData
							.put("orderMealName", "名稱：" + mealName[i][j]);
					myOrderMealData.put("orderMealCount",
							"數量：" + Integer.toString(mealCount[i][j]));
					myOrderMealData.put("orderMealPrice",
							"單價：" + Integer.toString(mealPrice[i][j]));
					myOrderMealData.put(
							"orderMealTotal",
							"小計："
									+ Integer.toString(mealPrice[i][j]
											* mealCount[i][j]));
					orderTotalPrice += mealCount[i][j] * mealPrice[i][j];
					
					listItem.add(myOrderMealData);
				}
			}
		}

		if (orderTotalPrice > 0) {
			bundleT.putString("allMealTotalPrice", Integer.toString(orderTotalPrice));
			it.putExtras(bundleT);
			bundle.putSerializable("allMyOrderMealData", listItem);
			it.putExtras(bundle);
			it.setClass(LiteralInterfaceActivity.this, CheckOutActivity.class);
			startActivity(it);
		} else {
			it.setClass(LiteralInterfaceActivity.this, NoOrderActivity.class);
			startActivity(it);
		}
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

	private int nowSpinnerInt = 0;
	private String[] mealGroup = { "湯品", "開胃料理", "經典料理", "義大利麵", "飯類", "比薩",
			"經典主菜", "甜點", "軟性飲料", "酒精飲料" };
	private String[][] mealName = {
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
	static private int[][] mealCount = {
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

	/**
	 * findViewById
	 * */
	public void findViewById() {
		mMyAdapter = new MyAdapter();
		literalListView = (ListView) findViewById(R.id.literalListView);
		literalListView.setAdapter(mMyAdapter);
		literalSpinner = (Spinner) findViewById(R.id.literalSpinner);
	}

	private MyAdapter mMyAdapter;
	private ListView literalListView;
	private Spinner literalSpinner;
}
