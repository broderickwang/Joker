package marc.com.joker.presenter;

import android.support.v7.widget.RecyclerView;

import marc.com.joker.contract.MainFragmentContract;

/**
 * Created by chengda
 * Date: 2017/10/19
 * Time: 16:02
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MainFragmentPresenter implements MainFragmentContract.Presenter {
	private MainFragmentContract.View mView;

	private RecyclerView.Adapter mAdapter;

	public MainFragmentPresenter(MainFragmentContract.View view) {
		mView = view;
	}

	@Override
	public void createAdapter() {


		mView.setAdapter(mAdapter);
	}
}
