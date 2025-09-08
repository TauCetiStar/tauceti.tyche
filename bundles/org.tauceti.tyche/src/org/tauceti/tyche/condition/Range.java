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

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Range<T extends Comparable<T>> {

	public static String DEFAULT_LEFT_NOTATION = "[";
	public static String DEFAULT_RIGHT_NOTATION = "]";

	protected String leftNotation;
	protected T min;
	protected T max;
	protected String rightNotation;

	/**
	 * @param leftNotation
	 * @param min
	 * @param max
	 * @param rightNotation
	 */
	public Range(String leftNotation, T min, T max, String rightNotation) {
		this.leftNotation = checkLeftNotation(leftNotation);
		this.min = min;
		this.max = max;
		this.rightNotation = checkRighNotation(rightNotation);
	}

	protected String checkLeftNotation(String leftNotation) {
		if (!"[".equals(leftNotation) && !"(".equals(leftNotation)) {
			leftNotation = DEFAULT_LEFT_NOTATION;
		}
		return leftNotation;
	}

	protected String checkRighNotation(String rightNotation) {
		if (!"]".equals(rightNotation) && !")".equals(rightNotation)) {
			rightNotation = DEFAULT_RIGHT_NOTATION;
		}
		return rightNotation;
	}

	public T getMin() {
		return min;
	}

	public void setMin(T min) {
		this.min = min;
	}

	public T getMax() {
		return max;
	}

	public void setMax(T max) {
		this.max = max;
	}

	public boolean inRange(T value) {
		boolean withinLeft = false;
		boolean withinRight = false;

		int leftCompare = value.compareTo(this.min);
		if ("[".equals(this.leftNotation)) {
			if (leftCompare >= 0) {
				withinLeft = true;
			}
		} else {
			if (leftCompare > 0) {
				withinLeft = true;
			}
		}

		int rightCompare = value.compareTo(this.max);
		if ("]".equals(this.rightNotation)) {
			if (rightCompare <= 0) {
				withinRight = true;
			}
		} else {
			if (rightCompare < 0) {
				withinRight = true;
			}
		}

		if (withinLeft && withinRight) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String text = this.leftNotation + this.min + "," + this.max + this.rightNotation;
		return text;
	}
}
