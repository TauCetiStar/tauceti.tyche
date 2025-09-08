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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.tauceti.tyche.chart.ReportConstants;
import org.tauceti.tyche.model.AreasCount;
import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFilter;
import org.tauceti.tyche.model.DrawReader;
import org.tauceti.tyche.model.DrawWriter;
import org.tauceti.tyche.model.Trend;
import org.tauceti.tyche.model.impl.DrawImpl;
import org.tauceti.tyche.model.impl.DrawWriterImpl;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class DrawHelper {

	public static List<Integer> N_NUMBERS = new ArrayList<Integer>();
	public static List<Integer> S_NUMBERS = new ArrayList<Integer>();
	public static List<Integer> W_NUMBERS = new ArrayList<Integer>();
	public static List<Integer> E_NUMBERS = new ArrayList<Integer>();

	public static List<Integer> NW_NUMBERS = new ArrayList<Integer>();
	public static List<Integer> NE_NUMBERS = new ArrayList<Integer>();
	public static List<Integer> SW_NUMBERS = new ArrayList<Integer>();
	public static List<Integer> SE_NUMBERS = new ArrayList<Integer>();

	public static List<Integer> PB_SECTION_1 = new ArrayList<Integer>();
	public static List<Integer> PB_SECTION_2 = new ArrayList<Integer>();
	public static List<Integer> PB_SECTION_3 = new ArrayList<Integer>();
	public static List<Integer> PB_SECTION_4 = new ArrayList<Integer>();
	public static List<Integer> PB_SECTION_5 = new ArrayList<Integer>();

	static {
		// NORTH
		// 1-40
		for (int i = 1; i <= 40; i++) {
			N_NUMBERS.add(i);
		}

		// SOUTH
		// 41-70
		for (int i = 41; i <= 70; i++) {
			S_NUMBERS.add(i);
		}

		// WEST
		// 1-5, 11-15, 21-25, 31-35, 41-45, 51-55, 61-65
		for (int i = 0; i <= 6; i++) {
			for (int j = 1; j <= 5; j++) {
				int num = i * 10 + j;
				W_NUMBERS.add(num);
			}
		}

		// EAST
		// 6-10, 16-20, 26-30, 36-40, 46-50, 56-60, 66-70
		for (int i = 0; i <= 6; i++) {
			for (int j = 6; j <= 10; j++) {
				int num = i * 10 + j;
				E_NUMBERS.add(num);
			}
		}

		// NORTH-WEST
		for (int i = 0; i <= 3; i++) {
			for (int j = 1; j <= 5; j++) {
				int num = i * 10 + j;
				NW_NUMBERS.add(num);
			}
		}

		// NORTH-EAST
		for (int i = 0; i <= 3; i++) {
			for (int j = 6; j <= 10; j++) {
				int num = i * 10 + j;
				NE_NUMBERS.add(num);
			}
		}

		// SOUTH-WEST
		for (int i = 4; i <= 6; i++) {
			for (int j = 1; j <= 5; j++) {
				int num = i * 10 + j;
				SW_NUMBERS.add(num);
			}
		}

		// SOUTH-EAST
		for (int i = 4; i <= 6; i++) {
			for (int j = 6; j <= 10; j++) {
				int num = i * 10 + j;
				SE_NUMBERS.add(num);
			}
		}

		for (int i = 1; i <= 5; i++) {
			PB_SECTION_1.add(i);
			PB_SECTION_2.add(i + 5);
			PB_SECTION_3.add(i + 10);
			PB_SECTION_4.add(i + 15);
			PB_SECTION_5.add(i + 20);
		}

		/*-
		printNumbers("NORTH", NUMBERS_N);
		printNumbers("SOUTH", NUMBERS_S);
		printNumbers("WEST", NUMBERS_W);
		printNumbers("EAST", NUMBERS_E);
		
		printNumbers("NORTH-WEST", NUMBERS_NW);
		printNumbers("NORTH-EAST", NUMBERS_NE);
		printNumbers("SOUTH-WEST", NUMBERS_SW);
		printNumbers("SOUTH-EAST", NUMBERS_SE);
		*/
	}

	public static void printNumbers(String label, List<Integer> numbers) {
		System.out.println(label);
		Integer[] numbersArray = numbers.toArray(new Integer[numbers.size()]);
		String numbersStr = Arrays.toString(numbersArray);
		System.out.println(numbersStr);
		System.out.println();
	}

	public static DrawHelper INSTANCE = new DrawHelper();

	public Date getDate(String dateStr) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param args
	 * @return
	 */
	public boolean isPowerball(Map<String, Object> args) {
		if (args != null) {
			String type = (String) args.get(ReportConstants.ARG__DRAWS_TYPE);
			if (ReportConstants.DRAWS_TYPE__POWERBALL.equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param args
	 * @return
	 */
	public boolean isMegaMillion(Map<String, Object> args) {
		if (args != null) {
			String type = (String) args.get(ReportConstants.ARG__DRAWS_TYPE);
			if (ReportConstants.DRAWS_TYPE__MEGA_MILLION.equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Draw> getDraws(Map<String, Object> args) {
		List<Draw> draws = null;
		if (args != null) {
			draws = (List<Draw>) args.get(ReportConstants.ARG__DRAWS_NUMBERS);
		}
		if (draws == null) {
			draws = new ArrayList<Draw>();
		}
		return draws;
	}

	/**
	 * 
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Draw> getPrevPredictionDraws(Map<String, Object> args) {
		List<Draw> draws = null;
		if (args != null) {
			draws = (List<Draw>) args.get(ReportConstants.ARG__DRAWS_PREV_PREDICTIONS);
		}
		if (draws == null) {
			draws = new ArrayList<Draw>();
		}
		return draws;
	}

	/**
	 * 
	 * @param args
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Draw> getPrevPredictionDraws(Map<String, Object> args, int index) {
		List<Draw> draws = null;
		if (args != null) {
			String key = ReportConstants.ARG__DRAWS_PREV_PREDICTIONS + index;
			draws = (List<Draw>) args.get(key);
		}
		return draws;
	}

	/**
	 * 
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Draw> getPredictionDraws(Map<String, Object> args) {
		List<Draw> draws = null;
		if (args != null) {
			draws = (List<Draw>) args.get(ReportConstants.ARG__DRAWS_PREDICTIONS);
		}
		if (draws == null) {
			draws = new ArrayList<Draw>();
		}
		return draws;
	}

	/**
	 * 
	 * @param args
	 * @param index
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Draw> getPredictionDraws(Map<String, Object> args, int index) {
		List<Draw> draws = null;
		if (args != null) {
			String key = ReportConstants.ARG__DRAWS_PREDICTIONS + index;
			draws = (List<Draw>) args.get(key);
		}
		return draws;
	}

	/**
	 * 
	 * @param draws
	 * @param file
	 */
	public void write(List<Draw> draws, File file) {
		FileOutputStream fos = null;
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();

			fos = new FileOutputStream(file);

			DrawWriter writer = new DrawWriterImpl();
			writer.write(draws, fos);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Draw> read(File file, DrawReader reader) {
		return read(file, reader, null);
	}

	public List<Draw> read(File file, DrawReader reader, DrawFilter filter) {
		if (file == null || !file.exists()) {
			return Collections.emptyList();
		}

		List<Draw> draws = null;

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			draws = reader.read(fis, filter);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtil.closeQuietly(fis, true);
		}

		if (draws == null) {
			draws = Collections.emptyList();
		}

		updateDraws(draws);

		return draws;
	}

	/**
	 * 
	 * @param draws
	 */
	public void updateDraws(List<Draw> draws) {
		if (draws != null) {
			int size = draws.size();
			for (int i = 0; i < size; i++) {
				Draw draw = draws.get(i);

				// Update prev and next Draws
				Draw prevDraw = (i > 0) ? draws.get(i - 1) : null;
				Draw nextDraw = (i < size - 1) ? draws.get(i + 1) : null;

				updateDraw(prevDraw, draw, nextDraw);
			}
		}
	}

	/**
	 * 
	 * @param prevDraw
	 * @param draw
	 * @param nextDraw
	 */
	public void updateDraw(Draw prevDraw, Draw draw, Draw nextDraw) {
		draw.setPrevDraw(prevDraw);
		draw.setNextDraw(nextDraw);

		// Update distances
		int distValue1 = draw.getNum2() - draw.getNum1();
		int distValue2 = draw.getNum3() - draw.getNum2();
		int distValue3 = draw.getNum4() - draw.getNum3();
		int distValue4 = draw.getNum5() - draw.getNum4();
		int diameter = draw.getNum5() - draw.getNum1();

		int distSum = distValue1 + distValue2 + distValue3 + distValue4;
		float distPercent1 = ((float) distValue1 / (float) distSum) * 100;
		float distPercent2 = ((float) distValue2 / (float) distSum) * 100;
		float distPercent3 = ((float) distValue3 / (float) distSum) * 100;
		float distPercent4 = ((float) distValue4 / (float) distSum) * 100;

		float distPercent1a = new BigDecimal(distPercent1).setScale(0, RoundingMode.HALF_UP).floatValue();
		float distPercent2a = new BigDecimal(distPercent2).setScale(0, RoundingMode.HALF_UP).floatValue();
		float distPercent3a = new BigDecimal(distPercent3).setScale(0, RoundingMode.HALF_UP).floatValue();
		float distPercent4a = new BigDecimal(distPercent4).setScale(0, RoundingMode.HALF_UP).floatValue();

		draw.setDistancesValue(new int[] { distValue1, distValue2, distValue3, distValue4 });
		draw.setDistancesPercent(new float[] { distPercent1a, distPercent2a, distPercent3a, distPercent4a });

		// Update trend
		Trend[] trend = new Trend[] { Trend.NONE, Trend.NONE, Trend.NONE, Trend.NONE, Trend.NONE, Trend.NONE };
		if (prevDraw != null) {
			trend[0] = getTrend(prevDraw.getNum1(), draw.getNum1());
			trend[1] = getTrend(prevDraw.getNum2(), draw.getNum2());
			trend[2] = getTrend(prevDraw.getNum3(), draw.getNum3());
			trend[3] = getTrend(prevDraw.getNum4(), draw.getNum4());
			trend[4] = getTrend(prevDraw.getNum5(), draw.getNum5());
			trend[5] = getTrend(prevDraw.getPB(), draw.getPB());
		}
		draw.setTrend(trend);

		// Update areas
		// int height = 2;
		int area = 0;
		int area1 = 0;
		int area2 = 0;
		int area3 = 0;
		int area4 = 0;
		if (prevDraw != null) {
			area = ((draw.getNum5() - draw.getNum1()) + (prevDraw.getNum5() - prevDraw.getNum1()));

			area1 = ((draw.getNum2() - draw.getNum1()) + (prevDraw.getNum2() - prevDraw.getNum1()));
			area2 = ((draw.getNum3() - draw.getNum2()) + (prevDraw.getNum3() - prevDraw.getNum2()));
			area3 = ((draw.getNum4() - draw.getNum3()) + (prevDraw.getNum4() - prevDraw.getNum3()));
			area4 = ((draw.getNum5() - draw.getNum4()) + (prevDraw.getNum5() - prevDraw.getNum4()));
		}
		draw.setDistanceAreas(new int[] { area1, area2, area3, area4 });

		// Count in areas
		int N_count = 0;
		int S_count = 0;
		int W_count = 0;
		int E_count = 0;

		int NW_count = 0;
		int NE_count = 0;
		int SW_count = 0;
		int SE_count = 0;

		for (int i = 0; i < 5; i++) {
			int num = draw.getNum(i);
			if (N_NUMBERS.contains(num)) {
				N_count++;
			}
			if (S_NUMBERS.contains(num)) {
				S_count++;
			}
			if (W_NUMBERS.contains(num)) {
				W_count++;
			}
			if (E_NUMBERS.contains(num)) {
				E_count++;
			}

			if (NW_NUMBERS.contains(num)) {
				NW_count++;
			}
			if (NE_NUMBERS.contains(num)) {
				NE_count++;
			}
			if (SW_NUMBERS.contains(num)) {
				SW_count++;
			}
			if (SE_NUMBERS.contains(num)) {
				SE_count++;
			}
		}

		int PB_section = -1;
		int PB_num = draw.getPB();
		if (PB_SECTION_1.contains(PB_num)) {
			PB_section = 1;
		} else if (PB_SECTION_2.contains(PB_num)) {
			PB_section = 2;
		} else if (PB_SECTION_3.contains(PB_num)) {
			PB_section = 3;
		} else if (PB_SECTION_4.contains(PB_num)) {
			PB_section = 4;
		} else if (PB_SECTION_5.contains(PB_num)) {
			PB_section = 5;
		} else {
			PB_section = 6;
			// throw new RuntimeException("Draw '" + draw + " - PB number " + PB_num + " is not supported.");
			// System.err.println("Draw '" + draw + " - PB number " + PB_num + " is not supported.");
		}

		AreasCount areasCount = new AreasCount(N_count, S_count, W_count, E_count, NW_count, NE_count, SW_count, SE_count, PB_section);
		draw.setAreasCount(areasCount);

		draw.setDiameter(diameter);
		draw.setArea(area);
	}

	public Trend getTrend(int prevNum, int num) {
		Trend trend = null;
		if (prevNum == num) {
			trend = Trend.FLAT;
		} else if (prevNum < num) {
			trend = Trend.UP;
		} else {
			trend = Trend.DOWN;
		}
		return trend;
	}

	public Trend getTrend2(int prevNum, int num) {
		Trend trend = null;
		if ((prevNum - 1) <= num && num <= (prevNum + 1)) {
			trend = Trend.FLAT;
		} else if ((prevNum + 1) < num) {
			trend = Trend.UP;
		} else {
			trend = Trend.DOWN;
		}
		return trend;
	}

	public String getFirstDigitsOf5numbers(Draw draw) {
		String str = "";
		str += String.valueOf(draw.getNum1() / 10) + "-";
		str += String.valueOf(draw.getNum2() / 10) + "-";
		str += String.valueOf(draw.getNum3() / 10) + "-";
		str += String.valueOf(draw.getNum4() / 10) + "-";
		str += String.valueOf(draw.getNum5() / 10);
		return str;
	}

	public String getFirstDigitsOf6numbers(Draw draw) {
		String str = "";
		str += String.valueOf(draw.getNum1() / 10) + "-";
		str += String.valueOf(draw.getNum2() / 10) + "-";
		str += String.valueOf(draw.getNum3() / 10) + "-";
		str += String.valueOf(draw.getNum4() / 10) + "-";
		str += String.valueOf(draw.getNum5() / 10) + "-";
		str += String.valueOf(draw.getPB() / 10);
		return str;
	}

	public String getDistancePercentages(Draw draw) {
		return draw.getDistancePercent(0) + "% " + draw.getDistancePercent(1) + "% " + draw.getDistancePercent(2) + "% " + draw.getDistancePercent(3) + "%";
	}

	public String getDistanceAreas(Draw draw) {
		return draw.getDistanceAreas()[0] + " " + draw.getDistanceAreas()[1] + " " + draw.getDistanceAreas()[2] + " " + draw.getDistanceAreas()[3];
	}

	/**
	 * 
	 * @param allDraws
	 * @return
	 */
	public Map<Integer, List<Draw>> groupByYear(List<Draw> allDraws) {
		return groupByYear(allDraws, 0);
	}

	/**
	 * 
	 * @param allDraws
	 * @param numberOfDrawsFromNextYear
	 * @return
	 */
	public Map<Integer, List<Draw>> groupByYear(List<Draw> allDraws, int numberOfDrawsFromNextYear) {
		Map<Integer, List<Draw>> yearToDraws = new TreeMap<Integer, List<Draw>>();

		for (int i = 0; i < allDraws.size(); i++) {
			Draw draw = allDraws.get(i);
			int year = DateUtil.getYear(draw.getDate());

			List<Draw> draws = yearToDraws.get(year);
			if (draws == null) {
				draws = new ArrayList<Draw>();
				yearToDraws.put(year, draws);
			}
			draws.add(draw);
		}

		if (numberOfDrawsFromNextYear > 0) {
			for (Iterator<Integer> yearItor = yearToDraws.keySet().iterator(); yearItor.hasNext();) {
				int year = yearItor.next();
				int nextYear = year + 1;

				List<Draw> drawsOfNextYear = yearToDraws.get(nextYear);
				if (drawsOfNextYear != null && !drawsOfNextYear.isEmpty()) {
					List<Draw> drawsOfCurrYear = yearToDraws.get(year);
					if (drawsOfCurrYear == null) {
						drawsOfCurrYear = new ArrayList<Draw>();
						yearToDraws.put(year, drawsOfCurrYear);
					}

					int numToAdd = numberOfDrawsFromNextYear < drawsOfNextYear.size() ? numberOfDrawsFromNextYear : drawsOfNextYear.size();
					drawsOfCurrYear.addAll(drawsOfNextYear.subList(0, numToAdd));
				}
			}
		}

		return yearToDraws;
	}

	/**
	 * 
	 * @param allDraws
	 * @return
	 */
	public Map<Integer, List<Draw>> groupByDrawId(List<Draw> allDraws) {
		Map<Integer, List<Draw>> idToDraws = new TreeMap<Integer, List<Draw>>();
		for (Draw draw : allDraws) {
			int drawId = draw.getDrawId();

			List<Draw> draws = idToDraws.get(drawId);
			if (draws == null) {
				draws = new ArrayList<Draw>();
				idToDraws.put(drawId, draws);
			}
			draws.add(draw);
		}
		return idToDraws;
	}

	/**
	 * 
	 * @param num1
	 * @param num5
	 * @param distancesPercent
	 * @return
	 */
	public int[] getNumbers(int num1, int num5, float[] distancesPercent) {
		int total = num5 - num1;
		float totalPercent = distancesPercent[0] + distancesPercent[1] + distancesPercent[2] + distancesPercent[3];
		float part1 = total * distancesPercent[0] / totalPercent;
		float part2 = total * distancesPercent[1] / totalPercent;
		float part3 = total * distancesPercent[2] / totalPercent;
		// float part4 = total * distancesPercent[3] / totalPercent;

		int num2 = (int) (num1 + part1);
		int num3 = (int) (num2 + part2);
		int num4 = (int) (num3 + part3);

		int[] numbers = new int[] { num1, num2, num3, num4, num5 };
		return numbers;
	}

	/**
	 * 
	 * @param draws
	 * @param distPercent1
	 * @param distPercent2
	 * @param distPercent3
	 * @param distPercent4
	 * @return
	 */
	public List<Draw> getDrawsWithSameDistances(List<Draw> draws, Draw lastDraw, int distPercent1, int distPercent2, int distPercent3, int distPercent4, int range) {
		List<Draw> resultDraws = new ArrayList<Draw>();
		for (Draw draw : draws) {
			if (draw == lastDraw) {
				continue;
			}
			int matchCount = 0;
			float currDistPercent1 = draw.getDistancePercent(0);
			float currDistPercent2 = draw.getDistancePercent(1);
			float currDistPercent3 = draw.getDistancePercent(2);
			float currDistPercent4 = draw.getDistancePercent(3);

			if (Math.abs(currDistPercent1 - distPercent1) <= range) {
				matchCount += 1;
			}
			if (Math.abs(currDistPercent2 - distPercent2) <= range) {
				matchCount += 1;
			}
			if (Math.abs(currDistPercent3 - distPercent3) <= range) {
				matchCount += 1;
			}
			if (Math.abs(currDistPercent4 - distPercent4) <= range) {
				matchCount += 1;
			}

			if (matchCount >= 3) {
				resultDraws.add(draw);
			}
		}
		return resultDraws;
	}

	/**
	 * 
	 * @param draws
	 * @param targetDraw
	 * @param range
	 * @return
	 */
	public List<Draw> getDrawsWithSameDistances(List<Draw> draws, Draw targetDraw, int range) {
		int distPercent0 = (int) targetDraw.getDistancePercent(0);
		int distPercent1 = (int) targetDraw.getDistancePercent(1);
		int distPercent2 = (int) targetDraw.getDistancePercent(2);
		int distPercent3 = (int) targetDraw.getDistancePercent(3);

		List<Draw> resultDraws = new ArrayList<Draw>();
		for (Draw draw : draws) {
			if (draw == targetDraw) {
				continue;
			}
			int matchCount = 0;
			float currDistPercent0 = draw.getDistancePercent(0);
			float currDistPercent1 = draw.getDistancePercent(1);
			float currDistPercent2 = draw.getDistancePercent(2);
			float currDistPercent3 = draw.getDistancePercent(3);

			if (Math.abs(currDistPercent0 - distPercent0) <= range) {
				matchCount += 1;
			}
			if (Math.abs(currDistPercent1 - distPercent1) <= range) {
				matchCount += 1;
			}
			if (Math.abs(currDistPercent2 - distPercent2) <= range) {
				matchCount += 1;
			}
			if (Math.abs(currDistPercent3 - distPercent3) <= range) {
				matchCount += 1;
			}

			if (matchCount >= 3) {
				resultDraws.add(draw);
			}
		}
		return resultDraws;
	}

	/**
	 * 
	 * @param draws
	 * @param targetDraw
	 * @param range
	 * @return
	 */
	public List<Draw> getDrawsWithSameAreas(List<Draw> draws, Draw targetDraw, int range) {
		int area0 = targetDraw.getDistanceAreas()[0];
		int area1 = targetDraw.getDistanceAreas()[1];
		int area2 = targetDraw.getDistanceAreas()[2];
		int area3 = targetDraw.getDistanceAreas()[3];

		List<Draw> resultDraws = new ArrayList<Draw>();
		for (Draw currDraw : draws) {
			if (currDraw == targetDraw) {
				continue;
			}
			int matchCount = 0;
			float currArea0 = currDraw.getDistanceAreas()[0];
			float currArea1 = currDraw.getDistanceAreas()[1];
			float currArea2 = currDraw.getDistanceAreas()[2];
			float currArea3 = currDraw.getDistanceAreas()[3];

			if (Math.abs(currArea0 - area0) <= range) {
				matchCount += 1;
			}
			if (Math.abs(currArea1 - area1) <= range) {
				matchCount += 1;
			}
			if (Math.abs(currArea2 - area2) <= range) {
				matchCount += 1;
			}
			if (Math.abs(currArea3 - area3) <= range) {
				matchCount += 1;
			}

			if (matchCount >= 3) {
				resultDraws.add(currDraw);
			}
		}
		return resultDraws;
	}

	/**
	 * 
	 * @param draws
	 * @param targetDraw
	 * @param range
	 * @param countThreshold
	 * @return
	 */
	public List<Draw> getDrawsWithSimilarNumbers(List<Draw> draws, Draw targetDraw, int range, int countThreshold) {
		int num0 = targetDraw.getNum(0);
		int num1 = targetDraw.getNum(1);
		int num2 = targetDraw.getNum(2);
		int num3 = targetDraw.getNum(3);
		int num4 = targetDraw.getNum(4);

		List<Draw> resultDraws = new ArrayList<Draw>();
		for (Draw currDraw : draws) {
			if (currDraw == targetDraw) {
				continue;
			}
			int matchCount = 0;
			int curr_num0 = currDraw.getNum(0);
			int curr_num1 = currDraw.getNum(1);
			int curr_num2 = currDraw.getNum(2);
			int curr_num3 = currDraw.getNum(3);
			int curr_num4 = currDraw.getNum(4);

			if (Math.abs(curr_num0 - num0) <= range) {
				matchCount += 1;
			}
			if (Math.abs(curr_num1 - num1) <= range) {
				matchCount += 1;
			}
			if (Math.abs(curr_num2 - num2) <= range) {
				matchCount += 1;
			}
			if (Math.abs(curr_num3 - num3) <= range) {
				matchCount += 1;
			}
			if (Math.abs(curr_num4 - num4) <= range) {
				matchCount += 1;
			}

			if (matchCount >= countThreshold) {
				resultDraws.add(currDraw);
			}
		}
		return resultDraws;
	}

	/**
	 * 
	 * @param draws
	 * @param targetDraw
	 * @return
	 */
	public List<Draw> getDrawsWithSameTrends(List<Draw> draws, Draw targetDraw) {
		Draw prev_targetDraw1 = targetDraw.getPrevDraw();
		Draw prev_targetDraw0 = (prev_targetDraw1 != null) ? prev_targetDraw1.getPrevDraw() : null;

		List<Draw> resultDraws = new ArrayList<Draw>();
		for (Draw currDraw : draws) {
			if (currDraw == targetDraw) {
				continue;
			}
			Draw prev_currDraw1 = currDraw.getPrevDraw();
			Draw prev_currDraw0 = (prev_currDraw1 != null) ? prev_currDraw1.getPrevDraw() : null;

			boolean match = false;
			if (matchTrend(targetDraw, currDraw)) {
				if (matchTrend(prev_targetDraw1, prev_currDraw1)) {
					if (matchTrend(prev_targetDraw0, prev_currDraw0)) {
						match = true;
					}
				}
			}

			if (match) {
				resultDraws.add(currDraw);
			}
		}
		return resultDraws;
	}

	public boolean matchTrend(Draw draw1, Draw draw2) {
		if (draw1 == null || draw2 == null) {
			return false;
		}
		Trend draw1_trend0 = draw1.getTrend()[0];
		Trend draw1_trend1 = draw1.getTrend()[1];
		Trend draw1_trend2 = draw1.getTrend()[2];
		Trend draw1_trend3 = draw1.getTrend()[3];
		Trend draw1_trend4 = draw1.getTrend()[4];

		Trend draw2_trend0 = draw2.getTrend()[0];
		Trend draw2_trend1 = draw2.getTrend()[1];
		Trend draw2_trend2 = draw2.getTrend()[2];
		Trend draw2_trend3 = draw2.getTrend()[3];
		Trend draw2_trend4 = draw2.getTrend()[4];

		int matchCount = 0;
		if (draw1_trend0.equals(draw2_trend0)) {
			matchCount += 1;
		}
		if (draw1_trend1.equals(draw2_trend1)) {
			matchCount += 1;
		}
		if (draw1_trend2.equals(draw2_trend2)) {
			matchCount += 1;
		}
		if (draw1_trend3.equals(draw2_trend3)) {
			matchCount += 1;
		}
		if (draw1_trend4.equals(draw2_trend4)) {
			matchCount += 1;
		}

		if (matchCount == 5) {
			return true;
		}
		return false;
	}

	public static List<Integer> BORDER_NUMS = new ArrayList<Integer>();
	static {
		BORDER_NUMS.add(1);
		BORDER_NUMS.add(2);
		BORDER_NUMS.add(3);
		BORDER_NUMS.add(4);
		BORDER_NUMS.add(5);
		BORDER_NUMS.add(6);
		BORDER_NUMS.add(7);
		BORDER_NUMS.add(8);
		BORDER_NUMS.add(9);
		BORDER_NUMS.add(10);

		BORDER_NUMS.add(11);
		BORDER_NUMS.add(21);
		BORDER_NUMS.add(31);
		BORDER_NUMS.add(41);
		BORDER_NUMS.add(51);

		BORDER_NUMS.add(20);
		BORDER_NUMS.add(30);
		BORDER_NUMS.add(40);
		BORDER_NUMS.add(50);
		BORDER_NUMS.add(60);

		BORDER_NUMS.add(61);
		BORDER_NUMS.add(62);
		BORDER_NUMS.add(63);
		BORDER_NUMS.add(64);
		BORDER_NUMS.add(65);
		BORDER_NUMS.add(66);
		BORDER_NUMS.add(67);
		BORDER_NUMS.add(68);
		BORDER_NUMS.add(69);
		BORDER_NUMS.add(70);
	}

	public int getNumsOnBorderCount(Draw draw) {
		int count = 0;
		for (int i = 0; i < 5; i++) {
			if (BORDER_NUMS.contains(draw.getNum(i))) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Date date = DrawHelper.INSTANCE.getDate("2023-10-09");
		int[] nums_1 = DrawHelper.INSTANCE.getNumbers(2, 50, new float[] { 8, 21, 13, 58 });
		int[] nums_2 = DrawHelper.INSTANCE.getNumbers(19, 49, new float[] { 9, 21, 13, 51 });
		int[] nums_3 = DrawHelper.INSTANCE.getNumbers(13, 48, new float[] { 10, 21, 13, 56 });
		int[] nums_4 = DrawHelper.INSTANCE.getNumbers(7, 47, new float[] { 11, 21, 13, 55 });
		int[] nums_5 = DrawHelper.INSTANCE.getNumbers(4, 46, new float[] { 12, 21, 13, 54 });

		Draw draw1 = new DrawImpl(date, nums_1, 1);
		Draw draw2 = new DrawImpl(date, nums_2, 2);
		Draw draw3 = new DrawImpl(date, nums_3, 3);
		Draw draw4 = new DrawImpl(date, nums_4, 4);
		Draw draw5 = new DrawImpl(date, nums_5, 5);

		DrawHelper.INSTANCE.updateDraw(null, draw1, null);
		DrawHelper.INSTANCE.updateDraw(null, draw2, null);
		DrawHelper.INSTANCE.updateDraw(null, draw3, null);
		DrawHelper.INSTANCE.updateDraw(null, draw4, null);
		DrawHelper.INSTANCE.updateDraw(null, draw5, null);

		System.out.println(draw1);
		System.out.println(draw2);
		System.out.println(draw3);
		System.out.println(draw4);
		System.out.println(draw5);
	}
}
