package life.tain.community.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author tian
 * @date 2020/7/3
 */
@Service
public class OSSFile {

    @Value("${aliyun.oss.endpoint}")
    private  String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId ;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret ;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public String oss(InputStream inputStream,String filename) {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        if(StringUtils.isEmpty(filename)){
           return "文件名为空";
        }

        filename= UUID.randomUUID().toString()+"."+filename;

        ossClient.putObject(bucketName, "community/"+filename, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        return "https://"+bucketName+"."+endpoint+"/community/"+filename;
    }
}
