package org.star_pet_house_commons.cache;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.star_pet_house_commons.utils.SpringContext;


/**
 * 缓存管理
 * @author gaowx
 *
 */
@Lazy(false)
@Component
public class CacheManager {

    private final Logger logger = LoggerFactory.getLogger(CacheManager.class);
    //缓存数据刷新时间间隔
    private static final int REFRESH_SLEEP_TIME = 30 * 60 * 1000;

    @Autowired
    private SpringContext springContext;

    @PostConstruct
    private void init() throws Exception {
        new Thread() {
            public void run() {
                while(true) {
                    refreshAllCache();
                    try {
                        Thread.sleep(REFRESH_SLEEP_TIME);
                    } catch (Exception e) {
                        logger.error("线程休眠发生错误", e);
                    }
                }
            }
        }.start();
    }

    @SuppressWarnings("rawtypes")
    private void refreshAllCache() {
        List<Map.Entry<String, ICache>> list = springContext.getOrderedBeans(ICache.class);
        if (list == null || list.isEmpty()) {
            try {
                Thread.sleep(10 * 1000);
            } catch (Exception e) {
                logger.error("线程休眠发生错误", e);
            }
            refreshAllCache();
        }

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, ICache> entry : list) {
            sb.append(entry.getValue().getId()+"  ");
        }
        logger.info("已加载的ICache子类有：["+sb.toString()+"]");
        for (Map.Entry<String, ICache> entry : list) {
            try {
                refreshOne(entry.getValue().getId());
            } catch (Exception e) {
                logger.error("更新缓存发生错误", e);
            }
        }
    }

    public void refreshOne(String cacheName) throws Exception {
        ICache<?> cache = findCacheBeanByName(cacheName);
        if (cache != null) {
            cache.refresh();
        }
        logger.info("刷新缓存[" + cacheName + "]成功！");
    }

    /**
     * 按cache名称或文件名称取缓存bean
     * @param cacheName
     * @return
     */
    @SuppressWarnings("rawtypes")
    private ICache findCacheBeanByName(String cacheName) {
        Map<String, ICache> beans = SpringContext.getBeansOfType(ICache.class);
        for (Map.Entry<String, ICache> entry : beans.entrySet()) {
            if (cacheName.equals(entry.getValue().getId())) {
                return entry.getValue();
            }
        }
        return null;
    }

}
