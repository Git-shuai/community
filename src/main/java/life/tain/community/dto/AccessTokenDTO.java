package life.tain.community.dto;

import lombok.Data;

/**
 * @author tian
 * @date 2020/6/10
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;


}
