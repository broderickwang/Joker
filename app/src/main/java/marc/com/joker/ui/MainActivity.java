package marc.com.joker.ui;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import marc.com.commlibrary.ui.BaseActivity;
import marc.com.joker.R;
import marc.com.joker.contract.MainContract;
import marc.com.joker.presenter.MainPresenter;

public class MainActivity extends BaseActivity implements MainContract.View {

	private MainContract.Presenter mPresenter;
	private ViewPager mViewPager;
	private MagicIndicator mMagicIndicator;
	private CommonNavigator mCommonNavigator;

	@Override
	public void setContent() {
		setContentView(R.layout.activity_main);
	}

	@Override
	public void initHead() {

	}

	@Override
	public void initLayout() {
		mViewPager = (ViewPager) findViewById(R.id.vp_view);
		mMagicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
		mCommonNavigator = new CommonNavigator(this);
	}

	@Override
	public void initData() {
		mPresenter = new MainPresenter(this);
	}

	@Override
	public void releaseData() {

	}

	@Override
	public void loadData() {
		mPresenter.createAdapter();
	}

	@Override
	public void setPageAdapter(PagerAdapter pagerAdapter) {
		mViewPager.setAdapter(pagerAdapter);
		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				mMagicIndicator.onPageScrolled(position,positionOffset,positionOffsetPixels);
			}

			@Override
			public void onPageSelected(int position) {
				mMagicIndicator.onPageSelected(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				mMagicIndicator.onPageScrollStateChanged(state);
			}
		});
	}

	@Override
	public void setCommonNavigtorAdapter(CommonNavigatorAdapter commonNavigatorAdapter) {
		mCommonNavigator.setAdapter(commonNavigatorAdapter);
		mCommonNavigator.setAdjustMode(true);
		mMagicIndicator.setNavigator(mCommonNavigator);
	}

	@Override
	public void setClickPageItem(int index) {
		mViewPager.setCurrentItem(index);
	}
}
