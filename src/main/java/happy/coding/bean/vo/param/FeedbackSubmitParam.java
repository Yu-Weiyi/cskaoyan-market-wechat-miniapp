package happy.coding.bean.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.param
 * @name FeedbackSubmitParam
 * @description Feedback submit param.
 * @since 2024-09-08 01:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackSubmitParam {

    private String content;
    private String feedType;
    private Boolean hasPicture;
    private String mobile;
    private String[] picUrls;
}
