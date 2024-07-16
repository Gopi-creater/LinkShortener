import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkShortener {
    private Map<String, String> urlMap;
    private String domain;

    public LinkShortener(String domain) {
        this.urlMap = new HashMap<>();
        this.domain = domain;
    }

    public String shortenURL(String longURL) {
        if (urlMap.containsValue(longURL)) {
            for (Map.Entry<String, String> entry : urlMap.entrySet()) {
                if (entry.getValue().equals(longURL)) {
                    return entry.getKey();
                }
            }
        }
        String shortURL = domain + Integer.toHexString(longURL.hashCode());
        urlMap.put(shortURL, longURL);
        return shortURL;
    }

    public String expandURL(String shortURL) {
        return urlMap.getOrDefault(shortURL, "URL not found!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkShortener shortener = new LinkShortener("http://short.url/");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.println("Enter the long URL:");
                String longURL = scanner.nextLine();
                String shortURL = shortener.shortenURL(longURL);
                System.out.println("Short URL: " + shortURL);
            } else if (choice == 2) {
                System.out.println("Enter the short URL:");
                String shortURL = scanner.nextLine();
                String longURL = shortener.expandURL(shortURL);
                System.out.println("Long URL: " + longURL);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
