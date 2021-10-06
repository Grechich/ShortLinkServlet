package ua.kiev.prog.ShortLinkServlet;

import java.util.*;

public class UrlDatabase {

    public final static UrlDatabase INSTANCE = new UrlDatabase();
    private final Map<String, UrlRecord> db = new HashMap<>();
    private final Map<String, String> dbLongUrl = new HashMap<>();

    private String randomString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    private UrlDatabase() {
    }

    public synchronized String save(String url) {
        UrlRecord value = new UrlRecord();
        value.setUrl(url);
        if (dbLongUrl.get(value.getUrl()) == null) {
            String key = randomString();
            db.put(key, value);
            dbLongUrl.put(value.getUrl(), key);
            return key;
        }else {
            return dbLongUrl.get(value.getUrl());
        }
    }

    public synchronized String get(String shortUrl) {
        UrlRecord value = db.get(shortUrl);
        value.inc();
        return value.getUrl();
    }

    public synchronized Collection<UrlRecord> getStats() {
        return db.values();
    }

    static public class UrlRecord {
        String url;
        long counter;

        public void inc() {
            counter++;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getCounter() {
            return counter;
        }

        public void setCounter(long counter) {
            this.counter = counter;
        }
    }
}
