package com.quantcast.strategy;

import com.quantcast.framework.IterableRich;
import com.quantcast.model.Cookie;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class SortedLinesDesc {

    private final ArrayList<String> lines;
    private final DataParser dataParser;

    public SortedLinesDesc(ArrayList<String> lines, DataParser dataParser) {
        this.lines = lines;
        this.dataParser = dataParser;
    }



    public IterableRich<Cookie> iterableOverAll() {
        return new IterableRich<>(lines).map(dataParser::parseLine);
    }

    public IterableRich<Map<String, OffsetDateTime>> iterable(LocalDate date) {
        int startingPoint = findStartingPoint(date);
        if (startingPoint < 0) return new IterableRich(Collections.EMPTY_LIST);
        return new IterableRich(new SmartIterable(lines, dataParser, startingPoint, date));
    }

    public int findStartingPoint(LocalDate date) {
        return findStartingPoint(0, lines.size() - 1, date);
    }

    public Cookie getParsed(int i) {
        return dataParser.parseLine(lines.get(i));
    }
    public String getLine(int i) {
        return lines.get(i);
    }

    public int size() {
        return lines.size();
    }

    public int findStartingPoint(int l, int r, LocalDate date) {

        OffsetDateTime lv = dataParser.parseLine(lines.get(l)).getTimestamp().toOffsetDateTime();
        LocalDate ld = lv.toLocalDate();
        if (date.isEqual(ld)) return l;

        int diff = r - l;
        if (diff == 0) return -1;
        if (diff == 1) {
            OffsetDateTime rv = dataParser.parseLine(lines.get(r)).getTimestamp().toOffsetDateTime();
            LocalDate rd = rv.toLocalDate();
            if (date.isEqual(rd)) return r;
            return -1;
        }

        int mi = (l + r) / 2;
        OffsetDateTime mv = null;
        mv = dataParser.parseLine(lines.get(mi)).getTimestamp().toOffsetDateTime();
        LocalDate md = mv.toLocalDate();

        if (date.isBefore(md)) {
            return findStartingPoint(mi, r, date);
        }
        if (date.isAfter(md)) {
            return findStartingPoint(l, mi, date);
        }

        return findStartingPoint(l, mi, date);
    }

    public static class SmartIterator implements Iterator<Cookie> {
        private final ArrayList<String> underlay;
        private final DataParser parser;
        private final int startingPoint;
        private final LocalDate demandedDate;

        private int current;
        Cookie bufferedItem;

        public SmartIterator(ArrayList<String> underlay, DataParser parser, int startingPoint, LocalDate demandedDate) {
            this.underlay = underlay;
            this.parser = parser;
            this.startingPoint = startingPoint;
            this.demandedDate = demandedDate;
            current = startingPoint;
        }

        @Override
        public boolean hasNext() {
            if (current > underlay.size()) return false;
            bufferedItem = parser.parseLine(underlay.get(current));
            return bufferedItem.getTimestamp().toLocalDate().isEqual(demandedDate);
        }

        @Override
        public Cookie next() {
            if (bufferedItem == null) throw new IllegalStateException("End of Iteration");
            Cookie tmp = bufferedItem;
            bufferedItem = null;
            current++;
            return tmp;
        }
    }

    public class SmartIterable implements Iterable<Cookie> {
        private final ArrayList<String> underlay;
        private final DataParser parser;
        private final int startingPoint;
        private final LocalDate demandedDate;

        public SmartIterable(ArrayList<String> underlay, DataParser parser, int startingPoint, LocalDate demandedDate) {
            this.underlay = underlay;
            this.parser = parser;
            this.startingPoint = startingPoint;
            this.demandedDate = demandedDate;
        }

        @Override
        public Iterator<Cookie> iterator() {
            return new SmartIterator(underlay, parser, startingPoint, demandedDate);
        }
    }

}
