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
package org.tauceti.tyche.analysis;

import java.util.List;

import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawStat;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class AnalysisContext {

	protected DrawStat globalStat;
	protected List<Draw> draws;

	public AnalysisContext() {
		this(new DrawStat());
	}

	public AnalysisContext(DrawStat globalStat) {
		this.globalStat = globalStat;
		if (this.globalStat == null) {
			this.globalStat = new DrawStat();
		}
	}

	public DrawStat getGlobalStat() {
		return this.globalStat;
	}

	public void setGlobalStat(DrawStat globalStat) {
		this.globalStat = globalStat;
	}

	public List<Draw> getDraws() {
		return this.draws;
	}

	public void setDraws(List<Draw> draws) {
		this.draws = draws;
	}
}
