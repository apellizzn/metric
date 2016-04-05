package core;

import java.util.function.Supplier;

public class SimpleMetric<T> extends Metric<T,T> {
    public SimpleMetric(Supplier<Long> measureTool) {
        super(measureTool);
    }
}
