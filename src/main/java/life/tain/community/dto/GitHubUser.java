package life.tain.community.dto;

import lombok.Data;

/**
 * @author tian
 * @date 2020/6/10
 */

@Data
public class GitHubUser {

    private Long id;

    private String name;

    private String bio;

    private String avatar_url;

}
