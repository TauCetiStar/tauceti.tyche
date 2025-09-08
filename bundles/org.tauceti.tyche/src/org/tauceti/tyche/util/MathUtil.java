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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.tauceti.tyche.model.Draw;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class MathUtil {

	public static class SeriesComparator implements Comparator<Series> {
		@Override
		public int compare(Series s1, Series s2) {
			return Integer.compare(s2.sum, s1.sum);
		}
	}

	public static class Series {
		public List<Draw> draws;
		public int startIndex;
		public int endIndex;
		public int sum;

		public Series() {
		}

		public Series(List<Draw> draws, int startIndex, int endIndex) {
			this.draws = draws;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		public Series(List<Draw> draws, int startIndex, int endIndex, int sum) {
			this.draws = draws;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.sum = sum;
		}
	}

	/**
	 * 
	 * @param draws
	 * @param seriesSize e.g. using 5 as the number of most recent draws
	 * @param numIndex   e.g. 0, 1, 2, 3, 4
	 * @param topX
	 * @return
	 */
	public static List<Series> getTopSimilarSeries(List<Draw> draws, int seriesSize, int numIndex, int topX) {
		int size = draws.size();
		int subDraws2StartIndex = size - seriesSize;

		// bigger list of draws
		List<Draw> subDraws1 = draws.subList(0, subDraws2StartIndex);
		// most recent X number of draws
		List<Draw> subDraws2 = draws.subList(subDraws2StartIndex, size);

		List<Series> seriesList = new ArrayList<Series>();

		int startIndex = 0;
		int endIndex = startIndex + seriesSize - 1;
		while (startIndex >= 0 && endIndex < subDraws1.size()) {
			int sum = 0;
			int j = 0;
			for (int i = startIndex; i <= endIndex; i++) {
				Draw drawA = subDraws1.get(i);
				Draw drawB = subDraws2.get(j);

				int valueA = subDraws1.get(i).getNum(numIndex);
				int valueB = subDraws2.get(j).getNum(numIndex);
				sum += (valueA * valueB);
				j++;
			}

			Series series = new Series(draws, startIndex, endIndex, sum);
			seriesList.add(series);

			startIndex++;
			endIndex++;
		}

		Collections.sort(seriesList, new SeriesComparator());
		List<Series> topX_seriesList = seriesList.subList(seriesList.size() - topX, seriesList.size());
		return topX_seriesList;
	}

	public static interface ValueRunner {
		int getValue(Draw drawA, Draw drawB);
	}

	/**
	 * 
	 * @param draws
	 * @param seriesSize e.g. using 5 as the number of most recent draws
	 * @param runner
	 * @param topX
	 * @return
	 */
	public static List<Series> getTopSimilarSeries(List<Draw> draws, int seriesSize, ValueRunner runner, int topX) {
		int size = draws.size();
		int subDraws2StartIndex = size - seriesSize;

		// bigger list of draws
		List<Draw> subDraws1 = draws.subList(0, subDraws2StartIndex);
		// most recent X number of draws
		List<Draw> subDraws2 = draws.subList(subDraws2StartIndex, size);

		List<Series> seriesList = new ArrayList<Series>();

		int startIndex = 0;
		int endIndex = startIndex + seriesSize - 1;
		while (startIndex >= 0 && endIndex < subDraws1.size()) {
			int sum = 0;
			int j = 0;
			for (int i = startIndex; i <= endIndex; i++) {
				Draw drawA = subDraws1.get(i);
				Draw drawB = subDraws2.get(j);
				int value = runner.getValue(drawA, drawB);
				sum += value;
				j++;
			}

			Series series = new Series(draws, startIndex, endIndex, sum);
			seriesList.add(series);

			startIndex++;
			endIndex++;
		}

		Collections.sort(seriesList, new SeriesComparator());
		List<Series> topX_seriesList = seriesList.subList(seriesList.size() - topX, seriesList.size());
		return topX_seriesList;
	}
}
