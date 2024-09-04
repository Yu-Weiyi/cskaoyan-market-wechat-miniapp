package happy.coding.exception;

import happy.coding.constant.ErrorCodeConstant;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.exception
 * @name SystemException
 * @description System exception.
 * @since 2024-09-04 16:06
 */
public class SystemException extends BaseException {

    public SystemException(ErrorCodeConstant errorCodeConstant) {

        super(errorCodeConstant);
    }
}
