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

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tauceti.tyche.chart.Report;
import org.tauceti.tyche.chart.ReportConstants;
import org.tauceti.tyche.chart.ReportService;
import org.tauceti.tyche.chart.impl.ReportServiceImpl;
import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFilter;
import org.tauceti.tyche.model.DrawFilterByFolder;
import org.tauceti.tyche.model.impl.PowerballReaderImpl;
import org.tauceti.tyche.model.impl.PredictionReaderImpl;
import org.tauceti.tyche.util.DrawHelper;
import org.tauceti.tyche.util.DrawUtil;
import org.tauceti.tyche.util.SystemUtil;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Powerball {

	public static void main(String[] args0) throws Exception {
		Powerball powerball = new Powerball();
		powerball.run();
	}

	public static File getDefaultFile() {
		return new File(SystemUtil.getUserDir(), "/data/powerball.csv");
	}

	public void run() {
		File file1 = new File(SystemUtil.getUserDir(), "/data/powerball.csv");
		File file2 = new File(SystemUtil.getUserDir(), "/data/powerball_predict.txt");
		if (!file1.exists()) {
			new PowerballDownloader().run();
		}
		if (!file1.exists()) {
			throw new RuntimeException(file1 + " doesn't exist.");
		}

		List<DrawFilter> filters = PowerballFilterManager.getDrawFilters();
		List<Draw> draws = DrawHelper.INSTANCE.read(file1, new PowerballReaderImpl());
		List<Draw> predictions = getPredictions(draws, file2);

		List<Report> reports = generateReports(filters, draws, null, predictions, file2);
		print(draws, predictions, reports);
	}

	public static List<Draw> getPredictions(List<Draw> draws, File file2) {
		List<Draw> predictionDraws = DrawHelper.INSTANCE.read(file2, new PredictionReaderImpl());
		Draw lastDraw = (!draws.isEmpty()) ? draws.get(draws.size() - 1) : null;
		for (Draw predictionDraw : predictionDraws) {
			DrawHelper.INSTANCE.updateDraw(lastDraw, predictionDraw, null);
		}
		return predictionDraws;
	}

	public static List<Report> generateReports(List<DrawFilter> filters, List<Draw> draws, List<Draw> prevPredictions, List<Draw> predictions, File file2) {
		// ---------------------------------------------------------------------
		// Generate reports for draws by filter
		// ---------------------------------------------------------------------
		for (DrawFilter filter : filters) {
			boolean is_3_in_a_row_filter = false;
			if (DrawFilter.ID__3_IN_A_ROW.equals(filter.getId()) || (filter.getId() != null && filter.getId().contains(DrawFilter.ID__3_IN_A_ROW))) {
				is_3_in_a_row_filter = true;
			}

			List<Draw> currDraws = DrawHelper.INSTANCE.read(getDefaultFile(), new PowerballReaderImpl(), filter);
			if (filter instanceof DrawFilterByFolder) {
				try {
					DrawFilterByFolder filterByFolder = (DrawFilterByFolder) filter;
					String reportId = filterByFolder.getReportId();
					String txtFolderName = filterByFolder.getTxtFolderName();
					String htmlFolderName = filterByFolder.getHtmlFolderName();

					reportId += "_(size=" + currDraws.size() + ")";

					// Create txt file
					DrawHelper.INSTANCE.write(currDraws, new File(txtFolderName, reportId + ".txt"));
					File folder = new File(htmlFolderName);

					// Create html file
					Map<String, Object> args = new HashMap<String, Object>();
					args.put(ReportConstants.ARG__DRAWS_TYPE, ReportConstants.DRAWS_TYPE__POWERBALL);
					args.put(ReportConstants.ARG__DRAWS_NUMBERS, currDraws);
					args.put(ReportConstants.ARG__REPORT_FOLDRE, folder);
					args.put(ReportConstants.ARG__NUMBER6_LABEL, "Power Ball");
					args.put(ReportConstants.ARG__REPORT_ID, reportId);
					args.put(ReportConstants.ARG__DRAW_FILTER, filter);

					if (is_3_in_a_row_filter) {
						List<Draw> currPredictions_0 = null;
						List<Draw> currPredictions_1 = getPredictions(currDraws, file2);
						args.put(ReportConstants.ARG__DRAWS_PREV_PREDICTIONS, currPredictions_0);
						args.put(ReportConstants.ARG__DRAWS_PREDICTIONS, currPredictions_1);
					}

					ReportService service = new ReportServiceImpl();
					service.setReportPrefix("Powerball");

					service.generateReports(args);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// ---------------------------------------------------------------------
		// Generate reports for all draws
		// ---------------------------------------------------------------------
		List<Report> reports = null;
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put(ReportConstants.ARG__DRAWS_TYPE, ReportConstants.DRAWS_TYPE__POWERBALL);
			args.put(ReportConstants.ARG__DRAWS_NUMBERS, draws);
			args.put(ReportConstants.ARG__DRAWS_PREV_PREDICTIONS, prevPredictions);
			args.put(ReportConstants.ARG__DRAWS_PREDICTIONS, predictions);
			args.put(ReportConstants.ARG__REPORT_FOLDRE, PowerballConstants.REPORTS_FOLDER);
			args.put(ReportConstants.ARG__NUMBER6_LABEL, "Power Ball");

			ReportService service = new ReportServiceImpl();
			service.setReportPrefix("Powerball");

			reports = service.generateReports(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}

	/**
	 * 
	 * @param draws
	 * @param predictionDraws_0
	 * @param predictionDraws_1
	 * @param reports
	 */
	public static void printResults(List<Draw> draws, List<Draw> predictionDraws_1, List<Report> reports) {
		// ---------------------------------------------------------------------
		// Print Reports
		// ---------------------------------------------------------------------
		System.out.println("Reports:");
		for (Report report : reports) {
			System.out.println(report);
		}
		System.out.println();

		// ---------------------------------------------------------------------
		// Print first digits statistics
		// ---------------------------------------------------------------------
		Map<String, Integer> firstDigitsCount = DrawUtil.getFirstDigitsCount(draws, false);
		int countThreshold = 11;
		int numOfEntries = 0;
		Iterator<String> itor1 = firstDigitsCount.keySet().iterator();
		while (itor1.hasNext()) {
			String key = itor1.next();
			Integer count = firstDigitsCount.get(key);
			if (count >= countThreshold) {
				numOfEntries++;
			}
		}

		System.out.println("First Digits Count (size=" + numOfEntries + "; count threshold=" + countThreshold + "):");
		Iterator<String> itor2 = firstDigitsCount.keySet().iterator();
		while (itor2.hasNext()) {
			String key = itor2.next();
			Integer count = firstDigitsCount.get(key);
			if (count >= countThreshold) {
				System.out.println(key + ": " + count);
			}
		}
		System.out.println();

		// ---------------------------------------------------------------------
		// Print similar draws
		// ---------------------------------------------------------------------
		boolean printPredictDrawsWithSameDistances = true;
		boolean printExistingDrawsWithSameDistances = true;

		Draw lastDraw = draws.get(draws.size() - 1);
		if (printExistingDrawsWithSameDistances) {
			System.out.println("Last Draw:");
			System.out.println(lastDraw);
			System.out.println();
		} else {
			System.out.println("Last Draw:");
			System.out.println(lastDraw);
			System.out.println();
		}

		if (printPredictDrawsWithSameDistances && predictionDraws_1 != null) {
			System.out.println("Prediction draws with distances:");
			for (Draw draw : predictionDraws_1) {
				System.out.println(draw);
			}
			System.out.println();
		}

		// ---------------------------------------------------------------------
		// Existing draws with same distances
		// ---------------------------------------------------------------------
		if (printExistingDrawsWithSameDistances) {
			System.out.println("Existing draws with same distances:");
			List<Draw> resultDraws = DrawHelper.INSTANCE.getDrawsWithSameDistances(draws, lastDraw, 57, 6, 6, 31, 2);
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
				System.out.println();
			}
			System.out.println();
		}

		// PowerballPrintManager.printSameFirstDigits(draws);

		// PowerballPrintManager.printNumOnBorderCounts(draws);
	}

	/**
	 * 
	 * @param draws
	 * @param predictions
	 * @param reports
	 */
	public void print(List<Draw> draws, List<Draw> predictions, List<Report> reports) {
		PowerballPrintManager.INSTANCE.print(draws, predictions, reports);
	}

	/*-
	public static void print(List<Draw> existingDraws, DrawFilter[] filters, List<Draw> draws, int inputSize, Draw actualDraw) {
		String line = "----------------------------------------------------------------------------------------------------";
	
		System.out.println("Recentg 20 Draws:");
		System.out.println(line);
		for (int i = existingDraws.size() - 20; i < existingDraws.size(); i++) {
			Draw draw = existingDraws.get(i);
	
			float mean = DrawUtil.getMeanOf5Numbers(draw);
			float SDof5 = DrawUtil.getSDof5Numbers(draw);
			float SDofDistances = DrawUtil.getSDofDistances(draw);
			int evenlyDivisibleCount = DrawUtil.getEvenlyDivisibleCount(draw, false);
			int continuousNumbersCount = DrawUtil.getContinuousNumbersCount(draw);
			int sameDoubleDigitsCount = DrawUtil.getSameDoubleDigitsCount(draw);
			int duplicateCount_2 = DrawUtil.getDuplicateNumbersCount(actualDraw, existingDraws.get(i - 2));
			int duplicateCount_1 = DrawUtil.getDuplicateNumbersCount(actualDraw, existingDraws.get(i - 1));
	
			System.out.println(draw + " - Mean [" + mean + "] - SD [" + SDof5 + "] - SD-of-Dist [" + SDofDistances + "] - Evenly-Divisible-Count [" + evenlyDivisibleCount + "] - Continuous-Count [" + continuousNumbersCount + "] - Same-Double-Digits-Count [" + sameDoubleDigitsCount + "] - Duplicate-2 [" + duplicateCount_2 + "] - Duplicate-1 [" + duplicateCount_1 + "]");
		}
		System.out.println(line);
		System.out.println();
	
		System.out.println("Draw Filters:");
		System.out.println(line);
		int i = 0;
		for (DrawFilter filter : filters) {
			System.out.println("[" + i + "] " + filter);
			i++;
		}
		System.out.println(line);
		System.out.println();
	
		if (actualDraw != null) {
			System.out.println("Actual draw:");
			System.out.println(line);
	
			float mean = DrawUtil.getMeanOf5Numbers(actualDraw);
			float SDof5 = DrawUtil.getSDof5Numbers(actualDraw);
			float SDofDistances = DrawUtil.getSDofDistances(actualDraw);
			int evenlyDivisibleCount = DrawUtil.getEvenlyDivisibleCount(actualDraw, false);
			int continuousNumbersCount = DrawUtil.getContinuousNumbersCount(actualDraw);
			int sameDigitsCount = DrawUtil.getSameDoubleDigitsCount(actualDraw);
			int duplicateCount_2 = DrawUtil.getDuplicateNumbersCount(actualDraw, existingDraws.get(existingDraws.size() - 2));
			int duplicateCount_1 = DrawUtil.getDuplicateNumbersCount(actualDraw, existingDraws.get(existingDraws.size() - 1));
	
			System.out.println(actualDraw + " - Mean [" + mean + "] - SD [" + SDof5 + "] - SD-of-Dist [" + SDofDistances + "] - Evenly-Divisible-Count [" + evenlyDivisibleCount + "] - Continuous-Count [" + continuousNumbersCount + "] - Same-Double-Digits-Count [" + sameDigitsCount + "] - Duplicate-2 [" + duplicateCount_2 + "] - Duplicate-1 [" + duplicateCount_1 + "]");
			System.out.println(line);
			System.out.println();
		}
	
		System.out.println("Generated draws:");
		System.out.println(line);
		for (Draw draw : draws) {
	
			float mean = DrawUtil.getMeanOf5Numbers(draw);
			float SDof5 = DrawUtil.getSDof5Numbers(draw);
			float SDofDistances = DrawUtil.getSDofDistances(draw);
			int evenlyDivisibleCount = DrawUtil.getEvenlyDivisibleCount(draw, false);
			int continuousNumbersCount = DrawUtil.getContinuousNumbersCount(draw);
			int sameDigitsCount = DrawUtil.getSameDoubleDigitsCount(draw);
			int duplicateCount_2 = DrawUtil.getDuplicateNumbersCount(actualDraw, existingDraws.get(existingDraws.size() - 2));
			int duplicateCount_1 = DrawUtil.getDuplicateNumbersCount(actualDraw, existingDraws.get(existingDraws.size() - 1));
	
			System.out.println(draw + " - Mean [" + mean + "] - SD [" + SDof5 + "] - SD-of-Dist [" + SDofDistances + "] - Evenly-Divisible-Count [" + evenlyDivisibleCount + "] - Continuous-Count [" + continuousNumbersCount + "] - Same-Double-Digits-Count [" + sameDigitsCount + "] - Duplicate-2 [" + duplicateCount_2 + "] - Duplicate-1 [" + duplicateCount_1 + "]");
		}
		System.out.println(line);
		System.out.println();
	}
	*/

	/*-
	 * 
	 * @param dateStr
	 * @return
	 *
	public static Date getDate(String dateStr) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	*/

	/*-
	Recentg 20 Draws:
	----------------------------------------------------------------------------------------------------
	Draw#1480 (06/07/2023) 16, 21, 29, 53, 66 [ 2] - mean [37.0] - SD-of5 [19.3] - SD-of-Dist [7.2] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1481 (06/10/2023) 21, 32, 42, 46, 50 [ 4] - mean [38.2] - SD-of5 [10.5] - SD-of-Dist [3.3] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1482 (06/12/2023)  2,  3, 16, 23, 68 [ 7] - mean [22.4] - SD-of5 [24.1] - SD-of-Dist [17.0] - Evenly-Divisible-Count [0] - Continuous-Count [1] - Same-Digits-Count [0]
	Draw#1483 (06/14/2023)  3, 20, 36, 42, 64 [ 4] - mean [33.0] - SD-of5 [20.6] - SD-of-Dist [5.8] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1484 (06/17/2023)  2, 12, 45, 61, 64 [26] - mean [36.8] - SD-of5 [25.4] - SD-of-Dist [11.1] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1485 (06/19/2023) 36, 39, 52, 57, 69 [ 1] - mean [50.6] - SD-of5 [12.1] - SD-of-Dist [4.3] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1486 (06/21/2023)  5, 11, 33, 35, 63 [14] - mean [29.4] - SD-of5 [20.5] - SD-of-Dist [10.8] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [2]
	Draw#1487 (06/24/2023)  2, 38, 44, 50, 62 [19] - mean [39.2] - SD-of5 [20.2] - SD-of-Dist [12.4] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1488 (06/26/2023)  6, 28, 39, 43, 54 [12] - mean [34.0] - SD-of5 [16.3] - SD-of-Dist [6.4] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1489 (06/28/2023) 19, 25, 34, 57, 68 [ 4] - mean [40.6] - SD-of5 [18.8] - SD-of-Dist [6.5] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1490 (07/01/2023)  4, 17, 35, 49, 61 [ 8] - mean [33.2] - SD-of5 [20.7] - SD-of-Dist [2.3] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1491 (07/03/2023) 15, 26, 31, 38, 61 [ 3] - mean [34.2] - SD-of5 [15.4] - SD-of-Dist [7.0] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1492 (07/05/2023) 17, 24, 48, 62, 68 [23] - mean [43.8] - SD-of5 [20.2] - SD-of-Dist [7.2] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1493 (07/08/2023)  7, 23, 24, 32, 43 [18] - mean [25.8] - SD-of5 [11.8] - SD-of-Dist [5.4] - Evenly-Divisible-Count [0] - Continuous-Count [1] - Same-Digits-Count [0]
	Draw#1494 (07/10/2023)  2, 24, 34, 53, 58 [13] - mean [34.2] - SD-of5 [20.3] - SD-of-Dist [6.8] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1495 (07/12/2023) 23, 35, 45, 66, 67 [20] - mean [47.2] - SD-of5 [17.2] - SD-of-Dist [7.1] - Evenly-Divisible-Count [0] - Continuous-Count [1] - Same-Digits-Count [1]
	Draw#1496 (07/15/2023)  2,  9, 43, 55, 57 [18] - mean [33.2] - SD-of5 [23.2] - SD-of-Dist [12.2] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1497 (07/17/2023)  5,  8,  9, 17, 41 [21] - mean [16.0] - SD-of5 [13.1] - SD-of-Dist [9.0] - Evenly-Divisible-Count [0] - Continuous-Count [1] - Same-Digits-Count [0]
	Draw#1498 (07/19/2023)  7, 10, 11, 13, 24 [24] - mean [13.0] - SD-of5 [5.8] - SD-of-Dist [4.0] - Evenly-Divisible-Count [0] - Continuous-Count [1] - Same-Digits-Count [1]
	Draw#1499 (07/22/2023) 25, 27, 36, 37, 63 [ 7] - mean [37.6] - SD-of5 [13.6] - SD-of-Dist [10.0] - Evenly-Divisible-Count [0] - Continuous-Count [1] - Same-Digits-Count [0]
	----------------------------------------------------------------------------------------------------
	
	Draw Filters:
	----------------------------------------------------------------------------------------------------
	[0] Max-Duplicate-of-5 [Draw#1498 (07/19/2023)  7, 10, 11, 13, 24 [24], 1.0]
	[1] Max-Duplicate-of-5 [Draw#1499 (07/22/2023) 25, 27, 36, 37, 63 [ 7], 0.0]
	[2] Powerball [3, 16]
	[3] Mean-Of-5 [25.0, 45.0]
	[4] Stand-Deviation-Of-5 [6.0, 23.0]
	[5] Stand-Deviation-Of-4-Distances [3.0, 12.0]
	[6] Evenly Divisible Count [0, 1]
	[7] Continuous Numbers Count [0, 0]
	[8] Same Digits Count [0, 1]
	----------------------------------------------------------------------------------------------------
	
	Actual draw:
	----------------------------------------------------------------------------------------------------
	Draw# 0 (07/26/2023)  3, 16, 40, 48, 60 [14] - mean [33.4] - SD-of5 [20.9] - SD-of-Dist [5.9] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Double-Digits-Count [0]
	----------------------------------------------------------------------------------------------------	
	
	Generated draws (input_size=100, output_size=15):
	----------------------------------------------------------------------------------------------------
	Draw#1001 (07/26/2023) 29, 31, 39, 42, 62 [11] - mean [40.6] - SD-of5 [11.7] - SD-of-Dist [7.2] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1002 (07/26/2023)  5, 33, 42, 56, 62 [ 9] - mean [39.6] - SD-of5 [20.1] - SD-of-Dist [8.4] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1003 (07/26/2023) 12, 16, 46, 55, 57 [ 3] - mean [37.2] - SD-of5 [19.3] - SD-of-Dist [11.1] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1004 (07/26/2023) 16, 23, 28, 32, 57 [ 4] - mean [31.2] - SD-of5 [14.0] - SD-of-Dist [8.6] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1005 (07/26/2023)  1,  4, 33, 46, 50 [ 3] - mean [26.8] - SD-of5 [20.6] - SD-of-Dist [10.4] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1006 (07/26/2023) 10, 17, 28, 50, 53 [13] - mean [31.6] - SD-of5 [17.3] - SD-of-Dist [7.1] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1007 (07/26/2023) 14, 23, 40, 48, 57 [10] - mean [36.4] - SD-of5 [15.8] - SD-of-Dist [3.6] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1008 (07/26/2023)  1, 12, 24, 50, 57 [16] - mean [28.8] - SD-of5 [21.6] - SD-of-Dist [7.2] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1009 (07/26/2023)  2, 34, 38, 52, 60 [11] - mean [37.2] - SD-of5 [19.9] - SD-of-Dist [10.7] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1010 (07/26/2023)  9, 19, 38, 43, 61 [15] - mean [34.0] - SD-of5 [18.3] - SD-of-Dist [5.8] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1011 (07/26/2023)  4, 17, 41, 54, 56 [16] - mean [34.4] - SD-of5 [20.6] - SD-of-Dist [7.8] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1012 (07/26/2023) 11, 16, 31, 40, 51 [ 6] - mean [29.8] - SD-of5 [14.8] - SD-of-Dist [3.6] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1013 (07/26/2023) 13, 16, 33, 41, 57 [ 7] - mean [32.0] - SD-of5 [16.3] - SD-of-Dist [5.8] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1014 (07/26/2023) 15, 32, 34, 40, 52 [14] - mean [34.6] - SD-of5 [12.0] - SD-of-Dist [5.7] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1015 (07/26/2023) 11, 15, 20, 41, 45 [ 7] - mean [26.4] - SD-of5 [13.9] - SD-of-Dist [7.2] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	----------------------------------------------------------------------------------------------------
	
	Generated draws (input_size=100, output_size=8):
	----------------------------------------------------------------------------------------------------
	Draw#1001 (07/26/2023) 15, 38, 41, 47, 49 [ 8] - mean [38.0] - SD-of5 [12.2] - SD-of-Dist [8.5] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1002 (07/26/2023)  8, 14, 31, 44, 62 [ 4] - mean [31.8] - SD-of5 [19.7] - SD-of-Dist [4.7] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1003 (07/26/2023) 12, 18, 22, 43, 52 [10] - mean [29.4] - SD-of5 [15.4] - SD-of-Dist [6.6] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1004 (07/26/2023)  5, 18, 20, 41, 65 [12] - mean [29.8] - SD-of5 [21.0] - SD-of-Dist [8.5] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1005 (07/26/2023)  6, 32, 35, 59, 68 [10] - mean [40.0] - SD-of5 [21.9] - SD-of-Dist [9.8] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1006 (07/26/2023) 19, 24, 34, 49, 67 [12] - mean [38.6] - SD-of5 [17.5] - SD-of-Dist [4.9] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1007 (07/26/2023)  1, 23, 48, 54, 60 [ 8] - mean [37.2] - SD-of5 [22.0] - SD-of-Dist [8.8] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1008 (07/26/2023)  3, 31, 43, 49, 52 [ 9] - mean [35.6] - SD-of5 [17.8] - SD-of-Dist [9.7] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	----------------------------------------------------------------------------------------------------
	
	Generated draws (input_size=100, output_size=14):
	----------------------------------------------------------------------------------------------------
	Draw#1001 (07/26/2023)  9, 18, 50, 52, 66 [10] - mean [39.0] - SD-of5 [21.7] - SD-of-Dist [11.1] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1002 (07/26/2023)  9, 20, 22, 34, 42 [ 3] - mean [25.4] - SD-of5 [11.5] - SD-of-Dist [3.9] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1003 (07/26/2023)  1, 16, 44, 48, 64 [16] - mean [34.6] - SD-of5 [22.8] - SD-of-Dist [8.5] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1004 (07/26/2023) 12, 44, 50, 54, 59 [11] - mean [43.8] - SD-of5 [16.6] - SD-of-Dist [11.7] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1005 (07/26/2023)  8, 17, 34, 42, 50 [11] - mean [30.2] - SD-of5 [15.6] - SD-of-Dist [3.8] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1006 (07/26/2023)  2, 18, 34, 38, 54 [ 4] - mean [29.2] - SD-of5 [17.8] - SD-of-Dist [5.2] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1007 (07/26/2023)  1, 19, 21, 35, 65 [ 3] - mean [28.2] - SD-of5 [21.3] - SD-of-Dist [10.0] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1008 (07/26/2023) 15, 23, 31, 49, 65 [ 6] - mean [36.6] - SD-of5 [18.1] - SD-of-Dist [4.6] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1009 (07/26/2023) 14, 18, 48, 53, 64 [13] - mean [39.4] - SD-of5 [19.8] - SD-of-Dist [10.5] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1010 (07/26/2023) 14, 17, 31, 58, 61 [ 4] - mean [36.2] - SD-of5 [19.9] - SD-of-Dist [9.9] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1011 (07/26/2023) 15, 26, 30, 40, 52 [ 7] - mean [32.6] - SD-of5 [12.6] - SD-of-Dist [3.1] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1012 (07/26/2023) 13, 33, 46, 49, 51 [ 4] - mean [38.4] - SD-of5 [14.2] - SD-of-Dist [7.4] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1013 (07/26/2023)  2, 26, 29, 50, 52 [ 9] - mean [31.8] - SD-of5 [18.3] - SD-of-Dist [10.1] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1014 (07/26/2023)  1, 15, 26, 31, 58 [12] - mean [26.2] - SD-of5 [18.9] - SD-of-Dist [8.0] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	----------------------------------------------------------------------------------------------------
	
	Generated draws (input_size=100, output_size=18):
	----------------------------------------------------------------------------------------------------
	Draw#1001 (07/26/2023)  1, 11, 43, 48, 59 [10] - mean [32.4] - SD-of5 [22.4] - SD-of-Dist [10.4] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1002 (07/26/2023)  9, 15, 26, 40, 57 [ 9] - mean [29.4] - SD-of5 [17.4] - SD-of-Dist [4.1] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1003 (07/26/2023)  4, 13, 31, 45, 50 [ 6] - mean [28.6] - SD-of5 [17.8] - SD-of-Dist [4.9] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1004 (07/26/2023)  8, 30, 38, 62, 67 [ 3] - mean [41.0] - SD-of5 [21.6] - SD-of-Dist [8.3] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1005 (07/26/2023)  5, 21, 39, 55, 61 [ 6] - mean [36.2] - SD-of5 [20.9] - SD-of-Dist [4.7] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1006 (07/26/2023) 12, 28, 31, 42, 52 [ 3] - mean [33.0] - SD-of5 [13.5] - SD-of-Dist [4.6] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1007 (07/26/2023) 16, 19, 38, 45, 57 [ 5] - mean [35.0] - SD-of5 [15.6] - SD-of-Dist [6.0] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1008 (07/26/2023) 13, 38, 40, 51, 60 [ 8] - mean [40.4] - SD-of5 [15.8] - SD-of-Dist [8.3] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1009 (07/26/2023) 14, 20, 41, 49, 69 [15] - mean [38.6] - SD-of5 [19.9] - SD-of-Dist [6.8] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1010 (07/26/2023) 11, 26, 31, 56, 64 [ 7] - mean [37.6] - SD-of5 [19.6] - SD-of-Dist [7.7] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1011 (07/26/2023) 11, 17, 34, 49, 56 [12] - mean [33.4] - SD-of5 [17.5] - SD-of-Dist [4.8] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1012 (07/26/2023) 13, 38, 45, 56, 65 [ 3] - mean [43.4] - SD-of5 [17.8] - SD-of-Dist [7.1] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1013 (07/26/2023)  4, 11, 34, 40, 51 [16] - mean [28.0] - SD-of5 [17.7] - SD-of-Dist [6.8] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1014 (07/26/2023)  6, 11, 23, 35, 67 [15] - mean [28.4] - SD-of5 [21.8] - SD-of-Dist [10.1] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1015 (07/26/2023) 16, 24, 47, 61, 67 [12] - mean [43.0] - SD-of5 [20.0] - SD-of-Dist [6.6] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1016 (07/26/2023) 11, 19, 47, 50, 53 [ 8] - mean [36.0] - SD-of5 [17.4] - SD-of-Dist [10.3] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1017 (07/26/2023)  6, 23, 30, 32, 51 [16] - mean [28.4] - SD-of5 [14.5] - SD-of-Dist [7.0] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1018 (07/26/2023) 16, 29, 51, 58, 69 [15] - mean [44.6] - SD-of5 [19.4] - SD-of-Dist [5.5] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	----------------------------------------------------------------------------------------------------	
	
	Generated draws (input_size=100, output_size=15):
	----------------------------------------------------------------------------------------------------
	Draw#1001 (07/26/2023)  5, 32, 51, 54, 67 [10] - mean [41.8] - SD-of5 [21.5] - SD-of-Dist [8.8] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1002 (07/26/2023)  7, 31, 33, 51, 60 [12] - mean [36.4] - SD-of5 [18.3] - SD-of-Dist [8.4] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1003 (07/26/2023)  4, 18, 22, 45, 67 [ 3] - mean [31.2] - SD-of5 [22.2] - SD-of-Dist [7.6] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1004 (07/26/2023)  1, 10, 18, 40, 58 [16] - mean [25.4] - SD-of5 [20.8] - SD-of-Dist [5.9] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1005 (07/26/2023)  4, 28, 38, 47, 53 [16] - mean [34.0] - SD-of5 [17.2] - SD-of-Dist [6.9] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1006 (07/26/2023)  4, 40, 50, 59, 67 [ 4] - mean [44.0] - SD-of5 [21.9] - SD-of-Dist [11.7] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1007 (07/26/2023) 23, 41, 47, 51, 58 [14] - mean [44.0] - SD-of5 [11.9] - SD-of-Dist [5.4] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1008 (07/26/2023)  6, 10, 22, 43, 68 [15] - mean [29.8] - SD-of5 [23.0] - SD-of-Dist [8.1] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1009 (07/26/2023)  5, 11, 18, 47, 61 [14] - mean [28.4] - SD-of5 [21.8] - SD-of-Dist [9.2] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1010 (07/26/2023) 19, 31, 46, 52, 67 [14] - mean [43.0] - SD-of5 [16.6] - SD-of-Dist [3.7] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1011 (07/26/2023) 19, 24, 46, 54, 65 [14] - mean [41.6] - SD-of5 [17.6] - SD-of-Dist [6.4] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1012 (07/26/2023)  1, 16, 35, 40, 42 [14] - mean [26.8] - SD-of5 [15.8] - SD-of-Dist [7.0] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1013 (07/26/2023) 13, 26, 33, 35, 64 [ 5] - mean [34.2] - SD-of5 [16.8] - SD-of-Dist [10.2] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [1]
	Draw#1014 (07/26/2023) 12, 24, 26, 57, 62 [ 3] - mean [36.2] - SD-of5 [19.7] - SD-of-Dist [11.3] - Evenly-Divisible-Count [1] - Continuous-Count [0] - Same-Digits-Count [0]
	Draw#1015 (07/26/2023)  2,  9, 34, 41, 56 [ 6] - mean [28.4] - SD-of5 [20.1] - SD-of-Dist [7.4] - Evenly-Divisible-Count [0] - Continuous-Count [0] - Same-Digits-Count [0]
	----------------------------------------------------------------------------------------------------
	
	public static void generateNumbers_2023_07_26(DrawGenerator generator) {
		try {
			List<Draw> existingDraws = DrawHelper.INSTANCE.read(getDefaultFile());
			Draw prevDraw = existingDraws.get(existingDraws.size() - 2);
			Draw lastDraw = existingDraws.get(existingDraws.size() - 1);
	
			int inputSize = 100;
			DrawFilter[] filters = new DrawFilter[] { //
					new PowerballRangeFilter(3, 16), //
					new Meanof5RangeFilter(25, 45), //
					new SDof5RangeFilter(6, 23), //
					new SDof4DistanceRangeFilter(3, 12), //
					new EvenlyDivisibleCountFilter(0, 1), //
					new ContinuousNumbersCountFilter(0, 0), //
					new SameDigitsCountFilter(0, 1), //
					new DuplicateOf5RangeFilter(prevDraw, 0, 0), //
					new DuplicateOf5RangeFilter(lastDraw, 0, 0), //
			};
	
			List<Draw> draws = generator.generate(Mode.SecureRandom, getDate("2023-07-26"), inputSize, filters);
	
			// 3, 16, 40, 48, 60
			Draw actualDraw = DrawFactory.getInstance().createDraw(getDate("2023-07-26"), 3, 16, 40, 48, 60, 14);
	
			print(existingDraws, filters, draws, inputSize, actualDraw);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
}
