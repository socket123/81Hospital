package com.chusu.platform.ehcache;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

@Component
public class LocalEhcacheService {

    @Autowired
    private EhCacheCacheManager ehcacheManager;

    public void add(String name, String key, Object value) {
        final Cache cache = ehcacheManager.getCacheManager().getCache(name);
        if (cache == null)
            return;
        final Element element = new Element(key, value);
        cache.put(element);
    }

    public void reset(String name, String key, Object value) {
        remove(name, key);
        add(name, key, value);
    }

    public Object get(String name, String key) {
        final Cache cache = ehcacheManager.getCacheManager().getCache(name);
        if (cache == null)
            return null;
        final Element element = cache.get(key);
        if (element == null) {
            return null;
        }
        final Object value = element.getValue();
        return value;
    }

    public List<Object> get(String name, String[] keys) {
        final Cache cache = ehcacheManager.getCacheManager().getCache(name);
        if (cache == null)
            return null;
        final List<Object> values = new ArrayList<Object>();
        for (String key : keys) {
            values.add(get(name, key));
        }
        return values;
    }

    public void remove(String name, String key) {
        final Cache cache = ehcacheManager.getCacheManager().getCache(name);
        if (cache == null)
            return;
        cache.remove(key);
    }

    public void remove(String name) {
        final Cache cache = ehcacheManager.getCacheManager().getCache(name);
        if (cache == null)
            return;
        cache.removeAll();

    }

    public int size(String name) {
        final Cache cache = ehcacheManager.getCacheManager().getCache(name);
        if (cache == null)
            return -1;
        return cache.getSize();
    }

    public Object keys(String name) {
        final Cache cache = ehcacheManager.getCacheManager().getCache(name);
        if (cache == null)
            return null;
        return cache.getKeys();
    }

    public void clear(String name) {
        final Cache cache = ehcacheManager.getCacheManager().getCache(name);
        if (cache != null) {
            cache.removeAll();
        }
    }

    public void clear() {
        ehcacheManager.getCacheManager().clearAll();
    }

    public EhCacheCacheManager getEhcacheCacheManager() {
        return ehcacheManager;
    }

    public Object getBusinessCache(String key) {
        return get("businessCache", key);
    }

    public void addBusinessCache(String key, Object value) {
        add("businessCache", key, value);
    }
}
