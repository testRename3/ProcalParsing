import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 1/12/2016.
 */
public class VariableMap {
    private Map<String, BigDecimal> storage = new HashMap<>();
    public BigDecimal get(String name) {
        if (storage.containsKey(name))
            return storage.get(name);
        else
            return setValue(name, new BigDecimal(0));
    }

    public BigDecimal setValue(String name, BigDecimal value) {
        storage.put(name, value);
        return value;
    }
}
