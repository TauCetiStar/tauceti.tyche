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
package org.tauceti.tyche.chart;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public interface ReportConstants {

	String DRAWS_TYPE__POWERBALL = "Powerball";
	String DRAWS_TYPE__MEGA_MILLION = "MegaMillion";

	String ARG__DRAWS_TYPE = "draws.type";
	String ARG__DRAWS_NUMBERS = "draws.numbers";

	String ARG__DRAWS_PREV_PREDICTIONS = "draws.prev.predictions";

	String ARG__DRAWS_PREDICTIONS = "draws.predictions";
	String ARG__DRAWS_PREDICTIONS_0 = "draws.predictions0";
	String ARG__DRAWS_PREDICTIONS_1 = "draws.predictions1";
	String ARG__DRAWS_PREDICTIONS_2 = "draws.predictions2";
	String ARG__DRAWS_PREDICTIONS_3 = "draws.predictions3";
	String ARG__DRAWS_PREDICTIONS_4 = "draws.predictions4";
	String ARG__DRAWS_PREDICTIONS_5 = "draws.predictions5";
	String ARG__DRAWS_PREDICTIONS_6 = "draws.predictions6";
	String ARG__DRAWS_PREDICTIONS_7 = "draws.predictions7";
	String ARG__DRAWS_PREDICTIONS_8 = "draws.predictions8";
	String ARG__DRAWS_PREDICTIONS_9 = "draws.predictions9";

	String ARG__DRAW_OPTIONS = "draws.options";
	int DRAW_OPTIONS__CHART = 1;
	int DRAW_OPTIONS__CARD = 2;
	int DRAW_OPTIONS__ALL = DRAW_OPTIONS__CHART | DRAW_OPTIONS__CARD;

	String ARG__REPORT_FOLDRE = "report.folder";
	String ARG__REPORT_ID = "report.id";
	String ARG__REPORT_DATE = "report.date";
	String ARG__NUMBER6_LABEL = "number6.label";
	String ARG__DRAW_FILTER = "draw.filter";

	String CHART__GOOGLE_CHART = "GoogleChart";

	String CHART_TYPE__COMBO = "combo";
	String CHART_TYPE__PIE = "pie";
	String CHART_TYPE__LINE = "line";
	String CHART_TYPE__COLUMN = "column";
	String CHART_TYPE__BAR = "bar";

	String COLUMN_TYPE__STRING = "string";
	String COLUMN_TYPE__NUMBER = "number";
	String COLUMN_TYPE__DATE_TIME = "datetime";

	String CHART_LINE_COLOR__RED = "#dc3912";
	String RED = "#ff0000";
	String CHART_LINE_COLOR__ORANGE = "#ff9900";
	String CHART_LINE_COLOR__YELLOW = "#ffff00";
	String CHART_LINE_COLOR__GREEN = "#109618";
	String CHART_LINE_COLOR__GREEN2 = "#00ff00";
	String CHART_LINE_COLOR__BLUE = "#3366cc";
	String BLUE = "#0000ff";
	String CHART_LINE_COLOR__GREEN_BLUE = "#0099c6";
	String CHART_LINE_COLOR__MAYA_BLUE = "#73c2fb";
	String CHART_LINE_COLOR__VIOLET = "#990099";

	String CHART_LINE_COLOR__GREY0 = "#333333";
	String CHART_LINE_COLOR__GREY1 = "#d3d3d3";
	String CHART_LINE_COLOR__GREY2 = "#bebebe";
	String CHART_LINE_COLOR__GREY3 = "#a9a9a9";
	String CHART_LINE_COLOR__GREY4 = "#949494";
	String CHART_LINE_COLOR__GREY5 = "#7f7f7f";
	String CHART_LINE_COLOR__GREY6 = "#efefef";
	String CHART_LINE_COLOR__GREY7 = "#fefefe";

	// https://en.wikipedia.org/wiki/Web_colors
	String CORAL = "FF7F50";

	// YELLOW
	String GOLD = "FFD700";

	String ORANGE = "FFA500";

	// GREEN
	String MEDIUM_AQUAMARINE = "66CDAA";
	String HONEYDEW = "F0FFF0";

	// BLUE
	String DODGER_BLUE = "1E90FF";
	String LIGHT_SKY_BLUE = "87CEFA";
	String SKY_BLUE = "87CEEB";
	String CADET_BLUE = "5F9EA0";

	// Cyan
	String DARK_TURQUOISE = "00CED1";
	String PALE_TURQUOISE = "AFEEEE";
	String LIGHT_CYAN = "E0FFFF";

	// GRAY
	String SILVER = "C0C0C0";
	String SLATE_GRAY = "708090";
	String GAINSBORO = "DCDCDC";

	// Variables
	String CHARG_BG = CHART_LINE_COLOR__GREY6;
}
