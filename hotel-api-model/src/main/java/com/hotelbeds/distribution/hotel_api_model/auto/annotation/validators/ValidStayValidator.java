/**
 * Autogenerated code by SdkModelGenerator.
 * Do not edit. Any modification on this file will be removed automatically after project build
 *
 */
package com.hotelbeds.distribution.hotel_api_model.auto.annotation.validators;

/*
 * #%L
 * hotel-api-model
 * %%
 * Copyright (C) 2015 HOTELBEDS, S.L.U.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hotelbeds.distribution.hotel_api_model.auto.model.Stay;

public class ValidStayValidator implements ConstraintValidator<ValidStay, Stay> {

    private long maxDaysRange;

    @Override
    public void initialize(final ValidStay constraintAnnotation) {
        maxDaysRange = constraintAnnotation.maxDaysRange();
    }

    @Override
    public boolean isValid(final Stay stay, final ConstraintValidatorContext context) {
        boolean result = true;
        context.disableDefaultConstraintViolation();
        if (stay.getCheckIn().isEqual(stay.getCheckOut())) {
            context.buildConstraintViolationWithTemplate("{com.hotelbeds.distribution.hotel_api_webapp.webapp.api.model.Stay.dates.before.message}")
                .addConstraintViolation();
            result = false;
        } else if (stay.getCheckIn().isAfter(stay.getCheckOut())) {
            context.buildConstraintViolationWithTemplate("{com.hotelbeds.distribution.hotel_api_webapp.webapp.api.model.Stay.dates.before.message}")
                .addConstraintViolation();
            result = false;
        } else if (!isValidDateRange(stay.getCheckIn(), stay.getCheckOut())) {
            context.buildConstraintViolationWithTemplate("{com.hotelbeds.distribution.hotel_api_webapp.webapp.api.model.Stay.dates.range.message}")
                .addConstraintViolation();
            result = false;
        }
        return result;
    }

    private boolean isValidDateRange(LocalDate checkIn, LocalDate checkOut) {
        final long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (Long.valueOf(days).compareTo(maxDaysRange) > 0) {
            return false;
        }
        return true;
    }
}
