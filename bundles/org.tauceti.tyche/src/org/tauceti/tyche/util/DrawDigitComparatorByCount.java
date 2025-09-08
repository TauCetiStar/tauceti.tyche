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

import org.tauceti.tyche.model.DrawDigit;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class DrawDigitComparatorByCount extends BaseComparator<DrawDigit> {

	public static DrawDigitComparatorByCount ASC = new DrawDigitComparatorByCount(BaseComparator.ASC);
	public static DrawDigitComparatorByCount DESC = new DrawDigitComparatorByCount(BaseComparator.DESC);

	public DrawDigitComparatorByCount() {
		this(BaseComparator.ASC);
	}

	public DrawDigitComparatorByCount(String sort) {
		this.sort = check(sort);
	}

	@Override
	public int compare(DrawDigit d1, DrawDigit d2) {
		if (desc()) {
			DrawDigit tmp = d1;
			d1 = d2;
			d2 = tmp;
		}

		int count1 = d1.getCount();
		int count2 = d2.getCount();

		return count1 - count2;
	}
}
