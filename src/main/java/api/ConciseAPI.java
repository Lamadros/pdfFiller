package api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConciseAPI {
    private static Map<String, String> header = new HashMap<>();

    public Map<String, String> getHeaders() {
        return header;
    }

    public static void setHeader(String name, String value) {
        header.put(name, value);
    }

    protected List<String> regexName(List<String> name) {
        for (int i = 0; i < name.size(); i++) {
            name.set(i, name.get(i).replaceAll("\\.[^.]*$", ""));
        }
        return name;
    }






}
