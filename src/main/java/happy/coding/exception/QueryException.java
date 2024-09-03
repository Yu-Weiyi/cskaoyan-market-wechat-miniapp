package happy.coding.exception;

import happy.coding.constant.ErrorCodeConstant;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.exception
 * @name QueryException
 * @description Query exception.
 * @since 2024-09-03 22:34
 */
public class QueryException extends BaseException {

    public QueryException(ErrorCodeConstant errorCodeConstant) {

        super(errorCodeConstant);
    }
}
