package marc.com.baselibrary.net;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by chengda
 * Date: 2017/10/18
 * Time: 10:35
 * Version: 1.0
 * Description:
 * Email:wangchengda1990@gmail.com
 **/
public class OkHttpEngine implements IHttpEngine {

	private static OkHttpClient mHttpClient = new OkHttpClient();

	@Override
	public void post(Context context, String url, Map<String,Object>params, final EngineCallback callback) {

		RequestBody body = appendBody(params);

		final Request request = new Request.Builder()
				.url(url)
				.post(body)
				.tag(context)
				.build();

		mHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				callback.onError(e);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String result = response.body().string();
				callback.onSuccess(result);
			}
		});
	}

	@Override
	public void get(Context context,String url, Map<String,Object>params,final EngineCallback callback) {

		final Request request = new Request.Builder()
				.url(url)
				.get()
				.tag(context)
				.build();
		mHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				callback.onError(e);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				String result = response.body().string();
				callback.onSuccess(result);
			}
		});
	}

	private RequestBody appendBody(Map<String,Object> params){
		MultipartBody.Builder builder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM);
		addParam(builder,params);
		return builder.build();
	}

	private void addParam(MultipartBody.Builder builder,Map<String,Object> params){
		if(params!=null&&!params.isEmpty()){
			for (String key:params.keySet()){
				builder.addFormDataPart(key,params.get(key)+"");
				Object value = params.get(key);
				if(value instanceof File){
					File file = (File) value;
					builder.addFormDataPart(key,file.getName()
							,RequestBody.create(
									MediaType.parse(guessMineType(file.getAbsolutePath()))
									,file));
				}else if(value instanceof List){
					List<File> files = (List<File>) value;
					for (int i=0;i<files.size();i++){
						File file = files.get(i);
						builder.addFormDataPart(key,file.getName()
								,RequestBody.create(
										MediaType.parse(file.getAbsolutePath())
										,file
								));
					}
				}else{
					builder.addFormDataPart(key,value+"");
				}
			}
		}
	}

	private String guessMineType(String path){
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentTypeFor = fileNameMap.getContentTypeFor(path);
		if(contentTypeFor == null){
			contentTypeFor = "application/octet-stream";
		}
		return contentTypeFor;
	}
}
