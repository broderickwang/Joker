package marc.com.router.router.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chengda
 * Date: 2017/10/26
 * Time: 11:05
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class UIRouter implements IUIRouter {

	List<IComponentRouter> uiRouters = new ArrayList<>();
	HashMap<IComponentRouter,Integer> priorities = new HashMap<>();

	private static UIRouter mRouter;

	private UIRouter() {
	}

	public static UIRouter getInstance(){
		if(mRouter == null){
			synchronized (UIRouter.class){
				if(mRouter == null){
					mRouter = new UIRouter();
				}
			}
		}
		return mRouter;
	}

	@Override
	public void registerUI(IComponentRouter router, int prioriy) {
		if(priorities.containsKey(router) && prioriy == priorities.get(router)){
			return;
		}
		removeOldUIRouter(router);
		int i = 0;
		for (IComponentRouter temp : uiRouters){
			Integer tp = priorities.get(temp);
			if(tp == null || tp < prioriy ){
				break;
			}
			i++;
		}
		uiRouters.add(i,router);
		priorities.put(router,prioriy);
	}

	@Override
	public void registerUI(IComponentRouter router) {
		registerUI(router,IUIRouter.PRIORITY_NORMAL);
	}

	@Override
	public void unregisterUI(IComponentRouter router) {
		for (int i=0;i<uiRouters.size();i++){
			if(router == uiRouters.get(i)){
				uiRouters.remove(i);
				priorities.remove(router);
				break;
			}
		}
	}

	public boolean openUri(Context context, Uri uri, Bundle bundle){
		boolean result = false;
		for (IComponentRouter tmp : uiRouters){
			if(tmp.verifyUri(uri) && tmp.openUri(context,uri,bundle)){
				result = true;
			}
		}
		return result;
	}

	public boolean openUri(Context context,String url,Bundle bundle){
		url = url.trim();
		if(!TextUtils.isEmpty(url)){
			if (!url.contains("://") &&
					(!url.startsWith("tel:") ||
							!url.startsWith("smsto:") ||
							!url.startsWith("file:"))) {
				url = "http://" + url;
			}
			Uri uri = Uri.parse(url);
			return openUri(context, uri, bundle);
		}
		return true;
	}

	private void removeOldUIRouter(IComponentRouter router){
		Iterator<IComponentRouter> iterator = uiRouters.iterator();
		while (iterator.hasNext()){
			IComponentRouter tmp = iterator.next();
			if(tmp == router){
				iterator.remove();
				priorities.remove(tmp);
			}
		}
	}

	public boolean verifyUri(Uri uri) {
		for (IComponentRouter temp : uiRouters) {
			if (temp.verifyUri(uri)) {
				return true;
			}
		}
		return false;
	}
}
