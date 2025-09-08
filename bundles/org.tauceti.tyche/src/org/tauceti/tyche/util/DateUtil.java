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
package org.tauceti.tyche.util;

import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class DateUtil {

	/*
	 * @see http://docs.oracle.com/javase/1.5.0/docs/api/java/text/SimpleDateFormat.html
	 */
	public static SimpleDateFormat SIMPLE_DATE_FORMAT0 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
	public static SimpleDateFormat SIMPLE_DATE_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	public static SimpleDateFormat SIMPLE_DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat SIMPLE_DATE_FORMAT3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	public static SimpleDateFormat SIMPLE_DATE_FORMAT4 = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
	public static SimpleDateFormat YEAR_MONTH_DAY_FORMAT1 = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat MONTH_DAY_YEAR_FORMAT1 = new SimpleDateFormat("MM/dd/yyyy");
	public static SimpleDateFormat MONTH_DAY_YEAR_FORMAT2 = new SimpleDateFormat("EEE.MMM dd,yyyy");

	public static SimpleDateFormat[] COMMON_DATE_FORMATS;

	static {
		COMMON_DATE_FORMATS = new SimpleDateFormat[] { SIMPLE_DATE_FORMAT0, SIMPLE_DATE_FORMAT1, SIMPLE_DATE_FORMAT2, SIMPLE_DATE_FORMAT3 };
	}

	public static SimpleDateFormat[] getCommonDateFormats() {
		return COMMON_DATE_FORMATS;
	}

	public static SimpleDateFormat getDefaultDateFormat() {
		return SIMPLE_DATE_FORMAT0;
	}

	public static SimpleDateFormat getJdbcDateFormat() {
		return SIMPLE_DATE_FORMAT3;
	}

	/**
	 * 
	 * @param milliseconds
	 * @return
	 */
	public static String formatDuration(long milliseconds) {
		String text = "";
		long seconds = milliseconds / 1000;

		long minutes = seconds / 60;
		long remainSeconds = seconds % 60;

		long hours = minutes / 60;
		long remainMinutes = minutes % 60;

		long days = hours / 24;
		long remainHours = hours % 24;

		// days
		if (days > 0) {
			text += days + " days";
		}

		// hours
		if (days > 0) {
			if (remainHours > 0) {
				if (!text.isEmpty()) {
					text += " ";
				}
				text += remainHours + " hours";
			}
		} else {
			if (hours > 0) {
				if (!text.isEmpty()) {
					text += " ";
				}
				text += hours + " hours";
			}
		}

		// minutes
		if (hours > 0) {
			if (remainMinutes > 0) {
				if (!text.isEmpty()) {
					text += " ";
				}
				text += remainMinutes + " minutes";
			}
		} else {
			if (minutes > 0) {
				if (!text.isEmpty()) {
					text += " ";
				}
				text += minutes + " minutes";
			}
		}

		// seconds
		if (!text.isEmpty()) {
			text += " ";
		}
		if (minutes > 0) {
			text += remainSeconds + " seconds";
		} else {
			text += seconds + " seconds";
		}

		return text;
	}

	/**
	 * 
	 * @param dateString
	 * @param formats
	 * @return
	 */
	public static Date toDate(String dateString, DateFormat... formats) {
		if (dateString == null) {
			throw new IllegalArgumentException("dateString is null.");
		}
		if (formats == null) {
			throw new IllegalArgumentException("formats is null.");
		}
		Date date = null;
		for (DateFormat format : formats) {
			try {
				date = format.parse(dateString);
				if (date != null) {
					break;
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	public static Date toDate(long time) {
		return new Date(time);
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(long time, DateFormat format) {
		Date date = new Date(time);
		if (format == null) {
			throw new IllegalArgumentException("format is null.");
		}
		return format.format(date);
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(Date date, DateFormat format) {
		if (date == null) {
			// throw new IllegalArgumentException("date is null.");
			return null;
		}
		if (format == null) {
			throw new IllegalArgumentException("format is null.");
		}
		return format.format(date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * Beware, months start at 0, not 1.
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		return month;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDaysBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return -1;
		}

		Calendar laterCalendar = new GregorianCalendar();
		laterCalendar.setTime(endDate);
		int year2 = laterCalendar.get(Calendar.YEAR);
		int month2 = laterCalendar.get(Calendar.MONTH) + 1;
		int day2 = laterCalendar.get(Calendar.DAY_OF_MONTH);

		Calendar firstCalendar = new GregorianCalendar();
		firstCalendar.setTime(startDate);
		int year1 = firstCalendar.get(Calendar.YEAR);
		int month1 = firstCalendar.get(Calendar.MONTH) + 1;
		int day1 = firstCalendar.get(Calendar.DAY_OF_MONTH);

		LocalDate laterLocalDate = LocalDate.of(year2, month2, day2);
		LocalDate firstLocalDate = LocalDate.of(year1, month1, day1);

		long daysBetween = ChronoUnit.DAYS.between(firstLocalDate, laterLocalDate);

		return daysBetween;
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getHoursBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return -1;
		}
		long duration = endDate.getTime() - startDate.getTime();
		long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
		return diffInHours;
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getMinutesBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return -1;
		}
		long duration = endDate.getTime() - startDate.getTime();
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		return diffInMinutes;
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getSecondsBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return -1;
		}
		long duration = endDate.getTime() - startDate.getTime();
		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		return diffInSeconds;
	}

	/*-
	public static Date addYears(Date date, int amount) {
		return getDateUtils().addYears(date, amount);
	}
	
	public static Date addMonths(Date date, int amount) {
		return getDateUtils().addMonths(date, amount);
	}
	
	public static Date addWeeks(Date date, int amount) {
		return getDateUtils().addWeeks(date, amount);
	}
	
	public static Date addDays(Date date, int amount) {
		return getDateUtils().addDays(date, amount);
	}
	
	public static Date addHours(Date date, int amount) {
		return getDateUtils().addHours(date, amount);
	}
	
	public static Date addMinutes(Date date, int amount) {
		return getDateUtils().addMinutes(date, amount);
	}
	
	public static Date addSeconds(Date date, int amount) {
		return getDateUtils().addSeconds(date, amount);
	}
	
	public static Date addMilliseconds(Date date, int amount) {
		return getDateUtils().addMilliseconds(date, amount);
	}
	*/

	public static final List<Long> times = Arrays.asList( //
			DAYS.toMillis(365), //
			DAYS.toMillis(30), //
			DAYS.toMillis(7), //
			DAYS.toMillis(1), //
			HOURS.toMillis(1), //
			MINUTES.toMillis(1), //
			SECONDS.toMillis(1) //
	);

	public static final List<String> timesString = Arrays.asList( //
			"year", //
			"month", //
			"week", //
			"day", //
			"hour", //
			"minute", //
			"second" //
	);

	/**
	 * Get relative time ago for date
	 *
	 * NOTE: if (duration > WEEK_IN_MILLIS) getRelativeTimeSpanString prints the date.
	 *
	 * ALT: return getRelativeTimeSpanString(date, now, SECOND_IN_MILLIS, FORMAT_ABBREV_RELATIVE);
	 *
	 * @see https://stackoverflow.com/questions/3859288/how-to-calculate-time-ago-in-java/37042254#37042254
	 * @param date String.valueOf(TimeUtils.getRelativeTime(1000L * Date/Time in Millis)
	 * @return relative time
	 */
	public static String getRelativeTime(final long date) {
		return toDuration(Math.abs(System.currentTimeMillis() - date));
	}

	public static String toDuration(long duration) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times.size(); i++) {
			Long current = times.get(i);
			long temp = duration / current;
			if (temp > 0) {
				sb.append(temp).append(" ").append(timesString.get(i)).append(temp > 1 ? "s" : "").append(" ago");
				break;
			}
		}
		return sb.toString().isEmpty() ? "now" : sb.toString();
	}

	/**
	 * 
	 * @param beforeTime
	 * @param timeToAdd
	 * @param timeUnit
	 * @return
	 */
	public static Date addTimeToDate(Date beforeTime, long timeToAdd, TimeUnit timeUnit) {
		long curTimeInMs = beforeTime.getTime();
		long millisecsToAdd = TimeUnit.MILLISECONDS.convert(timeToAdd, timeUnit);
		return new Date(curTimeInMs + millisecsToAdd);
	}

	/**
	 * Prints the duration in a human readable format as X days Y hours Z minutes etc.
	 *
	 * @param uptime the uptime in millis
	 * @return the time used for displaying on screen or in logs
	 * @see
	 */
	public static String printDuration(double uptime) {
		// Code taken from Karaf
		// https://svn.apache.org/repos/asf/karaf/trunk/shell/commands/src/main/java/org/apache/karaf/shell/commands/impl/InfoAction.java
		NumberFormat fmtI = new DecimalFormat("###,###", new DecimalFormatSymbols(Locale.ENGLISH));
		NumberFormat fmtD = new DecimalFormat("###,##0.000", new DecimalFormatSymbols(Locale.ENGLISH));

		uptime /= 1000;
		if (uptime < 60) {
			return fmtD.format(uptime) + " seconds";
		}
		uptime /= 60;
		if (uptime < 60) {
			long minutes = (long) uptime;
			String s = fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
			return s;
		}
		uptime /= 60;
		if (uptime < 24) {
			long hours = (long) uptime;
			long minutes = (long) ((uptime - hours) * 60);
			String s = fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
			if (minutes != 0) {
				s += " " + fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
			}
			return s;
		}
		uptime /= 24;
		long days = (long) uptime;
		long hours = (long) ((uptime - days) * 24);
		String s = fmtI.format(days) + (days > 1 ? " days" : " day");
		if (hours != 0) {
			s += " " + fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
		}
		return s;
	}
}
