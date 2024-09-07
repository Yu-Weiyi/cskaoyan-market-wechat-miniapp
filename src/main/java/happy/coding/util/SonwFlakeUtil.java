package happy.coding.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import java.net.InetAddress;


public class SonwFlakeUtil {

    private static final long WORKER_ID;
    private static final long DATACENTER_ID = 1L;

    static {
        // 使用静态代码块初始化 WORKER_ID
        String hostname = System.getenv("HOSTNAME");  // Linux or Docker

        if (hostname == null || hostname.isEmpty()) {
            hostname = System.getenv("COMPUTERNAME");  // Windows
        }

        if (hostname == null || hostname.isEmpty()) {
            hostname = "default";
        }

        WORKER_ID = Math.abs(hostname.hashCode()) % 32;
    }

    private static Snowflake snowflake = IdUtil.getSnowflake(WORKER_ID, DATACENTER_ID);

    public static String getUniqueId() {

        return snowflake.nextIdStr();
    }
}
