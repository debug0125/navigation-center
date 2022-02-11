package com.pzc.navigationweb.domain.mapstruct;

import org.mapstruct.Named;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ryf
 * @date 2/11/22 5:48 PM
 */
public class DateMapper {

    @Named("asString")
    public String asString(Date date) {
        return date != null ? new SimpleDateFormat( "yyyy-MM-dd" )
                .format( date ) : null;
    }

    @Named("asStringTime")
    public String asStringTime(Date date) {
        return date != null ? new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" )
                .format( date ) : null;
    }

    public Date asDate(String date) {
        try {
            return date != null ? new SimpleDateFormat( "yyyy-MM-dd" )
                    .parse( date ) : null;
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
    }
}
