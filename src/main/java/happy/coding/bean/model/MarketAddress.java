package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 收货地址表
 */
@Schema(description="收货地址表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketAddress implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 收货人名称
    */
    @Schema(description="收货人名称")
    private String name;

    /**
    * 用户表的用户ID
    */
    @Schema(description="用户表的用户ID")
    private Integer userId;

    /**
    * 行政区域表的省ID
    */
    @Schema(description="行政区域表的省ID")
    private String province;

    /**
    * 行政区域表的市ID
    */
    @Schema(description="行政区域表的市ID")
    private String city;

    /**
    * 行政区域表的区县ID
    */
    @Schema(description="行政区域表的区县ID")
    private String county;

    /**
    * 详细收货地址
    */
    @Schema(description="详细收货地址")
    private String addressDetail;

    /**
    * 地区编码
    */
    @Schema(description="地区编码")
    private String areaCode;

    /**
    * 邮政编码
    */
    @Schema(description="邮政编码")
    private String postalCode;

    /**
    * 手机号码
    */
    @Schema(description="手机号码")
    private String tel;

    /**
    * 是否默认地址
    */
    @Schema(description="是否默认地址")
    private Boolean isDefault;

    /**
    * 创建时间
    */
    @Schema(description="创建时间")
    private Date addTime;

    /**
    * 更新时间
    */
    @Schema(description="更新时间")
    private Date updateTime;

    /**
    * 逻辑删除
    */
    @Schema(description="逻辑删除")
    private Boolean deleted;

    private static final long serialVersionUID = 1L;
}