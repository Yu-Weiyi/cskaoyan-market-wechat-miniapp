package happy.coding.handler;

import happy.coding.bean.vo.BaseRespVo;
import happy.coding.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.handler
 * @name GlobalExceptionHandler
 * @description 捕获全局异常（这次真的是全局的，你们要相信我呜呜呜）。
 * @since 2024-08-31 10:47
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @name handleException
     * @description 全局异常处理。
     * @param ex java.lang.Exception
     * @return BaseRespVo
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-08-31, Sat, 12:37, CST
     */
    @ExceptionHandler
    public BaseRespVo handleException(BaseException ex) {

        ex.printStackTrace();
        log.debug(ex.getMessage());
        return BaseRespVo.error(ex);
    }
}
