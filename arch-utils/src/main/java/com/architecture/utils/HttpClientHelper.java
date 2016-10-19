//package com.arch.base.common.net;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpException;
//import org.apache.http.HttpRequest;
//import org.apache.http.HttpRequestInterceptor;
//import org.apache.http.ParseException;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.config.SocketConfig;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.impl.client.*;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.impl.cookie.BasicClientCookie;
//import org.apache.http.protocol.HttpContext;
//
//import java.io.IOException;
//import java.util.Map;
//
///**
// * @FileName HttpClientHelper.java
// * @Description:
// * @Date 2016年3月18日 下午7:54:55
// * @author Fish Exp
// * @version V1.0
// * 
// */
//public class HttpClientHelper {
//	public static void get() {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		try {
//			// 创建httpget.
//			HttpPost httpget = new HttpPost(
//					"http://localhost:8080/solr/mycore/dataimport?command=full-import&clean=false&commit=true&wt=json&indent=true&entity=solr_test&verbose=false&optimize=false&debug=false&start="
//							+ i + "&size=2000");
//			i = i + 2000;
//			// 执行get请求.
//			CloseableHttpResponse response = httpclient.execute(httpget);
//			try {
//				// 获取响应实体
//				HttpEntity entity = response.getEntity();
//				// 打印响应状态
//				System.out.println(response.getStatusLine());
//				System.out.println(response.getStatusLine().getStatusCode());
//				// if (entity != null) {
//				// // 打印响应内容长度
//				// System.out.println("Response content length: " +
//				// entity.getContentLength());
//				// // 打印响应内容
//				// System.out.println("Response content: " +
//				// EntityUtils.toString(entity));
//				// }
//			} finally {
//				response.close();
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			// 关闭连接,释放资源
//			try {
//				httpclient.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
