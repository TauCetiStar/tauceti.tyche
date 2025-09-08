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

import java.util.Date;

import org.tauceti.tyche.model.AreasCount;
import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawStat;
import org.tauceti.tyche.model.Trend;
import org.tauceti.tyche.util.DateUtil;
import org.tauceti.tyche.util.DrawHelper;
import org.tauceti.tyche.util.DrawUtil;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class DrawImpl implements Draw {

	protected Draw prevDraw;
	protected Draw nextDraw;

	// 1 based index
	protected int drawId;
	protected int index;
	protected Date date;
	protected int num1;
	protected int num2;
	protected int num3;
	protected int num4;
	protected int num5;
	protected int pb;

	protected DrawStat stat;
	protected boolean isDummy;

	protected int[] distancesValue = new int[] { 0, 0, 0, 0 };
	protected float[] distancesPercent = new float[] { 0, 0, 0, 0 };
	protected int[] distanceAreas = new int[] { 0, 0, 0, 0 };
	protected Trend[] trend = new Trend[] { Trend.NONE, Trend.NONE, Trend.NONE, Trend.NONE, Trend.NONE, Trend.NONE };
	protected AreasCount areasCount;
	protected int diameter;
	protected int area;

	public DrawImpl() {
	}

	/**
	 * 
	 * @param date
	 * @param nums
	 * @param pb
	 */
	public DrawImpl(Date date, int[] nums, int pb) {
		this.date = date;
		this.num1 = nums[0];
		this.num2 = nums[1];
		this.num3 = nums[2];
		this.num4 = nums[3];
		this.num5 = nums[4];
		this.pb = pb;
	}

	/**
	 * 
	 * @param date
	 * @param num1
	 * @param num2
	 * @param num3
	 * @param num4
	 * @param num5
	 * @param pb
	 */
	public DrawImpl(Date date, int num1, int num2, int num3, int num4, int num5, int pb) {
		this.date = date;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		this.num4 = num4;
		this.num5 = num5;
		this.pb = pb;
	}

	@Override
	public Draw getPrevDraw() {
		return this.prevDraw;
	}

	@Override
	public void setPrevDraw(Draw prevDraw) {
		this.prevDraw = prevDraw;
	}

	@Override
	public Draw getNextDraw() {
		return this.nextDraw;
	}

	@Override
	public void setNextDraw(Draw nextDraw) {
		this.nextDraw = nextDraw;
	}

	@Override
	public int getDrawId() {
		return this.drawId;
	}

	@Override
	public void setDrawId(int drawId) {
		this.drawId = drawId;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int getIndex() {
		return this.index;
	}

	@Override
	public Date getDate() {
		return this.date;
	}

	@Override
	public String getDateString() {
		String dateStr = this.date != null ? DateUtil.toString(this.date, DateUtil.MONTH_DAY_YEAR_FORMAT1) : "null";
		return dateStr;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int getNum(int index) {
		if (index == 0) {
			return getNum1();

		} else if (index == 1) {
			return getNum2();

		} else if (index == 2) {
			return getNum3();

		} else if (index == 3) {
			return getNum4();

		} else if (index == 4) {
			return getNum5();

		} else if (index == 5) {
			return getPB();
		}
		throw new IndexOutOfBoundsException("index must be [0, 5]");
	}

	@Override
	public String getNum(int index, boolean doubleDigits) {
		String str = "" + getNum(index);
		if (str.length() == 1 && doubleDigits) {
			str = "0" + str;
		}
		return str;
	}

	@Override
	public int getNum1() {
		return this.num1;
	}

	@Override
	public void setNum1(int num1) {
		this.num1 = num1;
	}

	@Override
	public int getNum2() {
		return this.num2;
	}

	@Override
	public void setNum2(int num2) {
		this.num2 = num2;
	}

	@Override
	public int getNum3() {
		return this.num3;
	}

	@Override
	public void setNum3(int num3) {
		this.num3 = num3;
	}

	@Override
	public int getNum4() {
		return this.num4;
	}

	@Override
	public void setNum4(int num4) {
		this.num4 = num4;
	}

	@Override
	public int getNum5() {
		return this.num5;
	}

	@Override
	public void setNum5(int num5) {
		this.num5 = num5;
	}

	@Override
	public int getPB() {
		return this.pb;
	}

	@Override
	public void setPB(int pb) {
		this.pb = pb;
	}

	@Override
	public boolean containsNum(int num) {
		if (this.num1 == num || this.num2 == num || this.num3 == num || this.num4 == num || this.num5 == num) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isPB(int pb) {
		if (this.pb == pb) {
			return true;
		}
		return false;
	}

	@Override
	public int[] getNumberArray(boolean includePb) {
		if (includePb) {
			return new int[] { this.num1, this.num2, this.num3, this.num4, this.num5, this.pb };
		} else {
			return new int[] { this.num1, this.num2, this.num3, this.num4, this.num5 };
		}
	}

	@Override
	public String getNumsString0(boolean includePb) {
		String str = "";
		str += ((this.num1 < 10) ? " " : "") + this.num1;
		str += " " + ((this.num2 < 10) ? " " : "") + this.num2;
		str += " " + ((this.num3 < 10) ? " " : "") + this.num3;
		str += " " + ((this.num4 < 10) ? " " : "") + this.num4;
		str += " " + ((this.num5 < 10) ? " " : "") + this.num5;
		if (includePb) {
			str += "   " + ((this.pb < 10) ? " " : "") + this.pb;
		}

		// first digits
		String firstDigits = DrawUtil.getFirstDigitsString(this, false);
		str += ",        " + firstDigits;

		return str;
	}

	@Override
	public String getNumsString(boolean includePb) {
		String str = "";
		str += addSpace(num1) + num1 + " ";
		str += addSpace(num2) + num2 + " ";
		str += addSpace(num3) + num3 + " ";
		str += addSpace(num4) + num4 + " ";
		str += addSpace(num5) + num5 + "";
		if (includePb) {
			str += " " + addSpace(pb) + pb;
		}
		return str;
	}

	protected String toString(Trend trend) {
		String str = trend.toString();
		if (str.length() == 2) {
			str = "  " + str;
		}
		return str;
	}

	protected String toString(float f) {
		String str = String.valueOf(f);
		if (f < 10) {
			str = " " + str;
		}
		return str;
	}

	private String addSpace(int num) {
		return num < 10 ? "0" : "";
		// return "";
	}

	private String addLetter(int num, int length, String str) {
		int currLength = 0;
		if (num < 10) {
			currLength = 1;
		} else if (num < 100) {
			currLength = 2;
		} else if (num < 1000) {
			currLength = 3;
		} else if (num < 10000) {
			currLength = 4;
		}
		int needLength = length - currLength;

		String spaces = "";
		if (needLength > 0) {
			for (int i = 0; i < needLength; i++) {
				spaces += str;
			}
		}
		return spaces;
	}

	@Override
	public synchronized DrawStat getStat() {
		if (this.stat == null) {
			this.stat = new DrawStat();
		}
		return this.stat;
	}

	@Override
	public synchronized void setStat(DrawStat stat) {
		this.stat = stat;
	}

	@Override
	public boolean isDummy() {
		return this.isDummy;
	}

	@Override
	public void setDummy(boolean isDummy) {
		this.isDummy = isDummy;
	}

	@Override
	public int[] getDistancesValue() {
		return this.distancesValue;
	}

	@Override
	public int getDistanceValue(int index) {
		return this.distancesValue[index];
	}

	@Override
	public void setDistancesValue(int[] distancesValue) {
		this.distancesValue = distancesValue;
	}

	@Override
	public float[] getDistancesPercent() {
		return this.distancesPercent;
	}

	@Override
	public float getDistancePercent(int index) {
		return this.distancesPercent[index];
	}

	@Override
	public void setDistancesPercent(float[] distancesPercent) {
		this.distancesPercent = distancesPercent;
	}

	@Override
	public int[] getDistanceAreas() {
		return this.distanceAreas;
	}

	@Override
	public int[] getDistanceAreas(int shift_v) {
		int area0 = this.distanceAreas[0] + shift_v * 0;
		int area1 = this.distanceAreas[1] + shift_v * 1;
		int area2 = this.distanceAreas[2] + shift_v * 2;
		int area3 = this.distanceAreas[3] + shift_v * 3;
		return new int[] { area0, area1, area2, area3 };
	}

	@Override
	public void setDistanceAreas(int[] areas) {
		this.distanceAreas = areas;
	}

	@Override
	public Trend[] getTrend() {
		return this.trend;
	}

	@Override
	public void setTrend(Trend[] trend) {
		this.trend = trend;
	}

	@Override
	public AreasCount getAreasCount() {
		return this.areasCount;
	}

	@Override
	public void setAreasCount(AreasCount areasCount) {
		this.areasCount = areasCount;
	}

	@Override
	public int getDiameter() {
		return this.diameter;
	}

	@Override
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	@Override
	public int getArea() {
		return this.area;
	}

	@Override
	public void setArea(int area) {
		this.area = area;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Draw other = (Draw) obj;
		if (this.date == null) {
			if (other.getDate() != null) {
				return false;
			}
		} else if (!date.equals(other.getDate())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String indexStr = addLetter(this.index, 4, " ") + this.index;
		String dateStr = getDateString();
		String numStr = getNumsString(false);
		String pbStr = addSpace(this.pb) + this.pb;
		String str = "Draw#" + indexStr + " (" + dateStr + ") " + numStr + " [" + pbStr + "]";

		str += " (" + toString(this.trend[0]) + " " + toString(this.trend[1]) + " " + toString(this.trend[2]) + " " + toString(this.trend[3]) + " " + toString(this.trend[4]) + ")";
		str += " (" + toString(this.distancesPercent[0]) + "% " + toString(this.distancesPercent[1]) + "% " + toString(this.distancesPercent[2]) + "% " + toString(this.distancesPercent[3]) + "%)";
		str += " (Areas:" + toString(this.distanceAreas[0]) + " " + toString(this.distanceAreas[1]) + " " + toString(this.distanceAreas[2]) + " " + toString(this.distanceAreas[3]) + ")";

		int onBorderCount = DrawHelper.INSTANCE.getNumsOnBorderCount(this);
		str += " (Num-on-border:" + onBorderCount + ")";

		AreasCount areasCount = getAreasCount();
		if (areasCount != null) {
			String areasCount1 = areasCount.getN_count() + "-" + areasCount.getS_count() + "-" + areasCount.getW_count() + "-" + areasCount.getE_count();
			String areasCount2 = areasCount.getNW_count() + "-" + areasCount.getNE_count() + "-" + areasCount.getSW_count() + "-" + areasCount.getSE_count();
			str += " (N-S-W-E=" + areasCount1 + " NW-NE-SW-SE=" + areasCount2 + ")";
		}

		return str;
	}
}
