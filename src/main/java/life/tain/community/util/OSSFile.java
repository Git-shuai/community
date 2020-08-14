package life.tain.community.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author tian
 * @date 2020/7/3
 */
@Service
public class OSSFile {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;



    public String oss(InputStream inputStream, String filename) {


        if (StringUtils.isEmpty(filename)) {
            return "文件名为空";
        }
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        filename = UUID.randomUUID().toString() + "." + filename;

        ossClient.putObject(bucketName, "community/" + filename, inputStream);

        String url = getUrl("community/"+filename);
        // 关闭OSSClient。
        ossClient.shutdown();

        return url;
    }


    /**
     * 获得url链接
     *
     * @param key
     * @return
     */
    public String getUrl(String key) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);

        ossClient.shutdown();

        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
