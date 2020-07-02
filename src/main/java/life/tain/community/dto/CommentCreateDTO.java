package life.tain.community.dto;

import lombok.Data;

/**
 * @author tian
 * @date 2020/6/25
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
