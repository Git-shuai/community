package life.tain.community.enums;

/**
 * @author tian
 * @date 2020/7/2
 */
public enum  NotificationStatusEnum {
    /**
     *
     */
    UNREAD(0),READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
