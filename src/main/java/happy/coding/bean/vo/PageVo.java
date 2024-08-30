package happy.coding.bean.vo;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    private int limit;
    private List list;
    private int page;
    private int pages;
    private int total;

    public static  <T> PageVo list(List<T> list){
        PageInfo<T> pageInfo=new PageInfo<>(list);
        return PageVo.builder()
                .list(pageInfo.getList())
                .page(pageInfo.getPageNum())
                .pages(pageInfo.getPages())
                .limit(pageInfo.getPageSize())
                .total((int) pageInfo.getTotal())
                .build();
    }
}
