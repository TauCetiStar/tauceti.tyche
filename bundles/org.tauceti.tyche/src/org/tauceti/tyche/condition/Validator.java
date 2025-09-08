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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFactory;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class Validator {

	public static Validator INSTANCE = new Validator();

	/**
	 * 
	 * @param draw
	 * @param conditions
	 */
	public void validate(Draw draw, Condition<?>... conditions) {
		String result = "";
		for (Condition<?> condition : conditions) {
			boolean match = condition.match(draw);
			String currResult = condition + " -> " + match;
			result += currResult + "\t";
		}

		System.out.print(draw.toString() + "\t");
		System.out.println(result);
	}

	public static void main(String[] args) {
		List<Draw> draws = new ArrayList<Draw>();
		Draw draw1 = DrawFactory.getInstance().createDraw(new Date(), 3, 26, 37, 58, 63, 22);
		draws.add(draw1);

		Condition<Integer> avgToMidCondition = new AvgToMidPercentCondition(new Range<Integer>("[", 80, 100, "]"));
		Condition<Integer> diffAvgCondition = new DiffAvgCondition(new Range<Integer>("[", 9, 10, "]"));
		Condition<Long> sumCondition = new SumCondition(new Range<Long>("[", (long) 130, (long) 170, "]"));

		for (Draw draw : draws) {
			Validator.INSTANCE.validate(draw, avgToMidCondition, diffAvgCondition, sumCondition);
		}
	}
}
