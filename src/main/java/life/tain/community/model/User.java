package life.tain.community.model;

import lombok.Data;

/**
 * @author tian
 * @date 2020/6/11
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreat;
    private Long gmtModified;
    private String avatarUrl;


}
