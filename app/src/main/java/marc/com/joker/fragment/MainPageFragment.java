package marc.com.joker.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;

import marc.com.joker.R;
import marc.com.joker.contract.MainFragmentContract;
import marc.com.joker.presenter.MainFragmentPresenter;

/**
 * Created by chengda
 * Date: 2017/10/19
 * Time: 14:49
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MainPageFragment extends Fragment implements MainFragmentContract.View {
	private String mKey;
	private String mUrl;
	private int mLayoutRes;
	private RecyclerView mRecyclerView;
	private MainFragmentContract.Presenter mPresenter;

	public static MainPageFragment newInstance(int res,String key, String url) {

		Bundle args = new Bundle();
		args.putString("key", key);
		args.putString("url",url);
		args.putInt("layout",res);
		MainPageFragment fragment = new MainPageFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if(args!=null) {
			mKey = args.getString("key");
			mUrl = args.getString("url");
			mLayoutRes = args.getInt("layout");
			Log.d("TAG", "onCreate: "+mKey+"-"+mUrl);
		}
		mPresenter = new MainFragmentPresenter(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.main_fragment,container,false);
		ViewStub viewStub = v.findViewById(R.id.viewStub);
		viewStub.setLayoutResource(mLayoutRes);
		viewStub.inflate();
		mRecyclerView = viewStub.findViewById(R.id.content_list);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));

		mPresenter.createAdapter();

		return v;
	}

	@Override
	public void setAdapter(RecyclerView.Adapter adapter) {
		mRecyclerView.setAdapter(adapter);
	}
}
