package life.tain.community.dto;

import life.tain.community.model.User;
import lombok.Data;

/**
 * @author tian
 * @date 2020/7/2
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private User notifier;
    private String outerTitle;
    private String type;
}
