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

import java.util.ArrayList;
import java.util.List;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFilter;
import org.tauceti.tyche.model.impl.DrawFilterByFolderImpl;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class PowerballFilterManager {

	public static List<DrawFilter> getDrawFilters() {
		List<DrawFilter> filters = new ArrayList<DrawFilter>();

		List<DrawFilter> filters1__same_3rd = getFilters_GroupBy_same_3rd();
		List<DrawFilter> filters2__same_PB = getFilters_GroupBy_same_PB();
		List<DrawFilter> filters3__same_double_digits = getFilters_GroupBy_same_double_digits();
		List<DrawFilter> filters4__3_in_a_row = getFilters_GroupBy_3_in_a_Row();
		// List<DrawFilter> filters5__same_5FirstDigit = getFilters_GroupBy_same_5FirstDigits();

		filters.addAll(filters1__same_3rd);
		filters.addAll(filters2__same_PB);
		filters.addAll(filters3__same_double_digits);
		filters.addAll(filters4__3_in_a_row);
		// filters.addAll(filters5__same_5FirstDigit);

		return filters;
	}

	/**
	 * 1. Group by Num 3 with same value
	 * 
	 * @return
	 */
	public static List<DrawFilter> getFilters_GroupBy_same_3rd() {
		List<DrawFilter> filters = new ArrayList<DrawFilter>();

		for (int num3 = 20; num3 < 60; num3++) {
			final int _num3 = num3;
			final String reportId = "Powerball_num3_" + num3;
			DrawFilter filter = new DrawFilterByFolderImpl() {
				@Override
				public boolean accept(Draw draw) {
					if (draw != null) {
						if (_num3 == draw.getNum3()) {
							return true;
						}
					}
					return false;
				}

				@Override
				public String getReportId() {
					return reportId;
				}

				@Override
				public String getTxtFolderName() {
					return PowerballConstants.REPORTS_FOLDER_PATH + "/num3/txt/";
				}

				@Override
				public String getHtmlFolderName() {
					return PowerballConstants.REPORTS_FOLDER_PATH + "/num3/html";
				}
			};
			filters.add(filter);
		}

		return filters;
	}

	/**
	 * 2. Group by PB with same value
	 * 
	 * @return
	 */
	public static List<DrawFilter> getFilters_GroupBy_same_PB() {
		List<DrawFilter> filters = new ArrayList<DrawFilter>();

		for (int pb = 1; pb < 27; pb++) {
			final int _pb = pb;
			final String reportId = "Powerball_pb_" + pb;
			DrawFilter filter = new DrawFilterByFolderImpl() {
				@Override
				public boolean accept(Draw draw) {
					if (draw != null) {
						if (_pb == draw.getPB()) {
							return true;
						}
					}
					return false;
				}

				@Override
				public String getReportId() {
					return reportId;
				}

				@Override
				public String getTxtFolderName() {
					return PowerballConstants.REPORTS_FOLDER_PATH + "/pb/txt/";
				}

				@Override
				public String getHtmlFolderName() {
					return PowerballConstants.REPORTS_FOLDER_PATH + "/pb/html";
				}
			};
			filters.add(filter);
		}

		return filters;
	}

	/**
	 * 3. Group by same double digits
	 * 
	 * @return
	 */
	public static List<DrawFilter> getFilters_GroupBy_same_double_digits() {
		List<DrawFilter> filters = new ArrayList<DrawFilter>();

		// 3. Group by same double digits
		for (int num = 1; num < 70; num++) {
			if (num == 11 || num == 22 || num == 33 || num == 44 || num == 55) {
				final int _num = num;
				final String reportId = "Powerball_double_digits_" + num;
				DrawFilter filter = new DrawFilterByFolderImpl() {
					@Override
					public boolean accept(Draw draw) {
						if (draw != null) {
							if (draw.containsNum(_num)) {
								return true;
							}
						}
						return false;
					}

					@Override
					public String getReportId() {
						return reportId;
					}

					@Override
					public String getTxtFolderName() {
						return PowerballConstants.REPORTS_FOLDER_PATH + "/double_digits/txt/";
					}

					@Override
					public String getHtmlFolderName() {
						return PowerballConstants.REPORTS_FOLDER_PATH + "/double_digits/html";
					}
				};
				filters.add(filter);
			}
		}

		return filters;
	}

	/**
	 * 
	 * @param draw
	 * @param fromValue
	 * @param toValue
	 * @return
	 */
	public static int getNumberCountInRange(Draw draw, int fromValue, int toValue) {
		int count = 0;
		if (fromValue <= draw.getNum1() && draw.getNum1() <= toValue) {
			count++;
		}
		if (fromValue <= draw.getNum2() && draw.getNum2() <= toValue) {
			count++;
		}
		if (fromValue <= draw.getNum3() && draw.getNum3() <= toValue) {
			count++;
		}
		if (fromValue <= draw.getNum4() && draw.getNum4() <= toValue) {
			count++;
		}
		if (fromValue <= draw.getNum5() && draw.getNum5() <= toValue) {
			count++;
		}
		return count;
	}

	/**
	 * 4. Group by 3 numbers in a same row
	 * 
	 * @return
	 */
	public static List<DrawFilter> getFilters_GroupBy_3_in_a_Row() {
		List<DrawFilter> filters = new ArrayList<DrawFilter>();

		List<int[]> ranges = new ArrayList<int[]>();
		ranges.add(new int[] { 1, 10 });
		ranges.add(new int[] { 11, 20 });
		ranges.add(new int[] { 21, 30 });
		ranges.add(new int[] { 31, 40 });
		ranges.add(new int[] { 41, 50 });
		ranges.add(new int[] { 51, 60 });
		ranges.add(new int[] { 61, 69 });

		for (int i = 0; i < ranges.size(); i++) {
			int[] range = ranges.get(i);
			int fromValue = range[0];
			int toValue = range[1];
			final String reportId = "3_in_a_row_from_" + fromValue + "_to_" + toValue;

			// 01-10 count is 3
			DrawFilter filter = new DrawFilterByFolderImpl(DrawFilter.ID__3_IN_A_ROW) {
				@Override
				public boolean accept(Draw draw) {
					int count = getNumberCountInRange(draw, fromValue, toValue);
					if (count == 3) {
						return true;
					}
					return false;
				}

				@Override
				public String getReportId() {
					return reportId;
				}

				@Override
				public String getTxtFolderName() {
					return PowerballConstants.REPORTS_FOLDER_PATH + "/3_in_a_row/txt/";
				}

				@Override
				public String getHtmlFolderName() {
					return PowerballConstants.REPORTS_FOLDER_PATH + "/3_in_a_row/html";
				}
			};
			filters.add(filter);
		}
		return filters;
	}

	/**
	 * 5. Group by same 5-first-digits
	 * 
	 * @return
	 */
	public static List<DrawFilter> getFilters_GroupBy_same_5FirstDigits() {
		List<DrawFilter> filters = new ArrayList<DrawFilter>();

		List<int[]> same_5_first_digits = new ArrayList<int[]>();
		same_5_first_digits.add(new int[] { 0, 1, 2, 3, 4 });
		same_5_first_digits.add(new int[] { 0, 1, 2, 3, 5 });
		same_5_first_digits.add(new int[] { 0, 1, 2, 3, 6 });

		same_5_first_digits.add(new int[] { 0, 0, 1, 2, 3 });
		same_5_first_digits.add(new int[] { 0, 0, 1, 2, 4 });
		same_5_first_digits.add(new int[] { 0, 0, 1, 2, 5 });
		same_5_first_digits.add(new int[] { 0, 0, 1, 2, 6 });

		same_5_first_digits.add(new int[] { 1, 2, 3, 4, 5 });
		same_5_first_digits.add(new int[] { 1, 1, 2, 3, 4 });

		same_5_first_digits.add(new int[] { 1, 2, 2, 3, 4 });

		same_5_first_digits.add(new int[] { 2, 3, 4, 5, 6 });
		same_5_first_digits.add(new int[] { 2, 2, 3, 4, 5 });

		same_5_first_digits.add(new int[] { 3, 3, 4, 5, 6 });

		return filters;
	}
}
