package com.quantcast.test;

import com.quantcast.configuration.CookieFileConfiguration;
import com.quantcast.model.Cookie;
import com.quantcast.strategy.CountingSimply;
import com.quantcast.strategy.CountingStrategy;
import com.quantcast.strategy.Loader;
import org.apache.commons.csv.CSVFormat;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class CountingTest {

    DataSetDynamic dataset = new DataSetDynamic();
    CookieFileConfiguration cookieFileConfiguration ;
    List<Map<String, OffsetDateTime>> la;
    @Test
    public void collectAll() throws IOException {

        DataSetDynamic.GeneratedSet gs =
                dataset.generate(6, 4, 30, 1, 20, 10, 100);

        File file = dataset.toTempFile(gs);

        cookieFileConfiguration = new CookieFileConfiguration(file.toPath(),LocalDate.now(), CountingStrategy.Type.SIMPLE, false,false,dataset.parser);

        la =
                Loader.lazy(file, CSVFormat.DEFAULT).map(dataset.parser::parseCsvRecord).collect();

        List<Cookie> lb =
                Loader.allSortedLinesDesc(cookieFileConfiguration).iterableOverAll().collect();

        la.stream().forEach(x-> x.entrySet().forEach(System.out::println));
        System.out.println("**********************************************");
        lb.stream().forEach(System.out::println);

       // assertThat(lb).containsExactlyInAnyOrderElementsOf(la);


    }

    @Test
    public void countOneDay() throws IOException {

        DataSetDynamic.GeneratedSet gs =
                dataset.generate(6, 4, 30, 1, 20, 10, 100);

        File file = dataset.toTempFile(gs);

        cookieFileConfiguration = new CookieFileConfiguration(file.toPath(),LocalDate.of(2020,4,02), CountingStrategy.Type.SIMPLE, false,false,dataset.parser);

        la =
                Loader.lazy(file, CSVFormat.DEFAULT).map(dataset.parser::parseCsvRecord).collect();
        List<String> lc =
                new CountingSimply(cookieFileConfiguration)
                .apply();

      //  System.out.println(lc);
        la.stream().forEach(x-> x.entrySet().forEach(System.out::println));
        System.out.println("**********************************************");
        lc.stream().forEach(System.out::println);
      // assertThat(lc).containsExactlyInAnyOrderElementsOf(la);
    }

   /* @Test
    public void countLazyHandlingBadData() throws IOException {

        DataSetDynamic.GeneratedSet gs =
                dataset.generate(6, 4, 30, 1, 20, 10, 100);

        File file = dataset.toTempFile(gs, true);


        assertThatExceptionOfType(BadDataFormatException.class)
                .isThrownBy(() -> {
                    new CountingWithLazyIteration(CSVFormat.DEFAULT, dataset.parser, true)
                            .apply(file, dataset.dt(6, 20));
                });

        assertThatNoException()
                .isThrownBy(() -> {
                    new CountingWithLazyIteration(CSVFormat.DEFAULT, dataset.parser, false)
                            .apply(file, dataset.dt(6, 20));
                });
    }

    @Test
    public void countSimplyNotHandlingBadData() throws IOException {

        io.github.sameei.interviews.quantcast.codingexercise.DataSetDynamic.GeneratedSet gs =
                dataset.generate(6, 4, 30, 1, 20, 10, 100);

        File file = dataset.toTempFile(gs, true);


        assertThatExceptionOfType(BadDataFormatException.class)
                .isThrownBy(() -> {
                    new CountingSimply(dataset.parser, false, true)
                            .apply(file, dataset.dt(6, 20));
                });

        assertThatExceptionOfType(BadDataFormatException.class)
                .isThrownBy(() -> {
                    new CountingSimply(dataset.parser, false, false)
                            .apply(file, dataset.dt(6, 20));
                });
    }

    @Test
    public void countSortedLinesDescNotHandlingBadData() throws IOException {

        io.github.sameei.interviews.quantcast.codingexercise.DataSetDynamic.GeneratedSet gs =
                dataset.generate(6, 4, 30, 1, 20, 10, 100);

        File file = dataset.toTempFile(gs, true);


        assertThatExceptionOfType(BadDataFormatException.class)
                .isThrownBy(() -> {
                    new CountingWithSortedLinesDesc(dataset.parser, false)
                            .apply(file, dataset.dt(6, 20));
                });

        assertThatExceptionOfType(BadDataFormatException.class)
                .isThrownBy(() -> {
                    new CountingWithSortedLinesDesc(dataset.parser, false)
                            .apply(file, dataset.dt(6, 20));
                });
    }*/

}
