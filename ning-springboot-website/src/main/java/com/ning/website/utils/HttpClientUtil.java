package com.ning.website.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;


@Slf4j
public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 30000;

    public HttpClientUtil() {
    }

    public static CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connMgr).setConnectionManagerShared(true).build();
        return httpClient;
    }

    public static String doGet(String url) throws Exception {
        return doGet(url, new HashMap());
    }

    public static String doGet(String url, Map<String, String> params) throws Exception {
        String param = doGetParam(params);
        url = url.indexOf("?") > -1 ? url + "&" + param : url + "?" + param;
        String httpStr = null;
        CloseableHttpClient httpclient = getHttpClient();
        CloseableHttpResponse response = null;
        log.info("HttpUtil doGet,url ={},param ={}", url, JSONObject.toJSONString(params));

        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            response = httpclient.execute(httpGet);
            httpStr = getResponse(url, httpStr, response);
        } catch (IOException var10) {
            log.error("请求失败, url = {}", new Object[]{url}, var10);
            throw new RuntimeException("请求失败！url=[" + url + "],请求参数:params:" + JSONObject.toJSONString(param), var10);
        } finally {
            closeIO(url, response, httpclient);
        }

        return httpStr;
    }

    private static String doGetParam(Map<String, String> bean) {
        StringBuilder result = new StringBuilder();
        Iterator it = bean.keySet().iterator();

        String key;
        while (it.hasNext()) {
            key = (String) it.next();
            if (key != null && bean.get(key) != null) {
                result.append(key);
                result.append("=");
                result.append((String) bean.get(key));
                result.append("&");
            }
        }

        key = result.toString();
        log.debug("参数:" + key);
        return key.endsWith("&") ? key.substring(0, key.length() - 1) : key;
    }

    public static String doPost(String url) throws Exception {
        return doPost(url, (Map) (new HashMap()));
    }

    public static <K, V> String doPost(String url, Map<K, V> params) {
        CloseableHttpClient httpClient = getHttpClient();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        log.info("HttpUtil doPost,url ={},param ={}", url, JSONObject.toJSONString(params));

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList(params.size());
            Iterator var7 = params.entrySet().iterator();

            while (var7.hasNext()) {
                Map.Entry<K, V> entry = (Map.Entry) var7.next();
                NameValuePair pair = new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue());
                pairList.add(pair);
            }

            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            httpStr = getResponse(url, httpStr, response);
            return httpStr;
        } catch (IOException var13) {
            log.error("请求失败, url = {}", new Object[]{url}, var13);
            throw new RuntimeException("请求失败！url=[" + url + "],请求参数:params:" + JSONObject.toJSONString(params), var13);
        } finally {
            closeIO(url, response, httpClient);
        }
    }

    private static String getResponse(String url, String httpStr, CloseableHttpResponse response) throws IOException {
        if (response != null) {
            if (200 != response.getStatusLine().getStatusCode()) {
                throw new RuntimeException("请求失败！url=[" + url + "]，请求状态码code=" + response == null ? null : response.getStatusLine().getStatusCode() + "");
            }

            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                httpStr = EntityUtils.toString(resEntity, "utf-8");
            }
        }

        return httpStr;
    }

    public static String doPost(String url, String json) {
        CloseableHttpClient httpClient = getHttpClient();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        httpStr = getPostConfig(url, json, httpClient, httpStr, httpPost, (CloseableHttpResponse) response);
        return httpStr;
    }

    public static String doPost(String url, String json, Map<String, String> header) {
        CloseableHttpClient httpClient = getHttpClient();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(url);
        Iterator iterator;
        if (header != null) {
            iterator = header.keySet().iterator();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                httpPost.addHeader(key, (String) header.get(key));
            }
        }

        CloseableHttpResponse closeableHttpResponse = null;
        httpStr = getPostConfig(url, json, httpClient, httpStr, httpPost, closeableHttpResponse);
        return httpStr;
    }

    public static String doPost(String url, Map<String, String> params, File file) {
        String result = "";
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("file", new FileBody(file));
        Iterator var5 = params.entrySet().iterator();

        while (var5.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) var5.next();
            if (entry.getValue() != null) {
                builder.addTextBody((String) entry.getKey(), (String) entry.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));
            }
        }

        HttpEntity reqEntity = builder.build();
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);
        httppost.setEntity(reqEntity);
        HttpResponse response = null;

        try {
            response = httpclient.execute(httppost);
            if (200 == response.getStatusLine().getStatusCode()) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity);
                }
            }

            return result;
        } catch (IOException var10) {
            log.error("请求失败, url = {}", new Object[]{url}, var10);
            throw new RuntimeException("请求失败！url=[" + url + "],请求参数:params:" + JSONObject.toJSONString(params), var10);
        }
    }

    public static String doPost(String url, Map<String, String> params, Map<String, File> files) throws Exception {
        String result = "";
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if (files != null && files.size() > 0) {
            Set<Map.Entry<String, File>> entries = files.entrySet();
            Iterator var6 = entries.iterator();

            while (var6.hasNext()) {
                Map.Entry<String, File> entry = (Map.Entry) var6.next();
                builder.addPart((String) entry.getKey(), new FileBody((File) entry.getValue()));
            }
        }

        Iterator var10 = params.entrySet().iterator();

        while (var10.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) var10.next();
            if (entry.getValue() != null) {
                builder.addTextBody((String) entry.getKey(), (String) entry.getValue(), ContentType.TEXT_PLAIN.withCharset("UTF-8"));
            }
        }

        HttpEntity reqEntity = builder.build();
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(requestConfig);
        httppost.setEntity(reqEntity);
        HttpResponse response = httpclient.execute(httppost);
        if (200 == response.getStatusLine().getStatusCode()) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
        }

        return result;
    }

    private static String getPostConfig(String url, String json, CloseableHttpClient httpClient, String httpStr,
                                        HttpPost httpPost, CloseableHttpResponse response) {
        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            log.info("HttpUtil getPostConfig,url ={},json ={}", url, json);
            response = httpClient.execute(httpPost);
            httpStr = getResponse(url, httpStr, response);
        } catch (IOException var10) {
            log.error("请求失败, url = {}", new Object[]{url}, var10);
            throw new RuntimeException("请求失败！url=[" + url + "],请求参数:params:" + json, var10);
        } finally {
            closeIO(url, response, httpClient);
        }

        return httpStr;
    }

    public static String doPostSSL(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = createSSLHttpClient();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList(params.size());
            Iterator var7 = params.entrySet().iterator();

            while (var7.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry) var7.next();
                NameValuePair pair = new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue());
                pairList.add(pair);
            }

            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            response = httpClient.execute(httpPost);
            httpStr = getResponse(url, httpStr, response);
            return httpStr;
        } catch (Exception var13) {
            log.error("请求失败, url = {}", new Object[]{url}, var13);
            throw new RuntimeException("请求失败！url=[" + url + "],请求参数:params:" + JSONObject.toJSONString(params), var13);
        } finally {
            closeIO(url, response, httpClient);
        }
    }

    public static String doGetSSL(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = createSSLHttpClient();
        String param = doGetParam(params);
        url = url.indexOf("?") > -1 ? url + "&" + param : url + "?" + param;
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            httpStr = getResponse(url, httpStr, response);
        } catch (Exception var11) {
            log.error("请求失败, url = {}", new Object[]{url}, var11);
            throw new RuntimeException("请求失败,url=[" + url + "],请求参数:params:" + JSONObject.toJSONString(params), var11);
        } finally {
            closeIO(url, response, httpClient);
        }

        return httpStr;
    }

    public static String doPostSSL(String url, String json) {
        CloseableHttpClient httpClient = createSSLHttpClient();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String httpStr = null;
        httpStr = getPostConfig(url, json, httpClient, httpStr, httpPost, (CloseableHttpResponse) response);
        return httpStr;
    }

    private static CloseableHttpClient createSSLHttpClient() {
        CloseableHttpClient httpClient = null;
        SSLContext ctx = null;

        try {
            ctx = SSLContext.getInstance("SSL");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init((KeyManager[]) null, new TrustManager[]{tm}, new SecureRandom());
            SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(ctx, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            Registry socketFactoryRegistry = RegistryBuilder.create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", ssf)
                    .build();
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
            return httpClient;
        } catch (Exception var6) {
            log.error("createSSLHttpClient 调用失败", var6);
            throw new RuntimeException("请求失败, createSSLHttpClient 调用失败", var6);
        }
    }

    public static Header[] createHeaders(Map<String, String> map) {
        List<Header> headerList = new ArrayList(map.size());
        Iterator var2 = map.entrySet().iterator();

        while (var2.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) var2.next();
            headerList.add(new BasicHeader((String) entry.getKey(), (String) entry.getValue()));
        }

        return (Header[]) headerList.toArray(new BasicHeader[headerList.size()]);
    }

    private static void closeIO(String url, CloseableHttpResponse response, CloseableHttpClient httpClient) {
        try {
            if (null != response) {
                EntityUtils.consume(response.getEntity());
            }

            if (null != httpClient) {
                httpClient.close();
            }

        } catch (IOException var4) {
            log.error("关闭Http IO 异常", new Object[]{url}, var4);
            throw new RuntimeException("关闭Http IO 异常");
        }
    }

    static {
        SSLConnectionSocketFactory sslsf = null;

        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException var3) {
            log.error("创建SSLConnectionSocketFactory失败", var3);
        }

        Registry socketFactoryRegistry = RegistryBuilder.create().register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
        connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connMgr.setMaxTotal(200);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(30000);
        configBuilder.setSocketTimeout(30000);
        configBuilder.setConnectionRequestTimeout(30000);
        requestConfig = configBuilder.build();
    }
}
