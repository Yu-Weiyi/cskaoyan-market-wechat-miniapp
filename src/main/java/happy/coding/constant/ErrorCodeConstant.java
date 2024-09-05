package happy.coding.constant;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public enum ErrorCodeConstant {

    API_NOT_IMPLEMENTED(-2, "接口未实现"),
    UNKNOWN_ERROR(-1, "未知错误"),
    OK(0, "成功"),

    INVALID_PARAM(1, "参数错误"),
    INVALID_TOKEN_SUBJECT(2, "Token 主题错误"),
    INVALID_TOKEN_ISSUER(3, "Token 签发者错误"),
    TOKEN_EXPIRED(4, "Token 已过期"),
    INVALID_TOKEN(5, "Token 已失效"),
    USER_NOT_FOUND(6, "用户不存在"),
    PASSWORD_NOT_MATCH(7, "密码不匹配"),
    INVALID_USER_STATUS(8, "用户状态无效"),
    INVALID_USER_LEVEL(9, "用户等级无效"),
    QUERY_FAILED(10, "查询失败"),
    SYSTEM_GLOBAL_PARAM_ERROR(11, "系统全局参数错误");



    private final int errno;
    private final String errmsg;
}
