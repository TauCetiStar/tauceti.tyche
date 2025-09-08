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
package org.tauceti.tyche.chart;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public interface ReportService {

	String getReportPrefix();

	void setReportPrefix(String reportPrefix);

	List<IReportGenerator> getReportGenerators(int options, boolean newInstances);

	void setReportGenerators(List<IReportGenerator> generators);

	List<Report> generateReports(Map<String, Object> args) throws Exception;
}
