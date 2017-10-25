package marc.com.joker.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import marc.com.baselibrary.net.EngineCallback;
import marc.com.baselibrary.net.HttpUtil;
import marc.com.joker.R;
import marc.com.joker.adapter.MainRecyclerAdapter;
import marc.com.joker.bean.TuijianBean;
import marc.com.joker.contract.MainFragmentContract;
import marc.com.multrecycleadapter.CommonRecycleAdapter;

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

	private CommonRecycleAdapter mAdapter;
	private MainPresenter mMainPresenter;

	private TuijianBean mTuijianBean;

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case 1:
					mAdapter.setmDatas(mTuijianBean.getData().getData());
					mAdapter.notifyDataSetChanged();
//					mMainPresenter.refresh();
					break;
				default:
			}
		}
	};

	public MainFragmentPresenter(MainFragmentContract.View view) {
		mView = view;
		mMainPresenter = new MainPresenter();
	}

	@Override
	public void createAdapter(String url) {
		mAdapter = new MainRecyclerAdapter(
				((Fragment)mView).getContext().getApplicationContext(),
				null,
				R.layout.recycler_item
		);
		HttpUtil.getInstance()
				.with(((Fragment) mView).getContext().getApplicationContext())
				.type(HttpUtil.GET)
				.url("http://iu.snssdk.com/neihan/stream/mix/v1/")
				.callback(new EngineCallback() {
					@Override
					public void onPreExcute() {

					}

					@Override
					public void onError(Exception e) {

					}

					@Override
					public void onSuccess(String result) {
						Gson gson = new Gson();
						mTuijianBean = gson.fromJson(result, TuijianBean.class);

						Message m = Message.obtain();
						m.what = 1;
						mHandler.sendMessage(m);
					}
				}).excute();

		mView.setAdapter(mAdapter);
	}
}
