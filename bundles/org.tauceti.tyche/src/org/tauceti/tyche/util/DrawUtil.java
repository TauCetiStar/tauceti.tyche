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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawDigit;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class DrawUtil {

	public static List<Integer> PRIME_NUMBERS__1_TO_100 = new ArrayList<Integer>();
	static {
		PRIME_NUMBERS__1_TO_100.add(2);
		PRIME_NUMBERS__1_TO_100.add(3);
		PRIME_NUMBERS__1_TO_100.add(5);
		PRIME_NUMBERS__1_TO_100.add(7);

		PRIME_NUMBERS__1_TO_100.add(11);
		PRIME_NUMBERS__1_TO_100.add(13);
		PRIME_NUMBERS__1_TO_100.add(17);
		PRIME_NUMBERS__1_TO_100.add(19);

		PRIME_NUMBERS__1_TO_100.add(23);
		PRIME_NUMBERS__1_TO_100.add(29);
		PRIME_NUMBERS__1_TO_100.add(31);
		PRIME_NUMBERS__1_TO_100.add(37);

		PRIME_NUMBERS__1_TO_100.add(41);
		PRIME_NUMBERS__1_TO_100.add(43);
		PRIME_NUMBERS__1_TO_100.add(47);
		PRIME_NUMBERS__1_TO_100.add(53);
		PRIME_NUMBERS__1_TO_100.add(59);

		PRIME_NUMBERS__1_TO_100.add(61);
		PRIME_NUMBERS__1_TO_100.add(67);
		PRIME_NUMBERS__1_TO_100.add(71);
		PRIME_NUMBERS__1_TO_100.add(73);
		PRIME_NUMBERS__1_TO_100.add(79);

		PRIME_NUMBERS__1_TO_100.add(83);
		PRIME_NUMBERS__1_TO_100.add(89);
		PRIME_NUMBERS__1_TO_100.add(97);
	}

	/**
	 * 
	 * @param draw
	 * @return
	 */
	public static int getOddCount(Draw draw) {
		int count = 0;
		if (draw.getNum1() % 2 == 1) {
			count++;
		}
		if (draw.getNum2() % 2 == 1) {
			count++;
		}
		if (draw.getNum3() % 2 == 1) {
			count++;
		}
		if (draw.getNum4() % 2 == 1) {
			count++;
		}
		if (draw.getNum5() % 2 == 1) {
			count++;
		}
		return count;
	}

	/**
	 * 
	 * @param draw
	 * @return
	 */
	public static int getEvenCount(Draw draw) {
		int count = 0;
		if (draw.getNum1() % 2 == 0) {
			count++;
		}
		if (draw.getNum2() % 2 == 0) {
			count++;
		}
		if (draw.getNum3() % 2 == 0) {
			count++;
		}
		if (draw.getNum4() % 2 == 0) {
			count++;
		}
		if (draw.getNum5() % 2 == 0) {
			count++;
		}
		return count;
	}

	/**
	 * 
	 * @param draw
	 * @return
	 */
	public static int getSumOfFirstDigits(Draw draw) {
		int sum = 0;
		if (draw.getNum1() > 10) {
			sum += draw.getNum1() / 10;
		}
		if (draw.getNum2() > 10) {
			sum += draw.getNum2() / 10;
		}
		if (draw.getNum3() > 10) {
			sum += draw.getNum3() / 10;
		}
		if (draw.getNum4() > 10) {
			sum += draw.getNum4() / 10;
		}
		if (draw.getNum5() > 10) {
			sum += draw.getNum5() / 10;
		}
		return sum;
	}

	/**
	 * 
	 * @param draw
	 * @return
	 */
	public static int getSumOfSecondDigits(Draw draw) {
		int sum = 0;
		sum += draw.getNum1() % 10;
		sum += draw.getNum2() % 10;
		sum += draw.getNum3() % 10;
		sum += draw.getNum4() % 10;
		sum += draw.getNum5() % 10;
		return sum;
	}

	/**
	 * 
	 * @param drawA
	 * @param drawB
	 * @return
	 */
	public static int getDuplicateNumbersCount(Draw drawA, Draw drawB) {
		int count = 0;

		int[] numsA = drawA.getNumberArray(false);
		int[] numsB = drawB.getNumberArray(false);

		for (int numA : numsA) {
			for (int numB : numsB) {
				if (numA == numB) {
					count++;
				}
			}
		}

		return count;
	}

	/**
	 * Get sum of 5 first digits.
	 * 
	 * @param draw
	 * @return
	 */
	public static int getSumOf5FirstDigits(Draw draw) {
		int sum = (draw.getNum1() / 10) + (draw.getNum2() / 10) + (draw.getNum3() / 10) + (draw.getNum4() / 10) + (draw.getNum5() / 10);
		return sum;
	}

	/**
	 * Get sum of 6 first digits.
	 * 
	 * @param draw
	 * @return
	 */
	public static int getSumOf6FirstDigits(Draw draw) {
		int sum = (draw.getNum1() / 10) + (draw.getNum2() / 10) + (draw.getNum3() / 10) + (draw.getNum4() / 10) + (draw.getNum5() / 10) + (draw.getPB() / 10);
		return sum;
	}

	/**
	 * Get sum of 5 numbers.
	 * 
	 * @param draw
	 * @return
	 */
	public static int getSumOf5Numbers(Draw draw) {
		int sum = draw.getNum1() + draw.getNum2() + draw.getNum3() + draw.getNum4() + draw.getNum5();
		return sum;
	}

	/**
	 * Get sum of 6 numbers.
	 * 
	 * @param draw
	 * @return
	 */
	public static int getSumOf6Numbers(Draw draw) {
		int sum = draw.getNum1() + draw.getNum2() + draw.getNum3() + draw.getNum4() + draw.getNum5() + draw.getPB();
		return sum;
	}

	/**
	 * Get mean of 5 numbers.
	 * 
	 * @param draw
	 * @return
	 */
	public static float getMeanOf5Numbers(Draw draw) {
		int sum = draw.getNum1() + draw.getNum2() + draw.getNum3() + draw.getNum4() + draw.getNum5();
		float mean = sum / 5f;
		return mean;
	}

	/**
	 * Get mean of 6 numbers.
	 * 
	 * @param draw
	 * @return
	 */
	public static float getMeanOf6Numbers(Draw draw) {
		int sum = draw.getNum1() + draw.getNum2() + draw.getNum3() + draw.getNum4() + draw.getNum5() + draw.getPB();
		float mean = sum / 6f;
		return mean;
	}

	/**
	 * Get stand deviation of 5 numbers.
	 * 
	 * @see https://www.khanacademy.org/math/statistics-probability/summarizing-quantitative-data/variance-standard-deviation-population/a/calculating-standard-deviation-step-by-step
	 * 
	 * @param draw
	 * @return
	 */
	public static float getSDof5Numbers(Draw draw) {
		int num1 = draw.getNum1();
		int num2 = draw.getNum2();
		int num3 = draw.getNum3();
		int num4 = draw.getNum4();
		int num5 = draw.getNum5();

		double mean = getMeanOf5Numbers(draw);
		double sum = Math.pow((num1 - mean), 2) + Math.pow((num2 - mean), 2) + Math.pow((num3 - mean), 2) + Math.pow((num4 - mean), 2) + Math.pow((num5 - mean), 2);
		double result = Math.sqrt(sum / 5d);

		return new BigDecimal(result).setScale(1, RoundingMode.HALF_UP).floatValue();
	}

	/**
	 * Get stand deviation of 4 distance values.
	 * 
	 * @param draw
	 * @return
	 */
	public static float getSDofDistances(Draw draw) {
		int num1 = draw.getNum1();
		int num2 = draw.getNum2();
		int num3 = draw.getNum3();
		int num4 = draw.getNum4();
		int num5 = draw.getNum5();

		int dist1 = num2 - num1;
		int dist2 = num3 - num2;
		int dist3 = num4 - num3;
		int dist4 = num5 - num4;

		double mean = (dist1 + dist2 + dist3 + dist4) / 4d;
		double sum = Math.pow((dist1 - mean), 2) + Math.pow((dist2 - mean), 2) + Math.pow((dist3 - mean), 2) + Math.pow((dist4 - mean), 2);
		double result = Math.sqrt(sum / 4d);

		return new BigDecimal(result).setScale(1, RoundingMode.HALF_UP).floatValue();
	}

	/**
	 * 
	 * @param draw
	 * @param includePB
	 * @return
	 */
	public static int getPrimeNumberCount(Draw draw, boolean includePB) {
		int count = 0;

		int num1 = draw.getNum1();
		int num2 = draw.getNum2();
		int num3 = draw.getNum3();
		int num4 = draw.getNum4();
		int num5 = draw.getNum5();
		int pb = draw.getPB();

		if (PRIME_NUMBERS__1_TO_100.contains(num1)) {
			count++;
		}
		if (PRIME_NUMBERS__1_TO_100.contains(num2)) {
			count++;
		}
		if (PRIME_NUMBERS__1_TO_100.contains(num3)) {
			count++;
		}
		if (PRIME_NUMBERS__1_TO_100.contains(num4)) {
			count++;
		}
		if (PRIME_NUMBERS__1_TO_100.contains(num5)) {
			count++;
		}
		if (PRIME_NUMBERS__1_TO_100.contains(pb) && includePB) {
			count++;
		}

		return count;
	}

	/**
	 * Get evenly divisible count among the 5 numbers.
	 * 
	 * @param draw
	 * @param adjacentOnly
	 * @return
	 */
	public static int getEvenlyDivisibleCount(Draw draw, boolean adjacentOnly) {
		int count = 0;

		int num1 = draw.getNum1();
		int num2 = draw.getNum2();
		int num3 = draw.getNum3();
		int num4 = draw.getNum4();
		int num5 = draw.getNum5();

		if (num1 > 1) {
			if (num2 % num1 == 0) {
				count++;
			}
			if (!adjacentOnly) {
				if (num3 % num1 == 0) {
					count++;
				}
				if (num4 % num1 == 0) {
					count++;
				}
				if (num5 % num1 == 0) {
					count++;
				}
			}
		}

		if (num3 % num2 == 0) {
			count++;
		}
		if (!adjacentOnly) {
			if (num4 % num2 == 0) {
				count++;
			}
			if (num5 % num2 == 0) {
				count++;
			}
		}

		if (num4 % num3 == 0) {
			count++;
		}
		if (!adjacentOnly) {
			if (num5 % num3 == 0) {
				count++;
			}
		}

		if (num5 % num4 == 0) {
			count++;
		}

		return count;
	}

	/**
	 * Get continuous numbers count among the 5 numbers.
	 * 
	 * @param draw
	 * @return
	 */
	public static int getContinuousNumbersCount(Draw draw) {
		int count = 0;

		int num1 = draw.getNum1();
		int num2 = draw.getNum2();
		int num3 = draw.getNum3();
		int num4 = draw.getNum4();
		int num5 = draw.getNum5();

		if (num1 + 1 == num2) {
			count++;
		}

		if (num2 + 1 == num3) {
			count++;
		}

		if (num3 + 1 == num4) {
			count++;
		}

		if (num4 + 1 == num5) {
			count++;
		}

		return count;
	}

	public static int getSameDoubleDigitsCount(Draw draw) {
		int count = 0;

		int num1 = draw.getNum1();
		int num2 = draw.getNum2();
		int num3 = draw.getNum3();
		int num4 = draw.getNum4();
		int num5 = draw.getNum5();

		if (num1 == 11 || num1 == 22 || num1 == 33 || num1 == 44 || num1 == 55 || num1 == 66) {
			count++;
		}

		if (num2 == 11 || num2 == 22 || num2 == 33 || num2 == 44 || num2 == 55 || num2 == 66) {
			count++;
		}

		if (num3 == 11 || num3 == 22 || num3 == 33 || num3 == 44 || num3 == 55 || num3 == 66) {
			count++;
		}

		if (num4 == 11 || num4 == 22 || num4 == 33 || num4 == 44 || num4 == 55 || num4 == 66) {
			count++;
		}

		if (num5 == 11 || num5 == 22 || num5 == 33 || num5 == 44 || num5 == 55 || num5 == 66) {
			count++;
		}

		return count;
	}

	/**
	 * 
	 * @param min
	 * @param max
	 * @param current
	 * @return
	 */
	public static float getPercent(float min, float max, float current) {
		if (min == max) {
			return 0;
		}
		float result = (current - min) / (max - min) * 100f;
		return new BigDecimal(result).setScale(1, RoundingMode.HALF_UP).floatValue();
	}

	/**
	 * 
	 * @param draw
	 * @param includePB
	 * @return
	 */
	public static List<DrawDigit> getDigitsCount(Draw draw, boolean includePB) {
		DrawDigit d0 = new DrawDigit(0);
		DrawDigit d1 = new DrawDigit(1);
		DrawDigit d2 = new DrawDigit(2);
		DrawDigit d3 = new DrawDigit(3);
		DrawDigit d4 = new DrawDigit(4);
		DrawDigit d5 = new DrawDigit(5);
		DrawDigit d6 = new DrawDigit(6);
		DrawDigit d7 = new DrawDigit(7);
		DrawDigit d8 = new DrawDigit(8);
		DrawDigit d9 = new DrawDigit(9);

		int maxIndex = includePB ? 5 : 4;
		for (int index = 0; index <= maxIndex; index++) {
			int num = draw.getNum(index);
			String numStr = Integer.valueOf(num).toString();

			if (numStr.length() == 1) {
				int digit2 = num;

				switch (digit2) {
				case 0:
					d0.increaseCount();
					break;
				case 1:
					d1.increaseCount();
					break;
				case 2:
					d2.increaseCount();
					break;
				case 3:
					d3.increaseCount();
					break;
				case 4:
					d4.increaseCount();
					break;
				case 5:
					d5.increaseCount();
					break;
				case 6:
					d6.increaseCount();
					break;
				case 7:
					d7.increaseCount();
					break;
				case 8:
					d8.increaseCount();
					break;
				case 9:
					d9.increaseCount();
					break;
				default:
				}

			} else if (numStr.length() == 2) {
				int digit1 = Integer.parseInt(numStr.substring(0, 1));
				int digit2 = Integer.parseInt(numStr.substring(1, 2));

				switch (digit1) {
				case 0:
					d0.increaseCount();
					break;
				case 1:
					d1.increaseCount();
					break;
				case 2:
					d2.increaseCount();
					break;
				case 3:
					d3.increaseCount();
					break;
				case 4:
					d4.increaseCount();
					break;
				case 5:
					d5.increaseCount();
					break;
				case 6:
					d6.increaseCount();
					break;
				case 7:
					d7.increaseCount();
					break;
				case 8:
					d8.increaseCount();
					break;
				case 9:
					d9.increaseCount();
					break;
				default:
				}

				switch (digit2) {
				case 0:
					d0.increaseCount();
					break;
				case 1:
					d1.increaseCount();
					break;
				case 2:
					d2.increaseCount();
					break;
				case 3:
					d3.increaseCount();
					break;
				case 4:
					d4.increaseCount();
					break;
				case 5:
					d5.increaseCount();
					break;
				case 6:
					d6.increaseCount();
					break;
				case 7:
					d7.increaseCount();
					break;
				case 8:
					d8.increaseCount();
					break;
				case 9:
					d9.increaseCount();
					break;
				default:
				}
			}
		}

		List<DrawDigit> digits = new ArrayList<DrawDigit>();
		digits.add(d0);
		digits.add(d1);
		digits.add(d2);
		digits.add(d3);
		digits.add(d4);
		digits.add(d5);
		digits.add(d6);
		digits.add(d7);
		digits.add(d8);
		digits.add(d9);

		/*-
		Collections.sort(digits, DrawDigitComparatorByCount.DESC);
		List<DrawDigit> top3Digits = new ArrayList<DrawDigit>();
		top3Digits.add(digits.get(0));
		top3Digits.add(digits.get(1));
		top3Digits.add(digits.get(2));
		*/
		return digits;
	}

	/**
	 * 
	 * @param draws
	 * @param includePB
	 * @return
	 */
	public static Map<String, Integer> getFirstDigitsCount(List<Draw> draws, boolean includePB) {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for (Draw draw : draws) {
			String key = DrawUtil.getFirstDigitsString(draw, false);
			Integer count = map.get(key);
			if (count == null) {
				map.put(key, Integer.valueOf(1));
			} else {
				map.put(key, Integer.valueOf(count.intValue() + 1));
			}
		}
		return map;
	}

	/**
	 * 
	 * @param draws
	 * @param includePB
	 * @return
	 */
	public static Map<String, Integer> getFirstDigitsCount(List<Draw> draws, boolean includePB, int minThreshold) {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for (Draw draw : draws) {
			String key = DrawUtil.getFirstDigitsString(draw, false);
			Integer count = map.get(key);
			if (count == null) {
				map.put(key, Integer.valueOf(1));
			} else {
				map.put(key, Integer.valueOf(count.intValue() + 1));
			}
		}

		Iterator<String> itor = map.keySet().iterator();
		while (itor.hasNext()) {
			String key = itor.next();
			Integer count = map.get(key);
			if (count.intValue() < minThreshold) {
				itor.remove();
			}
		}

		return map;
	}

	/**
	 * 
	 * @param draw
	 * @param includePB
	 * @return
	 */
	public static String getFirstDigitsString(Draw draw, boolean includePB) {
		String str = "";
		String num1 = String.valueOf(draw.getNum1());
		str += (num1.length() == 1) ? "0" : num1.substring(0, 1);

		String num2 = String.valueOf(draw.getNum2());
		str += "-";
		str += (num2.length() == 1) ? "0" : num2.substring(0, 1);

		String num3 = String.valueOf(draw.getNum3());
		str += "-";
		str += (num3.length() == 1) ? "0" : num3.substring(0, 1);

		String num4 = String.valueOf(draw.getNum4());
		str += "-";
		str += (num4.length() == 1) ? "0" : num4.substring(0, 1);

		String num5 = String.valueOf(draw.getNum5());
		str += "-";
		str += (num5.length() == 1) ? "0" : num5.substring(0, 1);

		if (includePB) {
			str += "-";
			String pb = String.valueOf(draw.getPB());
			str += (pb.length() == 1) ? "0" : pb.substring(0, 1);
		}

		return str;
	}

	public static boolean matchFirstDigits(Draw draw1, Draw draw2) {
		String str1 = getFirstDigitsString(draw1, false);
		String str2 = getFirstDigitsString(draw2, false);
		return (str1.equals(str2)) ? true : false;
	}

	public static void main(String[] args) {
		System.out.println("Math.pow(5, 2) = " + Math.pow(5, 2));
		System.out.println("Math.pow(4, 2) = " + Math.pow(4, 2));
		System.out.println("Math.pow(4, 3) = " + Math.pow(4, 3));
		System.out.println("Math.sqrt(64) = " + Math.sqrt(64));
		System.out.println("Math.sqrt(256) = " + Math.sqrt(256));
	}

	/**
	 * 
	 * @param prevDraw
	 * @param lastDraw
	 * @return
	 */
	public static int getHorizontalLinesMatchCount(Draw prevDraw, Draw lastDraw) {
		if (prevDraw == null || lastDraw == null) {
			return 0;
		}

		int count = 0;
		// Value range is [0, 6]
		int num11 = prevDraw.getNum1() / 10;
		int num12 = prevDraw.getNum2() / 10;
		int num13 = prevDraw.getNum3() / 10;
		int num14 = prevDraw.getNum4() / 10;
		int num15 = prevDraw.getNum5() / 10;

		if (prevDraw.getNum1() % 10 == 0) {
			num11--;
		}
		if (prevDraw.getNum2() % 10 == 0) {
			num12--;
		}
		if (prevDraw.getNum3() % 10 == 0) {
			num13--;
		}
		if (prevDraw.getNum4() % 10 == 0) {
			num14--;
		}
		if (prevDraw.getNum5() % 10 == 0) {
			num15--;
		}

		int num21 = lastDraw.getNum1() / 10;
		int num22 = lastDraw.getNum2() / 10;
		int num23 = lastDraw.getNum3() / 10;
		int num24 = lastDraw.getNum4() / 10;
		int num25 = lastDraw.getNum5() / 10;

		if (lastDraw.getNum1() % 10 == 0) {
			num21--;
		}
		if (lastDraw.getNum2() % 10 == 0) {
			num22--;
		}
		if (lastDraw.getNum3() % 10 == 0) {
			num23--;
		}
		if (lastDraw.getNum4() % 10 == 0) {
			num24--;
		}
		if (lastDraw.getNum5() % 10 == 0) {
			num25--;
		}

		// Array size is 7
		int[] array = new int[8];
		array[num11] = 1;
		array[num12] = 1;
		array[num13] = 1;
		array[num14] = 1;
		array[num15] = 1;

		if (array[num21] == 1) {
			count++;
			array[num21] = 2;
		}
		if (array[num22] == 1) {
			count++;
			array[num22] = 2;
		}
		if (array[num23] == 1) {
			count++;
			array[num23] = 2;
		}
		if (array[num24] == 1) {
			count++;
			array[num24] = 2;
		}
		if (array[num25] == 1) {
			count++;
			array[num25] = 2;
		}
		return count;
	}

	/**
	 * 
	 * @param prevDraw
	 * @param lastDraw
	 * @return
	 */
	public static int getVerticalLinesMatchCount(Draw prevDraw, Draw lastDraw) {
		if (prevDraw == null || lastDraw == null) {
			return 0;
		}

		int count = 0;
		// Value range is [0, 9]
		int lastDigit11 = prevDraw.getNum1() % 10;
		int lastDigit12 = prevDraw.getNum2() % 10;
		int lastDigit13 = prevDraw.getNum3() % 10;
		int lastDigit14 = prevDraw.getNum4() % 10;
		int lastDigit15 = prevDraw.getNum5() % 10;

		int lastDigit21 = lastDraw.getNum1() % 10;
		int lastDigit22 = lastDraw.getNum2() % 10;
		int lastDigit23 = lastDraw.getNum3() % 10;
		int lastDigit24 = lastDraw.getNum4() % 10;
		int lastDigit25 = lastDraw.getNum5() % 10;

		// Array size is 10
		int[] array = new int[10];
		array[lastDigit11] = 1;
		array[lastDigit12] = 1;
		array[lastDigit13] = 1;
		array[lastDigit14] = 1;
		array[lastDigit15] = 1;

		if (array[lastDigit21] == 1) {
			count++;
			array[lastDigit21] = 2;
		}
		if (array[lastDigit22] == 1) {
			count++;
			array[lastDigit22] = 2;
		}
		if (array[lastDigit23] == 1) {
			count++;
			array[lastDigit23] = 2;
		}
		if (array[lastDigit24] == 1) {
			count++;
			array[lastDigit24] = 2;
		}
		if (array[lastDigit25] == 1) {
			count++;
			array[lastDigit25] = 2;
		}
		return count;
	}
}
