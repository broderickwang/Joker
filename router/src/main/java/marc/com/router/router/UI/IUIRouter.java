package marc.com.router.router.UI;

/**
 * Created by chengda
 * Date: 2017/10/26
 * Time: 09:25
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public interface IUIRouter {
	int PRIORITY_NORMAL = 0;
	int PRIORITY_LOW = -1000;
	int PRIORITY_HEIGHT = 1000;

	void registerUI(IComponentRouter router,int prioriy);

	void registerUI(IComponentRouter router);

	void unregisterUI(IComponentRouter router);
}
