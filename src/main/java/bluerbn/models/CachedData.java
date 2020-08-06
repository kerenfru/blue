package bluerbn.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CachedData {

    private String key;
    private Object value;
    private long expiry;

    public boolean isExpired() {
        return System.currentTimeMillis() > expiry;
    }
}
