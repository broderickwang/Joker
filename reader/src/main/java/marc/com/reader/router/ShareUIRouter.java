package marc.com.reader.router;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import marc.com.reader.ShareActivity;
import marc.com.router.router.UI.IComponentRouter;

/**
 * Created by chengda
 * Date: 2017/10/26
 * Time: 10:39
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class ShareUIRouter implements IComponentRouter {
	private static final String SCHME = "componentdemo";

	private static final String SHAREHOST = "share";

	private static String[] HOSTS = new String[]{SHAREHOST};

	private static ShareUIRouter instance = new ShareUIRouter();

	private ShareUIRouter() {

	}

	public static ShareUIRouter getInstance(){
		if (instance == null){
			synchronized (ShareUIRouter.class){
				if(instance == null){
					instance = new ShareUIRouter();
				}
			}
		}
		return instance;
	}

	@Override
	public boolean openUri(Context context, Uri uri, Bundle bundle) {
		if(uri==null || context==null){
			return true;
		}
		String host = uri.getHost();
		if(SHAREHOST.equalsIgnoreCase(host)){
			Intent intent = new Intent(context, ShareActivity.class);
			intent.putExtras(bundle==null?new Bundle():bundle);
			context.startActivity(intent);
			return true;
		}
		return false;
	}

	@Override
	public boolean openUri(Context context, String url, Bundle bundle) {
		if(TextUtils.isEmpty(url) || context == null){
			return true;
		}
		return openUri(context,Uri.parse(url),bundle);
	}

	@Override
	public boolean verifyUri(Uri uri) {
		return false;
	}
}
