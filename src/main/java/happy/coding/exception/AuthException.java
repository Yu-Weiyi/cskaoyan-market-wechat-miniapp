package happy.coding.exception;

import happy.coding.constant.ErrorCodeConstant;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.exception
 * @name AuthException
 * @description 认证异常。
 * @since 2024-09-02 02:16
 */
public class AuthException extends BaseException {

    public AuthException(ErrorCodeConstant errorCodeConstant) {

        super(errorCodeConstant);
    }
}
