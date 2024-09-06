package happy.coding.bean.vo;

import com.aliyun.oss.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author stone
 * @date 2023/04/21 17:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssPutResult {
    PutObjectResult result;
    String url;
}
