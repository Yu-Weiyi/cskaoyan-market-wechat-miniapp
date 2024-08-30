package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关键字表
 */
@Schema(description="关键字表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketKeyword implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 关键字
    */
    @Schema(description="关键字")
    private String keyword;

    /**
    * 关键字的跳转链接
    */
    @Schema(description="关键字的跳转链接")
    private String url;

    /**
    * 是否是热门关键字
    */
    @Schema(description="是否是热门关键字")
    private Boolean isHot;

    /**
    * 是否是默认关键字
    */
    @Schema(description="是否是默认关键字")
    private Boolean isDefault;

    /**
    * 排序
    */
    @Schema(description="排序")
    private Integer sortOrder;

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