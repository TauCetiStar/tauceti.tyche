/*
 * Copyright (c) 2025 TauCeti.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Yang Yang - initial API and implementation
 */
package org.tauceti.tyche.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class BigDecimalUtil {

	public static BigDecimal toBigDecimal(int i) {
		return toBigDecimal(i, 0, RoundingMode.CEILING);
	}

	public static BigDecimal toBigDecimal(int i, int scale, RoundingMode roundingMode) {
		BigDecimal value = new BigDecimal(i);
		value = value.setScale(scale, roundingMode);
		return value;
	}

	public static BigDecimal toBigDecimal(long l) {
		return toBigDecimal(l, 0, RoundingMode.CEILING);
	}

	public static BigDecimal toBigDecimal(long l, int scale, RoundingMode roundingMode) {
		BigDecimal value = new BigDecimal(l);
		value = value.setScale(scale, roundingMode);
		return value;
	}

	public static BigDecimal toBigDecimal(double d) {
		return toBigDecimal(d, 2, RoundingMode.CEILING);
	}

	public static BigDecimal toBigDecimal(double d, int scale, RoundingMode roundingMode) {
		BigDecimal value = new BigDecimal(d);
		value = value.setScale(scale, roundingMode);
		return value;
	}

	public static BigDecimal toBigDecimal(String literal) {
		return toBigDecimal(literal, 2, RoundingMode.CEILING);
	}

	public static BigDecimal toBigDecimal(String literal, int scale) {
		return toBigDecimal(literal, scale, RoundingMode.CEILING);
	}

	public static BigDecimal toBigDecimal(String literal, int scale, RoundingMode roundingMode) {
		BigDecimal value = new BigDecimal(literal);
		value = value.setScale(scale, roundingMode);
		return value;
	}

	public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
		return multiply(a, b, 2, RoundingMode.CEILING);
	}

	public static BigDecimal multiply(BigDecimal a, BigDecimal b, int scale, RoundingMode roundingMode) {
		if (a == null) {
			return b;
		}
		if (b == null) {
			return a;
		}
		BigDecimal result = a.multiply(b);
		result = result.setScale(scale, roundingMode);
		return result;
	}

	public static BigDecimal multiply(BigDecimal... a) {
		return multiply(2, RoundingMode.CEILING, a);
	}

	public static BigDecimal multiply(int scale, BigDecimal... a) {
		return multiply(scale, RoundingMode.CEILING, a);
	}

	/**
	 * 
	 * @param scale
	 * @param roundingMode
	 * @param a
	 * @return
	 */
	public static BigDecimal multiply(int scale, RoundingMode roundingMode, BigDecimal... a) {
		BigDecimal result = null;
		if (a != null) {
			for (int i = 0; i < a.length; i++) {
				if (i == 0) {
					result = a[0];
				} else {
					result = result.multiply(a[i]);
				}
			}
		}
		if (result != null) {
			result = result.setScale(scale, roundingMode);
		}
		return result;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static double rounding(double value) {
		return rounding(value, 2);
	}

	/**
	 * 
	 * @param value
	 * @param scale
	 * @return
	 */
	public static double rounding(double value, int scale) {
		return toBigDecimal(value, scale, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * 
	 * @param value
	 * @param scale
	 * @return
	 */
	public static float rounding(float value) {
		return rounding(value, 2);
	}

	/**
	 * 
	 * @param value
	 * @param scale
	 * @return
	 */
	public static float rounding(float value, int scale) {
		return toBigDecimal(value, scale, RoundingMode.HALF_UP).floatValue();
	}

}
