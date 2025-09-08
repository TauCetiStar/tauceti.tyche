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
package org.tauceti.tyche.filter;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFilter;
import org.tauceti.tyche.model.impl.DrawFilterImpl;
import org.tauceti.tyche.util.DrawUtil;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class EvenlyDivisibleCountFilter extends DrawFilterImpl implements DrawFilter {

	protected int min = -1;
	protected int max = -1;

	public EvenlyDivisibleCountFilter() {
	}

	/**
	 * 
	 * @param min
	 * @param max
	 */
	public EvenlyDivisibleCountFilter(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return this.min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return this.max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public boolean accept(Draw draw) {
		int count = DrawUtil.getEvenlyDivisibleCount(draw, false);

		if (this.min <= count && count <= this.max) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Evenly Divisible Count [" + this.min + ", " + this.max + "]";
	}
}
