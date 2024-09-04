package happy.coding.context;

import com.github.pagehelper.PageInfo;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.context
 * @name PageInfoContext
 * @description Page info context.
 * @since 2024-09-04 21:14
 */
public class PageInfoContext {

    private static ThreadLocal<PageInfo> threadLocalPageInfo = ThreadLocal.withInitial(() -> null);

    public static void serPageInfo(PageInfo pageInfo) {

        threadLocalPageInfo.set(pageInfo);
    }

    public static PageInfo getPageInfo() {

        return threadLocalPageInfo.get();
    }

    public static void removePageInfo() {

        threadLocalPageInfo.remove();
    }
}
