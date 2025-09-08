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

import java.util.List;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class StatUtil {

	public static int min(int... n) {
		int min = Integer.MAX_VALUE;
		for (int v : n) {
			if (v < min) {
				min = v;
			}
		}
		return min;
	}

	public static Integer min(List<Integer> n) {
		int min = Integer.MAX_VALUE;
		for (int v : n) {
			if (v < min) {
				min = v;
			}
		}
		return min;
	}

	public static int max(int... n) {
		int max = Integer.MIN_VALUE;
		for (int v : n) {
			if (v > max) {
				max = v;
			}
		}
		return max;
	}

	public static Integer max(List<Integer> n) {
		int max = Integer.MIN_VALUE;
		for (int v : n) {
			if (v > max) {
				max = v;
			}
		}
		return max;
	}

	public static long sum(int... n) {
		long sum = 0;
		for (int v : n) {
			sum += v;
		}
		return sum;
	}

	public static long sum(List<Integer> n) {
		long sum = 0;
		for (int v : n) {
			sum += v;
		}
		return sum;
	}

	public static float sum(float... f) {
		float sum = 0;
		for (float v : f) {
			sum += v;
		}
		return sum;
	}

	public static double sum(double... d) {
		double sum = 0;
		for (double v : d) {
			sum += v;
		}
		return sum;
	}

	public static double diff_avg(int scale, int... n) {
		int diffs[] = new int[n.length - 1];
		for (int i = 0; i < diffs.length; i++) {
			diffs[i] = (n[i + 1] - n[i]);
		}
		return avg(scale, diffs);
	}

	public static double avg(int scale, int... n) {
		int size = n.length;
		long sum = sum(n);
		double avg = sum / size;
		double avgWithScale = BigDecimalUtil.rounding(avg, scale);
		return avgWithScale;
	}

	public static double avg(int scale, List<Integer> n) {
		int size = n.size();
		long sum = sum(n);
		double avg = sum / size;
		double avgWithScale = BigDecimalUtil.rounding(avg, scale);
		return avgWithScale;
	}

	/**
	 * 
	 * @param subNum
	 * @param baseNum
	 * @param multiplyWith
	 * @return
	 */
	public static int normalizeByPercentage(int subNum, int baseNum, int multiplyWith) {
		double ratio = (double) subNum / (double) baseNum;
		double ratioWithScale2 = BigDecimalUtil.rounding(ratio);
		return (int) (ratioWithScale2 * multiplyWith);
	}

	/**
	 * 
	 * @param numbers
	 * @param scale
	 * @return
	 */
	public static float getAvg(List<Integer> numbers, int scale) {
		int length = numbers.size();
		long value_sum = 0;
		for (int num : numbers) {
			value_sum += num;
		}
		return BigDecimalUtil.rounding(value_sum / length, scale);
	}

	/**
	 * 
	 * @param numbers
	 * @param scale
	 * @return
	 */
	public static float getStandardDeviation(List<Integer> numbers, int scale) {
		int length = numbers.size();
		float value_sum = (float) 0.0;
		float sd_sum = (float) 0.0;
		for (int num : numbers) {
			value_sum += num;
		}
		float mean = value_sum / length;
		for (int num : numbers) {
			sd_sum += Math.pow(num - mean, 2);
		}
		return (float) BigDecimalUtil.rounding(Math.sqrt(sd_sum / length), scale);
	}

	public static double getStandardDeviation(double[] numbers) {
		int length = numbers.length;
		double value_sum = 0.0;
		double sd_sum = 0.0;
		for (double num : numbers) {
			value_sum += num;
		}
		double mean = value_sum / length;
		for (double num : numbers) {
			sd_sum += Math.pow(num - mean, 2);
		}
		return Math.sqrt(sd_sum / length);
	}

	public static float getStandardDeviation(float[] numbers) {
		int length = numbers.length;
		float value_sum = (float) 0.0;
		float sd_sum = (float) 0.0;
		for (double num : numbers) {
			value_sum += num;
		}
		float mean = value_sum / length;
		for (float num : numbers) {
			sd_sum += Math.pow(num - mean, 2);
		}
		return (float) Math.sqrt(sd_sum / length);
	}

}
