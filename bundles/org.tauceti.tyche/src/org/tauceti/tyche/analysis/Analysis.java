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

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public interface Analysis {

	/**
	 * Get updater id.
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * Get priority. (highest to lowest) 1 to 10.
	 * 
	 * @return
	 */
	public int getPriority();

	/**
	 * Register the analysis.
	 * 
	 */
	public void register();

	/**
	 * Unregister the analysis.
	 * 
	 */
	public void unregister();

	/**
	 * Run the analysis.
	 * 
	 * @param context
	 */
	public void run(AnalysisContext context);

	/**
	 * 
	 * @param context
	 */
	public void printResult(AnalysisContext context);
}
