package happy.coding.bean.vo.data;

import happy.coding.bean.model.MarketGoods;
import lombok.Data;

import java.util.List;
@Data
public class HomeIndexFloorGoodData {
    List<MarketGoods> goodsList;
    int id;
    String name;
}
