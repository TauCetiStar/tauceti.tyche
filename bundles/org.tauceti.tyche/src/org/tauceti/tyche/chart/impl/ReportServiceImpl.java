package org.tauceti.tyche.chart.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tauceti.tyche.chart.IReportGenerator;
import org.tauceti.tyche.chart.Report;
import org.tauceti.tyche.chart.ReportConstants;
import org.tauceti.tyche.chart.ReportService;
import org.tauceti.tyche.model.Draw;
import org.tauceti.tyche.util.DrawHelper;

/**
 * 
 * @author Yang Yang
 *
 */
public class ReportServiceImpl implements ReportService {

	protected String reportPrefix;
	protected File reportsFolder;
	protected List<IReportGenerator> reportGenerators;

	public ReportServiceImpl() {
	}

	@Override
	public String getReportPrefix() {
		return this.reportPrefix;
	}

	@Override
	public void setReportPrefix(String reportPrefix) {
		this.reportPrefix = reportPrefix;
	}

	protected String createReportId(Draw lastDraw) {
		Date date = (lastDraw != null) ? lastDraw.getDate() : new Date();
		String reportId = this.reportPrefix + "_" + new SimpleDateFormat("yyyy-MM-dd").format(date);
		return reportId;
	}

	@Override
	public List<IReportGenerator> getReportGenerators(int options, boolean newInstances) {
		if (newInstances) {
			List<IReportGenerator> reportGenerators = new ArrayList<IReportGenerator>();
			if ((ReportConstants.DRAW_OPTIONS__CHART & options) == ReportConstants.DRAW_OPTIONS__CHART) {
				reportGenerators.add(new ReportGeneratorChartImpl());
			}
			if ((ReportConstants.DRAW_OPTIONS__CARD & options) == ReportConstants.DRAW_OPTIONS__CARD) {
				reportGenerators.add(new ReportGeneratorCardImpl());
			}
			return reportGenerators;

		} else {
			if (this.reportGenerators == null) {
				this.reportGenerators = new ArrayList<IReportGenerator>();
				if ((ReportConstants.DRAW_OPTIONS__CHART & options) == ReportConstants.DRAW_OPTIONS__CHART) {
					this.reportGenerators.add(new ReportGeneratorChartImpl());
				}
				if ((ReportConstants.DRAW_OPTIONS__CARD & options) == ReportConstants.DRAW_OPTIONS__CARD) {
					this.reportGenerators.add(new ReportGeneratorCardImpl());
				}
			}
			return this.reportGenerators;
		}
	}

	@Override
	public void setReportGenerators(List<IReportGenerator> generators) {
		this.reportGenerators = generators;
	}

	@Override
	public synchronized List<Report> generateReports(Map<String, Object> args) throws Exception {
		List<Report> reports = new ArrayList<Report>();

		List<Draw> draws = DrawHelper.INSTANCE.getDraws(args);
		Draw lastDraw = draws.get(draws.size() - 1);

		String reportId = (String) args.get(ReportConstants.ARG__REPORT_ID);
		if (reportId == null) {
			reportId = createReportId(lastDraw);
			args.put(ReportConstants.ARG__REPORT_ID, reportId);
		}

		Date nowDate = new Date();
		args.put(ReportConstants.ARG__REPORT_DATE, nowDate);

		// Generate draws
		List<IReportGenerator> generators = getReportGenerators(ReportConstants.DRAW_OPTIONS__ALL, false);
		for (IReportGenerator generator : generators) {
			try {
				if (generator.isSupported(args)) {
					Report report = generator.generate(args);
					if (report != null) {
						reports.add(report);
					}
				}
			} catch (Exception e) {
				handleException(e);
			}
		}

		// Generate predictions
		List<IReportGenerator> generators2 = getReportGenerators(ReportConstants.DRAW_OPTIONS__CHART, true);
		int i = 0;
		List<Draw> predictionDraws = DrawHelper.INSTANCE.getPredictionDraws(args);
		for (Draw predictionDraw : predictionDraws) {
			String currReportId = reportId + "_predic_(" + i + ")";

			List<Draw> currDraws = new ArrayList<Draw>(draws);
			currDraws.add(predictionDraw);

			for (IReportGenerator generator : generators2) {
				try {
					if (generator.isSupported(args)) {
						Map<String, Object> currArgs = new HashMap<String, Object>();
						currArgs.putAll(args);
						currArgs.put(ReportConstants.ARG__REPORT_ID, currReportId);
						currArgs.put(ReportConstants.ARG__DRAWS_NUMBERS, currDraws);

						Report currReport = generator.generate(currArgs);
						if (currReport != null) {
							reports.add(currReport);
						}
					}
				} catch (Exception e) {
					handleException(e);
				}
			}

			i++;
		}

		return reports;
	}

	protected void handleException(Exception e) throws Exception {
		e.printStackTrace();
	}
}
