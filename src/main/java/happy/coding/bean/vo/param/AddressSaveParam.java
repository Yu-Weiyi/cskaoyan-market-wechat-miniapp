package happy.coding.bean.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.bean.vo.param
 * @name AddressSaveParam
 * @description Address save param.
 * @since 2024-09-06 00:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressSaveParam {

    private Integer id;// useless
    private String name;
    private String tel;
    private String addressDetail;
    private Boolean isDefault;
    private String areaCode;
    private String province;
    private String city;
    private String county;
}
