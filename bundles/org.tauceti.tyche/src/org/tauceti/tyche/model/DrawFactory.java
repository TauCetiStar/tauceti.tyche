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

import org.tauceti.tyche.model.impl.DrawImpl;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class DrawFactory {

	private static DrawFactory INSTANCE = new DrawFactory();

	public static DrawFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * 
	 * @return
	 */
	public Draw createDraw() {
		return new DrawImpl();
	}

	/**
	 * 
	 * @param date
	 * @param numbers
	 * @param pb
	 * @return
	 */
	public Draw createDraw(Date date, int[] numbers, int pb) {
		return new DrawImpl(date, numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], pb);
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
	 * @return
	 */
	public Draw createDraw(Date date, int num1, int num2, int num3, int num4, int num5, int pb) {
		return new DrawImpl(date, num1, num2, num3, num4, num5, pb);
	}
}
