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
package org.tauceti.tyche.analysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.util.Comparators;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class NumberReports {

	protected List<Draw> draws;
	protected int fromDrawIndex; // inclusive
	protected int toDrawIndex; // inclusive
	protected Map<Integer, Integer> numberToCountMap;

	public NumberReports() {
	}

	public NumberReports(List<Draw> draws, int fromDrawIndex, int toDrawIndex) {
		this.draws = draws;
		this.fromDrawIndex = fromDrawIndex;
		this.toDrawIndex = toDrawIndex;
	}

	public List<Draw> getDraws() {
		return this.draws;
	}

	public int getFromDrawIndex() {
		return this.fromDrawIndex;
	}

	public int getToDrawIndex() {
		return this.toDrawIndex;
	}

	public synchronized void calculate(Map<Integer, Integer> prevNumberToCountMap, Draw drawToRemove, Draw drawToAdd) {
		Map<Integer, Integer> numberToCountMap = new TreeMap<Integer, Integer>();

		numberToCountMap.putAll(prevNumberToCountMap);

		decreaseCount(numberToCountMap, drawToRemove.getNum1());
		decreaseCount(numberToCountMap, drawToRemove.getNum2());
		decreaseCount(numberToCountMap, drawToRemove.getNum3());
		decreaseCount(numberToCountMap, drawToRemove.getNum4());
		decreaseCount(numberToCountMap, drawToRemove.getNum5());

		increaseCount(numberToCountMap, drawToAdd.getNum1());
		increaseCount(numberToCountMap, drawToAdd.getNum2());
		increaseCount(numberToCountMap, drawToAdd.getNum3());
		increaseCount(numberToCountMap, drawToAdd.getNum4());
		increaseCount(numberToCountMap, drawToAdd.getNum5());

		this.numberToCountMap = numberToCountMap;
	}

	public synchronized void calculate() {
		Map<Integer, Integer> numberToCountMap = new TreeMap<Integer, Integer>();

		for (int i = this.fromDrawIndex; i <= this.toDrawIndex; i++) {
			Draw draw = this.draws.get(i);
			increaseCount(numberToCountMap, draw.getNum1());
			increaseCount(numberToCountMap, draw.getNum2());
			increaseCount(numberToCountMap, draw.getNum3());
			increaseCount(numberToCountMap, draw.getNum4());
			increaseCount(numberToCountMap, draw.getNum5());
		}

		this.numberToCountMap = numberToCountMap;
	}

	private void increaseCount(Map<Integer, Integer> numberToCountMap, Integer number) {
		if (number == null) {
			return;
		}
		Integer count = numberToCountMap.get(number);
		if (count == null) {
			count = 1;
		} else {
			count = Integer.valueOf(count.intValue() + 1);
		}
		numberToCountMap.put(number, count);
	}

	private void decreaseCount(Map<Integer, Integer> numberToCountMap, Integer number) {
		if (number == null) {
			return;
		}
		Integer count = numberToCountMap.get(number);
		if (count != null) {
			if (count.intValue() == 1) {
				numberToCountMap.remove(number);
			} else {
				numberToCountMap.put(number, Integer.valueOf(count.intValue() - 1));
			}
		}
	}

	public synchronized Map<Integer, Integer> getNumberToCountMap() {
		if (this.numberToCountMap == null) {
			this.numberToCountMap = new TreeMap<Integer, Integer>();
		}
		return this.numberToCountMap;
	}

	/**
	 * 
	 * @param numberToCountMap
	 * @param firstNNums
	 * @return
	 */
	public static List<NumberReport> convertToNumberReports(Map<Integer, Integer> numberToCountMap, int firstNNums) {
		List<NumberReport> numberReports = new ArrayList<NumberReport>();

		for (Iterator<Integer> numberItor = numberToCountMap.keySet().iterator(); numberItor.hasNext();) {
			Integer number = numberItor.next();
			Integer count = numberToCountMap.get(number);

			NumberReport numReport = new NumberReport(number);
			numReport.setCount(count);
			numberReports.add(numReport);
		}

		Collections.sort(numberReports, Comparators.NumberReportCountComparator.DESC);

		if (firstNNums <= 0) {
			// firstN is not specified --- return all reports
			return numberReports;

		} else {
			// firstN is specified --- return first N reports
			if (numberReports.size() <= firstNNums) {
				return numberReports;
			} else {
				return numberReports.subList(0, firstNNums);
			}
		}
	}

	/**
	 * 
	 * @param numberReports
	 * @param withCount
	 * @return
	 */
	public static String toNumberReportsString(List<NumberReport> numberReports, boolean withCount) {
		String numbersStr = "";
		if (withCount) {
			// with numbers count
			int i = 0;
			for (NumberReport numberReport : numberReports) {
				if (i > 0) {
					numbersStr += ", ";
				}
				numbersStr += numberReport.getNumber() + " (" + numberReport.getCount() + ")";
				i++;
			}

		} else {
			// without numbers count
			int i = 0;
			for (NumberReport numberReport : numberReports) {
				if (i > 0) {
					numbersStr += ", ";
				}
				numbersStr += numberReport.getNumber();
				i++;
			}
		}
		return numbersStr;
	}
}
