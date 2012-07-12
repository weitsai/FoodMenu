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
			{ "�p�֫n�ʿ@��", "��ץ��F��", "�������A��", "�q�j�Q��ۣ�p�ֿ@��", "�p�֥ɦ̿@��", "�I���Q���״�",
					"��ץ��F��-�j��", "�ɦ̿@��-�j��", "�������A��-�j��", "�I���Q���״�-�j��", "�n�ʿ@��-���\��",
					"�v���M��-���\��", "�ɦ̿@��-���\��", "����(�����i)" },
			{ "�������צ��q�j�Q����L", "�M�����p�A��", "�����������פⱲ", "�F�ݽަפⱲ", "���p�����z��", "�����N����",
					"�N����", "�ڰ�������", "���������󭷨�", "�u�`�u����G", "�[���N����", "���ĨF��",
					"���\�F��-���ĨF��", "���\�F��-���L�͵�F��", "���\�F��-������רF��", "���\�F��-�u�`�A���ɽ�" },
			{ "���N�ަױ�", "�_�h�K�N�ɦ�", "�[�N�����jۣ", "���t�D���F��", "�j����F��", "�A�N�z��", "�q���ͤ���",
					"���泥ۣ�_�h��", "�F�ݬ��߽ަ�N", "��@���_�h�N�A��", "���ڰ_�h�KĨۣ" },
			{ "���A���X���q�j�Q��", "���ګC���q�j�Q��", "���濻�X�q�j�Q��(�����i)", "���Y�������q�j�Q��",
					"���jۣ���׸q�j�Q��", "�������׸q�j�Q��", "���Ӥl�z��q�j�Q��", "�ڰǽ���q�j�Q��",
					"�[���հs��ĸq�j�Q��", "��孻�z�����K�׺���", "���A�K�N�׺���", "��ۣ�����K�׺���",
					"��Ŀ��X������", "���o�D���A������", "���o�������(�����i)", "�Q�龥����" },
			{ "�C��z���A�K��", "���������K��", "��Z�����A�N��", "���X�����K��", "�Q�龥�L��", "���Y���ï]���y�K��" },
			{ "��O���פ���", "�����C������", "��ۣ�`�׫C�����", "���Ӥl�z�����", "�@���ަפ���", "�������z����",
					"���d�������(�����i)", "�l������", "�`�פ���", "���ڤ���", "�q��þ�z����",
					"�ů��_�h���eī�G����", "�����R�S����", "�L�¦i����", "�ӿN���פ���", "����+�ӿN���פ���",
					"����+�ů��_�h���eī�G����", "�ӿN����+�ů��_�h���eī�G����" },
			{ "�q���g�����", "�������N����", "�_�h�N����", "�������L", "�q������N��" },
			{ "���J�O�ߤl�J�|", "�ů��_�h�J�|", "���G�]�_��(�����i)", "�ʭ����T", "���Nī�G���u��}��(�����i)",
					"�����g�J�|(�����i)", "�k������J�}����(�����i)", "���i���尮�_�h�J�|(�����i)", "�@�Y�@�إ����M",
					"���Ԧ�Ĭ(�����i)", "�e��ե��J�O�t�N�M(�����i)", "�U�ȯ��氮�`��", "���H�`���w���I" },
			{ "�����f�c��", "�B�@��", "�����H�Ω@��", "�x�x�����f�c��", "�v�̵���", "�K�cī�G��", "���B�磻��",
					"�B�۪��C�K���", "�B�}�ɤ��G��", "�B�J�}����", "�B�@�خ��K", "�@�خ��K", "�j�N�����G��",
					"�B�@�ؼ��d", "�B�������K", "�d���_��", "�J�}���䫢��", "���@�ؼ��d", "���������K",
					"������-���\����", "������-���\����", "�B����-���\����", "�B����-���\����" },
			{ "��ù���F", "�B�s", "�e��Ѱ�", "�R�������s", "ù�����]", "�����a�Ĳ������s", "�����[�Ǥ��",
					"�¤��s(�~)", "�ʫ°�s(�~)", "�Q���s(�~)", "�����ڰ�s(�~)", "Orion�Ͱ�s(��)",
					"CL-�b��d�B��Ĭ���ά��s(�M)", "CL-�b��L�h�R�հs(�M)", "CR-�[�{�d�B��Ĭ���ά��s(�M)",
					"CR-�[�{�L�h�R�հs", "CL-�b��d�B��Ĭ���ά��s(�~)", "CL-�b��L�h�R�հs(�~)",
					"CR-�[�{�d�B��Ĭ���ά��s(�~)", "CR-�[�{�U�h�հs(�~)" } };
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
