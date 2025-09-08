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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawWriter;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class DrawWriterImpl implements DrawWriter {

	protected SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");
	protected boolean groupByYear = true;

	public DrawWriterImpl() {
	}

	public boolean isGroupByYear() {
		return this.groupByYear;
	}

	public void setGroupByYear(boolean groupByYear) {
		this.groupByYear = groupByYear;
	}

	@Override
	public void write(List<Draw> draws, OutputStream output) throws IOException {
		if (draws == null || output == null) {
			return;
		}
		String lineSeparator = System.getProperty("line.separator");

		OutputStreamWriter outputWriter = null;
		PrintWriter printWriter = null;
		try {
			outputWriter = new OutputStreamWriter(output, "UTF-8");
			printWriter = new PrintWriter(outputWriter);

			printWriter.write("Draw Date,    Winning Numbers, Powerball, First Digits" + lineSeparator);

			int prevYear = -1;
			for (Draw draw : draws) {
				Date date = draw.getDate();
				String dateStr = draw.getDateString();
				String numsStr = draw.getNumsString0(true);
				String str = dateStr + ",   " + numsStr;

				if (isGroupByYear()) {
					int currYear = Integer.valueOf(this.yearDateFormat.format(date));
					if (prevYear != -1 && prevYear != currYear) {
						printWriter.write(lineSeparator);
					}
					prevYear = currYear;
				}
				printWriter.write(str + lineSeparator);
			}
		} finally {
			printWriter.close();
			outputWriter.close();
		}
	}
}
