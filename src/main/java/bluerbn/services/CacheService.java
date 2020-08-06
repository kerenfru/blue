package bluerbn.services;


import bluerbn.models.CachedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CacheService {

    private static final int CLEAN_UP_PERIOD_IN_MILLIS = 20000;
    private final ConcurrentHashMap<String, CachedData> cache = new ConcurrentHashMap();

    @Scheduled(fixedRate = 30000)
    private void run() {

        List<CachedData> expiredData = cache.values().stream().filter(CachedData::isExpired).collect(Collectors.toList());
        expiredData.forEach(cached -> {
            cache.remove(cached.getKey());
            log.info(String.format("removing item with key %s from cache", cached.getKey()));
        });
    }

    public void add(String key, Object value) {
        CachedData cachedData = CachedData.builder()
            .key(key)
            .value(value)
            .expiry(System.currentTimeMillis() + CLEAN_UP_PERIOD_IN_MILLIS)
            .build();

        cache.put(key, cachedData);
    }


    public Optional<Object> get(String key) {
        CachedData cachedData = cache.get(key);

        if (Objects.isNull(cachedData)) {
            return Optional.empty();
        }
        return Optional.of(cachedData.getValue());
    }

}
