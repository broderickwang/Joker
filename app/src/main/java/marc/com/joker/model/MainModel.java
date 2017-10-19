package marc.com.joker.model;

import marc.com.joker.contract.MainContract;

/**
 * Created by chengda
 * Date: 2017/10/19
 * Time: 11:17
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MainModel implements MainContract.Model {
	private int mLayoutRes;
	private String mTitle;
	private String mUrl;

	public MainModel(int layoutRes, String title, String url) {
		mLayoutRes = layoutRes;
		mTitle = title;
		mUrl = url;
	}

	public int getLayoutRes() {
		return mLayoutRes;
	}

	public void setLayoutRes(int layoutRes) {
		mLayoutRes = layoutRes;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		mUrl = url;
	}
}
