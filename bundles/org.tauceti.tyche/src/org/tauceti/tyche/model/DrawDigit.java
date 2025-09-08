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

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class DrawDigit {

	protected int digit;
	protected int count;

	public DrawDigit(int digit) {
		this.digit = digit;
	}

	public int getDigit() {
		return this.digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void increaseCount() {
		this.count += 1;
	}

	public void decreaseCount() {
		this.count -= 1;
	}
}
