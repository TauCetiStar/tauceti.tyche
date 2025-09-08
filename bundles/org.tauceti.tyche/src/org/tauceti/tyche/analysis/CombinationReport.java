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
import java.util.Date;
import java.util.List;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.util.Comparators;
import org.tauceti.tyche.util.DateUtil;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class CombinationReport {

	protected String combinationKey;
	protected int[] combination;
	protected List<Draw> draws = new ArrayList<Draw>();
	protected List<Integer> numberOfDrawsBetween = new ArrayList<Integer>();

	/**
	 * 
	 * @param combinationKey
	 * @param combination
	 */
	public CombinationReport(String combinationKey, int[] combination) {
		this.combinationKey = combinationKey;
		this.combination = combination;
	}

	public String getCombinationKey() {
		return this.combinationKey;
	}

	public void setCombinationKey(String combinationKey) {
		this.combinationKey = combinationKey;
	}

	public int[] getCombination() {
		return this.combination;
	}

	public void setCombination(int[] combination) {
		this.combination = combination;
	}

	public List<Draw> getDraws() {
		return this.draws;
	}

	public void setDraws(List<Draw> draws) {
		this.draws = draws;

		if (!this.draws.isEmpty()) {
			Collections.sort(this.draws, Comparators.DrawComparator.ASC);
		}
		updateNumberOfDrawsBetween();
	}

	public boolean addDraw(Draw draw) {
		boolean succeed = false;
		if (draw != null && !this.draws.contains(draw)) {
			succeed = this.draws.add(draw);
		}
		if (succeed) {
			if (!this.draws.isEmpty()) {
				Collections.sort(this.draws, Comparators.DrawComparator.ASC);
			}
			updateNumberOfDrawsBetween();
		}
		return succeed;
	}

	public boolean removeDraws(List<Draw> draws) {
		boolean succeed = this.draws.removeAll(draws);
		if (succeed) {
			if (!this.draws.isEmpty()) {
				Collections.sort(this.draws, Comparators.DrawComparator.ASC);
			}
			updateNumberOfDrawsBetween();
		}
		return succeed;
	}

	public int getCount() {
		return this.draws.size();
	}

	public void updateNumberOfDrawsBetween() {
		this.numberOfDrawsBetween.clear();

		boolean printNumOfDrawsBetween = true;
		if (printNumOfDrawsBetween) {
			for (int i = 1; i < this.draws.size(); i++) {
				Draw prevDraw = this.draws.get(i - 1);
				Draw currDraw = this.draws.get(i);

				int prevIndex = prevDraw.getIndex();
				int currIndex = currDraw.getIndex();

				int numOfDrawsBetween = currIndex - prevIndex;
				this.numberOfDrawsBetween.add(numOfDrawsBetween);
			}
		}
	}

	public List<Integer> getNumberOfDrawsBetween() {
		return this.numberOfDrawsBetween;
	}

	@Override
	public String toString() {
		String str = "";
		str += "Number combination (" + this.combinationKey + ")";
		str += ", repeated " + getCount() + " times";

		String dateStr = "";
		for (int i = 0; i < this.draws.size(); i++) {
			Draw draw = this.draws.get(i);
			if (i > 0) {
				dateStr += ", ";
			}
			dateStr += ("#" + draw.getIndex() + " " + draw.getDateString());
		}
		str += ", dates (" + dateStr + ")";

		boolean printDaysBetween = false;
		if (printDaysBetween) {
			String daysBetweenStr = "";
			for (int i = 1; i < this.draws.size(); i++) {
				Draw prevDraw = this.draws.get(i - 1);
				Draw currDraw = this.draws.get(i);

				Date prevDate = prevDraw.getDate();
				Date currDate = currDraw.getDate();

				long daysBetween = DateUtil.getDaysBetween(prevDate, currDate);

				if (i > 1) {
					daysBetweenStr += " $ ";
				}
				daysBetweenStr += (daysBetween + " days");
			}
			str += ", interval (days: " + daysBetweenStr + ")";
		}

		boolean printNumOfDrawsBetween = true;
		if (printNumOfDrawsBetween) {
			String numOfDrawsBetweenStr = "";
			for (int i = 0; i < this.numberOfDrawsBetween.size(); i++) {
				int numOfDrawsBetween = numberOfDrawsBetween.get(i);
				if (i > 0) {
					numOfDrawsBetweenStr += " $ ";
				}
				numOfDrawsBetweenStr += (numOfDrawsBetween + " draws");
			}
			str += ", interval (draws: " + numOfDrawsBetweenStr + ")";
		}
		return str;
	}
}
