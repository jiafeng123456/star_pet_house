package org.star_pet_house_commons.cache;

import org.springframework.core.Ordered;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Map;

/**
 * 功能说明: 统一缓存<br>
 * 系统版本: v1.0<br>
 */
public interface ICache<T> extends Serializable, Ordered {

    public void refresh() throws Exception;

    public String getId();

    public Map<String, T> getConfigData();
}
