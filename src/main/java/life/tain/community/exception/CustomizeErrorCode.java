package life.tain.community.exception;

/**
 * @author tian
 * @date 2020/6/25
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    /**
     * 问题不存在异常
     */
    QUESTION_NOT_FOUND("你找的问题不存在，或者已经删除了，换个试试！");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
