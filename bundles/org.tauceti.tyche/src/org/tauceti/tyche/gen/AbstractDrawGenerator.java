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
package org.tauceti.tyche.gen;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFilter;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public abstract class AbstractDrawGenerator implements DrawGenerator {

	/**
	 * 
	 * @param min
	 * @param max
	 * @param size
	 * @return
	 */
	protected int[] generate(Mode mode, int min, int max, int size) {
		List<Integer> numbers = new ArrayList<Integer>();

		if (Mode.ThreadLocalRandom.equals(mode)) {
			for (int i = 0; i < size; i++) {
				int number = ThreadLocalRandom.current().nextInt(min, max + 1);
				while (numbers.contains(number)) {
					number = ThreadLocalRandom.current().nextInt(min, max + 1);
				}
				numbers.add(number);
			}

		} else if (Mode.SecureRandom.equals(mode)) {
			for (int i = 0; i < size; i++) {
				int number = new SecureRandom().nextInt(max - min + 1) + min;
				while (numbers.contains(number)) {
					number = new SecureRandom().nextInt(max - min + 1) + min;
				}
				numbers.add(number);
			}

		} else if (Mode.Random.equals(mode)) {
			for (int i = 0; i < size; i++) {
				int number = new Random().nextInt(max - min + 1) + min;
				while (numbers.contains(number)) {
					number = new Random().nextInt(max - min + 1) + min;
				}
				numbers.add(number);
			}
		}

		Collections.sort(numbers);

		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = numbers.get(i);
		}
		return array;
	}

	/**
	 * 
	 * @param mode
	 * @param min
	 * @param max
	 * @return
	 */
	protected int generate(Mode mode, int min, int max) {
		int number = 0;
		if (Mode.ThreadLocalRandom.equals(mode)) {
			number = ThreadLocalRandom.current().nextInt(min, max + 1);

		} else if (Mode.SecureRandom.equals(mode)) {
			number = new SecureRandom().nextInt(max - min + 1) + min;

		} else if (Mode.Random.equals(mode)) {
			number = new Random().nextInt(max - min + 1) + min;
		}
		return number;
	}

	/**
	 * 
	 * @param draw
	 * @param filters
	 * @return
	 */
	protected boolean isAccepted(Draw draw, DrawFilter[] filters) {
		boolean isAccepted = false;

		if (filters != null && filters.length > 0) {
			boolean hasAccepted = false;
			boolean hasNotAccepted = false;
			for (DrawFilter filter : filters) {
				if (filter.accept(draw)) {
					hasAccepted = true;
				} else {
					hasNotAccepted = true;
				}
			}
			isAccepted = (hasAccepted && !hasNotAccepted) ? true : false;
		} else {
			isAccepted = true;
		}

		return isAccepted;
	}
}
