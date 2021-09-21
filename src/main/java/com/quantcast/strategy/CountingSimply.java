package com.quantcast.strategy;


import com.quantcast.configuration.CookieFileConfiguration;
import com.quantcast.exceptions.BadDataFormatException;
import com.quantcast.exceptions.NoDataFoundException;
import com.quantcast.framework.Counting;
import com.quantcast.model.Cookie;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CountingSimply implements Counting {


    private static Logger LOG = LogManager.getLogger(CountingSimply.class);

    CookieFileConfiguration cookieFileConfiguration;



    public CountingSimply(CookieFileConfiguration cookieFileConfiguration) {
        this.cookieFileConfiguration = cookieFileConfiguration;
    }


    @Override
    public List<String> apply() {
        try {
            SortedLinesDesc lines =
                    Loader.allSortedLinesDesc(cookieFileConfiguration);
            if (lines.size()==0){
                throw new NoDataFoundException("No Data Found");
            }

            int startingPoint = lines.findStartingPoint(cookieFileConfiguration.getDate());


            Counter counter = new Counter();
            if (startingPoint < 0) return Collections.EMPTY_LIST;
            Cookie e = null;
            for (int i = startingPoint; i < lines.size(); i++) {

                try {
                    e = lines.getParsed(i);
                } catch (BadDataFormatException cause) {
                    if (cookieFileConfiguration.getFailFast()) throw cause;
                    LOG.warn("SKIPPED_MALFORMED_RECORD: {}", lines.getLine(i));
                    continue;
                }

                if (!e.getTimestamp().toLocalDate().isEqual(cookieFileConfiguration.getDate())) break;
                counter.inc(e.getCookieId());

            }
            return counter.onlyMaximums();
        } catch (IOException cause) {
            throw new RuntimeException(cause);
        }
    }
}
