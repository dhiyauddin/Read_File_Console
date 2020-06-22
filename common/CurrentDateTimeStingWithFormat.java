package common;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CurrentDateTimeStingWithFormat {

	public String processDate() {

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.parse(dateFormat.format(date).toString(),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");
		ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// String today = dateFormat.format(date).toString();
		System.out.println("today : " + format.format(asiaZonedDateTime));
		String result = format.format(asiaZonedDateTime);
		return result;
	}

}
