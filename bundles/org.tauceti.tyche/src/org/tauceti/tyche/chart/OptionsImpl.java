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
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class OptionsImpl implements Options {

	protected String title;
	protected int width;
	protected int height;
	protected String widthStr;
	protected String heightStr;
	protected String hAxisTitle;
	protected int hAxisShowTextEvery = 10;
	protected String vAxisTitle;
	protected String backgroundColor;
	protected String[] colors;
	protected String curveType;
	protected String seriesType;
	protected Map<Integer, Map<String, Object>> seriesAttributesMap = new TreeMap<Integer, Map<String, Object>>();
	protected boolean isExplorerEnabled;

	public OptionsImpl() {
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String getWidthStr() {
		return this.widthStr;
	}

	@Override
	public void setWidthStr(String widthStr) {
		this.widthStr = widthStr;
	}

	@Override
	public String getHeightStr() {
		return this.heightStr;
	}

	@Override
	public void setHeightStr(String heightStr) {
		this.heightStr = heightStr;
	}

	@Override
	public String getHAxisTitle() {
		return this.hAxisTitle;
	}

	@Override
	public void setHAxisTitle(String xTitle) {
		this.hAxisTitle = xTitle;
	}

	@Override
	public int getHAxisShowTextEvery() {
		return this.hAxisShowTextEvery;
	}

	@Override
	public void setHAxisShowTextEvery(int hAxisShowTextEvery) {
		this.hAxisShowTextEvery = hAxisShowTextEvery;
	}

	@Override
	public String getVAxisTitle() {
		return this.vAxisTitle;
	}

	@Override
	public void setVAxisTitle(String yTitle) {
		this.vAxisTitle = yTitle;
	}

	@Override
	public String getBackgroundColor() {
		return this.backgroundColor;
	}

	@Override
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@Override
	public String[] getColors() {
		return this.colors;
	}

	@Override
	public void setColors(String[] colors) {
		this.colors = colors;
	}

	@Override
	public String getCurveType() {
		return this.curveType;
	}

	@Override
	public void setCurveType(String curveType) {
		this.curveType = curveType;
	}

	@Override
	public String getSeriesType() {
		return this.seriesType;
	}

	@Override
	public void setSeriesType(String seriesType) {
		this.seriesType = seriesType;
	}

	@Override
	public synchronized int[] getSeriesIndexes() {
		Set<Integer> seriesIndexes = this.seriesAttributesMap.keySet();
		int[] indexes = new int[seriesIndexes.size()];
		int i = 0;
		for (Integer seriesIndex : seriesIndexes) {
			indexes[i++] = seriesIndex;
		}
		return indexes;
	}

	@Override
	public synchronized Map<String, Object> getSeriesAttributes(int seriesIndex) {
		Map<String, Object> attributes = this.seriesAttributesMap.get(seriesIndex);
		if (attributes == null) {
			attributes = new TreeMap<String, Object>();
			this.seriesAttributesMap.put(seriesIndex, attributes);
		}
		return attributes;
	}

	@Override
	public boolean isExplorerEnabled() {
		return this.isExplorerEnabled;
	}

	@Override
	public void setExplorerEnabled(boolean isExplorerenabled) {
		this.isExplorerEnabled = isExplorerenabled;
	}
}
