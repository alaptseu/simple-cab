package com.datarepublic.simplecab;

import com.datarepublic.simplecab.service.SimpleCabService;
import com.datarepublic.simplecab.service.SimpleCabServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.stream.Collectors.toSet;

public class Client {

    private static final String DEFAULT_ENDPOINT = "http://localhost:8080/cabtrip";

    public static void main(String[] args) throws IOException, ParseException {
        SimpleCabService service = new SimpleCabServiceImpl(DEFAULT_ENDPOINT);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            out.println();
            out.println("print command....");
            String line = br.readLine();
            if (line.startsWith("-exit")) {
                break;
            } else if (line.startsWith("-flush")) {
                service.deleteCache();
            } else if (line.startsWith("-init")) {
                StringTokenizer st = new StringTokenizer(line);
                String url = null;
                while (st.hasMoreTokens()) {
                    String token = st.nextToken();
                    if (token.startsWith("-init")) {
                        continue;
                    }
                    url = token.trim();
                }
                if (url == null || url.equals("")) {
                    throw new IllegalArgumentException("Must be 2 arguments");
                }
                service = new SimpleCabServiceImpl(url);
                out.println(format("Using %s", url));
            } else if (line.startsWith("-info")) {
                Map<String, Set<String>> arguments = parseInfoParams(line);
                if (arguments.get("date") == null || arguments.get("date").size() == 0) {
                    throw new IllegalArgumentException("date value is mandatory");
                }
                if (arguments.get("medallion") == null || arguments.get("medallion").size() == 0) {
                    throw new IllegalArgumentException("medallion value is mandatory");
                }
                boolean ignoreCache = (arguments.get("ignoreCache") != null && arguments.get("ignoreCache").size() != 0) ?
                    Boolean.valueOf(arguments.get("ignoreCache").iterator().next()) : false;
                service.getMedallionsSummary(arguments.get("date").iterator().next(),
                    ignoreCache, arguments.get("medallion"));
            } else {
                out.println("Unknown command");
            }

        }
    }

    private static Map<String, Set<String>> parseInfoParams(final String line) {
        StringTokenizer st = new StringTokenizer(line);
        boolean medallionFound = false;
        boolean dateFound = false;
        Map<String, Set<String>> arguments = new HashMap<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.startsWith("-info")) {
                continue;
            }
            if (token.equals("=")){
                continue;
            }
            if (token.startsWith("date")) {
                medallionFound = false;
                dateFound = true;
                if (token.split("=").length > 1) {
                    arguments.put("date", Stream.of(token.split("=")[1]).collect(toSet()));
                    continue;
                }
                arguments.put("date", new HashSet<>());
                continue;
            }
            if (token.startsWith("medallion")) {
                dateFound = false;
                medallionFound = true;
                if (token.split("=").length > 1) {
                    arguments.put("medallion", Stream.of(token.split("=")[1].split(",")).collect(toSet()));
                    continue;
                }
                arguments.put("medallion", new HashSet<>());
                continue;
            }
            if (token.startsWith("ignoreCache")) {
                dateFound = false;
                medallionFound = false;
                if (token.split("=").length > 1) {
                    arguments.put("ignoreCache", Stream.of(token.split("=")[1]).collect(toSet()));
                    continue;
                }
                arguments.put("ignoreCache", new HashSet<>());
                continue;
            }
            if (medallionFound) {
                arguments.get("medallion").addAll(Stream.of(token.split(",")).collect(toSet()));
            }
            if (dateFound && arguments.get("date").size() == 0){
                arguments.get("date").add(token);
            }
        }
        return arguments;
    }

}
