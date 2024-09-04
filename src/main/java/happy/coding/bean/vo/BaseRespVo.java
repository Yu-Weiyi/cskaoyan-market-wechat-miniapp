package happy.coding.bean.vo;


import com.github.pagehelper.PageInfo;
import happy.coding.exception.BaseException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//基本的页面返回的数据
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRespVo<T> {

    /**
     * @name data
     * @type T
     * @description 数据
     */
    @Schema(description="数据")
    T data;
    /**
     * @name errmsg
     * @type String
     * @description 错误信息
     */
    @Schema(description="错误信息")
    String errmsg;
    /**
     * @name errno
     * @type int
     * @description 错误码
     */
    @Schema(description="错误码")
    int errno;

    public static <V> BaseRespVo<V> success(V successData){
        return BaseRespVo.<V>builder()
                .data(successData)
                .errmsg("成功")
                .errno(0)
                .build();
    }

    public static <V> BaseRespVo<V> success() {

        return success(null);
    }

    public static <V extends List> BaseRespVo<PageVo> successPage(V listData) {

        return success(PageVo.list(listData));
    }

    public static <V extends List> BaseRespVo<PageVo> successPage(V listData, PageInfo pageInfo) {

        return success(PageVo.list(listData, pageInfo));
    }
    public static <V> BaseRespVo fail(V failData) {
        return BaseRespVo.builder()
                .data(failData)
                .errmsg("失败")
                .errno(400)
                .build();
    }

    public static <V> BaseRespVo error(V errorData, String errorMsg, int errorCode) {
        return BaseRespVo.builder()
                .data(errorData)
                .errmsg(errorMsg)
                .errno(errorCode)
                .build();
    }

    public static <V> BaseRespVo error(Exception ex) {

        if (ex instanceof BaseException) {
            BaseException baseException = (BaseException) ex;
            return BaseRespVo.error(null, baseException.getErrmsg(), baseException.getErrno());
        } else {
            return BaseRespVo.error(null, ex.getMessage(), 500);
        }
    }
}
