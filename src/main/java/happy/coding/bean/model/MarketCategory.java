package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类目表
 */
@Schema(description="类目表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketCategory implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 类目名称
    */
    @Schema(description="类目名称")
    private String name;

    /**
    * 类目关键字，以JSON数组格式
    */
    @Schema(description="类目关键字，以JSON数组格式")
    private String[] keywords;

    /**
    * 类目广告语介绍
    */
    @Schema(description="类目广告语介绍")
    private String desc;

    /**
    * 父类目ID
    */
    @Schema(description="父类目ID")
    private Integer pid;

    /**
    * 类目图标
    */
    @Schema(description="类目图标")
    private String iconUrl;

    /**
    * 类目图片
    */
    @Schema(description="类目图片")
    private String picUrl;

    @Schema(description="")
    private String level;

    /**
    * 排序
    */
    @Schema(description="排序")
    private Byte sortOrder;

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