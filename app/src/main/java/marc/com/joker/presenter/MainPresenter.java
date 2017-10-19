package marc.com.joker.presenter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.Toast;
import com.google.gson.Gson;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import java.util.ArrayList;
import java.util.List;
import marc.com.baselibrary.net.EngineCallback;
import marc.com.baselibrary.net.HttpUtil;
import marc.com.joker.R;
import marc.com.joker.adapter.MainPageAdapter;
import marc.com.joker.bean.TabsBean;
import marc.com.joker.contract.MainContract;
import marc.com.joker.model.MainModel;

/**
 * Created by chengda
 * Date: 2017/10/19
 * Time: 11:17
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MainPresenter implements MainContract.Presenter {
	private MainContract.View mView;
	private PagerAdapter mPagerAdapter;
	private CommonNavigatorAdapter mCommonNavigatorAdapter;

	private List<MainModel> mMainModels;

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case 1:
					mCommonNavigatorAdapter.notifyDataSetChanged();
					mPagerAdapter.notifyDataSetChanged();
					break;
				default:
			}
		}
	};

	public MainPresenter(MainContract.View view) {
		mView = view;
		mMainModels = new ArrayList<>();
	}

	@Override
	public void createAdapter() {
		initData();
		mCommonNavigatorAdapter = new CommonNavigatorAdapter() {
			@Override
			public int getCount() {
				return mMainModels.size();
			}

			@Override
			public IPagerTitleView getTitleView(Context context,final int index) {
				ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
				clipPagerTitleView.setText(mMainModels.get(index).getTitle());
				clipPagerTitleView.setTextColor(Color.parseColor("#f2c4c4"));
				clipPagerTitleView.setClipColor(Color.GREEN);

				clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mView.setClickPageItem(index);
					}
				});

				return clipPagerTitleView;

			}

			@Override
			public IPagerIndicator getIndicator(Context context) {
				return null;
			}
		};

		mView.setCommonNavigtorAdapter(mCommonNavigatorAdapter);

		mPagerAdapter = new MainPageAdapter(((FragmentActivity)mView).getSupportFragmentManager(),mMainModels);
		mView.setPageAdapter(mPagerAdapter);

	}

	private void initData(){
		HttpUtil.getInstance()
				.with((Context) mView)
				.url("http://lf.snssdk.com/neihan/service/tabs/")
				.type(HttpUtil.GET)
				.callback(new EngineCallback() {
					@Override
					public void onPreExcute() {

					}

					@Override
					public void onError(Exception e) {
						Toast.makeText(((Context) mView).getApplicationContext(), e.getMessage()
								, Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onSuccess(String result) {
						Gson gson = new Gson();
						TabsBean bean = gson.fromJson(result, TabsBean.class);
						for (TabsBean.DataBean dataBean : bean.getData()) {
							MainModel mainModel = new MainModel(R.layout.test
									,dataBean.getName(),dataBean.getUrl());
							mMainModels.add(mainModel);
						}
						Message message = Message.obtain();
						message.what = 1;
						mHandler.sendMessage(message);
					}
				})
				.excute();
	}


}
