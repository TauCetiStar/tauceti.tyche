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
package org.tauceti.tyche.model.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.tauceti.tyche.model.AbstractDrawReader;
import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFactory;
import org.tauceti.tyche.model.DrawFilter;
import org.tauceti.tyche.model.DrawReader;
import org.tauceti.tyche.util.Comparators;
import org.tauceti.tyche.util.DateUtil;
import org.tauceti.tyche.util.IOUtils;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class PowerballReaderImpl extends AbstractDrawReader implements DrawReader {

	public PowerballReaderImpl() {
	}

	@Override
	public List<Draw> read(InputStream input) throws IOException {
		return read(input, null);
	}

	@Override
	public List<Draw> read(InputStream input, DrawFilter filter) throws IOException {
		List<Draw> draws = new ArrayList<Draw>();

		List<String> lines = IOUtils.readLines(input, "UTF-8");
		if (lines != null) {
			int index = 0;
			for (String line : lines) {
				if (line.contains("Draw #") || line.contains("Draw Date") || line.contains("Number_") || line.startsWith("//")) {
					continue;
				}

				Draw draw = convertToDraw(index + 1, line);
				if (draw == null) {
					continue;
				}

				if ((filter == null) || (filter.accept(draw))) {
					draws.add(draw);
					index++;
				}
			}
		}

		if (draws.size() > 1) {
			Collections.sort(draws, Comparators.DrawComparator.ASC);
		}

		// set 1 based index to draws.
		// first draw's index is 1
		// latest draw's index is the size of all draws
		for (int i = 0; i < draws.size(); i++) {
			Draw draw = draws.get(i);
			draw.setIndex(i + 1);
		}
		return draws;
	}

	protected Draw convertToDraw(int drawId, String line) {
		if (line == null || line.trim().isEmpty() || line.startsWith("#")) {
			return null;
		}

		try {
			int index1 = line.indexOf(",");
			int index2 = line.lastIndexOf(",");
			if (index1 > 0 && index2 > 0) {
				String segment1 = line.substring(0, index1).trim();
				String segment2 = line.substring(index1 + 1, index2).trim();
				// String segment3 = line.substring(index2 + 1);

				Date date = DateUtil.toDate(segment1, DateUtil.MONTH_DAY_YEAR_FORMAT1);

				String[] segments = segment2.split("\\s+");
				if (segments.length == 6) {
					String num1Str = segments[0];
					String num2Str = segments[1];
					String num3Str = segments[2];
					String num4Str = segments[3];
					String num5Str = segments[4];
					String pbStr = segments[5];

					int num1 = Integer.parseInt(num1Str);
					int num2 = Integer.parseInt(num2Str);
					int num3 = Integer.parseInt(num3Str);
					int num4 = Integer.parseInt(num4Str);
					int num5 = Integer.parseInt(num5Str);
					int pb = Integer.parseInt(pbStr);

					List<Integer> nums = new ArrayList<Integer>();
					nums.add(num1);
					nums.add(num2);
					nums.add(num3);
					nums.add(num4);
					nums.add(num5);
					Collections.sort(nums);

					int newNum1 = nums.get(0);
					int newNum2 = nums.get(1);
					int newNum3 = nums.get(2);
					int newNum4 = nums.get(3);
					int newNum5 = nums.get(4);

					Draw draw = DrawFactory.getInstance().createDraw(date, newNum1, newNum2, newNum3, newNum4, newNum5, pb);
					draw.setDrawId(drawId);
					return draw;
				}
			}
		} catch (Exception e) {
			System.err.println(getClass().getSimpleName() + ".convertToDraw(int index, String line) drawId = " + drawId + " invalid line: " + line);
		}
		return null;
	}
}

// /**
// *
// * @param file
// * @return
// * @throws IOException
// */
// public static List<Draw> read(File file) throws IOException {
// List<Draw> draws = null;
// FileInputStream fis = null;
// try {
// fis = new FileInputStream(file);
// DrawReaderV2 reader = new DrawReaderV2();
// draws = reader.read(fis);
// } finally {
// IOUtil.closeQuietly(fis, true);
// }
// if (draws == null) {
// draws = Collections.emptyList();
// }
// return draws;
// }
