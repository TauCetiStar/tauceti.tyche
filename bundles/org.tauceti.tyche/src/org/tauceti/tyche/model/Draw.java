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
package org.tauceti.tyche.model;

import java.util.Date;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public interface Draw {

	Draw getPrevDraw();

	void setPrevDraw(Draw prevDraw);

	Draw getNextDraw();

	void setNextDraw(Draw nextDraw);

	int getDrawId();

	void setDrawId(int drawId);

	void setIndex(int index);

	int getIndex();

	Date getDate();

	String getDateString();

	void setDate(Date date);

	int getNum(int index);

	String getNum(int index, boolean doubleDigits);

	int getNum1();

	void setNum1(int num1);

	int getNum2();

	void setNum2(int num2);

	int getNum3();

	void setNum3(int num3);

	int getNum4();

	void setNum4(int num4);

	int getNum5();

	void setNum5(int num5);

	int getPB();

	void setPB(int pb);

	boolean containsNum(int num);

	boolean isPB(int pb);

	int[] getNumberArray(boolean includePb);

	String getNumsString0(boolean includePb);

	String getNumsString(boolean includePb);

	DrawStat getStat();

	void setStat(DrawStat stat);

	boolean isDummy();

	void setDummy(boolean isDummy);

	int[] getDistancesValue();

	int getDistanceValue(int index);

	void setDistancesValue(int[] distancesValue);

	float[] getDistancesPercent();

	float getDistancePercent(int index);

	void setDistancesPercent(float[] distancesPercent);

	int[] getDistanceAreas();

	int[] getDistanceAreas(int shift_v);

	void setDistanceAreas(int[] distanceAreas);

	Trend[] getTrend();

	void setTrend(Trend[] trend);

	AreasCount getAreasCount();

	void setAreasCount(AreasCount areasCount);

	int getDiameter();

	void setDiameter(int diameter);

	int getArea();

	void setArea(int area);
}
