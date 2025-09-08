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
package org.tauceti.tyche.powerball;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.tauceti.tyche.chart.Report;
import org.tauceti.tyche.model.AreasCount;
import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFilter;
import org.tauceti.tyche.model.impl.DrawFilterImpl;
import org.tauceti.tyche.util.DrawHelper;
import org.tauceti.tyche.util.DrawUtil;
import org.tauceti.tyche.util.MathUtil;
import org.tauceti.tyche.util.StatUtil;
import org.tauceti.tyche.util.MathUtil.Series;
import org.tauceti.tyche.util.MathUtil.ValueRunner;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class PowerballPrintManager {

	public static PowerballPrintManager INSTANCE = new PowerballPrintManager();

	public static String line = "---------------------------------------------------------------------------------------------------------------------------------------------------";

	public PowerballPrintManager() {
	}

	public void print(List<Draw> draws, List<Draw> predictions, List<Report> reports) {
		// printReports(reports);
		printRecentDraws(draws, 60);
		printPredictDraws(predictions);

		// printFirstDigitsCount(draws, 10);
		printNumOnBorderCounts(draws);
		// printAreasCount1(draws, 20);
		// printAreasCount2(draws, 20);

		printExistingDrawsWithSameFirstDigits(draws);
		printExistingDrawsWithSameDistances(draws);
		printExistingDrawsWithSameAreas(draws);
		printExistingDrawsWithSimilarNumbers(draws);
		printExistingDrawsWithSameTrends(draws);

		// printTopSimilarSeries(draws);
	}

	public void printReports(List<Report> reports) {
		System.out.println(line);
		System.out.println("Reports:");
		for (Report report : reports) {
			System.out.println(report);
		}
		System.out.println();
	}

	public void printRecentDraws(List<Draw> draws, int recentDrawSize) {
		// ---------------------------------------------------------------------
		// Recent draws
		// ---------------------------------------------------------------------
		/*-
		Draw lastDraw = draws.get(draws.size() - 1);
		System.out.println(line);
		System.out.println("Last Draw:");
		System.out.println(lastDraw);
		System.out.println();
		*/
		int size = recentDrawSize;
		System.out.println(line);
		System.out.println("Recent draws:");
		for (int i = draws.size() - size; i < draws.size(); i++) {
			Draw draw = draws.get(i);
			System.out.println(draw);
		}
		System.out.println();
	}

	public void printPredictDraws(List<Draw> predictions) {
		System.out.println(line);
		System.out.println("Prediction draws:");
		for (Draw draw : predictions) {
			System.out.println(draw);
		}
		System.out.println();
	}

	public void printFirstDigitsCount(List<Draw> draws, int minThreshold) {
		Map<String, Integer> firstDigitsCount = DrawUtil.getFirstDigitsCount(draws, false, minThreshold);
		System.out.println(line);
		System.out.println("First Digits Count (size=" + firstDigitsCount.size() + "; count threshold=" + minThreshold + "):");
		Iterator<String> itor = firstDigitsCount.keySet().iterator();
		while (itor.hasNext()) {
			String key = itor.next();
			Integer count = firstDigitsCount.get(key);
			System.out.println(key + ": " + count);
		}
		System.out.println();
	}

	public void printNumOnBorderCounts(List<Draw> draws) {
		TreeMap<Integer, Integer> count2TimesMap = new TreeMap<Integer, Integer>();
		for (Draw draw : draws) {
			int currCount = DrawHelper.INSTANCE.getNumsOnBorderCount(draw);

			Integer times = count2TimesMap.get(currCount);
			if (times == null) {
				times = 1;
				count2TimesMap.put(currCount, times);
			} else {
				times = times.intValue() + 1;
				count2TimesMap.put(currCount, times);
			}
		}

		int totalTimes = 0;
		Iterator<Integer> itor0 = count2TimesMap.keySet().iterator();
		while (itor0.hasNext()) {
			Integer onBorderCount = itor0.next();
			Integer times = count2TimesMap.get(onBorderCount);
			totalTimes += times;
		}
		// int totalPercent = 0;

		System.out.println(line);
		System.out.println("Numbers-on-border:");
		Iterator<Integer> itor = count2TimesMap.keySet().iterator();
		while (itor.hasNext()) {
			Integer onBorderCount = itor.next();
			Integer times = count2TimesMap.get(onBorderCount);

			String timesStr = times.toString();
			if (times < 100) {
				timesStr = " " + timesStr;
			}

			int percent = StatUtil.normalizeByPercentage(times, totalTimes, 100);
			// totalPercent += percent;
			String percentStr = String.valueOf(percent);
			if (percent < 10) {
				percentStr = " " + percentStr;
			}

			System.out.println("[Count=" + onBorderCount + "] number of draws: " + timesStr + " (" + percentStr + "%)");
		}
		System.out.println("          Total number of draws: " + totalTimes);
		// System.out.println(" total percent: " + totalPercent + "%");
		System.out.println();
	}

	public void printAreasCount1(List<Draw> draws, int countMinThreshold) {
		System.out.println(line);
		System.out.println("N-S-W-E");

		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		for (Draw draw : draws) {
			AreasCount areasCount = draw.getAreasCount();
			String key = areasCount.getN_count() + "-" + areasCount.getS_count() + "-" + areasCount.getW_count() + "-" + areasCount.getE_count();

			Integer times = map.get(key);
			if (times == null) {
				times = 1;
				map.put(key, times);
			} else {
				times = times.intValue() + 1;
				map.put(key, times);
			}
		}

		Iterator<String> itor = map.keySet().iterator();
		while (itor.hasNext()) {
			String key = itor.next();
			Integer times = map.get(key);

			if (times >= countMinThreshold) {
			} else {
				continue;
			}

			String timesStr = times.toString();
			if (times < 10) {
				timesStr = "  " + timesStr;
			} else if (times < 100) {
				timesStr = " " + timesStr;
			}

			System.out.println("[N-S-W-E=" + key + "] number of draws: " + timesStr);
		}
		System.out.println();
	}

	public void printAreasCount2(List<Draw> draws, int countMinThreshold) {
		System.out.println(line);
		System.out.println("NW-NE-SW-SE");

		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		for (Draw draw : draws) {
			AreasCount areasCount = draw.getAreasCount();
			String key = areasCount.getNW_count() + "-" + areasCount.getNE_count() + "-" + areasCount.getSW_count() + "-" + areasCount.getSE_count();

			Integer times = map.get(key);
			if (times == null) {
				times = 1;
				map.put(key, times);
			} else {
				times = times.intValue() + 1;
				map.put(key, times);
			}
		}

		Iterator<String> itor = map.keySet().iterator();
		while (itor.hasNext()) {
			String key = itor.next();
			Integer times = map.get(key);

			if (times >= countMinThreshold) {
			} else {
				continue;
			}

			String timesStr = times.toString();
			if (times < 10) {
				timesStr = "  " + timesStr;
			} else if (times < 100) {
				timesStr = " " + timesStr;
			}

			System.out.println("[NW-NE-SW-SE=" + key + "] number of draws: " + timesStr);
		}
		System.out.println();
	}

	public void printExistingDrawsWithSameFirstDigits(List<Draw> draws) {
		Draw lastDraw = draws.get(draws.size() - 1);

		System.out.println(line);
		// System.out.println("Last draw:");
		// printDrawAndPreviousDraws(draws, lastDraw, 3);
		// System.out.println();
		System.out.println("1.Existing draws with same first digits:");
		System.out.println();

		String lastDraw_firstDigits = DrawHelper.INSTANCE.getFirstDigitsOf5numbers(lastDraw);
		System.out.println("First digits:");
		System.out.println(lastDraw_firstDigits);
		System.out.println("      " + lastDraw.getPrevDraw().getPrevDraw());
		System.out.println("      " + lastDraw.getPrevDraw());
		System.out.println("      " + lastDraw);
		System.out.println();

		for (Draw draw : draws) {
			if (draw == lastDraw) {
				break;
			}
			String firstDigits = DrawHelper.INSTANCE.getFirstDigitsOf5numbers(draw);
			if (firstDigits.equals(lastDraw_firstDigits)) {
				Draw prevDraw = draw.getPrevDraw();
				Draw nextDraw = draw.getNextDraw();

				String str = "";
				if (prevDraw != null) {
					str += "Prev: " + prevDraw;
					str += "\r\n";
				}

				str += "Curr: " + draw;
				str += "\r\n";

				if (nextDraw != null) {
					str += "Next: " + nextDraw;
					str += "\r\n";
				}
				System.out.println(str);
			}
		}
	}

	public void printExistingDrawsWithSameDistances(List<Draw> draws) {
		final Draw lastDraw = draws.get(draws.size() - 1);

		System.out.println(line);
		// System.out.println("Last draw:");
		// printDrawAndPreviousDraws(draws, lastDraw, 3);
		// System.out.println();
		System.out.println("2.Existing draws with same distances:");
		System.out.println();

		String lastDraw_distancePercentages = DrawHelper.INSTANCE.getDistancePercentages(lastDraw);
		System.out.println("Distance percentages:");
		System.out.println(lastDraw_distancePercentages);
		System.out.println("      " + lastDraw.getPrevDraw().getPrevDraw());
		System.out.println("      " + lastDraw.getPrevDraw());
		System.out.println("      " + lastDraw);
		System.out.println();

		List<Draw> resultDraws = DrawHelper.INSTANCE.getDrawsWithSameDistances(draws, lastDraw, 2);
		for (Draw draw : resultDraws) {
			Draw prevDraw = draw.getPrevDraw();
			Draw nextDraw = draw.getNextDraw();

			String str = "";
			if (prevDraw != null) {
				str += "Prev: " + prevDraw;
				str += "\r\n";
			}

			str += "Curr: " + draw;
			str += "\r\n";

			if (nextDraw != null) {
				str += "Next: " + nextDraw;
				str += "\r\n";
			}
			System.out.println(str);
		}
	}

	/**
	 * 
	 * @param draws
	 */
	public void printExistingDrawsWithSameAreas(List<Draw> draws) {
		final Draw lastDraw = draws.get(draws.size() - 1);

		System.out.println(line);
		// System.out.println("Last draw:");
		// printDrawAndPreviousDraws(draws, lastDraw, 3);
		// System.out.println();
		System.out.println("3.Existing draws with same areas:");
		System.out.println();

		String lastDraw_areas = DrawHelper.INSTANCE.getDistanceAreas(lastDraw);
		System.out.println("Distance areas:");
		System.out.println(lastDraw_areas);
		System.out.println("      " + lastDraw.getPrevDraw().getPrevDraw());
		System.out.println("      " + lastDraw.getPrevDraw());
		System.out.println("      " + lastDraw);
		System.out.println();

		List<Draw> resultDraws = DrawHelper.INSTANCE.getDrawsWithSameAreas(draws, lastDraw, 1);
		for (Draw draw : resultDraws) {
			Draw prevDraw = draw.getPrevDraw();
			Draw nextDraw = draw.getNextDraw();

			String str = "";
			if (prevDraw != null) {
				str += "Prev: " + prevDraw;
				str += "\r\n";
			}

			str += "Curr: " + draw;
			str += "\r\n";

			if (nextDraw != null) {
				str += "Next: " + nextDraw;
				str += "\r\n";
			}
			System.out.println(str);
		}
	}

	public void printExistingDrawsWithSimilarNumbers(List<Draw> draws) {
		final Draw lastDraw = draws.get(draws.size() - 1);

		System.out.println(line);
		// System.out.println("Last draw:");
		// printDrawAndPreviousDraws(draws, lastDraw, 3);
		// System.out.println();

		System.out.println("4.Existing draws with similar numbers:");
		List<Draw> resultDraws = DrawHelper.INSTANCE.getDrawsWithSimilarNumbers(draws, lastDraw, 2, 5);
		for (Draw draw : resultDraws) {
			Draw prevDraw = draw.getPrevDraw();
			Draw nextDraw = draw.getNextDraw();

			String str = "";
			if (prevDraw != null) {
				str += "Prev: " + prevDraw;
				str += "\r\n";
			}

			str += "Curr: " + draw;
			str += "\r\n";

			if (nextDraw != null) {
				str += "Next: " + nextDraw;
				str += "\r\n";
			}
			System.out.println(str);
		}
	}

	public void printExistingDrawsWithSameTrends(List<Draw> draws) {
		final Draw lastDraw = draws.get(draws.size() - 1);

		System.out.println(line);
		// System.out.println("Last draw:");
		// printDrawAndPreviousDraws(draws, lastDraw, 3);
		// System.out.println();

		System.out.println("5.Existing draws with same trends:");
		List<Draw> resultDraws = DrawHelper.INSTANCE.getDrawsWithSameTrends(draws, lastDraw);
		for (Draw draw : resultDraws) {
			Draw prevDraw = draw.getPrevDraw();
			Draw nextDraw = draw.getNextDraw();

			Draw prev_prevDraw = (prevDraw != null) ? prevDraw.getPrevDraw() : null;

			String str = "";
			if (prev_prevDraw != null) {
				str += "Prev0 " + prev_prevDraw;
				str += "\r\n";
			}

			if (prevDraw != null) {
				str += "Prev: " + prevDraw;
				str += "\r\n";
			}

			str += "Curr: " + draw;
			str += "\r\n";

			if (nextDraw != null) {
				str += "Next: " + nextDraw;
				str += "\r\n";
			}
			System.out.println(str);
		}
	}

	public void printSameFirstDigits(List<Draw> draws) {
		final Draw lastDraw = draws.get(draws.size() - 1);

		DrawFilter filter = new DrawFilterImpl() {
			@Override
			public boolean accept(Draw draw) {
				if (draw != lastDraw) {
					if (DrawUtil.matchFirstDigits(lastDraw, draw)) {
						return true;
					}
				}
				return false;
			}
		};

		System.out.println(line);
		System.out.println("Same First Digits Draws in history:");
		int size = draws.size();
		for (int i = 0; i < size - 1; i++) {
			Draw currDraw = draws.get(i);
			Draw nextDraw = draws.get(i + 1);
			if (filter.accept(currDraw)) {
				System.out.println("Curr: " + currDraw);
				System.out.println("Next: " + nextDraw);
				System.out.println();
			}
		}
	}

	public void printDrawAndPreviousDraws(List<Draw> draws, Draw targetDraw, int size) {
		int index = draws.indexOf(targetDraw);
		int startIndex = index - size + 1;

		for (int i = startIndex; i < draws.size(); i++) {
			Draw currDraw = draws.get(i);
			System.out.println(currDraw);
		}
	}

	public static class ValueRunnerImpl implements ValueRunner {
		public int numIndex = 0;

		public ValueRunnerImpl(int numIndex) {
			this.numIndex = numIndex;
		}

		@Override
		public int getValue(Draw drawA, Draw drawB) {
			return drawA.getNum(numIndex) * drawB.getNum(numIndex);
		}

	}

	public void printTopSimilarSeries(List<Draw> draws) {
		int seriesSize = 5;
		int topX = 5;

		System.out.println(line);
		System.out.println("getTopSimilarSeries() seriesSize = " + seriesSize + ", numIndex = 0, topX = " + topX);

		List<Series> list_num0 = MathUtil.getTopSimilarSeries(draws, seriesSize, new ValueRunnerImpl(0), topX);
		for (Series series : list_num0) {
			for (int i = series.startIndex; i <= series.endIndex; i++) {
				Draw currDraw = series.draws.get(i);
				System.out.println(currDraw);
			}
			System.out.println();
		}

		System.out.println(line);
		System.out.println("getTopSimilarSeries() seriesSize = " + seriesSize + ", numIndex = 1, topX = " + topX);
		List<Series> list_num1 = MathUtil.getTopSimilarSeries(draws, seriesSize, new ValueRunnerImpl(1), topX);
		for (Series series : list_num1) {
			for (int i = series.startIndex; i <= series.endIndex; i++) {
				Draw currDraw = series.draws.get(i);
				System.out.println(currDraw);
			}
			System.out.println();
		}

		System.out.println(line);
		System.out.println("getTopSimilarSeries() seriesSize = " + seriesSize + ", numIndex = 2, topX = " + topX);
		List<Series> list_num2 = MathUtil.getTopSimilarSeries(draws, seriesSize, new ValueRunnerImpl(2), topX);
		for (Series series : list_num2) {
			for (int i = series.startIndex; i <= series.endIndex; i++) {
				Draw currDraw = series.draws.get(i);
				System.out.println(currDraw);
			}
			System.out.println();
		}

		System.out.println(line);
		System.out.println("getTopSimilarSeries() seriesSize = " + seriesSize + ", numIndex = 3, topX = " + topX);
		List<Series> list_num3 = MathUtil.getTopSimilarSeries(draws, seriesSize, new ValueRunnerImpl(3), topX);
		for (Series series : list_num3) {
			for (int i = series.startIndex; i <= series.endIndex; i++) {
				Draw currDraw = series.draws.get(i);
				System.out.println(currDraw);
			}
			System.out.println();
		}

		System.out.println(line);
		System.out.println("getTopSimilarSeries() seriesSize = " + seriesSize + ", numIndex = 4, topX = " + topX);
		List<Series> list_num4 = MathUtil.getTopSimilarSeries(draws, seriesSize, new ValueRunnerImpl(4), topX);
		for (Series series : list_num4) {
			for (int i = series.startIndex; i <= series.endIndex; i++) {
				Draw currDraw = series.draws.get(i);
				System.out.println(currDraw);
			}
			System.out.println();
		}
	}
}
