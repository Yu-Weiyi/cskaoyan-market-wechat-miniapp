package happy.coding.exception;

import happy.coding.constant.ErrorCodeConstant;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.exception
 * @name StatusException
 * @description Status exception.
 * @since 2024-09-05 20:19
 */
public class StatusException extends BaseException {

    public StatusException(ErrorCodeConstant errorCodeConstant) {

        super(errorCodeConstant);
    }
}
