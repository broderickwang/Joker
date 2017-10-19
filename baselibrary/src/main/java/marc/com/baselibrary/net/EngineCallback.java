package marc.com.baselibrary.net;

/**
 * Created by chengda
 * Date: 2017/10/18
 * Time: 10:46
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public interface EngineCallback {

	void onPreExcute();

	void onError(Exception e);

	void onSuccess(String result);

}
