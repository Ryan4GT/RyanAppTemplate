package com.ryan.ryanapp.ui.customeview;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.StringUtil;
import com.ryan.ryanapp.Utils.UnitFormatter;

/**
 * 
 * @author yt
 *  类描述	对话框
 * 创建日期 ： 2014年12月15日 下午2:16:13
 */
public class TianmaDailogBuilder extends Builder implements OnClickListener {

	private Context				context;
	private TextView			title;
	private TextView			content;
	private Button				positiveBtn;
	private Button				negativeBtn;
	private OnClickedListener	listener;
	private LinearLayout		view;
	private AlertDialog			alertDialog;

	public TianmaDailogBuilder setOnClickedListener(OnClickedListener listener) {

		this.listener = listener;
		return this;
	}

	public TianmaDailogBuilder(Context context) {

		super(context);
		this.context = context;
		initBuilder();
	}

	private void initBuilder() {

		view = (LinearLayout) View.inflate(context, R.layout.activity_dialog, null);
		title = (TextView) view.findViewById(R.id.dialogTitle);
		content = (TextView) view.findViewById(R.id.dialogContent);
		positiveBtn = (Button) view.findViewById(R.id.positiveBtn);
		negativeBtn = (Button) view.findViewById(R.id.negativeBtn);
		positiveBtn.setOnClickListener(this);
		negativeBtn.setOnClickListener(this);
		setView(view);
	}

	public TianmaDailogBuilder setTitle(String title) {

		if (StringUtil.isEmpty(title)) {
			this.title.setVisibility(View.GONE);
		}
		this.title.setText(title);
		return this;
	}

	public TianmaDailogBuilder setTitle(int titleRes) {

		String title = context.getString(titleRes);
		setTitle(title);
		return this;
	}

	public TianmaDailogBuilder setContent(String content) {

		this.content.setText(content);
		return this;
	}

	public TianmaDailogBuilder setContent(int contentRes) {

		if (contentRes == 0) {
			content.setVisibility(View.GONE);
		} else {
			this.content.setText(contentRes);
		}
		return this;
	}

	public TianmaDailogBuilder setPositiveBtn(String positive) {

		positiveBtn.setText(positive);
		return this;
	}

	public TianmaDailogBuilder setPositiveBtn(int positiveRes) {

		positiveBtn.setText(positiveRes);
		return this;
	}

	public TianmaDailogBuilder setNegativeBtn(String positive) {

		negativeBtn.setText(positive);
		return this;
	}

	public TianmaDailogBuilder setNegativeBtn(int positiveRes) {

		negativeBtn.setText(positiveRes);
		return this;
	}

	public TianmaDailogBuilder setCustomContent(boolean titleVisible, View customView, boolean ButtonVisible) {

		if (!titleVisible) {
			setTitle("");
		}
		if (!ButtonVisible) {
			positiveBtn.setVisibility(View.GONE);
			negativeBtn.setVisibility(View.GONE);
			view.removeView(view.findViewById(R.id.dailogWithTwoButton));
		}
		content.setVisibility(View.GONE);
		LinearLayout customContentContainer = (LinearLayout) view.findViewById(R.id.customContentContainer);
		customContentContainer.addView(customView);
		return this;
	}

	public TianmaDailogBuilder setBackground(int res) {

		view.setBackgroundResource(res);
		return this;
	}

	public TianmaDailogBuilder setOneButton(boolean showOneBtn) {

		if (showOneBtn) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			params.leftMargin = UnitFormatter.getDPUnit(10);
			params.rightMargin = UnitFormatter.getDPUnit(10);
			positiveBtn.setLayoutParams(params);
			negativeBtn.setVisibility(View.GONE);
		}
		return this;
	}

	/**
	 * 显示标题
	 * @param titleRes 标题资源
	 * @param contentRes	内容资源
	 * @param positiveBtnRes	确定按钮
	 * @param negativeBtnRes	取消按钮
	 * @param showOneBtn	是否只显示一个按钮
	 * @param listener	按钮监听器
	 * @return	当前对象
	 */
	public TianmaDailogBuilder setBuilderParams(int titleRes, int contentRes, int positiveBtnRes, int negativeBtnRes, boolean showOneBtn, OnClickedListener listener) {

		return setTitle(titleRes).setContent(contentRes).setPositiveBtn(positiveBtnRes).setNegativeBtn(negativeBtnRes).setOneButton(showOneBtn).setOnClickedListener(listener);
	}

	/**
	 * 不现显示标题
	 * @param contentRes 对话框内容
	 * @param positiveBtnRes	确定按钮
	 * @param negativeBtnRes	取消按钮
	 * @param showOneBtn	是否只显示一个按钮
	 * @param listener	按钮监听器
	 * @return	当前对象
	 */
	public TianmaDailogBuilder setBuilderParams(int contentRes, int positiveBtnRes, int negativeBtnRes, boolean showOneBtn, OnClickedListener listener) {

		return setTitle("").setContent(contentRes).setPositiveBtn(positiveBtnRes).setNegativeBtn(negativeBtnRes).setOneButton(showOneBtn).setOnClickedListener(listener);
	}

	/**
	 * 确定按钮为“确定”， 取消按钮为“取消”
	 * @param titleRes		标题
	 * @param contentRes	内容
	 * @param showOneBtn	是否只显示一个按钮
	 * @param listener		按钮监听器
	 * @return	当前对象
	 */
	public TianmaDailogBuilder setBuilderParams(int titleRes, int contentRes, boolean showOneBtn, OnClickedListener listener) {

		return setTitle(titleRes).setContent(contentRes).setOneButton(showOneBtn).setOnClickedListener(listener);
	}

	/**
	 * 确定按钮为“确定”， 取消按钮为“取消”
	 * @param contentRes	内容
	 * @param showOneBtn	是否只显示一个按钮
	 * @param listener		按钮监听器
	 * @return	当前对象
	 */
	public TianmaDailogBuilder setBuilderParams(int contentRes, boolean showOneBtn, OnClickedListener listener) {

		return setTitle("").setContent(contentRes).setOneButton(showOneBtn).setOnClickedListener(listener);
	}

	public interface OnClickedListener {

		/**
		 * @param positiveBtn true点击的是确定键， false 点击的是取消键
		 */
		void onBtnClicked(AlertDialog alertDialog, boolean positiveBtn);
	}

	@Override
	public AlertDialog create() {

		alertDialog = super.create();
		return alertDialog;
	}

	@Override
	public void onClick(View v) {

		if (listener != null) {
			switch (v.getId()) {
				case R.id.negativeBtn:
					listener.onBtnClicked(alertDialog, false);
					break;
				case R.id.positiveBtn:
					listener.onBtnClicked(alertDialog, true);
					break;
			}
		}
	}
}
