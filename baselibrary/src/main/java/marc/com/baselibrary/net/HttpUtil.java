package marc.com.baselibrary.net;


import android.content.Context;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengda
 * Date: 2017/10/18
 * Time: 10:36
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class HttpUtil {

	@IntDef({POST,GET})
	@Retention(RetentionPolicy.SOURCE)
	public @interface RequestMode{}

	public static  final int POST = 0x00011;
	public static final int GET = 0x00022;

	private static HttpUtil mHttpUtil;

	private Context mContext;

	private IHttpEngine mHttpEngine = new OkHttpEngine();

	private int mType = GET;

	private HashMap<String ,Object> mParams;

	private EngineCallback mCallback;

	private String mUrl;

	private HttpUtil() {
		mParams = new HashMap<>();
	}

	public static HttpUtil getInstance(){
		if (mHttpUtil == null){
			synchronized (HttpUtil.class){
				if(mHttpUtil == null){
					mHttpUtil = new HttpUtil();
				}
			}
		}
		return mHttpUtil;
	}

	public HttpUtil with(Context context){
		this.mContext = context;
		return this;
	}

	public HttpUtil exChange(IHttpEngine httpEngine){
		this.mHttpEngine = httpEngine;
		return this;
	}

	public HttpUtil type(@RequestMode int type){
		this.mType = type;
		return this;
	}

	public HttpUtil init(IHttpEngine engine){
		this.mHttpEngine = engine;
		return this;
	}

	public HttpUtil addParam(String key,Object value){
		mParams.put(key,value);
		return this;
	}

	public HttpUtil addParams(Map<String,Object> params){
		mParams.putAll(params);
		return this;
	}

	public HttpUtil callback(EngineCallback callback){
		this.mCallback = callback;
		return this;
	}

	public HttpUtil url(String url){
		this.mUrl = url;
		return this;
	}

	public void excute(){
		if(mType == POST){
			post(mContext,mUrl,mParams,mCallback);
		}else {
			get(mContext,mUrl,mParams,mCallback);
		}
	}
	private void get(Context context,String url, Map<String,Object>params,EngineCallback callback){
		mHttpEngine.get(context,url,params,callback);
	}

	private void post(Context context,String url, Map<String,Object>params,EngineCallback callback){
		mHttpEngine.post(context,url,params,callback);
	}
}
