package tw.android.Menu_07;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class GraphicalEveryMealActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.graphical_every_meal);

		findViewById();
		setMealContent();
	}

	/**
	 * set meal Count
	 * */
	public void setMealContent() {
		Bundle bundle = this.getIntent().getExtras();
		String mealName_s = bundle.getString("clickMealName");
		String mealCount_s = bundle.getString("clickMealCount");
		System.out.println(mealCount_s);
		System.out.println(mealName_s);

		for (int i = 0; i < mealName.length; i++) {
			for (int j = 0; j < mealName[i].length; j++) {
				if (mealName[i][j].equals(mealName_s)) {
					graphicalEveryMealTextViewMealName.setText(mealName[i][j]);
					graphicalEveryMealTextViewMealPrice.setText(Integer.toString(mealPrice[i][j]));
					graphicalEveryMealImageViewPicture.setImageResource(mealIcon[i][j]);
					graphicalEveryMealTextViewMealCount.setText(mealCount_s);
				}
			}
		}
	}

	/**
	 * sub one of meal count
	 * */
	public void subMealCount(View v) {
		int nowMealCount = Integer.parseInt(graphicalEveryMealTextViewMealCount
				.getText().toString());
		nowMealCount--;
		if (nowMealCount > 0)
			graphicalEveryMealTextViewMealCount.setText(Integer
					.toString(nowMealCount));
		else
			graphicalEveryMealTextViewMealCount.setText("0");
	}

	/**
	 * add one of meal count
	 * */
	public void addMealCount(View v) {
		int nowMealCount = Integer.parseInt(graphicalEveryMealTextViewMealCount
				.getText().toString());
		nowMealCount++;
		graphicalEveryMealTextViewMealCount.setText(Integer
				.toString(nowMealCount));
	}

	/**
	 * cancel save meal count
	 * */
	public void cancelSaveMealCount(View v) {
		GraphicalEveryMealActivity.this.finish();
	}

	/**
	 * confirm save meal count
	 * */
	public void confirmSaveMealCount(View v) {
		GraphicalEveryMealActivity.this.finish();
		
		HashMap<String, String> aaa = new HashMap<String, String>();

		if (graphicalEveryMealTextViewMealCount.getText().toString().equals("0")) {
			aaa.put("food_name", graphicalEveryMealTextViewMealName.getText().toString());
			aaa.put("food_number", "0");
			GraphicalInterfaceActivity.arrtmp.add(aaa);
			GraphicalEveryMealActivity.this.finish();
		} else {
			aaa.put("food_name", graphicalEveryMealTextViewMealName.getText().toString());
			aaa.put("food_number", graphicalEveryMealTextViewMealCount.getText().toString());
			GraphicalInterfaceActivity.arrtmp.add(aaa);
			GraphicalEveryMealActivity.this.finish();
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

	String[][] mealName = {
			{ "¶p¥Ö«n¥Ê¿@´ö", "µð¦×¥©¹F´ö", "®õ¦¡®üÂA´ö", "¸q¤j§Q³¥Û£¶p¥Ö¿@´ö", "¶p¥Ö¥É¦Ì¿@´ö", "¦I¤ú§Q¤û¦×´ö",
					"µð¦×¥©¹F´ö-¤j´ö", "¥É¦Ì¿@´ö-¤j´ö", "®õ¦¡®üÂA´ö-¤j´ö", "¦I¤ú§Q¤û¦×´ö-¤j´ö", "«n¥Ê¿@´ö-¤ÈÀ\´ö",
					"¬v½µ²M´ö-¤ÈÀ\´ö", "¥É¦Ì¿@´ö-¤ÈÀ\´ö", "¯À´ö(¯À­¹¥i)" },
			{ "­»·ÎÂû¦×¦õ¸q¤j§Q¸²µå¾L", "©M­·­»¶pÂA³½", "Áú¦¡»¶¨ý¤û¦×¤â±²", "¨FÜÝ½Þ¦×¤â±²", "­»¶p»¶¨ý³z©â", "»¶¨ý¯NÂû¯Í",
					"¯N¥ÐÁ³", "ÅÚ°ÇÂæÂû¦×", "¬µÁ¦±ø­»¯ó­·¨ý", "©u¸`Àu®æ¤ôªG", "»[­»¯NÂû¦×", "³ÍÂÄ¨F©Ô",
					"¤ÈÀ\¨F©Ô-³ÍÂÄ¨F©Ô", "¤ÈÀ\¨F©Ô-¤õ»L¥Íµæ¨F©Ô", "¤ÈÀ\¨F©Ô-¹à·ÎÂû¦×¨F©Ô", "¤ÈÀ\¨F©Ô-©u¸`ÂAª£®É½­" },
			{ "­»¯N½Þ¦×±²", "°_¤hÖK¯NÃÉ¦×", "»[¯N¤û¦×ÀjÛ£", "·ÏÂtÂD³½¨F©Ô", "¥j©ÔÀï¨F©Ô", "ÂA¯N³z©â", "¸q¦¡¥Í¤û¦×",
					"¥ÕÂæ³¥Û£°_¤hÁç", "¨FÜÝ¬¡µß½Þ¦ê¿N", "ºñ©@­ù°_¤h¯NÂA³½", "°ö®Ú°_¤hÖKÄ¨Û£" },
			{ "®üÂA¿»­X´ö¸q¤j§QÄÑ", "°ö®Ú«Cªáµæ¸q¤j§QÄÑ", "½­µæ¿»­X¸q¤j§QÄÑ(¯À­¹¥i)", "®³©Y¨½½¼¤¯¸q¤j§QÄÑ",
					"§öÀjÛ£¤û¦×¸q¤j§QÄÑ", "»¶¨ýÂû¦×¸q¤j§QÄÑ", "©ú¤Ó¤l³z©â¸q¤j§QÄÑ", "ÅÚ°Ç½­µæ¸q¤j§QÄÑ",
					"»[­»¥Õ°sµð¸Ä¸q¤j§QÄÑ", "·ç¨å­»¸z¦×ÂæÖK±×ºÞÄÑ", "®üÂAÖK¯N±×ºÞÄÑ", "³¥Û£Âû¦×ÖK±×ºÞÄÑ",
					"µð¸Ä¿»­X´ö«óÄÑ", "¥¤ªoÂD³½ÂA½¼«óÄÑ", "¥¤ªo½­µæ«óÄÑ(¯À­¹¥i)", "¯Q¸é¾¥«óÄÑ" },
			{ "«CÂæ³z©âÂAÖK¶º", "¦ÌÄõ¤û¦×ÖK¶º", "¦è¯Z¤ú®üÂA¯N¶º", "¿»­XÂû¦×ÖK¶º", "¯Q¸é¾¥¿L¶º", "®³©Y¨½¬Ã¯]½¼²yÖK¶º" },
			{ "µá¤O¤û¦×¤ñÂÄ", "»¶¨ýÂC³½¤ñÂÄ", "³¥Û£Á`¶×«CÂæ¤ñÂÄ", "©ú¤Ó¤l³z©â¤ñÂÄ", "©@­ù½Þ¦×¤ñÂÄ", "»¶¨ý­»¸z¤ñÂÄ",
					"°·±d½­µæ¤ñÂÄ(¯À­¹¥i)", "õl³½¤ñÂÄ", "Á`¶×¤ñÂÄ", "°ö®Ú¤ñÂÄ", "¸q¦¡Ã¾¸z¤ñÂÄ",
					"ÂÅ¯¾°_¤h¸Á»eÄ«ªG¤ñÂÄ", "º¿®æÄR¯S¤ñÂÄ", "®L«Â¦i¤ñÂÄ", "·Ó¿NÂû¦×¤ñÂÄ", "°ö®Ú+·Ó¿NÂû¦×¤ñÂÄ",
					"°ö®Ú+ÂÅ¯¾°_¤h¸Á»eÄ«ªG¤ñÂÄ", "·Ó¿NÂû¦×+ÂÅ¯¾°_¤h¸Á»eÄ«ªG¤ñÂÄ" },
			{ "¸q¦¡¸g¨å¤û±Æ", "¦ÌÄõ¬´¯N¤û±Æ", "°_¤h¯N©ú½¼", "®ü³°«÷½L", "¸q¦¡­»¯ó¯NÂû" },
			{ "¥©§J¤O®ß¤l³J¿|", "ÂÅ¯¾°_¤h³J¿|", "¤ôªG¯]Ä_²°(¯À­¹¥i)", "¦Ê­»¥¤¹T", "­»¯NÄ«ªG¦õÀu®æ¼}´µ(¯À­¹¥i)",
					"¥¬Äõ©g³J¿|(¯À­¹¥i)", "ªk¦¡­»¯óµJ¿}¥¬Á¢(¯À­¹¥i)", "Äõ©i¸²µå°®°_¤h³J¿|(¯À­¹¥i)", "¿@ÁY©@°Ø¥¤Á÷ªM",
					"´£©Ô¦ÌÄ¬(¯À­¹¥i)", "»e®ç¥Õ¥©§J¤O¸t¥NªM(¯À­¹¥i)", "¤U¤È¯ù»æ°®Á`¶×", "±¡¤H¸`­­©w²¢ÂI" },
			{ "ª÷®ÜÂfÂc¯ù", "¦B©@°Ø", "¬´¤õµH·Î©@°Ø", "·x·x¥ÍÁ¤ÂfÂc¯ù", "¬v¥Ìµâªá¯ù", "±K¬cÄ«ªG¯ù", "¾í­»§BÀï¥¤¯ù",
					"¦B®ÛªáÆC±Kªá¯ù", "¦BÄ}¯É¤ôªG¯ù", "¦BµJ¿}¥¤¯ù", "¦B©@°Ø®³ÅK", "©@°Ø®³ÅK", "¤j¦NÀ­¤ôªG¯ù",
					"¦B©@°Ø¼¯¥d", "¦BÁ¡²ü®³ÅK", "¥d¥¬©_¿Õ", "µJ¿}º¿¨ä«¢¦·", "¼ö©@°Ø¼¯¥d", "¼öÁ¡²ü®³ÅK",
					"¼ö¥¤¯ù-¤ÈÀ\¶¼®Æ", "¼ö¬õ¯ù-¤ÈÀ\¶¼®Æ", "¦B¬õ¯ù-¤ÈÀ\¶¼®Æ", "¦B¥¤¯ù-¤ÈÀ\¶¼®Æ" },
			{ "¦òÃ¹¨½¹F", "¦B¤s", "»e®ç¤Ñ°ó", "·Rº¸Äõ¥¤°s", "Ã¹°¨¤§©]", "¥ÕÄõ¦a»Ä²¢Âû§À°s", "¼ö±¡¥[°Ç¤ñ®ü",
					"´Â¤é°à°s(²~)", "¦Ê«Â°à°s(²~)", "ÄQÅï°à°s(²~)", "®ü¥§®Ú°à°s(²~)", "Orion¥Í°à°s(Åø)",
					"CL-Åb´ò¥d§B¯ÇÄ¬ºû¯Î¬õ°s(ªM)", "CL-Åb´ò®L¦h®R¥Õ°s(ªM)", "CR-¥[¦{¥d§B¯ÇÄ¬ºû¯Î¬õ°s(ªM)",
					"CR-¥[¦{®L¦h®R¥Õ°s", "CL-Åb´ò¥d§B¯ÇÄ¬ºû¯Î¬õ°s(²~)", "CL-Åb´ò®L¦h®R¥Õ°s(²~)",
					"CR-¥[¦{¥d§B¯ÇÄ¬ºû¯Î¬õ°s(²~)", "CR-¥[¦{¤U¦h¥Õ°s(²~)" } };
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
		graphicalEveryMealImageViewPicture = (ImageView) findViewById(R.id.graphicalEveryMealImageViewPicture);
		graphicalEveryMealTextViewMealName = (TextView) findViewById(R.id.graphicalEveryMealTextViewMealName);
		graphicalEveryMealTextViewMealCount = (TextView) findViewById(R.id.graphicalEveryMealTextViewMealCount);
		graphicalEveryMealTextViewMealPrice = (TextView) findViewById(R.id.graphicalEveryMealTextViewMealPrice);
	}

	private ImageView graphicalEveryMealImageViewPicture;
	private TextView graphicalEveryMealTextViewMealName;
	private TextView graphicalEveryMealTextViewMealCount;
	private TextView graphicalEveryMealTextViewMealPrice;
}
