package happy.coding.bean.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.param
 * @name CommentPostParam
 * @description Comment post param.
 * @since 2024-09-08 00:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentPostParam {

    private Byte type;
    private Integer valueId;
    private String content;
    private Boolean hasPicture;
    private String[] picUrls;
    private Short star;
}
