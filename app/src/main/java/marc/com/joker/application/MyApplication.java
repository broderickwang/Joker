package marc.com.joker.application;

import android.app.Application;

import marc.com.router.router.Router;

/**
 * Created by chengda
 * Date: 2017/10/26
 * Time: 10:10
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		Router.registComponent("marc.com.reader.applike.ShareApplicationLike");
	}


}
