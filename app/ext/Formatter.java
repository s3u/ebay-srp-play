
package ext;

import com.google.gson.JsonObject;
import play.templates.JavaExtensions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formatter
 *
 * @author Subbu Allamaraju
 */
public class Formatter extends JavaExtensions {
    public static String getTimeLeft(JsonObject item) {
        Pattern pattern = Pattern.compile("P(\\d+)DT(\\d+)H(\\d+)M(\\d+)S");
        String timeLeft = item.get("sellingStatus").getAsJsonArray().get(0).getAsJsonObject().get("timeLeft").getAsString();
        Matcher matcher = pattern.matcher(timeLeft);
        boolean found = matcher.find();
        assert found;

        String tl = "";

        int d = Integer.parseInt(matcher.group(1));
        int h = Integer.parseInt(matcher.group(2));
        int m = Integer.parseInt(matcher.group(3));

        if(d > 0) {
            tl = d + "d";
        }
        if(h > 0) {
            tl = tl + " " + h + "h";
        }
        if(d == 0 && m > 0) {
            tl = tl + " " + m + "m";
        }
        if(d == 0 && h == 0 && m == 0) {
            tl = "<1m";
        }

        return tl;
    }
}

