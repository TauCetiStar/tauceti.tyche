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

import java.util.Map;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public interface Options {

	String CURVE_TYPE__FUNCTION = "function";

	String getTitle();

	void setTitle(String title);

	int getWidth();

	void setWidth(int width);

	int getHeight();

	void setHeight(int height);

	String getWidthStr();

	void setWidthStr(String width);

	String getHeightStr();

	void setHeightStr(String height);

	void setHAxisTitle(String hAxisTitle);

	String getHAxisTitle();

	int getHAxisShowTextEvery();

	void setHAxisShowTextEvery(int hAxisShowTextEvery);

	void setVAxisTitle(String vAxisTitle);

	String getVAxisTitle();

	void setBackgroundColor(String backgroundColor);

	String getBackgroundColor();

	void setColors(String[] colors);

	String[] getColors();

	String getCurveType();

	void setCurveType(String curveType);

	void setSeriesType(String seriesType);

	String getSeriesType();

	int[] getSeriesIndexes();

	Map<String, Object> getSeriesAttributes(int seriesIndex);

	boolean isExplorerEnabled();

	void setExplorerEnabled(boolean isExplorerenabled);
}
