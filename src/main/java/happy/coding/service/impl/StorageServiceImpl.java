package happy.coding.service.impl;

import com.aliyun.oss.model.PutObjectResult;
import happy.coding.bean.model.MarketStorage;
import happy.coding.bean.vo.OssPutResult;
import happy.coding.client.AliyunClient;
import happy.coding.mapper.MarketStorageMapper;
import happy.coding.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name StorageServiceImpl
 * @description Storage service implement.
 * @since 2024-09-06 16:24
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private AliyunClient aliyunClient;
    @Autowired
    private MarketStorageMapper marketStorageMapper;

    @Override
    public MarketStorage upload(MultipartFile file) {

        OssPutResult ossPutResult = aliyunClient.save(file);
        Date now = new Date();

        MarketStorage marketStorage = new MarketStorage();
        marketStorage.setKey(ossPutResult.getKey());
        marketStorage.setName(file.getOriginalFilename());
        marketStorage.setType(file.getContentType());
        marketStorage.setSize((int) file.getSize());
        marketStorage.setUrl(ossPutResult.getUrl());
        marketStorage.setAddTime(now);
        marketStorage.setUpdateTime(now);
        marketStorage.setDeleted(false);
        marketStorageMapper.insertSelective(marketStorage);
        return marketStorage;
    }
}
