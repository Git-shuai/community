package life.tain.community.provider;

import com.alibaba.fastjson.JSON;
import life.tain.community.dto.AccessTokenDTO;
import life.tain.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author tian
 * @date 2020/6/10
 */
@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        String url = "https://github.com/login/oauth/access_token";
        try {
            String s = post(url, accessTokenDTO);

            System.out.println(s);
            System.out.println(s.split("&")[0].split("=")[1]);
            return s.split("&")[0].split("=")[1];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    public GitHubUser getUser(String accessToken) {
        String url = "https://api.github.com/user";
        try {
            String string = run(url, accessToken);
            return JSON.parseObject(string, GitHubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }


    public static final MediaType MEDIA_TYPE
            = MediaType.get("application/json; charset=utf-8");


    String post(String url, AccessTokenDTO json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(json), MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    String run(String url, String accessToken) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "token " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
