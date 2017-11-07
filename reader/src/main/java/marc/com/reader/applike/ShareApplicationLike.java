package marc.com.reader.applike;

import marc.com.reader.router.ShareUIRouter;
import marc.com.router.applike.IApplicationLike;
import marc.com.router.router.UI.UIRouter;

/**
 * Created by chengda
 * Date: 2017/10/26
 * Time: 10:14
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class ShareApplicationLike implements IApplicationLike {

	UIRouter mRouter = UIRouter.getInstance();
	ShareUIRouter mShareUIRouter = ShareUIRouter.getInstance();

	@Override
	public void onCreate() {
		mRouter.registerUI(mShareUIRouter);
	}

	@Override
	public void onStop() {
		mRouter.unregisterUI(mShareUIRouter);
	}
}
