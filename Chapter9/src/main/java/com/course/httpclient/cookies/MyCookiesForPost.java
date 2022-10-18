package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        //从配置中获取url
        String uri = bundle.getString("getCookies.uri");
        System.out.println(uri);
        String testurl = this.url + uri;

        //测试逻辑代码
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
//        System.out.println(result);


        //获取cookies信息
        //实际运用应该把cookie取出作为公共变量
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
//            System.out.println("cookie = " + name + " : " + value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException{
        String uri = bundle.getString("test.post.with.coolies");
        String testurl = this.url + uri;
        //申明client obj
        DefaultHttpClient client = new DefaultHttpClient();

        //申明post方法
        HttpPost post = new HttpPost(testurl);

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name", "laowang");
        param.put("sex", "man");

        //设置请求头
        post.setHeader("Content-Type","application/json");
        //把参数信息添加到方法内
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //申明对象 存储相应结果
        String result;

        //设置cookies信息
        client.setCookieStore(this.store);

        //执行post方法
        HttpResponse response = client.execute(post);

        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("结果是:"+result);
        //处理结果，判断返回结果是否复合预期

        //将返回相应结果字符串转化为json Obj
        JSONObject resultJson = new JSONObject(result);
        //判断结果返回的值
        //获取到结果值
        String success = resultJson.get("res_test_key").toString();
        String status = resultJson.get("res_test_key_status").toString();

        Assert.assertEquals("10000",success);
        Assert.assertEquals("1",status);

    }
}
