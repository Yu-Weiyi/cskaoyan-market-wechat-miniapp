package happy.coding.exception;

import happy.coding.constant.ErrorCodeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.exception
 * @name BaseException
 * @description 基础自定义异常类。
 * @since 2024-08-31 10:51
 */
@Data
@NoArgsConstructor
public class BaseException extends Exception {

    private int errno;
    private String errmsg;

    public BaseException(int errno, String errmsg) {

        super(String.format("Ex[%d]: %s", errno, errmsg));
        this.errno = errno;
        this.errmsg = errmsg;
    }

    public BaseException(ErrorCodeConstant errorCodeConstant) {

        this(errorCodeConstant.getErrno(), errorCodeConstant.getErrmsg());
    }
}
