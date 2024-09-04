package happy.coding.service.impl;

import happy.coding.bean.model.MarketCategory;
import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.QueryException;
import happy.coding.service.CatalogService;
import happy.coding.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.service.impl
 * @name CatalogServiceImpl
 * @description Catalog service implement.
 * @since 2024-09-04 10:48
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CategoryService categoryService;

    @Override
    public Map<String, Object> index() {

        List<MarketCategory> marketCategoryList = categoryService.listAll("L1");// TODO magic value

        MarketCategory currentCategory = null;
        List<MarketCategory> currentSubCategory = null;

        if (marketCategoryList != null && marketCategoryList.size() > 0) {
            int categoryId = marketCategoryList.get(0).getId();

            ExecutorService executorService = Executors.newFixedThreadPool(10);

            FutureTask<MarketCategory> currentCategoryTask = new FutureTask<>(() -> categoryService.selectById(categoryId));
            FutureTask<List<MarketCategory>> currentSubCategoryTask = new FutureTask<>(() -> categoryService.list("L2", categoryId, 0));

            executorService.submit(currentCategoryTask);
            executorService.submit(currentSubCategoryTask);

            try {
                currentCategory = currentCategoryTask.get();
                currentSubCategory = currentSubCategoryTask.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new QueryException(ErrorCodeConstant.QUERY_FAILED);
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("categoryList", marketCategoryList);
        map.put("currentCategory", currentCategory);
        map.put("currentSubCategory", currentSubCategory);
        return map;
    }
}
