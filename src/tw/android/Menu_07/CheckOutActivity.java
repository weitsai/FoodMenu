package tw.android.Menu_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CheckOutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.checkout);

		findViewById();
		showMyOrderMeal();
		spinnerTableNumber();
		spinnerWaiterID();

		Bundle bundleT = this.getIntent().getExtras();
		String totalPrice = bundleT.getString("allMealTotalPrice");
		checkoutTextViewOrderTotal.setText("總計：" + totalPrice);
	}

	public void showMyOrderMeal() {

		Bundle bundle = this.getIntent().getExtras();

		ArrayList<HashMap<String, String>> listItem = (ArrayList<HashMap<String, String>>) bundle
				.getSerializable("allMyOrderMealData");

		SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(),
				listItem, R.layout.checkout_listview, new String[] {
						"orderMealName", "orderMealCount", "orderMealPrice",
						"orderMealTotal" }, new int[] {
						R.id.checkoutListViewTextViewMealName,
						R.id.checkoutListViewTextViewMealCount,
						R.id.checkoutListViewTextViewMealPrice,
						R.id.checkoutListViewTextViewMealTotal });

		checkoutListView.setAdapter(mSchedule);
	}

	/**
	 * spinner of table number
	 * */
	public void spinnerTableNumber() {
		List<String> str = new ArrayList<String>();
		for (int i = 0; i < table_number_content.length; i++) {
			str.add(table_number_content[i]);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.checkout_spinner, str);
		spinner_table_number.setAdapter(adapter);
	}

	/**
	 * spinner of waiter ID
	 * */
	public void spinnerWaiterID() {
		List<String> str = new ArrayList<String>();
		for (int i = 0; i < waiter_id_content.length; i++) {
			str.add(waiter_id_content[i]);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.checkout_spinner, str);
		spinner_waiter_id.setAdapter(adapter);
	}

	/**
	 * cancel checkout
	 * */
	public void cancelCheckOut(View v) {
		CheckOutActivity.this.finish();
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

	private String[] table_number_content = { "第一桌", "第二桌", "第三桌", "第四桌",
			"第五桌", "第六桌", "第七桌", "第八桌", "第九桌", "第十桌" };
	private String[] waiter_id_content = { "小明", "小華", "小蓁" };

	/**
	 * findViewById
	 * */
	public void findViewById() {
		checkoutListView = (ListView) findViewById(R.id.checkoutListView);
		spinner_table_number = (Spinner) findViewById(R.id.checkoutSpinnerTable);
		spinner_waiter_id = (Spinner) findViewById(R.id.checkoutSpinnerWaiter);
		checkoutTextViewOrderTotal = (TextView) findViewById(R.id.checkoutTextViewOrderTotal);
	}

	private ListView checkoutListView;
	private Spinner spinner_table_number;
	private Spinner spinner_waiter_id;
	private TextView checkoutTextViewOrderTotal;
}
