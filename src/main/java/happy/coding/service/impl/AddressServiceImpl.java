package happy.coding.service.impl;

import com.github.pagehelper.PageHelper;
import happy.coding.bean.model.MarketAddress;
import happy.coding.bean.model.MarketAddressExample;
import happy.coding.bean.vo.param.AddressSaveParam;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.context.UserInfoContext;
import happy.coding.exception.QueryException;
import happy.coding.mapper.MarketAddressMapper;
import happy.coding.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name AddressServiceImpl
 * @description Address service implement.
 * @since 2024-09-05 23:47
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private MarketAddressMapper marketAddressMapper;

    @Override
    public List<MarketAddress> list() {

        MarketAddressExample marketAddressExample = new MarketAddressExample();
        marketAddressExample.createCriteria()
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andDeletedEqualTo(false);
        PageHelper.startPage(1, 10);
        List<MarketAddress> marketAddressList = marketAddressMapper.selectByExample(marketAddressExample);
        if (marketAddressList == null || marketAddressList.isEmpty()) {
            return new ArrayList<>();
        }
        return marketAddressList;
    }

    @Override
    public int save(AddressSaveParam addressSaveParam) {

        if (addressSaveParam.getIsDefault()) {
            MarketAddress record = new MarketAddress();
            record.setIsDefault(false);
            record.setUpdateTime(new Date());
            MarketAddressExample marketAddressExample = new MarketAddressExample();
            marketAddressExample.createCriteria()
                    .andUserIdEqualTo(UserInfoContext.getUserId())
                    .andIsDefaultEqualTo(true)
                    .andDeletedEqualTo(false);
            marketAddressMapper.updateByExampleSelective(record, marketAddressExample);
        }

        MarketAddress marketAddress = new MarketAddress();
        marketAddress.setName(addressSaveParam.getName());
        marketAddress.setUserId(UserInfoContext.getUserId());
        marketAddress.setProvince(addressSaveParam.getProvince());
        marketAddress.setCity(addressSaveParam.getCity());
        marketAddress.setCounty(addressSaveParam.getCounty());
        marketAddress.setAddressDetail(addressSaveParam.getAddressDetail());
        marketAddress.setAreaCode(addressSaveParam.getAreaCode());
        marketAddress.setTel(addressSaveParam.getTel());
        marketAddress.setIsDefault(addressSaveParam.getIsDefault());
        marketAddressMapper.insertSelective(marketAddress);
        return marketAddress.getId();
    }

    @Override
    public MarketAddress detail(Integer addressId) {

        MarketAddressExample marketAddressExample = new MarketAddressExample();
        marketAddressExample.createCriteria()
                .andIdEqualTo(addressId)
                .andUserIdEqualTo(UserInfoContext.getUserId())
                .andDeletedEqualTo(false);
        List<MarketAddress> marketAddressList = marketAddressMapper.selectByExample(marketAddressExample);
        if (marketAddressList == null || marketAddressList.isEmpty()) {
            return null;
        }
        return marketAddressList.get(0);
    }

    @Override
    public void delete(Integer addressId) {

        MarketAddress record = new MarketAddress();
        record.setUpdateTime(new Date());
        record.setDeleted(true);
        MarketAddressExample marketAddressExample = new MarketAddressExample();
        marketAddressExample.createCriteria()
                .andIdEqualTo(addressId)
                .andUserIdEqualTo(UserInfoContext.getUserId());
        marketAddressMapper.updateByExampleSelective(record, marketAddressExample);
    }

    @Override
    public String getFullAddress(Integer addressId) {

        MarketAddress marketAddress = marketAddressMapper.selectByPrimaryKey(addressId);
        String fullAddress = getFullAddress(marketAddress);
        return fullAddress;
    }

    @Override
    public String getFullAddress(MarketAddress marketAddress) {

        if (marketAddress == null) {
            throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
        }

        return marketAddress.getProvince() +
                marketAddress.getCity() +
                marketAddress.getCounty() +
                " " +
                marketAddress.getAddressDetail();
    }
}
