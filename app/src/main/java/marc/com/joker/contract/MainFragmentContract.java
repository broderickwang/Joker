package marc.com.joker.contract;

import android.support.v7.widget.RecyclerView;

/**
 * Created by chengda
 * Date: 2017/10/19
 * Time: 16:02
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public interface MainFragmentContract {
	interface Model {
	}

	interface View {
		void setAdapter(RecyclerView.Adapter adapter);
	}

	interface Presenter {
		void createAdapter(String url);
	}
}
