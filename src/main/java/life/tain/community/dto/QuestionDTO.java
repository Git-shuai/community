package life.tain.community.dto;

import life.tain.community.model.User;
import lombok.Data;

/**
 * @author tian
 * @date 2020/6/16
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
