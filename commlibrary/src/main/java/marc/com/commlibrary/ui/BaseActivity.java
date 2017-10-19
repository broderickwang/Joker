package marc.com.commlibrary.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by chengda
 * Date: 2017/10/19
 * Time: 11:09
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public abstract class BaseActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContent();

		initHead();

		initLayout();

		initData();

		loadData();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		releaseData();
	}
	public abstract void setContent();

	public abstract void initHead();

	public abstract void initLayout();

	public abstract void initData();

	public abstract void loadData();

	public abstract void releaseData();
}
