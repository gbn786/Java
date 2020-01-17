

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FindManager {

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in,"UTF-8");
        BufferedReader in  = new BufferedReader(reader);
        String employee = null;
        String manager = null;

        Map<String,String> empMap = null;

        String line;
        boolean tofind = true;
        while((line = in.readLine()) != null) {
            if (!line.contains("/")) {
                break;
            }

            if (tofind) {
                employee = line.split("/")[0].trim();
                manager = line.split("/")[1].trim();
                tofind = false;
                continue;
            }

            if( null == empMap )
                empMap = new HashMap<>();

            String[] inputs = line.split("/");
            empMap.put(inputs[0].trim(), inputs[1].trim());

        }

        if( null != empMap) {
            getEmployeeLevel(employee, manager, empMap, 1, empMap.entrySet().size());
        }
        System.out.println("0");

    }

    private static void getEmployeeLevel(String employee, String manager, Map<String, String> mapEmployee, int level, int totalSize) {

        String uplevel = mapEmployee.get(employee);
        if(null != uplevel && uplevel.equalsIgnoreCase(manager)) {
            System.out.println(++level);
            System.exit(0);
        } else {
            if (level <= totalSize)
                getEmployeeLevel(uplevel, manager, mapEmployee, ++level, --totalSize);
        }
    }


}
