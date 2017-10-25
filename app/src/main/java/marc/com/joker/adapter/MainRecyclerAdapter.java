package marc.com.joker.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import marc.com.joker.R;
import marc.com.joker.bean.TuijianBean;
import marc.com.multrecycleadapter.CommonRecycleAdapter;
import marc.com.multrecycleadapter.ViewHolder;

/**
 * Created by chengda
 * Date: 2017/10/19
 * Time: 16:47
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MainRecyclerAdapter extends CommonRecycleAdapter<TuijianBean.DataBeanX.DataBean>{
	public MainRecyclerAdapter(Context context, List<TuijianBean.DataBeanX.DataBean> datas, int layoutId) {
		super(context, datas, layoutId);
	}

	@Override
	public void convert(ViewHolder holder, TuijianBean.DataBeanX.DataBean item) {
		holder.setImageByUrl(R.id.item_img
				, new ViewHolder.ImageLoader("http://p3.pstatp.com/medium/3e810005af872c403b78") {
			@Override
			public void displayImage(Context context, ImageView imageView, String imagePath) {
				Glide.with(context).load(imagePath).into(imageView);
			}
		});
	}

	@Override
	public int getLayoutId(Object item, int position) {
		return 0;
	}
}
