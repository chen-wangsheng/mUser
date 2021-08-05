package com.chinasoft.user.utils;


import com.chinasoft.common.utils.FileUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.AppendObjectRequest;
import com.qcloud.cos.model.AppendObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @Author: VanceChen
 * @Date: 2021/8/4 15:58
 * @Description: 腾讯云cos
 **/
@Slf4j
public class TencentCOSClient {

    private static String secretId = "AKIDzrXzDC2g8NOAi27xazBAwWs1xhDUYP2V";

    private static String secretKey = "IMCoCPPBi070e4HG0VlmY6ORK1Ujj3HK";

    private static String bucketName = "cos-1258886224";

    private static String regionName = "ap-guangzhou";

    private static String address = "https://cos-1258886224.cos.ap-guangzhou.myqcloud.com";

    public static void putFileObject() {

        // SECRETID和SECRETKEY请登录访问管理控制台进行查看和管理
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(regionName);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        StringBuilder sb = new StringBuilder("/avatar/");
//        String key = "/avatar/default.jpg";
        try {
//            String filename = file.getOriginalFilename();
            File localFile = new File("G:\\123.jpg");
            String fileUUIDName = UUID.randomUUID().toString();
            String key = sb.append(fileUUIDName.substring(0, 10)).append(".jpg").toString();

            AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName, key, localFile);
            appendObjectRequest.setPosition(0L);
            AppendObjectResult appendObjectResult = cosClient.appendObject(appendObjectRequest);
            long nextAppendPosition = appendObjectResult.getNextAppendPosition();
            System.out.println(nextAppendPosition);
            /*
            localFile = new File("2M.txt");
            appendObjectRequest = new AppendObjectRequest(bucketName, key, localFile);
            appendObjectRequest.setPosition(nextAppendPosition);
            appendObjectResult = cosClient.appendObject(appendObjectRequest);
            nextAppendPosition = appendObjectResult.getNextAppendPosition();
            System.out.println(nextAppendPosition);*/
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // 关闭客户端
        cosClient.shutdown();

    }

    public static String putFile(MultipartFile multipartFile) {

        // SECRETID和SECRETKEY请登录访问管理控制台进行查看和管理
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(regionName);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        String key = null;
        try {
//            File file = FileUtil.multipartFileToFile(multipartFile);
            // 获取文件的后缀名
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//            log.debug(suffixName);

            String fileUrl = FileUtil.approvalFile(multipartFile);
            File localFile = new File(fileUrl);
            // 文件上传后的路径
            String fileUUIDName = UUID.randomUUID().toString();
            StringBuilder sb = new StringBuilder("/avatar/");
            key = sb.append(fileUUIDName.substring(0, 10)).append(".jpeg").toString();

            AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketName, key, localFile);
            appendObjectRequest.setPosition(0L);
            AppendObjectResult appendObjectResult = cosClient.appendObject(appendObjectRequest);
            long nextAppendPosition = appendObjectResult.getNextAppendPosition();
            // 删除本地图片
            FileUtil.delteTempFile(localFile);
//            System.out.println(nextAppendPosition);
            /*
            localFile = new File("2M.txt");
            appendObjectRequest = new AppendObjectRequest(bucketName, key, localFile);
            appendObjectRequest.setPosition(nextAppendPosition);
            appendObjectResult = cosClient.appendObject(appendObjectRequest);
            nextAppendPosition = appendObjectResult.getNextAppendPosition();
            System.out.println(nextAppendPosition);*/
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭客户端
        cosClient.shutdown();
        return address + key;

    }


    public static void main(String[] args) {
        putFileObject();
    }



}
