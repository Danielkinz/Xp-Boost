package me.danielkinz.xpboost;

/**
 * Used to convert a user readable time string to long and back
 * 
 * @author Danielkinz
 */
public class TimeFormat {

	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;
	private int days = 0;
	private long time = 0;

	/**
	 * Converts a long to the time it represents
	 * 
	 * @param time
	 */
	public TimeFormat(long time) {
		this.time = time;
		this.days = (int) (time / 86400000);
		time = time % 86400000;
		this.hours = (int) (time / 3600000);
		time = time % 3600000;
		this.minutes = (int) (time / 60000);
		time = time % 60000;
		this.seconds = (int) (time / 1000);
	}

	/**
	 * Parses a time format from a string
	 * 
	 * @param text
	 * @return
	 */
	public static TimeFormat parse(String text) throws Exception {
		TimeFormat format = null;
		long millis = 0;
		String[] substrings = text.split(" ");

		try {
			for (String string : substrings) {
				char timec = string.charAt(string.length() - 1);

				if (string.length() < 2) {
					throw new Exception("Invalid format");
				}

				switch (timec) {
				case 's':
					millis += Math.abs(Integer.parseInt((String) string.subSequence(0, string.length() - 1))) * 1000;
					break;
				case 'm':
					millis += Math.abs(Integer.parseInt((String) string.subSequence(0, string.length() - 1))) * 60000;
					break;
				case 'h':
					millis += Math.abs(Integer.parseInt((String) string.subSequence(0, string.length() - 1))) * 3600000;
					break;
				case 'd':
					millis += Math.abs(Integer.parseInt((String) string.subSequence(0, string.length() - 1)))
							* 86400000;
					break;
				default:
					throw new Exception("Invalid format");
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return new TimeFormat(millis);
	}

	/**
	 * Creates a time format using specified time
	 * 
	 * @param millis
	 * @param seconds
	 * @param minutes
	 * @param hours
	 * @param days
	 * @param weeks
	 * @param years
	 */
	public TimeFormat(int millis, int seconds, int minutes, int hours, int days, int weeks, int years) {
		this.seconds = seconds;
		this.minutes = minutes;
		this.hours = hours;
		this.days = days;
		this.time = millis + 1000 * seconds + 60000 * minutes + 3600000 * hours + 86400000 * days + 604800000 * weeks
				+ 31536000000L * years;
	}

	public long getRemainingTime() {
		return this.time - System.currentTimeMillis();
	}

	public long getTimeStamp() {
		return this.time;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getHours() {
		return hours;
	}

	public int getDays() {
		return days;
	}

	/**
	 * Gets the difference between 2 dates
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long getDifference(TimeFormat a, TimeFormat b) {
		return a.getTimeStamp() - b.getTimeStamp();
	}

	/**
	 * Gets the difference between 2 dates
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long getDifference(TimeFormat a, long b) {
		return a.getTimeStamp() - b;
	}

	/**
	 * Gets the difference between 2 dates
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long getDifference(long a, long b) {
		return a - b;
	}

}
