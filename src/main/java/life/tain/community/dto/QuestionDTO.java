package life.tain.community.dto;

import life.tain.community.model.User;
import lombok.Data;

/**
 * @author tian
 * @date 2020/6/16
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
