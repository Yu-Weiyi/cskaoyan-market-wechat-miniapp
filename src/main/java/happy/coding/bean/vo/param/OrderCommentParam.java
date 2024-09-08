package happy.coding.bean.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.param
 * @name OrderCommentParam
 * @description Order comment param.
 * @since 2024-09-08 21:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCommentParam {

    private String content;
    private Boolean hasPicture;
    private Integer orderGoodsId;
    private String[] picUrls;
    private Short star;
}
