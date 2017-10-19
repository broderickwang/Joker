package marc.com.joker.contract;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;

/**
 * Created by chengda
 * Date: 2017/10/19
 * Time: 11:17
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public interface MainContract {
	interface Model {
	}

	interface View {
		void setPageAdapter(PagerAdapter pagerAdapter);

		void setCommonNavigtorAdapter(CommonNavigatorAdapter commonNavigatorAdapter);

		void setClickPageItem(int index);
	}

	interface Presenter {
		void createAdapter();
	}
}
