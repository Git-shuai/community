package life.tain.community.controller;

import life.tain.community.dto.AccessTokenDTO;
import life.tain.community.dto.GitHubUser;
import life.tain.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author tian
 * @date 2020/6/10
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("Iv1.160de4e8641db070");
        accessTokenDTO.setClient_secret("2e3bb0914c73af54b801e493162ca637300f3a13");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);

        System.out.println("id="+user.getId());
        gitHubProvider.getAccessToken(accessTokenDTO);
        return "index";
    }
}
