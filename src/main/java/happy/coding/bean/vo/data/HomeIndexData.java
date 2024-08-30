package happy.coding.bean.vo.data;

import happy.coding.bean.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeIndexData {
    List<MarketAd> banner;
    List<MarketBrand> brandList;
    List<MarketCategory> channel;
    List<MarketCoupon> couponList;
    List<HomeIndexFloorGoodData> floorGoodsList;
    List<MarketGoods> hotGoodsList;
    List<MarketGoods> newGoodsList;
    List<MarketTopic> topicList;
}
