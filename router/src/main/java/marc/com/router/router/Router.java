package marc.com.router.router;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

import marc.com.router.applike.IApplicationLike;

/**
 * Created by chengda
 * Date: 2017/10/26
 * Time: 09:24
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class Router {
	private static Router mUIRouter;
	private static HashMap<String,IApplicationLike> components = new HashMap<>();

	private Router() {
	}

	public static Router getInstance(){
		if(mUIRouter == null){
			synchronized (Router.class){
				if(mUIRouter == null){
					mUIRouter = new Router();
				}
			}
		}
		return mUIRouter;
	}

	public boolean openUri(Context context, Uri uri, Bundle bundle){
		boolean result = false;

		return result;
	}

	public static void registComponent(@Nullable String classname) {
		if(TextUtils.isEmpty(classname)){
			return;
		}
		if(components.containsKey(classname)){
			return;
		}
		try {
			Class clz = Class.forName(classname);
			IApplicationLike applicationLike = (IApplicationLike) clz.newInstance();
			applicationLike.onCreate();
			components.put(classname,applicationLike);
		}catch (Exception e){
			Log.e("TAG", "registComponent: ",e);
		}
	}

	public static void unregisterComponent(@Nullable String classname){
		if(TextUtils.isEmpty(classname)){
			return;
		}
		if(!components.containsKey(classname)){
			return;
		}
		if(components.keySet().contains(classname)){
			components.get(classname).onStop();
			components.remove(classname);
			return;
		}
		try {
			Class clz = Class.forName(classname);
			IApplicationLike applicationLike = (IApplicationLike) clz.newInstance();
			applicationLike.onStop();
			components.remove(classname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
