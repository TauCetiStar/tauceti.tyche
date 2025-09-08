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
import java.time.LocalDate;
import java.time.ZoneId;
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
import org.tauceti.tyche.util.IOUtils;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class MegaMillionReaderImpl extends AbstractDrawReader implements DrawReader {

	public MegaMillionReaderImpl() {
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
				if (line.contains("Draw #") || line.contains("Draw Date") || line.contains("Number_")) {
					continue;
				}

				Draw draw = convertToDraw(index, line);
				if (draw != null) {
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

	protected Draw convertToDraw(int index, String line) {
		if (line == null || line.trim().isEmpty() || line.startsWith("#")) {
			return null;
		}

		try {
			int drawId = index + 1;

			String[] segments = line.split(",");
			String monthStr = segments[1];
			String dayStr = segments[2];
			String yearStr = segments[3];
			int year = Integer.parseInt(yearStr);
			int month = Integer.parseInt(monthStr);
			int day = Integer.parseInt(dayStr);

			ZoneId defaultZoneId = ZoneId.systemDefault();
			LocalDate localDate = LocalDate.of(year, month, day);
			Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

			String num1Str = segments[4];
			String num2Str = segments[5];
			String num3Str = segments[6];
			String num4Str = segments[7];
			String num5Str = segments[8];
			String pbStr = segments[9];

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

		} catch (Exception e) {
			System.err.println(getClass().getSimpleName() + ".convertToDraw(int index, String line) index = " + index + " invalid line: " + line);
		}
		return null;
	}
}
