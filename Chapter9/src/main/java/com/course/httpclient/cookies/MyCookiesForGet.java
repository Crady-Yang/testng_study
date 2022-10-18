package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

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
        String testurl = this.url + uri;

        //测试逻辑代码
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);


        //获取cookies信息
        //实际运用应该把cookie取出作为公共变量
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie = " + name + " : " + value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException{
        String uri = bundle.getString("test.get.with.coolies");
        String testurl = this.url + uri;
        HttpGet get = new HttpGet(testurl);
        DefaultHttpClient client = new DefaultHttpClient();


        //设置cookies信息,携带cookie访问
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);

        //获取http状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("statutesCode:" + statusCode);

        if(statusCode == 200){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }

    }
}
