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
package org.tauceti.tyche.megamillion;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tauceti.tyche.chart.Report;
import org.tauceti.tyche.chart.ReportConstants;
import org.tauceti.tyche.chart.ReportService;
import org.tauceti.tyche.chart.impl.ReportServiceImpl;
import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.model.DrawFilter;
import org.tauceti.tyche.model.impl.MegaMillionReaderImpl;
import org.tauceti.tyche.model.impl.PredictionReaderImpl;
import org.tauceti.tyche.util.DrawHelper;
import org.tauceti.tyche.util.SystemUtil;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class MegaMillion {

	public static void main(String[] args0) {
		MegaMillion megaMillion = new MegaMillion();
		megaMillion.run();
	}

	public static String line = "---------------------------------------------------------------------------------------------------------------------------------------------------";

	public MegaMillion() {
	}

	public void run() {
		List<DrawFilter> filters = MegaMillionFilterManager.getDrawFilters();

		File file1 = new File(SystemUtil.getUserDir(), "/data/megamillions.csv");
		File file2 = new File(SystemUtil.getUserDir(), "/data/megamillions_predict.txt");
		if (!file1.exists()) {
			new MegaMillionDownloader().run();
		}
		if (!file1.exists()) {
			throw new RuntimeException(file1 + " doesn't exist.");
		}

		List<Draw> draws = DrawHelper.INSTANCE.read(file1, new MegaMillionReaderImpl());
		List<Draw> predictions = getPredictions(draws, file2);

		List<Report> reports = generateReports(filters, draws, predictions);
		print(draws, predictions, reports);
	}

	public List<Draw> getPredictions(List<Draw> draws, File file) {
		List<Draw> predictions = DrawHelper.INSTANCE.read(file, new PredictionReaderImpl());
		Draw lastDraw = (!draws.isEmpty()) ? draws.get(draws.size() - 1) : null;
		for (Draw prediction : predictions) {
			DrawHelper.INSTANCE.updateDraw(lastDraw, prediction, null);
		}
		return predictions;
	}

	/**
	 * 
	 * @param filters
	 * @param draws
	 * @param predictions
	 * @return
	 */
	public List<Report> generateReports(List<DrawFilter> filters, List<Draw> draws, List<Draw> predictions) {
		List<Report> reports = null;
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			args.put(ReportConstants.ARG__DRAWS_TYPE, ReportConstants.DRAWS_TYPE__MEGA_MILLION);
			args.put(ReportConstants.ARG__DRAWS_NUMBERS, draws);
			args.put(ReportConstants.ARG__DRAWS_PREDICTIONS, predictions);
			args.put(ReportConstants.ARG__REPORT_FOLDRE, MegaMillionConstants.REPORTS_FOLDER);
			args.put(ReportConstants.ARG__NUMBER6_LABEL, "Mega Ball");

			ReportService service = new ReportServiceImpl();
			service.setReportPrefix("MegaMillion");

			reports = service.generateReports(args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}

	/**
	 * 
	 * @param draws
	 * @param predictions
	 * @param reports
	 */
	public void print(List<Draw> draws, List<Draw> predictions, List<Report> reports) {
		MegaMillionPrintManager.INSTANCE.print(draws, predictions, reports);
	}
}
