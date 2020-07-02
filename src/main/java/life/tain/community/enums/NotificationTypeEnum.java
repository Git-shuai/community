package life.tain.community.enums;

/**
 * @author tian
 * @date 2020/7/2
 */
public enum NotificationTypeEnum {
    /**
     *
     */
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论")
    ;
    private final int type;
    private final String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int status, String name) {
        this.type = status;
        this.name = name;
    }
}
