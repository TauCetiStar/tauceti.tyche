package org.tauceti.tyche.chart.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;

import org.tauceti.tyche.chart.AbstractReportGenerator;
import org.tauceti.tyche.chart.IReportContentGenerator;
import org.tauceti.tyche.chart.Report;
import org.tauceti.tyche.chart.ReportConstants;
import org.tauceti.tyche.util.DrawHelper;
import org.tauceti.tyche.util.IOUtils;
import org.tauceti.tyche.util.PropertiesHelper;

/**
 * 
 * @author Yang Yang
 */
public class ReportGeneratorCardImpl extends AbstractReportGenerator {

	public static String ID = "ReportGeneratorCardImpl";

	public ReportGeneratorCardImpl() {
		setId(ID);
	}

	@Override
	public Report generate(Map<String, Object> args) {
		// Get report config info
		File reportFolder = PropertiesHelper.INSTANCE.getValue(args, File.class, ReportConstants.ARG__REPORT_FOLDRE, null, true);
		String reportId = PropertiesHelper.INSTANCE.getValue(args, String.class, ReportConstants.ARG__REPORT_ID, null, true);
		Date reportDate = PropertiesHelper.INSTANCE.getValue(args, Date.class, ReportConstants.ARG__REPORT_DATE, null, true);

		// Get HTML report content and generate html file
		File reportFile = null;
		FileOutputStream output = null;
		try {
			reportFile = new File(reportFolder, reportId + "_card.html");
			if (!reportFile.getParentFile().exists()) {
				reportFile.getParentFile().mkdirs();
			}
			if (reportFile.exists()) {
				reportFile.delete();
			}

			String htmlContent = getHtmlContent(args);

			output = new FileOutputStream(reportFile);
			output.write(htmlContent.getBytes(Charset.defaultCharset()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				IOUtils.closeQuietly(output);
			}
		}

		ReportImpl report = new ReportImpl();
		report.setId(reportId);
		report.setDate(reportDate);
		if (reportFile != null) {
			report.setFile(reportFile);
			report.setFileName(reportFile.getName());
		}

		return report;
	}

	protected String getHtmlContent(Map<String, Object> args) {
		// 1. top
		String topContent = null;
		InputStream topInput = null;
		try {
			topInput = getClass().getResourceAsStream("html_top.txt");
			topContent = IOUtils.toString(topInput);

			if (DrawHelper.INSTANCE.isPowerball(args)) {
				topContent = topContent.replaceAll("Lottery", "Powerball - Card");
			} else if (DrawHelper.INSTANCE.isMegaMillion(args)) {
				topContent = topContent.replaceAll("Lottery", "MegaMillion - Card");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (topInput != null) {
				try {
					IOUtils.closeQuietly(topInput);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// 2. bottom
		String bottomContent = null;
		InputStream bottomInput = null;
		try {
			bottomInput = getClass().getResourceAsStream("html_bottom.txt");
			bottomContent = IOUtils.toString(bottomInput);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bottomInput != null) {
				try {
					IOUtils.closeQuietly(bottomInput);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// 3. contents
		String mainContents = "";

		String currContent = null;
		IReportContentGenerator reportContentGenerator = getReportContentGenerator();
		if (reportContentGenerator != null && reportContentGenerator.isSupported(args)) {
			Object reportContentObj = reportContentGenerator.generate(args);
			if (reportContentObj != null) {
				currContent = reportContentObj.toString();
			}
		}
		if (currContent != null && !currContent.isEmpty()) {
			mainContents += currContent;
		}

		String newLine = System.lineSeparator();

		String htmlContent = "";
		htmlContent += topContent;
		htmlContent += newLine;
		htmlContent += mainContents;
		htmlContent += newLine;
		htmlContent += bottomContent;

		return htmlContent;
	}

	protected IReportContentGenerator getReportContentGenerator() {
		return new ReportContentGeneratorCardImpl();
	}
}
