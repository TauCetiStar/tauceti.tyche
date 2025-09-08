package org.tauceti.tyche.chart.impl;

import java.util.List;
import java.util.Map;

import org.tauceti.tyche.chart.AbstractReportContentGenerator;
import org.tauceti.tyche.chart.ReportConstants;
import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.util.DrawHelper;

public class ReportContentGeneratorCardImpl extends AbstractReportContentGenerator {

	public static String ID = "ReportContentGeneratorCardImpl";

	public ReportContentGeneratorCardImpl() {
		setId(ID);
	}

	protected boolean isPowerBall(Map<String, Object> args) {
		boolean isPowerBall = false;
		// boolean isMegaMillion = false;
		if (args != null) {
			Object type = args.get(ReportConstants.ARG__DRAWS_TYPE);
			if (ReportConstants.DRAWS_TYPE__POWERBALL.equals(type)) {
				isPowerBall = true;
			} else if (ReportConstants.DRAWS_TYPE__MEGA_MILLION.equals(type)) {
				// isMegaMillion = true;
			}
		}
		return isPowerBall;
	}

	protected boolean isMegaMillion(Map<String, Object> args) {
		// boolean isPowerBall = false;
		boolean isMegaMillion = false;
		if (args != null) {
			Object type = args.get(ReportConstants.ARG__DRAWS_TYPE);
			if (ReportConstants.DRAWS_TYPE__POWERBALL.equals(type)) {
				// isPowerBall = true;
			} else if (ReportConstants.DRAWS_TYPE__MEGA_MILLION.equals(type)) {
				isMegaMillion = true;
			}
		}
		return isMegaMillion;
	}

	@Override
	public Object generate(Map<String, Object> args) {
		return generate_0(args);
	}

	public Object generate_0(Map<String, Object> args) {
		// args.put(ReportConstants.ARG__DRAWS_TYPE, ReportConstants.DRAWS_TYPE__MEGA_MILLION);

		String contents = "";
		try {
			String styleContents = getStyle();
			contents += styleContents;

			// History draws
			int colSize = 10;
			List<Draw> draws = DrawHelper.INSTANCE.getDraws(args);
			int size = draws.size();

			for (int i = 0; i < size; i++) {
				int j = i + (colSize - 1);
				if (j >= size) {
					j = size - 1;
				}

				String rowContents = getRowHtml(args, draws, i, j, false, false);
				contents += rowContents;

				i = j;
			}

			// Prev Prediction draws
			{
				List<Draw> predictionDraws = DrawHelper.INSTANCE.getPrevPredictionDraws(args);
				if (predictionDraws != null && !predictionDraws.isEmpty()) {
					int size2 = predictionDraws.size();
					String rowContents = getRowHtml(args, predictionDraws, 0, size2 - 1, true, false);
					contents += rowContents;
				}
			}

			for (int index = 0; index < 10; index++) {
				List<Draw> predictionDraws = DrawHelper.INSTANCE.getPrevPredictionDraws(args, index);
				if (predictionDraws != null && !predictionDraws.isEmpty()) {
					int size2 = predictionDraws.size();
					String rowContents = getRowHtml(args, predictionDraws, 0, size2 - 1, true, false);
					contents += rowContents;
				}
			}

			// Prediction draws
			{
				List<Draw> predictionDraws = DrawHelper.INSTANCE.getPredictionDraws(args);
				if (predictionDraws != null && !predictionDraws.isEmpty()) {
					int size2 = predictionDraws.size();
					String rowContents = getRowHtml(args, predictionDraws, 0, size2 - 1, false, true);
					contents += rowContents;
				}
			}

			for (int index = 0; index < 10; index++) {
				List<Draw> predictionDraws = DrawHelper.INSTANCE.getPredictionDraws(args, index);
				if (predictionDraws != null && !predictionDraws.isEmpty()) {
					int size2 = predictionDraws.size();
					String rowContents = getRowHtml(args, predictionDraws, 0, size2 - 1, false, true);
					contents += rowContents;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

	public Object generate_1(Map<String, Object> args) {
		// args.put(ReportConstants.ARG__DRAWS_TYPE, ReportConstants.DRAWS_TYPE__MEGA_MILLION);
		String contents = "";
		try {
			String styleContents = getStyle();
			contents += styleContents;

			// History draws
			int colSize = 10;
			List<Draw> draws = DrawHelper.INSTANCE.getDraws(args);
			int size = draws.size();

			for (int i = 0; i < size; i++) {
				int j = i + (colSize - 1);
				if (j >= size) {
					j = size - 1;
				}

				String rowContents = getRowHtml(args, draws, i, j, false, false);
				contents = rowContents + contents;

				i = j;
			}

			String predictionContents = "";
			// Prev Prediction draws
			{
				List<Draw> predictionDraws = DrawHelper.INSTANCE.getPrevPredictionDraws(args);
				if (predictionDraws != null && !predictionDraws.isEmpty()) {
					int size2 = predictionDraws.size();
					String rowContents = getRowHtml(args, predictionDraws, 0, size2 - 1, true, false);
					predictionContents += rowContents;
				}
			}

			for (int index = 0; index < 10; index++) {
				List<Draw> predictionDraws = DrawHelper.INSTANCE.getPrevPredictionDraws(args, index);
				if (predictionDraws != null && !predictionDraws.isEmpty()) {
					int size2 = predictionDraws.size();
					String rowContents = getRowHtml(args, predictionDraws, 0, size2 - 1, true, false);
					predictionContents += rowContents;
				}
			}

			// Prediction draws
			{
				List<Draw> predictionDraws = DrawHelper.INSTANCE.getPredictionDraws(args);
				if (predictionDraws != null && !predictionDraws.isEmpty()) {
					int size2 = predictionDraws.size();
					String rowContents = getRowHtml(args, predictionDraws, 0, size2 - 1, false, true);
					predictionContents += rowContents;
				}
			}

			for (int index = 0; index < 10; index++) {
				List<Draw> predictionDraws = DrawHelper.INSTANCE.getPredictionDraws(args, index);
				if (predictionDraws != null && !predictionDraws.isEmpty()) {
					int size2 = predictionDraws.size();
					String rowContents = getRowHtml(args, predictionDraws, 0, size2 - 1, false, true);
					predictionContents += rowContents;
				}
			}

			contents = predictionContents + contents;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

	/**
	 * https://en.wikipedia.org/wiki/Web_colors
	 * 
	 * https://stackoverflow.com/questions/32660748/how-to-use-apples-new-san-francisco-font-on-a-webpage
	 * 
	 * https://www.w3schools.com/css/css_float_examples.asp
	 * 
	 * https://www.geeksforgeeks.org/how-to-fix-the-height-of-rows-in-the-table/
	 * 
	 * @return
	 */
	protected String getStyle() {
		String contents = "";
		contents += "<style>\r";

		contents += "body {,\r";
		contents += "  font-family: -apple-system, BlinkMacSystemFont, sans-serif;\r";
		contents += "  font-weight: 400;\r";
		contents += "}\r";

		contents += "* {\r";
		contents += "  box-sizing: border-box;\r";
		contents += "}\r";

		contents += ".box {\r";
		contents += "  float: left;\r";
		contents += "  width: 10%;\r";
		contents += "  padding: 15px;\r";
		contents += "}\r";

		contents += ".clearfix::after {\r";
		contents += "  content: \"\";\r";
		contents += "  clear: both;\r";
		contents += "  display: table;\r";
		contents += "}\r";

		// contents += "table,\r";
		contents += "td {\r";
		contents += "  border: 1px solid #999999;\r";
		contents += "  width: 10px;\r";
		contents += "  text-align:center;\r";
		contents += "  font-family: -apple-system, BlinkMacSystemFont, sans-serif;\r";
		contents += "  font-weight: 400;\r";
		contents += "  font-size: 12px;\r";
		contents += "  padding: 4px 2px 4px 2px;\r";
		contents += "}\r";

		contents += "tr {,\r";
		contents += "  height: 12px;\r";
		contents += "}\r";

		contents += ".title_left {\r";
		contents += "  text-align:left;\r";
		contents += "  font-weight: bold;\r";
		contents += "  padding: 2px 4px 2px 4px;\r";
		contents += "}\r";

		contents += ".title_center {\r";
		contents += "  text-align:center;\r";
		contents += "  font-weight: bold;\r";
		contents += "  padding: 2px 4px 2px 4px;\r";
		contents += "}\r";

		contents += ".title_right {\r";
		contents += "  text-align:right;\r";
		contents += "  font-weight: bold;\r";
		contents += "  padding: 2px 4px 2px 4px;\r";
		contents += "}\r";

		// real numbers
		contents += ".isNum1 {\r";
		contents += "  background-color: #0d802b;\r";
		contents += "  color: #ffffff;\r";
		contents += "  font-weight: bold;\r";
		contents += "}\r";

		contents += ".isPB1 {\r";
		contents += "  background-color: #0d802b;\r";
		contents += "  color: #ffffff;\r";
		contents += "  font-weight: bold;\r";
		contents += "}\r";

		// prediction numbers
		contents += ".isNum2 {\r";
		contents += "  background-color: #2c6ee8;\r";
		contents += "  color: #ffffff;\r";
		contents += "  font-weight: bold;\r";
		contents += "}\r";

		contents += ".isPB2 {\r";
		contents += "  background-color: #2c6ee8;\r";
		contents += "  color: #ffffff;\r";
		contents += "  font-weight: bold;\r";
		contents += "}\r";

		// prev prediction numbers
		contents += ".isNum3 {\r";
		contents += "  background-color: #FF8C00;\r";
		contents += "  color: #ffffff;\r";
		contents += "  font-weight: bold;\r";
		contents += "}\r";

		contents += ".isPB3 {\r";
		contents += "  background-color: #FF8C00;\r";
		contents += "  color: #ffffff;\r";
		contents += "  font-weight: bold;\r";
		contents += "}\r";

		contents += "</style>\r";
		return contents;
	}

	/**
	 * 
	 * @param args
	 * @param draws
	 * @param i
	 * @param j
	 * @param isPrevPrediction
	 * @param isPrediction
	 * @return
	 */
	protected String getRowHtml(Map<String, Object> args, List<Draw> draws, int i, int j, boolean isPrevPrediction, boolean isPrediction) {
		boolean isMegaMillion = isMegaMillion(args);
		String contents = "";
		try {
			contents += "<div class=\"clearfix\">\r\n";
			for (int index = i; index <= j; index++) {
				contents += "  <div class=\"box\">\r\n";

				Draw draw = draws.get(index);

				String cardContent = null;
				if (isMegaMillion) {
					cardContent = getCardHtml_MegaMillion(args, draw, isPrevPrediction, isPrediction);
				} else {
					cardContent = getCardHtml_PowerBall(args, draw, isPrevPrediction, isPrediction);
				}

				contents += cardContent;
				contents += "  </div>\r\n";
			}
			contents += "</div>\r\n";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

	/**
	 * 
	 * @param args
	 * @param draw
	 * @param isPrevPrediction
	 * @param isPrediction
	 * @return
	 */
	protected String getCardHtml_PowerBall(Map<String, Object> args, Draw draw, boolean isPrevPrediction, boolean isPrediction) {
		String contents = "";
		try {
			contents += "<table>\r\n";

			int id = draw.getDrawId();
			String dateStr = draw.getDateString();

			// Section 1
			contents += "<tr>\r\n";
			contents += "<td colspan=\"6\" class=\"title_left\">PLAY&nbsp;[" + dateStr + "]</td>\r\n";
			contents += "<td colspan=\"4\" class=\"title_center\">&#9660;SELECT 5</td>\r\n";
			contents += "</tr>\r\n";

			int num = 1;
			for (int i = 0; i < 7; i++) {
				contents += "<tr>\r\n";
				for (int j = 0; j < 10; j++) {
					if (num <= 69) {
						if (draw.containsNum(num)) {
							if (isPrevPrediction) {
								contents += "<td class=\"isNum3\">" + num + "</td>\r\n";
							} else if (isPrediction) {
								contents += "<td class=\"isNum2\">" + num + "</td>\r\n";
							} else {
								contents += "<td class=\"isNum1\">" + num + "</td>\r\n";
							}
						} else {
							contents += "<td>" + num + "</td>\r\n";
						}
					} else {
						contents += "<td></td>\r\n";
					}
					num++;
				}
				contents += "</tr>\r\n";
			}

			// Section 2
			contents += "<tr>\r\n";
			contents += "<td colspan=\"6\" class=\"title_left\">ID&nbsp;[#" + id + "]</td>\r\n";
			contents += "<td colspan=\"4\" class=\"title_center\">&#9660;SELECT 1</td>\r\n";
			contents += "</tr>\r\n";

			int pb = 1;
			for (int i = 0; i < 3; i++) {
				contents += "<tr>\r\n";
				for (int j = 0; j < 10; j++) {
					if (pb <= 26) {
						if (draw.isPB(pb)) {
							if (isPrevPrediction) {
								contents += "<td class=\"isPB3\">" + pb + "</td>\r\n";
							} else if (isPrediction) {
								contents += "<td class=\"isPB2\">" + pb + "</td>\r\n";
							} else {
								contents += "<td class=\"isPB1\">" + pb + "</td>\r\n";
							}
						} else {
							contents += "<td>" + pb + "</td>\r\n";
						}
					} else {
						contents += "<td></td>\r\n";
					}
					pb++;
				}
				contents += "</tr>\r\n";
			}

			contents += "</table>\r\n";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}

	/**
	 * 
	 * @param args
	 * @param draw
	 * @param isPrevPrediction
	 * @param isPrediction
	 * @return
	 */
	protected String getCardHtml_MegaMillion(Map<String, Object> args, Draw draw, boolean isPrevPrediction, boolean isPrediction) {
		String contents = "";
		try {
			contents += "<table>\r\n";

			int id = draw.getDrawId();
			String dateStr = draw.getDateString();

			// Section 1
			contents += "<tr>\r\n";
			contents += "<td colspan=\"6\" class=\"title_left\">PLAY&nbsp;[" + dateStr + "]</td>\r\n";
			contents += "<td colspan=\"4\" class=\"title_center\">&#9660;SELECT 5</td>\r\n";
			contents += "</tr>\r\n";

			int num = 1;
			for (int i = 0; i < 7; i++) {
				contents += "<tr>\r\n";
				for (int j = 0; j < 10; j++) {
					if (num <= 70) {
						if (draw.containsNum(num)) {
							if (isPrevPrediction) {
								contents += "<td class=\"isNum3\">" + num + "</td>\r\n";
							} else if (isPrediction) {
								contents += "<td class=\"isNum2\">" + num + "</td>\r\n";
							} else {
								contents += "<td class=\"isNum1\">" + num + "</td>\r\n";
							}
						} else {
							contents += "<td>" + num + "</td>\r\n";
						}
					} else {
						contents += "<td></td>\r\n";
					}
					num++;
				}
				contents += "</tr>\r\n";
			}

			// Section 2
			contents += "<tr>\r\n";
			contents += "<td colspan=\"6\" class=\"title_left\">ID&nbsp;[#" + id + "]</td>\r\n";
			contents += "<td colspan=\"4\" class=\"title_center\">&#9660;SELECT 1</td>\r\n";
			contents += "</tr>\r\n";

			int pb = 1;
			for (int i = 0; i < 3; i++) {
				contents += "<tr>\r\n";
				for (int j = 0; j < 10; j++) {
					if (pb <= 25) {
						if (draw.isPB(pb)) {
							if (isPrevPrediction) {
								contents += "<td class=\"isPB3\">" + pb + "</td>\r\n";
							} else if (isPrediction) {
								contents += "<td class=\"isPB2\">" + pb + "</td>\r\n";
							} else {
								contents += "<td class=\"isPB1\">" + pb + "</td>\r\n";
							}
						} else {
							contents += "<td>" + pb + "</td>\r\n";
						}
					} else {
						contents += "<td></td>\r\n";
					}
					pb++;
				}
				contents += "</tr>\r\n";
			}

			contents += "</table>\r\n";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contents;
	}
}
