package util;

import exception.CTimeOutException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ViewNotResponseChecker {

    public static <V> V check(Callable<V> callable) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<V> future = executor.submit(callable);
        try {
            return future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new CTimeOutException("현재 해당 페이지를 불러올 수 없습니다.");
        }
    }

}
