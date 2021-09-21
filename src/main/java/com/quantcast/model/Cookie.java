package com.quantcast.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Cookie {

	public String cookieId;

	public Date date;

	ZonedDateTime zonedDateTime;

	public Cookie(String cookieId, OffsetDateTime parseDateTime) {
		this.cookieId = cookieId;
		this.zonedDateTime = parseDateTime.toZonedDateTime();
	}

	public Cookie(String cookieId, Date date) {
		this.cookieId = cookieId;
		this.date = date;
		this.zonedDateTime = date.toInstant().atZone(ZoneId.systemDefault());

	}

	public ZonedDateTime getTimestamp() {
		return zonedDateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cookieId == null) ? 0 : cookieId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cookie other = (Cookie) obj;
		if (cookieId == null) {
			if (other.cookieId != null)
				return false;
		} else if (!cookieId.equals(other.cookieId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cookie{" +
				"cookieId='" + cookieId + '\'' +
				", date=" + date +
				", zonedDateTime=" + zonedDateTime +
				'}';
	}
}