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

import java.util.List;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public abstract class AbstractChartGenerator implements IChartGenerator {

	protected String id;
	protected String name;
	protected String chartType = ReportConstants.CHART_TYPE__LINE;
	protected String[][] columns;
	protected Object[][] rowsArray;
	protected List<Object[]> rowsList;
	protected Options options;

	public AbstractChartGenerator() {
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getChartType() {
		return this.chartType;
	}

	@Override
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}

	@Override
	public String[][] getColumns() {
		return this.columns;
	}

	@Override
	public void setColumns(String[][] columns) {
		this.columns = columns;
	}

	@Override
	public Object[][] getRowsArray() {
		return this.rowsArray;
	}

	@Override
	public void setRowsArray(Object[][] rows) {
		this.rowsArray = rows;
	}

	@Override
	public List<Object[]> getRows() {
		return this.rowsList;
	}

	@Override
	public void setRows(List<Object[]> rows) {
		this.rowsList = rows;
	}

	@Override
	public synchronized Options getOptions() {
		if (this.options == null) {
			this.options = new OptionsImpl();
		}
		return this.options;
	}
}
