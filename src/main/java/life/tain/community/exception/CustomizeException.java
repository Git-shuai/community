package life.tain.community.exception;

/**
 * @author tian
 * @date 2020/6/25
 */

public class CustomizeException extends RuntimeException{

    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
       this.message= errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
