package org.tauceti.tyche.chart.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.tauceti.tyche.chart.Report;

/**
 * 
 * @author Yang Yang
 *
 */
public class ReportImpl implements Report {

	protected String id;
	protected Date date;
	protected File file;
	protected String fileName;

	public ReportImpl() {
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Date getDate() {
		return this.date;
	}

	@Override
	public File getFile() {
		return this.file;
	}

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String getFileName() {
		return this.fileName;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Report [" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.date) + " | " + id + " | " + this.file.getPath() + "]";
	}
}
