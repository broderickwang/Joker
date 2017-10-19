package marc.com.joker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
import marc.com.joker.fragment.MainPageFragment;
import marc.com.joker.model.MainModel;

/**
 * Created by chengda
 * Date: 2017/10/19
 * Time: 11:19
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MainPageAdapter extends FragmentPagerAdapter {

	private List<MainModel> mMainModels;

	public MainPageAdapter(FragmentManager fm,List<MainModel> models) {
		super(fm);
		this.mMainModels = models;
	}

	@Override
	public Fragment getItem(int position) {
		return MainPageFragment.newInstance(mMainModels.get(position).getLayoutRes()
				,mMainModels.get(position).getTitle(),mMainModels.get(position).getUrl());
	}

	@Override
	public int getCount() {
		return mMainModels.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mMainModels.get(position).getTitle();
	}

}

