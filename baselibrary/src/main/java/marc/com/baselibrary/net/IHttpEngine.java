package marc.com.baselibrary.net;

import android.content.Context;

import java.util.Map;

/**
 * Created by chengda
 * Date: 2017/10/18
 * Time: 10:17
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public interface IHttpEngine {
	public void post(Context context, String url, Map<String,Object> params, EngineCallback callback);

	public void get(Context context,String url, Map<String,Object>params,EngineCallback callback);
}
