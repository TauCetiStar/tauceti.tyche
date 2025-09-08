package org.tauceti.tyche.chart.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.tauceti.tyche.chart.AbstractReportContentGenerator;
import org.tauceti.tyche.chart.IChartGenerator;
import org.tauceti.tyche.chart.Options;
import org.tauceti.tyche.chart.ReportConstants;
import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawDigit;
import org.tauceti.tyche.util.DrawHelper;
import org.tauceti.tyche.util.DrawUtil;
import org.tauceti.tyche.util.PropertiesHelper;

public class ReportContentGeneratorChartImpl extends AbstractReportContentGenerator {

	public static String ID = "ReportContentGeneratorChartImpl";

	public ReportContentGeneratorChartImpl() {
		setId(ID);
	}

	@Override
	public Object generate(Map<String, Object> args) {
		String contents = "";
		try {
			String number6Label = PropertiesHelper.INSTANCE.getStringValue(args, ReportConstants.ARG__NUMBER6_LABEL, "Number6");

			List<Draw> draws = DrawHelper.INSTANCE.getDraws(args);
			int size = draws.size();
			Draw prevDraw = (draws.size() > 1) ? draws.get(size - 2) : null;
			Draw lastDraw = (draws.size() > 0) ? draws.get(size - 1) : null;

			int chartWidth_1 = 250000;
			int chartWidth_2 = 250370;

			int chartHeight_500 = 500;
			int chartHeight_600 = 600;
			int chartHeight_800 = 800;
			int chartHeight_1000 = 1000;
			int chartHeight_2100 = 2100;

			Object filter = args.get(ReportConstants.ARG__DRAW_FILTER);
			if (filter != null) {
				chartWidth_1 = size * 150;

				chartHeight_800 = 900 - 300;
				chartHeight_2100 = 2200 - 660;
			}

			// Numbers 1-5
			contents += getLineChart01(args, "line_chart_01", "Numbers", "Date", "Numbers", chartWidth_1, chartHeight_1000, draws, lastDraw);
			contents += "<br/>";

			// First Digits 1-5
			contents += getLineChart02(args, "line_chart_02", "First digits", "Date", "First digits", chartWidth_1, chartHeight_1000, draws, lastDraw);
			contents += "<br/>";

			// PB number
			contents += getLineChart03(args, "line_chart_03", number6Label, "Date", number6Label, chartWidth_1, chartHeight_600, draws, lastDraw);
			contents += "<br/>";

			// Odd vs Even count
			contents += getLineChart04(args, "line_chart_04", "Odd vs Even count", "Date", "Odd vs Even count", chartWidth_1, chartHeight_600, draws, lastDraw);
			contents += "<br/>";

			// Sum of first digits and second digits
			contents += getLineChart05(args, "line_chart_05", "First digits and Second digits sum", "Date", "First digits and Second digits sum", chartWidth_1, chartHeight_600, draws, lastDraw);
			contents += "<br/>";

			// Same lines matching count (compare to previous draw)
			contents += getLineChart06(args, "line_chart_06", "Same line count", "Date", "Same line count", chartWidth_1, chartHeight_600, draws, prevDraw, lastDraw);
			contents += "<br/>";

			// Diameter
			contents += getLineChart07(args, "line_chart_07", "Diameter", "Date", "Diameter", chartWidth_1, chartHeight_600, draws, lastDraw);
			contents += "<br/>";

			// Area
			contents += getLineChart08(args, "line_chart_08", "Area", "Date", "Area", chartWidth_1, chartHeight_600, draws, lastDraw);
			contents += "<br/>";

			// On Border Count
			contents += getLineChart09(args, "line_chart_09", "On Border Count", "Date", "On Border Count", chartWidth_1, chartHeight_600, draws, lastDraw);
			contents += "<br/>";

			// Sum of 5 first digits and 6 first digits
			contents += getLineChart10(args, "line_chart_10", "Sum of 5 first digits", "Date", "Sum of 5 first digits", chartWidth_1, chartHeight_800, draws, lastDraw);
			contents += "<br/>";

			// Sum of 5 numbers and 6 numbers
			contents += getLineChart11(args, "line_chart_11", "Sum of numbers", "Date", "Sum of numbers", chartWidth_1, chartHeight_800, draws, lastDraw);
			contents += "<br/>";

			// Distances of 5
			contents += getLineChart12(args, "line_chart_12", "Distances of 5", "Date", "Distances of 5", chartWidth_1, chartHeight_1000, draws, lastDraw);
			contents += "<br/>";

			// Distances percentage of 5 (3rd-centered)
			contents += getLineChart13(args, "line_chart_13", "Distances percentage of 5 (3rd-centered)", "Date", "Distances percentage of 5 (3rd-centered)", chartWidth_1, chartHeight_1000, draws, lastDraw);
			contents += "<br/>";

			/*-
			contents += getLineChart12(args, "line_chart_12_3", "Areas_Distance_3", "Date", "Areas_Distance_3", chartWidth_1, chartHeight_500, draws, lastDraw, 3);
			contents += "<br/>";
			
			contents += getLineChart12(args, "line_chart_12_2", "Areas_Distance_2", "Date", "Areas_Distance_2", chartWidth_1, chartHeight_500, draws, lastDraw, 2);
			contents += "<br/>";
			
			contents += getLineChart12(args, "line_chart_12_1", "Areas_Distance_1", "Date", "Areas_Distance_1", chartWidth_1, chartHeight_500, draws, lastDraw, 1);
			contents += "<br/>";
			
			contents += getLineChart12(args, "line_chart_12_0", "Areas_Distance_0", "Date", "Areas_Distance_0", chartWidth_1, chartHeight_500, draws, lastDraw, 0);
			contents += "<br/>";
			*/

			contents += getLineChart14(args, "line_chart_14", "Stand Deviation of 5 numbers", "Date", "SD of 5", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart15(args, "line_chart_15", "Prime Number Count (5)", "Date", "Prime Number Count (5)", chartWidth_1, chartHeight_500, draws, lastDraw, false);
			contents += "<br/>";

			contents += getLineChart16(args, "line_chart_16", "Evenly Divisible Count", "Date", "Evenly divisible count ", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart17(args, "line_chart_17", "Continuous Numbers Count", "Date", "Continuous Numbers Count", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart18(args, "line_chart_18", "Same Digits Count", "Date", "Same Digits Count", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart19_N(args, "line_chart_9_N", "Numbers Count - North Area", "Date", "Numbers Count - North Area", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart19_S(args, "line_chart_19_S", "Numbers Count - South Area", "Date", "Numbers Count - South Area", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart19_W(args, "line_chart_19_W", "Numbers Count - West Area", "Date", "Numbers Count - West Area", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart19_E(args, "line_chart_19_E", "Numbers Count - East Area", "Date", "Numbers Count - East Area", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart20_NW(args, "line_chart_20_NW", "Numbers Count - North West Area", "Date", "Numbers Count - North West Area", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart20_NE(args, "line_chart_20_NE", "Numbers Count - North East Area", "Date", "Numbers Count - North East Area", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart20_SW(args, "line_chart_20_SW", "Numbers Count - South West Area", "Date", "Numbers Count - South West Area", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart20_SE(args, "line_chart_20_SE", "Numbers Count - South East Area", "Date", "Numbers Count - South East Area", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			contents += getLineChart21_PB_section(args, "line_chart_21_PB_section", "PB section", "Date", "PB section", chartWidth_1, chartHeight_500, draws, lastDraw);
			contents += "<br/>";

			String[] colors = new String[] { //
					ReportConstants.CHART_LINE_COLOR__GREY0, //
					ReportConstants.RED, //
					ReportConstants.CHART_LINE_COLOR__ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.CHART_LINE_COLOR__GREEN2, //
					ReportConstants.CHART_LINE_COLOR__GREEN_BLUE, //
					ReportConstants.CHART_LINE_COLOR__BLUE, //
					ReportConstants.CHART_LINE_COLOR__MAYA_BLUE, //
					ReportConstants.BLUE, //
					ReportConstants.CHART_LINE_COLOR__VIOLET, //
			};

			for (int num = 0; num <= 9; num++) {
				contents += getLineChart22(args, "line_chart_22_" + num, "Digit Count (5 numbers)", "Date", "Digit Count (5 numbers)", chartWidth_1, chartHeight_500, draws, lastDraw, false, num, colors);
				contents += "<br/>";
			}

			contents += getLineChart23(args, "line_chart_23", "Digit Count (5 numbers)", "Date", "Digit Count (5 numbers)", chartWidth_2, chartHeight_2100, draws, lastDraw, false);
			contents += "<br/>";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

	/**
	 * Number1 to Number5
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart01(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //

					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number5 - " + lastDraw.getNum(4, true) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number4 - " + lastDraw.getNum(3, true) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number3 - " + lastDraw.getNum(2, true) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number2 - " + lastDraw.getNum(1, true) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number1 - " + lastDraw.getNum(0, true) }, //

					/*-
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number5" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number4" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number3" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number2" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Number1" }, //
					*/
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				rows.add(new Object[] { //
						"\"" + date + "\"", //
						draw.getNum5(), //
						draw.getNum4(), //
						draw.getNum3(), //
						draw.getNum2(), //
						draw.getNum1() //
				});
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CORAL, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DARK_TURQUOISE, //
					ReportConstants.DODGER_BLUE, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * First digit of 5 Numbers
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart02(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "FirstDigit5 - " + lastDraw.getNum5() / 10 }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "FirstDigit4 - " + lastDraw.getNum4() / 10 }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "FirstDigit3 - " + lastDraw.getNum3() / 10 }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "FirstDigit2 - " + lastDraw.getNum2() / 10 }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "FirstDigit1 - " + lastDraw.getNum1() / 10 }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				rows.add(new Object[] { //
						"\"" + date + "\"", //
						draw.getNum5() / 10, //
						draw.getNum4() / 10, //
						draw.getNum3() / 10, //
						draw.getNum2() / 10, //
						draw.getNum1() / 10 //
				});
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CORAL, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DARK_TURQUOISE, //
					ReportConstants.DODGER_BLUE, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Powerball
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart03(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			int min = 1;
			int max = 26;
			if (DrawHelper.INSTANCE.isPowerball(args)) {
				min = 1;
				max = 26;
			} else if (DrawHelper.INSTANCE.isMegaMillion(args)) {
				min = 1;
				max = 25;
			}
			float percent = DrawUtil.getPercent(min, max, lastDraw.getPB());

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, yTitle + " - [" + min + ", " + max + "] - " + lastDraw.getPB() + " (" + percent + "%)" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				rows.add(new Object[] { "\"" + date + "\"", draw.getPB() });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__VIOLET, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Odd vs Even count
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart04(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Event count - " + DrawUtil.getEvenCount(lastDraw) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Odd count - " + DrawUtil.getOddCount(lastDraw) }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				rows.add(new Object[] { //
						"\"" + date + "\"", //
						DrawUtil.getEvenCount(draw), //
						DrawUtil.getOddCount(draw), //
				});
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.BLUE, //
					ReportConstants.RED, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Sum of first digits and second digits
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart05(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Sum of second digits - " + DrawUtil.getSumOfSecondDigits(lastDraw) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Sum of first digits - " + DrawUtil.getSumOfFirstDigits(lastDraw) }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				rows.add(new Object[] { //
						"\"" + date + "\"", //
						DrawUtil.getSumOfSecondDigits(draw), //
						DrawUtil.getSumOfFirstDigits(draw), //
				});
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.BLUE, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Same lines matching count (compare to previous draw)
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart06(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw thePrevDraw, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			int hCount = DrawUtil.getHorizontalLinesMatchCount(thePrevDraw, lastDraw);
			int vCount = DrawUtil.getVerticalLinesMatchCount(thePrevDraw, lastDraw);
			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Vertical lines match count - " + (vCount) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Horizontal lines match count - " + hCount }, //
					// new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Sum lines match count - " + (hCount + vCount) }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			Draw prevDraw = null;
			for (Draw draw : draws) {
				int currHCount = DrawUtil.getHorizontalLinesMatchCount(prevDraw, draw);
				int currVCount = DrawUtil.getVerticalLinesMatchCount(prevDraw, draw);

				String date = draw.getDateString();
				int totalCount = currVCount + currHCount;
				// rows.add(new Object[] { "\"" + date + "\"", currVCount, currHCount, totalCount });
				rows.add(new Object[] { "\"" + date + "\"", currVCount, currHCount });
				prevDraw = draw;
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.RED, //
					ReportConstants.BLUE, //
					// ReportConstants.ORANGE, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Diameter
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart07(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (Draw draw : draws) {
				int diameter = draw.getDiameter();
				if (diameter < min) {
					min = diameter;
				}
				if (diameter > max) {
					max = diameter;
				}
			}
			float percent = DrawUtil.getPercent(min, max, lastDraw.getDiameter());

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, yTitle + " - [" + min + ", " + max + "] - " + lastDraw.getDiameter() + " (" + percent + "%)" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				rows.add(new Object[] { "\"" + date + "\"", draw.getDiameter() });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__VIOLET, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Area
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @param index
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart08(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (Draw draw : draws) {
				int area = draw.getArea();
				if (area <= 0) {
					continue;
				}
				if (area < min) {
					min = area;
				}
				if (area > max) {
					max = area;
				}
			}
			float percent = DrawUtil.getPercent(min, max, lastDraw.getArea());

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Area - [" + min + ", " + max + "] - " + lastDraw.getArea() + " (" + percent + "%)" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				rows.add(new Object[] { "\"" + date + "\"", draw.getArea(), });
			}

			String[] colors = new String[] { //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
			};

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(colors);
			output = chartGenerator.generate(args);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Areas
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @param index
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart06(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw, int index) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			int[] lastDraw_areas = lastDraw.getDistanceAreas();

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Area[" + index + "] - " + lastDraw_areas[index] }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int[] areas = draw.getDistanceAreas();

				rows.add(new Object[] { //
						"\"" + date + "\"", //
						areas[index], //
						// areas[2], //
						// areas[1], //
						// areas[0], //
				});
			}

			String[] colors = null;
			if (index == 3) {
				colors = new String[] { //
						ReportConstants.CHART_LINE_COLOR__RED, //
				};
			} else if (index == 2) {
				colors = new String[] { //
						ReportConstants.ORANGE, //
				};
			} else if (index == 1) {
				colors = new String[] { //
						ReportConstants.CHART_LINE_COLOR__GREEN, //
				};
			} else if (index == 0) {
				colors = new String[] { //
						ReportConstants.DODGER_BLUE, //
				};
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(colors);
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Numbers-on-Border Count
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart09(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (Draw draw : draws) {
				int count = DrawHelper.INSTANCE.getNumsOnBorderCount(draw);
				if (count < min) {
					min = count;
				}
				if (count > max) {
					max = count;
				}
			}

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "On Border Count - [" + min + ", " + max + "] - " + DrawHelper.INSTANCE.getNumsOnBorderCount(lastDraw) }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = DrawHelper.INSTANCE.getNumsOnBorderCount(draw);
				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Sum of 5 first digits
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart10(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			float _sumOf6 = DrawUtil.getSumOf6FirstDigits(lastDraw);
			float _sumOf5 = DrawUtil.getSumOf5FirstDigits(lastDraw);

			float min6 = Float.MAX_VALUE;
			float max6 = Float.MIN_VALUE;

			float min5 = Float.MAX_VALUE;
			float max5 = Float.MIN_VALUE;
			for (Draw draw : draws) {
				float currSumOf6 = DrawUtil.getSumOf6FirstDigits(draw);
				float currSumOf5 = DrawUtil.getSumOf5FirstDigits(draw);

				if (currSumOf6 < min6) {
					min6 = currSumOf6;
				}
				if (currSumOf6 > max6) {
					max6 = currSumOf6;
				}

				if (currSumOf5 < min5) {
					min5 = currSumOf5;
				}
				if (currSumOf5 > max5) {
					max5 = currSumOf5;
				}
			}

			float percent_sum6 = DrawUtil.getPercent(min6, max6, _sumOf6);
			float percent_sum5 = DrawUtil.getPercent(min5, max5, _sumOf5);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Sum of 6 first digits - [" + min6 + ", " + max6 + "] - " + _sumOf6 + " (" + percent_sum6 + "%)" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Sum of 5 first digits - [" + min5 + ", " + max5 + "] - " + _sumOf5 + " (" + percent_sum5 + "%)" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				float sumOf6 = DrawUtil.getSumOf6FirstDigits(draw);
				float sumOf5 = DrawUtil.getSumOf5FirstDigits(draw);

				rows.add(new Object[] { "\"" + date + "\"", sumOf6, sumOf5 });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__RED, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
			}); //
			// options.setCurveType(Options.CURVE_TYPE__FUNCTION);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Sum of numbers
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart11(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			float _sumOf6 = DrawUtil.getSumOf6Numbers(lastDraw);
			float _sumOf5 = DrawUtil.getSumOf5Numbers(lastDraw);

			float min6 = Float.MAX_VALUE;
			float max6 = Float.MIN_VALUE;

			float min5 = Float.MAX_VALUE;
			float max5 = Float.MIN_VALUE;
			for (Draw draw : draws) {
				float currSumOf6 = DrawUtil.getSumOf6Numbers(draw);
				float currSumOf5 = DrawUtil.getSumOf5Numbers(draw);

				if (currSumOf6 < min6) {
					min6 = currSumOf6;
				}
				if (currSumOf6 > max6) {
					max6 = currSumOf6;
				}

				if (currSumOf5 < min5) {
					min5 = currSumOf5;
				}
				if (currSumOf5 > max5) {
					max5 = currSumOf5;
				}
			}

			float percent_sum6 = DrawUtil.getPercent(min6, max6, _sumOf6);
			float percent_sum5 = DrawUtil.getPercent(min5, max5, _sumOf5);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Sum of 6 numbers - [" + min6 + ", " + max6 + "] - " + _sumOf6 + " (" + percent_sum6 + "%)" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Sum of 5 numbers - [" + min5 + ", " + max5 + "] - " + _sumOf5 + " (" + percent_sum5 + "%)" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				float sumOf6 = DrawUtil.getSumOf6Numbers(draw);
				float sumOf5 = DrawUtil.getSumOf5Numbers(draw);

				rows.add(new Object[] { "\"" + date + "\"", sumOf6, sumOf5 });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__RED, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
			}); //
			// options.setCurveType(Options.CURVE_TYPE__FUNCTION);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * 4 distance percentage of n1 - n5
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart10_0(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance Percent (n5-n4) - " + lastDraw.getDistancePercent(3) + "%" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance Percent (n4-n3) - " + lastDraw.getDistancePercent(2) + "%" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance Percent (n3-n2) - " + lastDraw.getDistancePercent(1) + "%" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance Percent (n2-n1) - " + lastDraw.getDistancePercent(0) + "%" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance (n/a) - 0%" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				// rows.add(new Object[] { "\"" + date + "\"", draw.getDistPercent4(), draw.getDistPercent3(), draw.getDistPercent2(), draw.getDistPercent1() });

				int v0 = 0;
				float v1 = v0 + draw.getDistancePercent(0);
				float v2 = v1 + draw.getDistancePercent(1);
				float v3 = v2 + draw.getDistancePercent(2);
				float v4 = v3 + draw.getDistancePercent(3);

				rows.add(new Object[] { "\"" + date + "\"", v4, v3, v2, v1, v0 });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					/*-
					ReportConstants.CHART_LINE_COLOR__RED, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DODGER_BLUE, //
					*/
					ReportConstants.CORAL, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DARK_TURQUOISE, //
					ReportConstants.DODGER_BLUE, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * 4 distance of n1 - n5
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart12(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance (n5-n4) - " + lastDraw.getDistanceValue(3) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance (n4-n3) - " + lastDraw.getDistanceValue(2) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance (n3-n2) - " + lastDraw.getDistanceValue(1) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance (n2-n1) - " + lastDraw.getDistanceValue(0) }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance (n/a) - 0" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				// rows.add(new Object[] { "\"" + date + "\"", draw.getDistValue4(), draw.getDistValue3(), draw.getDistValue2(), draw.getDistValue1() });

				int v0 = 0;
				float v1 = v0 + draw.getDistanceValue(0);
				float v2 = v1 + draw.getDistanceValue(1);
				float v3 = v2 + draw.getDistanceValue(2);
				float v4 = v3 + draw.getDistanceValue(3);

				rows.add(new Object[] { "\"" + date + "\"", v4, v3, v2, v1, v0 });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					/*-
					ReportConstants.CHART_LINE_COLOR__RED, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DODGER_BLUE, //
					*/
					ReportConstants.CORAL, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DARK_TURQUOISE, //
					ReportConstants.DODGER_BLUE, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * 4 distance percentage of n1 - n5
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart13(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance Percent (n5-n4) - " + lastDraw.getDistancePercent(3) + "%" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance Percent (n4-n3) - " + lastDraw.getDistancePercent(2) + "%" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance Percent (n3-n2) - " + lastDraw.getDistancePercent(1) + "%" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance Percent (n2-n1) - " + lastDraw.getDistancePercent(0) + "%" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance (n/a) - 0%" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();

				int v0 = 0;
				float v1 = v0 + draw.getDistancePercent(0);
				float v2 = v1 + draw.getDistancePercent(1);
				float v3 = v2 + draw.getDistancePercent(2);
				float v4 = v3 + draw.getDistancePercent(3);

				rows.add(new Object[] { "\"" + date + "\"", v4 - v2, v3 - v2, v2 - v2, v1 - v2, v0 - v2 });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					/*-
					ReportConstants.CHART_LINE_COLOR__RED, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DODGER_BLUE, //
					*/
					ReportConstants.CORAL, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DARK_TURQUOISE, //
					ReportConstants.DODGER_BLUE, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Sum of 5 numbers
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String _getLineChart04(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			int _sumOf5 = DrawUtil.getSumOf5Numbers(lastDraw);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Sum of 5 numbers - " + _sumOf5 }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int sumOf5 = DrawUtil.getSumOf5Numbers(draw);
				rows.add(new Object[] { "\"" + date + "\"", sumOf5 });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__RED, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * First digit of 5 Numbers count
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String _getLineChart06(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__BAR);

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
			String[] keys = map.keySet().toArray(new String[map.size()]);

			String[][] columns = new String[keys.length + 1][2];
			columns[0] = new String[] { "First Digits", "Count" };
			int i = 1;
			for (String key : keys) {
				columns[i++] = new String[] { key, map.get(key).toString() };
			}

			List<Object[]> rows = new ArrayList<Object[]>();
			for (String key : keys) {
				String count = map.get(key).toString();
				rows.add(new Object[] { count, count });
			}

			// chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CORAL, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DARK_TURQUOISE, //
					ReportConstants.DODGER_BLUE, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * 4 distance values of n1 - n5
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart09(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw, int index) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance[" + index + "] - " + lastDraw.getDistancesValue()[index] }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				rows.add(new Object[] { //
						"\"" + date + "\"", //
						draw.getDistancesValue()[index], //
				} //
				);
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			String[] colors = null;
			if (index == 3) {
				colors = new String[] { //
						ReportConstants.CHART_LINE_COLOR__RED, //
				};
			} else if (index == 2) {
				colors = new String[] { //
						ReportConstants.ORANGE, //
				};
			} else if (index == 1) {
				colors = new String[] { //
						ReportConstants.CHART_LINE_COLOR__GREEN, //
				};
			} else if (index == 0) {
				colors = new String[] { //
						ReportConstants.DODGER_BLUE, //
				};
			}

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(colors);
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Areas
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @param index
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart11(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw, int index) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			int[] lastDraw_areas = lastDraw.getDistanceAreas();

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Area[" + index + "] - " + lastDraw_areas[index] }, //
					// new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Distance[" + index + "] - " + lastDraw.getDistancesValue()[index] }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int[] areas = draw.getDistanceAreas();

				rows.add(new Object[] { //
						"\"" + date + "\"", //
						areas[index], //
						// draw.getDistancesValue()[index], //
				});
			}

			String[] colors = null;
			if (index == 3) {
				colors = new String[] { //
						ReportConstants.CHART_LINE_COLOR__RED, //
						// ReportConstants.CHART_LINE_COLOR__RED, //
				};
			} else if (index == 2) {
				colors = new String[] { //
						ReportConstants.ORANGE, //
						// ReportConstants.ORANGE, //
				};
			} else if (index == 1) {
				colors = new String[] { //
						ReportConstants.CHART_LINE_COLOR__GREEN, //
						// ReportConstants.CHART_LINE_COLOR__GREEN, //
				};
			} else if (index == 0) {
				colors = new String[] { //
						ReportConstants.DODGER_BLUE, //
						// ReportConstants.DODGER_BLUE, //
				};
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(colors);
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Stand Deviation of 5 numbers and 4 distances
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart14(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			float _SD5 = DrawUtil.getSDof5Numbers(lastDraw);
			float _SD4 = DrawUtil.getSDofDistances(lastDraw);

			float min_SD5 = Float.MAX_VALUE;
			float max_SD5 = Float.MIN_VALUE;

			float min_SD4 = Float.MAX_VALUE;
			float max_SD4 = Float.MIN_VALUE;

			for (Draw draw : draws) {
				float SD5 = DrawUtil.getSDof5Numbers(draw);
				if (SD5 < min_SD5) {
					min_SD5 = SD5;
				}
				if (SD5 > max_SD5) {
					max_SD5 = SD5;
				}

				float SD4 = DrawUtil.getSDofDistances(draw);
				if (SD4 < min_SD4) {
					min_SD4 = SD4;
				}
				if (SD4 > max_SD4) {
					max_SD4 = SD4;
				}
			}

			float percent_SD5 = DrawUtil.getPercent(min_SD5, max_SD5, _SD5);
			float percent_SD4 = DrawUtil.getPercent(min_SD4, max_SD4, _SD4);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Stand Deviation of 5 numbers - [" + min_SD5 + ", " + max_SD5 + "] - " + _SD5 + " (" + percent_SD5 + "%)" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Stand Deviation of 4 distances - [" + min_SD4 + ", " + max_SD4 + "] - " + _SD4 + " (" + percent_SD4 + "%)" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();

				float SD5 = DrawUtil.getSDof5Numbers(draw);
				float SD4 = DrawUtil.getSDofDistances(draw);

				rows.add(new Object[] { "\"" + date + "\"", SD5, SD4 });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.CHART_LINE_COLOR__BLUE, //
			}); //
			// options.setCurveType(Options.CURVE_TYPE__FUNCTION);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Stand Deviation of 4 distances
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String _getLineChart10(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			float _SD4 = DrawUtil.getSDofDistances(lastDraw);
			float min_SD4 = Float.MAX_VALUE;
			float max_SD4 = Float.MIN_VALUE;
			for (Draw draw : draws) {
				float SD4 = DrawUtil.getSDofDistances(draw);
				if (SD4 < min_SD4) {
					min_SD4 = SD4;
				}
				if (SD4 > max_SD4) {
					max_SD4 = SD4;
				}
			}
			float percent_SD4 = DrawUtil.getPercent(min_SD4, max_SD4, _SD4);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Stand Deviation of 4 distances - [" + min_SD4 + ", " + max_SD4 + "] - " + _SD4 + " (" + percent_SD4 + "%)" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				float SD4 = DrawUtil.getSDofDistances(draw);

				rows.add(new Object[] { "\"" + date + "\"", SD4 });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			/*-
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__RED, //
			});
			*/
			// options.setCurveType(Options.CURVE_TYPE__FUNCTION);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Prime Number Count
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @param includePB
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart15(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw, boolean includePB) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Prime Number Count" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = DrawUtil.getPrimeNumberCount(draw, includePB);

				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			/*-
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__RED, //
			});
			*/
			// options.setCurveType(Options.CURVE_TYPE__FUNCTION);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Evenly Divisible Count
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart16(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Evenly divisible count" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = DrawUtil.getEvenlyDivisibleCount(draw, false);

				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			/*-
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__RED, //
			});
			*/
			// options.setCurveType(Options.CURVE_TYPE__FUNCTION);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Continuous Numbers Count
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart17(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Continuous Numbers Count" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = DrawUtil.getContinuousNumbersCount(draw);

				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			/*-
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__RED, //
			});
			*/
			// options.setCurveType(Options.CURVE_TYPE__FUNCTION);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Same Digits Count
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart18(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Same Digits Count" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = DrawUtil.getSameDoubleDigitsCount(draw);

				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			/*-
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__RED, //
			});
			*/
			// options.setCurveType(Options.CURVE_TYPE__FUNCTION);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Area
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @return
	 * @throws Exception
	 */
	protected String _getLineChart16(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			int shift_v = 0;
			int[] lastDraw_areas = lastDraw.getDistanceAreas(shift_v);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Area 4 - " + lastDraw_areas[3] }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Area 3 - " + lastDraw_areas[2] }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Area 2 - " + lastDraw_areas[1] }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Area 1 - " + lastDraw_areas[0] }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int[] areas = draw.getDistanceAreas(shift_v);

				rows.add(new Object[] { //
						"\"" + date + "\"", //
						areas[3], //
						areas[2], //
						areas[1], //
						areas[0], //
				});
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__RED, //
					ReportConstants.ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.DODGER_BLUE, //
			}); //
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	protected String getLineChart19_N(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Numbers Count - North Area" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = draw.getAreasCount().getN_count();
				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	protected String getLineChart19_S(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Numbers Count - South Area" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = draw.getAreasCount().getS_count();
				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	protected String getLineChart19_W(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Numbers Count - West Area" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = draw.getAreasCount().getW_count();
				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	protected String getLineChart19_E(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Numbers Count - East Area" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = draw.getAreasCount().getE_count();
				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	protected String getLineChart20_NW(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Numbers Count - North West Area" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = draw.getAreasCount().getNW_count();
				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	protected String getLineChart20_NE(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Numbers Count - North East Area" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = draw.getAreasCount().getNE_count();
				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	protected String getLineChart20_SW(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Numbers Count - South West Area" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = draw.getAreasCount().getSW_count();
				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	protected String getLineChart20_SE(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Numbers Count - South East Area" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int count = draw.getAreasCount().getSE_count();
				rows.add(new Object[] { "\"" + date + "\"", count });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	protected String getLineChart21_PB_section(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw) {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "PB Section" }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				int PB_section = draw.getAreasCount().getPB_section();
				rows.add(new Object[] { "\"" + date + "\"", PB_section });
			}

			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Digits Count in 5 Numbers or 6 Numbers
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @param includePB
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart22(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw, boolean includePB, int number, String[] colors) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			List<DrawDigit> lastDraw_digits = DrawUtil.getDigitsCount(lastDraw, includePB);
			int lastDraw_numCount = lastDraw_digits.get(number).getCount();

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit " + number + " Count - " + lastDraw_numCount }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				List<DrawDigit> digits = DrawUtil.getDigitsCount(draw, includePB);

				rows.add(new Object[] { //
						"\"" + date + "\"", //
						digits.get(number).getCount(), //
				});
			}
			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);

			String color = colors[number];
			options.setColors(new String[] { color });
			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}

	/**
	 * Digits Count in 5 Numbers or 6 Numbers
	 * 
	 * @param args
	 * @param chartName
	 * @param chartTitle
	 * @param xTitle
	 * @param yTitle
	 * @param chartWidth
	 * @param chartHeight
	 * @param draws
	 * @param lastDraw
	 * @param includePB
	 * @return
	 * @throws Exception
	 */
	protected String getLineChart23(Map<String, Object> args, String chartName, String chartTitle, String xTitle, String yTitle, int chartWidth, int chartHeight, List<Draw> draws, Draw lastDraw, boolean includePB) throws Exception {
		String chartContent = "";

		Object output = null;
		try {
			IChartGenerator chartGenerator = createChartGenerator(ReportConstants.CHART__GOOGLE_CHART);
			chartGenerator.setName(chartName);
			chartGenerator.setChartType(ReportConstants.CHART_TYPE__LINE);

			List<DrawDigit> lastDraw_digits = DrawUtil.getDigitsCount(lastDraw, includePB);

			String[][] columns = new String[][] { //
					new String[] { ReportConstants.COLUMN_TYPE__STRING, "X" }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 0 Count - " + lastDraw_digits.get(0).getCount() }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 1 Count - " + lastDraw_digits.get(1).getCount() }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 2 Count - " + lastDraw_digits.get(2).getCount() }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 3 Count - " + lastDraw_digits.get(3).getCount() }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 4 Count - " + lastDraw_digits.get(4).getCount() }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 5 Count - " + lastDraw_digits.get(5).getCount() }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 6 Count - " + lastDraw_digits.get(6).getCount() }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 7 Count - " + lastDraw_digits.get(7).getCount() }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 8 Count - " + lastDraw_digits.get(8).getCount() }, //
					new String[] { ReportConstants.COLUMN_TYPE__NUMBER, "Digit 9 Count - " + lastDraw_digits.get(9).getCount() }, //
			};

			List<Object[]> rows = new ArrayList<Object[]>();
			for (Draw draw : draws) {
				String date = draw.getDateString();
				List<DrawDigit> digits = DrawUtil.getDigitsCount(draw, includePB);

				rows.add(new Object[] { //
						"\"" + date + "\"", //
						digits.get(0).getCount(), //
						digits.get(1).getCount(), //
						digits.get(2).getCount(), //
						digits.get(3).getCount(), //
						digits.get(4).getCount(), //
						digits.get(5).getCount(), //
						digits.get(6).getCount(), //
						digits.get(7).getCount(), //
						digits.get(8).getCount(), //
						digits.get(9).getCount(), //
				});
			}
			chartGenerator.setColumns(columns);
			chartGenerator.setRows(rows);

			Options options = chartGenerator.getOptions();
			options.setTitle(chartTitle);
			options.setHAxisTitle(xTitle);
			options.setVAxisTitle(yTitle);
			options.setWidth(chartWidth);
			options.setHeight(chartHeight);
			options.setBackgroundColor(ReportConstants.CHARG_BG);
			options.setColors(new String[] { //
					ReportConstants.CHART_LINE_COLOR__GREY0, //
					ReportConstants.RED, //
					ReportConstants.CHART_LINE_COLOR__ORANGE, //
					ReportConstants.CHART_LINE_COLOR__GREEN, //
					ReportConstants.CHART_LINE_COLOR__GREEN2, //
					ReportConstants.CHART_LINE_COLOR__GREEN_BLUE, //
					ReportConstants.CHART_LINE_COLOR__BLUE, //
					ReportConstants.CHART_LINE_COLOR__MAYA_BLUE, //
					ReportConstants.BLUE, //
					ReportConstants.CHART_LINE_COLOR__VIOLET, //
			}); //
			// options.setCurveType(Options.CURVE_TYPE__FUNCTION);

			output = chartGenerator.generate(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (output != null) {
			chartContent += output.toString();
		}
		return chartContent;
	}
}
