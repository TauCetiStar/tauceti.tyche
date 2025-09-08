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
public class FirstDigitsCount {

	protected int[] firstDigitsArray;
	protected String firstDigits;
	protected Integer count;

	public FirstDigitsCount() {
	}

	public FirstDigitsCount(int[] firstDigitsArray, String firstDigits, Integer count) {
		this.firstDigitsArray = firstDigitsArray;
		this.firstDigits = firstDigits;
		this.count = count;
	}

	public int[] getFirstDigitsArray() {
		return this.firstDigitsArray;
	}

	public void setFirstDigitsArray(int[] firstDigitsArray) {
		this.firstDigitsArray = firstDigitsArray;
	}

	public String getFirstDigits() {
		return this.firstDigits;
	}

	public void setFirstDigits(String firstDigits) {
		this.firstDigits = firstDigits;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
