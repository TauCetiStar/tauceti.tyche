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
package org.tauceti.tyche.condition;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.util.StatUtil;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class AvgToMidPercentCondition extends Condition<Integer> {

	public AvgToMidPercentCondition(Range<Integer> range) {
		super("avg/mid");
		setRange(range);
	}

	@Override
	public boolean match(Draw draw) {
		int n1 = draw.getNum1();
		int n2 = draw.getNum2();
		int n3 = draw.getNum3();
		int n4 = draw.getNum4();
		int n5 = draw.getNum5();
		int mid = n3;

		int avg = (int) StatUtil.avg(2, n1, n2, n3, n4, n5);
		int avgToMidPercent = StatUtil.normalizeByPercentage(avg, mid, 100);

		this.actualValue = avgToMidPercent;

		boolean match = this.range.inRange(avgToMidPercent);
		return match;
	}
}
