package org.tauceti.tyche.chart.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.tauceti.tyche.chart.AbstractChartGenerator;
import org.tauceti.tyche.chart.Options;
import org.tauceti.tyche.chart.ReportConstants;

/**
 * 
 * @author Yang Yang
 *
 */
public class ChartGeneratorGoogleChartImpl extends AbstractChartGenerator {

	public static String ID = ReportConstants.CHART__GOOGLE_CHART;

	public ChartGeneratorGoogleChartImpl() {
		setId(ID);
	}

	@Override
	public Object generate(Map<String, Object> args) {
		// Check parameters
		if (this.name == null || this.name.isEmpty()) {
			throw new IllegalStateException("name is not set.");
		}
		if (this.chartType == null || this.chartType.isEmpty()) {
			throw new IllegalStateException("chart type is not set.");
		}
		if (this.columns == null || this.columns.length == 0) {
			// throw new IllegalStateException("columns are not set.");
		}

		// Prepare data
		String NEW_LINE = System.lineSeparator();
		String functionName = "draw" + this.name + "Function";
		String divId = this.name + "_div";

		String output = "";
		output += "<script type=\"text/javascript\">" + NEW_LINE;
		output += "google.charts.setOnLoadCallback(" + functionName + ");" + NEW_LINE;

		// Start of function
		output += "function " + functionName + "() {" + NEW_LINE;

		// 1. Create data table
		output += "  var data = new google.visualization.DataTable();" + NEW_LINE;

		// 2. Set columns
		int columnNum = 0;
		if (this.columns != null) {
			for (String[] column : this.columns) {
				String columnType = column[0];
				String columnName = column[1];
				output += "  data.addColumn('" + columnType + "', '" + columnName + "');" + NEW_LINE;
			}
			columnNum = this.columns.length;
		}

		// 3. Set rows
		String rowsStr = "";

		if (this.rowsArray != null && this.rowsArray.length > 0) {
			int rowSize = this.rowsArray.length;
			for (int i = 0; i < rowSize; i++) {
				Object[] row = this.rowsArray[i];
				if (row.length >= columnNum) {
					if (!rowsStr.isEmpty()) {
						rowsStr += ", ";
					}

					String rowStr = "";
					for (Object rowElement : row) {
						if (!rowStr.isEmpty()) {
							rowStr += ",";
						}
						if (rowElement instanceof Date) {
							Date currDate = (Date) rowElement;

							Calendar cal = Calendar.getInstance();
							cal.setTime(currDate);
							int year = cal.get(Calendar.YEAR);
							int month = cal.get(Calendar.MONTH); // 0 based
							int day = cal.get(Calendar.DAY_OF_MONTH);
							int hour = cal.get(Calendar.HOUR_OF_DAY);
							int minute = cal.get(Calendar.MINUTE);
							int second = cal.get(Calendar.SECOND);

							// Convert Java Date object to "yyyy, MM, dd, HH, mm, ss"
							// Convert to 0 based month for javascript Date
							String currDateStr = year + ", " + month + ", " + day + ", " + hour + ", " + minute + ", " + second;

							rowStr += "new Date(" + currDateStr + ")";
						} else {
							rowStr += rowElement;
						}
					}
					rowsStr += "[" + rowStr + "]";
				}
			}

		} else if (this.rowsList != null && !this.rowsList.isEmpty()) {
			for (Object[] row : this.rowsList) {
				if (row.length >= columnNum) {
					if (!rowsStr.isEmpty()) {
						rowsStr += ", ";
					}

					String rowStr = "";
					for (Object rowElement : row) {
						if (!rowStr.isEmpty()) {
							rowStr += ",";
						}
						if (rowElement instanceof Date) {
							Date currDate = (Date) rowElement;

							Calendar cal = Calendar.getInstance();
							cal.setTime(currDate);
							int year = cal.get(Calendar.YEAR);
							int month = cal.get(Calendar.MONTH); // 0 based
							int day = cal.get(Calendar.DAY_OF_MONTH);
							int hour = cal.get(Calendar.HOUR_OF_DAY);
							int minute = cal.get(Calendar.MINUTE);
							int second = cal.get(Calendar.SECOND);

							// Convert Java Date object to "yyyy, MM, dd, HH, mm, ss"
							// Convert to 0 based month for javascript Date
							String currDateStr = year + ", " + month + ", " + day + ", " + hour + ", " + minute + ", " + second;

							rowStr += "new Date(" + currDateStr + ")";
						} else {
							rowStr += rowElement;
						}
					}
					rowsStr += "[" + rowStr + "]";
				}
			}

		} else {
			throw new IllegalStateException("rows are not set.");
		}

		output += NEW_LINE + "  data.addRows([" + rowsStr + "]);" + NEW_LINE;

		// 4. Set options
		Options options = getOptions();

		String title = options.getTitle();
		int width = options.getWidth();
		int height = options.getHeight();
		String hTitle = options.getHAxisTitle();
		int hShowTextEvery = options.getHAxisShowTextEvery();
		String vTitle = options.getVAxisTitle();
		String backgroundColor = options.getBackgroundColor();
		String[] colors = options.getColors();
		String curveType = options.getCurveType();
		String seriesType = options.getSeriesType();
		int[] seriesNumbers = options.getSeriesIndexes();

		boolean hasTitle = (title != null) ? true : false;
		boolean hasWidth = (width > 0) ? true : false;
		boolean hasHeight = (height > 0) ? true : false;
		boolean hasHAxis = (hTitle != null) ? true : false;
		boolean hasVAxis = (vTitle != null) ? true : false;
		boolean hasBackgroundColor = (backgroundColor != null) ? true : false;
		boolean hasColors = (colors != null && colors.length > 0) ? true : false;
		boolean hasCurveType = (curveType != null) ? true : false;
		boolean hasSeriesType = (seriesType != null) ? true : false;
		boolean hasSeries = (seriesNumbers != null && seriesNumbers.length > 0) ? true : false;

		String optionsStr = "";

		// (1) title, width, height
		if (hasTitle) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}
			optionsStr += NEW_LINE + "\t\ttitle: '" + title + "'";
		}
		if (hasWidth) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}
			optionsStr += NEW_LINE + "\t\twidth: " + width + "";
		}
		if (hasHeight) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}
			optionsStr += NEW_LINE + "\t\theight: " + height + "";
		}

		// (2) hAxis
		if (hasHAxis) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}
			optionsStr += NEW_LINE + "\t\thAxis: {";
			if (hTitle != null) {
				optionsStr += NEW_LINE + "\t\t\ttitle: '" + hTitle + "'";
			}
			if (hShowTextEvery > 1) {
				if (hTitle != null) {
					optionsStr += ",";
				}
				optionsStr += NEW_LINE + "\t\t\tshowTextEvery: '" + hShowTextEvery + "'";
			}
			optionsStr += NEW_LINE + "\t\t}";
		}

		// (3) vAxis
		if (hasVAxis) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}
			optionsStr += NEW_LINE + "\t\tvAxis: {";
			if (vTitle != null) {
				optionsStr += NEW_LINE + "\t\t\ttitle: '" + vTitle + "'";
			}
			optionsStr += NEW_LINE + "\t\t}";
		}

		// (4) background color
		if (hasBackgroundColor) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}
			optionsStr += NEW_LINE + "\t\tbackgroundColor: '" + backgroundColor + "'";
		}

		// (5) colors
		if (hasColors) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}

			String colorsStr = "";
			for (String lineColor : colors) {
				if (!colorsStr.isEmpty()) {
					colorsStr += ", ";
				}
				colorsStr += "'" + lineColor + "'";
			}

			optionsStr += NEW_LINE + "\t\tcolors: [" + colorsStr + "]";
		}

		// (6) curveType
		if (hasCurveType) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}

			optionsStr += NEW_LINE + "\t\tcurveType: '" + curveType + "'";
		}

		// (7) seriesType
		if (hasSeriesType) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}

			optionsStr += NEW_LINE + "\t\tseriesType: '" + seriesType + "'";
		}

		// (8) series (series number is 0-based, which doesn't include the first column, which is for X axis)
		if (hasSeries) {
			String seriesStr = "";

			for (int seriesNumber : seriesNumbers) {
				Map<String, Object> attributes = options.getSeriesAttributes(seriesNumber);
				if (attributes == null || attributes.isEmpty()) {
					continue;
				}

				String attrsStr = "";
				Iterator<String> attrItor = attributes.keySet().iterator();
				while (attrItor.hasNext()) {
					String attrName = attrItor.next();
					Object attrValue = attributes.get(attrName);
					if (attrValue != null) {
						if (!attrsStr.isEmpty()) {
							attrsStr += ", ";
						}
						attrsStr += attrName + ": '" + attrValue + "'";
					}
				}

				if (!attrsStr.isEmpty()) {
					if (!seriesStr.isEmpty()) {
						seriesStr += ",";
					}
					seriesStr += seriesNumber + ": {" + attrsStr + "}";
				}
			}

			if (!seriesStr.isEmpty()) {
				if (!optionsStr.isEmpty()) {
					optionsStr += ", ";
				}
				optionsStr += NEW_LINE + "\t\tseries: {" + seriesStr + "}";
			}
		}

		if (options.isExplorerEnabled()) {
			if (!optionsStr.isEmpty()) {
				optionsStr += ", ";
			}
			// Use hard-coded values for now. All the statistics history data are time-based.
			String explorerStr = NEW_LINE + "\t\t\tactions: ['dragToZoom', 'rightClickToReset'], axis: 'horizontal', keepInBounds: true, maxZoomIn: 300";
			optionsStr += NEW_LINE + "\t\texplorer: {" + explorerStr + "}";
		}

		output += NEW_LINE + "  var options = {" + optionsStr + NEW_LINE + "\t};" + NEW_LINE + NEW_LINE;

		// 5. Create and draw chart
		if (ReportConstants.CHART_TYPE__PIE.equals(this.chartType)) {
			output += "  var chart = new google.visualization.PieChart(document.getElementById('" + divId + "'));" + NEW_LINE;

		} else if (ReportConstants.CHART_TYPE__LINE.equals(this.chartType)) {
			output += "  var chart = new google.visualization.LineChart(document.getElementById('" + divId + "'));" + NEW_LINE;
			// output += " var chart = new google.charts.Line(document.getElementById('" + divId + "'));" + NEW_LINE;

		} else if (ReportConstants.CHART_TYPE__COLUMN.equals(this.chartType)) {
			output += "  var chart = new google.visualization.ColumnChart(document.getElementById('" + divId + "'));" + NEW_LINE;

		} else if (ReportConstants.CHART_TYPE__COMBO.equals(this.chartType)) {
			output += "  var chart = new google.visualization.ComboChart(document.getElementById('" + divId + "'));" + NEW_LINE;

		} else if (ReportConstants.CHART_TYPE__BAR.equals(this.chartType)) {
			output += "  var chart = new google.visualization.BarChart(document.getElementById('" + divId + "'));" + NEW_LINE;

		} else {
			output += "  var chart = new google.visualization.LineChart(document.getElementById('" + divId + "'));" + NEW_LINE;
		}

		output += "  chart.draw(data, options);" + NEW_LINE;

		// End of function
		output += "}" + NEW_LINE;
		output += "</script>" + NEW_LINE;
		output += "<div id=\"" + divId + "\"></div>" + NEW_LINE;
		return output;
	}
}
