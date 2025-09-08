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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFactory;
import org.tauceti.tyche.model.DrawFilter;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class MegaMillionGenerator extends AbstractDrawGenerator {

	@Override
	public List<Draw> generate(Mode mode, Date date, int size, DrawFilter[] filters) {
		List<Draw> draws = new ArrayList<Draw>();

		int index = 0;
		for (int i = 0; i < size; i++) {
			int[] numbers = generate(mode, 1, 70, 5);
			int pb = generate(mode, 1, 25);

			Draw draw = DrawFactory.getInstance().createDraw(date, numbers, pb);

			boolean isAccepted = isAccepted(draw, filters);
			if (isAccepted) {
				draw.setDrawId(1000 + index + 1);
				draw.setIndex(1000 + index + 1);
				draws.add(draw);
				index++;
			}
		}
		return draws;
	}
}
