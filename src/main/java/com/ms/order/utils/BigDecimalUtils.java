package com.ms.order.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

	public static BigDecimal zeroIfNull(BigDecimal value) {

		return value == null ? BigDecimal.ZERO : value;
	}

	public static boolean isGreaterThanZero(BigDecimal value) {

		return isGreaterThan(value, BigDecimal.ZERO);
	}

	public static boolean isGreaterOrEqualThanZero(BigDecimal value) {

		return isGreaterOrEqualThan(value, BigDecimal.ZERO);
	}

	public static boolean isGreaterOrEqualThan(BigDecimal value, BigDecimal compare) {

		return isGreaterThan(value, compare) || isEqual(value, compare);
	}

	public static boolean isGreaterThan(BigDecimal value, BigDecimal compare) {

		int resultCompare = compare(value, compare);
		return resultCompare == 1;
	}

	public static boolean isLessThanZero(BigDecimal value) {

		return isLessThan(value, BigDecimal.ZERO);
	}

	public static boolean isLessOrEqualThanZero(BigDecimal value) {

		return isLessOrEqualThan(value, BigDecimal.ZERO);
	}

	public static boolean isLessOrEqualThan(BigDecimal value, BigDecimal compare) {

		return isLessThan(value, compare) || isEqual(value, compare);
	}

	public static boolean isLessThan(BigDecimal value, BigDecimal compare) {

		return compare(value, compare) == -1;
	}

	public static boolean isZero(BigDecimal value) {

		return isEqual(value, BigDecimal.ZERO);
	}

	public static boolean isEqual(BigDecimal a, BigDecimal b) {

		return compare(a, b) == 0;
	}

	private static int compare(BigDecimal value, BigDecimal compare) {

		value = zeroIfNull(value);

		if (compare == null)
			compare = BigDecimal.ZERO;

		int resultado = value.compareTo(compare);

		return resultado;
	}
}
