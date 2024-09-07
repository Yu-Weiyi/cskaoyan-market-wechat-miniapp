package happy.coding.service;

import happy.coding.bean.vo.param.FeedbackSubmitParam;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service
 * @name FeedbackService
 * @description Feedback service interface.
 * @since 2024-09-08 01:32
 */
public interface FeedbackService {

    void submit(FeedbackSubmitParam feedbackSubmitParam);
}
