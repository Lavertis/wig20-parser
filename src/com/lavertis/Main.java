package com.lavertis;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static final Dictionary<Field, String> fields = new Hashtable<>(7);

    static {
        fields.put(Field.symbol, "[a-z]\">(.+?)<");
        fields.put(Field.name, ">(.+?)<");
        fields.put(Field.lastRate, "c[0-9]\">(.+?)<");
        fields.put(Field.changePercentage, "c[0-9]\">(.+?)<");
        fields.put(Field.changeNumeral, "c[0-9]\">(.+?)<");
        fields.put(Field.volume, "v2\">(.+?)<");
        fields.put(Field.changeDate, "t2\">(.+?)<");
    }

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://stooq.com/t/?i=532").get(); //WIG20 Stocks web page
        String[] body = doc.body().html().split("\n");
        ArrayList<Stock> list = new ArrayList<>(20);
        Pattern pattern = Pattern.compile("r_[0-9]{1,2}\">");

        for (int i = 0; i < body.length; i++)
            if (pattern.matcher(body[i]).find()) {
                String s = find(body[++i], fields.get(Field.symbol));
                String n = find(body[++i], fields.get(Field.name));
                float lr = Float.parseFloat(find(body[++i], fields.get(Field.lastRate)));
                String cp = find(body[++i], fields.get(Field.changePercentage));
                float cn = Float.parseFloat(find(body[++i], fields.get(Field.changeNumeral)));
                String v = find(body[++i], fields.get(Field.volume));
                String d = find(body[++i], fields.get(Field.changeDate));
                list.add(new Stock(s, n, lr, cp, cn, v, d));
            }

        for (Stock item : list)
            System.out.println(item);
    }

    private static String find(String line, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find())
            return "unknown";
        return matcher.group(1);
    }
}