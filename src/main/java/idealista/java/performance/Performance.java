package idealista.java.performance;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Performance {

	public static void main(String[] args) {
		measurePerformance("classic", Performance::classicStyle);
		measurePerformance("sequentialStream", Performance::usingSequentialStream);
		measurePerformance("parallelStream", Performance::usingParallelStream);
		measurePerformance("sequentialIntStream", Performance::usingSequentialIntStream);
		measurePerformance("parallelIntStream", Performance::usingParallelIntStream);
	}

	public static void measurePerformance(String method, Function<Integer, Integer> function) {
		long fastestExecution = Long.MAX_VALUE;
		for (int i = 0; i < 5; i++) {
			long initTime = System.currentTimeMillis();
			function.apply(10_0000_000);
			long finalTime = System.currentTimeMillis();
			fastestExecution = Math.min(fastestExecution, finalTime - initTime);
		}
		System.out.println("Fastest execution using " + method + ": " + fastestExecution + " ms");
	}

	public static Integer classicStyle(Integer limit) {
		int result = 0;
		for (int i = 0; i < limit; i++) {
			result += i;
		}
		return result;
	}

	public static Integer usingSequentialStream(Integer limit) {
		return Stream.iterate(0, i -> i + 1)
				.limit(limit)
				.reduce(0, Integer::sum);
	}

	public static Integer usingSequentialIntStream(Integer limit) {
		return IntStream.iterate(0, i -> i + 1)
				.limit(limit)
				.reduce(0, Integer::sum);
	}

	public static Integer usingParallelStream(Integer limit) {
		return Stream.iterate(0, i -> i + 1)
				.limit(limit)
				.parallel()
				.reduce(0, Integer::sum);
	}

	public static Integer usingParallelIntStream(Integer limit) {
		return IntStream.range(0, limit)
				.parallel()
				.reduce(0, Integer::sum);
	}
}
