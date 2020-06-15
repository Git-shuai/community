package life.tain.community.controller;

import life.tain.community.dto.AccessTokenDTO;
import life.tain.community.dto.GitHubUser;
import life.tain.community.mapper.UserMapper;
import life.tain.community.model.User;
import life.tain.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @author tian
 * @date 2020/6/10
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        //System.out.println(accessToken);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);

        //System.out.println(gitHubUser.getName());
        if (gitHubUser != null) {
            User user = new User();
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreat(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreat());

            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            //登录成功
//            request.getSession().setAttribute("user", gitHubUser);
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }

    }
}
