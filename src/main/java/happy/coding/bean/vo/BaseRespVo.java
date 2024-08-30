package happy.coding.bean.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//基本的页面返回的数据
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRespVo<T> {
    T data;
    String errmsg;
    int errno;

    static public <V> BaseRespVo<V> success(V successData){
        return BaseRespVo.<V>builder()
                .data(successData)
                .errmsg("成功")
                .errno(0)
                .build();
    }
    public static <V> BaseRespVo fail(V failData) {
        return BaseRespVo.builder()
                .data(failData)
                .errmsg("失败")
                .errno(400)
                .build();
    }
}
