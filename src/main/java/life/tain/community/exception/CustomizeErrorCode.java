package life.tain.community.exception;

/**
 * @author tian
 * @date 2020/6/25
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    /**
     * 问题不存在异常
     */
    QUESTION_NOT_FOUND(2001,"你找的问题不存在，或者已经删除了，换个试试！"),

    TARGET_PARAM_NOT_FOUND(2002,"未选择任何评论和回复！"),

    NO_LOGIN(2003,"请先登录,在进行评论"),

    SYS_ERROR(2004,"服务器跑丢了"),

    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),

    COMMENT_NOT_FOUND(2006,"你回复的评论已经和谐，换个试试？"),

    COMMENT_IS_EMPTY(2007,"输入的评论不能为空")

    ;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;




    CustomizeErrorCode(Integer code,String message) {
        this.code=code;
        this.message = message;
    }
}
