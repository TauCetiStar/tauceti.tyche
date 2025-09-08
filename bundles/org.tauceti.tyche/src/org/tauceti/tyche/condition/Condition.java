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

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public abstract class Condition<T extends Comparable<T>> {

	protected String name;
	protected Range<T> range;
	protected T actualValue;

	public Condition(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Range<T> getRange() {
		return range;
	}

	public void setRange(Range<T> range) {
		this.range = range;
	}

	public T getActualValue() {
		return this.actualValue;
	}

	public void setActualValue(T actualValue) {
		this.actualValue = actualValue;
	}

	public abstract boolean match(Draw draw);

	public String toString() {
		String text = getName() + " -> " + this.range.toString() + " " + this.actualValue;
		return text;
	}
}
