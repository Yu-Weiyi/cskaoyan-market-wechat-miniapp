package happy.coding.constant;

import lombok.Getter;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-one
 * @package constant
 * @name ErrorCodeConstant
 * @description 错误码与错误信息常量。
 * @since 2024-08-31 10:54
 */
@Getter
public enum ErrorCodeConstant {

    UNKNOWN_ERROR(-1, "未知错误"),
    OK(0, "成功");

    private final int errno;
    private final String errmsg;

    ErrorCodeConstant(int errno, String errmsg) {

        this.errno = errno;
        this.errmsg = errmsg;
    }
}
