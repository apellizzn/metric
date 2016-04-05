package core;

import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Metric<T, R> {
    private PrintStream logger;
    private Supplier<Long> measureTool;

    public Metric(Supplier<Long> measureTool) {
        logger = System.out;
        this.measureTool = measureTool;
    }

    public Metric(Supplier<Long> measureTool, PrintStream logger){
        this.logger = logger;
        this.measureTool= measureTool;
    }

    public void measure(Consumer<T> consumer, T input) {
        long start = measureTool.get();
        consumer.accept(input);
        report(start);
    }

    public T measure(Supplier<T> supplier) {
        long start = measureTool.get();
        T result = supplier.get();
        report(start);
        return result;
    }

    public R measure(Function<T, R> foo, T input) {
        long start = measureTool.get();
        R result = foo.apply(input);
        report(start);
        return result;
    }

    private void report(long start) {
        logger.println("time elapsed :" + (measureTool.get() - start));
    }
}
