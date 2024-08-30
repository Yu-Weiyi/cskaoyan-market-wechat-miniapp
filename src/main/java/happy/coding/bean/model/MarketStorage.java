package happy.coding.bean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件存储表
 */
@Schema(description="文件存储表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketStorage implements Serializable {
    @Schema(description="")
    private Integer id;

    /**
    * 文件的唯一索引
    */
    @Schema(description="文件的唯一索引")
    private String key;

    /**
    * 文件名
    */
    @Schema(description="文件名")
    private String name;

    /**
    * 文件类型
    */
    @Schema(description="文件类型")
    private String type;

    /**
    * 文件大小
    */
    @Schema(description="文件大小")
    private Integer size;

    /**
    * 文件访问链接
    */
    @Schema(description="文件访问链接")
    private String url;

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