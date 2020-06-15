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
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                return string.split("&")[0].split("=")[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public GitHubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            //System.out.println("string"+string);
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
