package com.ing.nzy.interieur.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
public class DateMapper {

    LocalDateTime timestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    Timestamp localDateTimeToTimeStamp(LocalDateTime dateTime) {
        return Timestamp.valueOf(dateTime);
    }

    LocalDate sqlDateToLocaleDate(Date date) {
        return date.toLocalDate();
    }

    Date localDateToSqlDate(LocalDate localDate) {
        return Date.valueOf(localDate);
    }

}
