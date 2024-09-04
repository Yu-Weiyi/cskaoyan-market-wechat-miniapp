package happy.coding.exception;

import happy.coding.constant.ErrorCodeConstant;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.exception
 * @name ParamException
 * @description Param Exception.
 * @since 2024-09-04 13:28
 */
public class ParamException extends BaseException {

    public ParamException(ErrorCodeConstant errorCodeConstant) {

        super(errorCodeConstant);
    }
}
