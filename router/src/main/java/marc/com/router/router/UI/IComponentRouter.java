package marc.com.router.router.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by chengda
 * Date: 2017/10/26
 * Time: 10:37
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public interface IComponentRouter {
	public boolean openUri(Context context, Uri uri, Bundle bundle);
	public boolean openUri(Context context,String url,Bundle bundle);
	public boolean verifyUri(Uri uri);
}
