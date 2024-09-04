package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import happy.coding.bean.model.MarketFootprint;
import happy.coding.bean.model.MarketFootprintExample;
import happy.coding.bean.model.MarketGoods;
import happy.coding.bean.vo.data.FootprintListData;
import happy.coding.context.PageInfoContext;
import happy.coding.context.UserInfoContext;
import happy.coding.mapper.MarketFootprintMapper;
import happy.coding.mapper.MarketGoodsMapper;
import happy.coding.service.FootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name FootprintServiceImpl
 * @description Footprint service implement.
 * @since 2024-09-04 20:26
 */
@Service
public class FootprintServiceImpl implements FootprintService {

    @Autowired
    private MarketFootprintMapper marketFootprintMapper;
    @Autowired
    private MarketGoodsMapper marketGoodsMapper;

    @Override
    public List<FootprintListData> list(int page, int limit) {

        MarketFootprintExample marketFootprintExample = new MarketFootprintExample();
        marketFootprintExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andDeletedEqualTo(false);
        marketFootprintExample.setOrderByClause("add_time DESC");
        if (page > 0 && limit > 0) {
            PageHelper.startPage(page, limit);
        }
        List<MarketFootprint> marketFootprintList = marketFootprintMapper.selectByExample(marketFootprintExample);
        PageInfoContext.serPageInfo(new PageInfo<>(marketFootprintList));
        List<FootprintListData> list = marketFootprintList.stream()
                .map(item -> {
                    MarketGoods marketGoods = marketGoodsMapper.selectByPrimaryKey(item.getGoodsId());
                    FootprintListData footprintListData = new FootprintListData(item.getId(), marketGoods.getName(), item.getGoodsId(), marketGoods.getPicUrl(), marketGoods.getBrief(), marketGoods.getRetailPrice(), item.getAddTime());
                    return footprintListData;
                })
                .collect(Collectors.toList());
        return list;
    }
}
