package MainWindows;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GenerateBilling {

    private static Map<String, Integer> dailyCountMap = new HashMap<>();

    public static String generateORNumber(int userId) {
        String currentDate = getCurrentDate();
        int count = getDailyCount(currentDate);

        String orNumber = currentDate + "-" + userId + "-" + String.format("%05d", count + 1);
        incrementDailyCount(currentDate);

        return orNumber;
    }

    private static String getCurrentDate() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(currentDate);
    }

    private static int getDailyCount(String currentDate) {
        if (dailyCountMap.containsKey(currentDate)) {
            return dailyCountMap.get(currentDate);
        } else {
            return 0;
        }
    }

    private static void incrementDailyCount(String currentDate) {
        int count = getDailyCount(currentDate);
        dailyCountMap.put(currentDate, count + 1);
    }

    public static void main(String[] args) {
        // Example usage
        int userId = 123;
        String orNumber = generateORNumber(userId);
        System.out.println("Generated OR Number: " + orNumber);
    }
}
