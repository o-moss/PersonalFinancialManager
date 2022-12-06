import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Statistic {

    public Map<String, String> purchaseWithCategory = new HashMap<>();
    public List<Category> categories = new ArrayList<>();
    public File file = new File("categories.csv");

    public void addPurchase(Purchase purchase) {
        if (purchaseWithCategory.isEmpty()) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            CSVParser parser = new CSVParserBuilder().withSeparator('\t').build();
            try (CSVReader reader = new CSVReaderBuilder(new FileReader("categories.csv")).withCSVParser(parser).build();) {
                List<String[]> allRows = reader.readAll();
                for (String[] row : allRows) {
                    String[] s = row[0].split("\\P{IsAlphabetic}+");
                    purchaseWithCategory.put(s[0], s[1]);
                    if (!categories.isEmpty()) {
                        for (Category cat : categories) {
                            if (cat.category.equals(s[1])) {
                                break;
                            }
                        }
                        categories.add(new Category(s[1], 0));
                    } else {
                        categories.add(new Category(s[1], 0));
                    }
                }
                categories.add(new Category("Другое", 0));
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }
        String nameCategory = purchaseWithCategory.get(purchase.title);
        if (nameCategory != null) {
            for (Category cat : categories) {
                if (cat.category.equals(nameCategory)) {
                    cat.addSum(purchase.sum);
                    break;
                }
            }
        } else {
            for (Category cat : categories) {
                if (cat.category.equals("Другое")) {
                    cat.addSum(purchase.sum);
                    break;
                }
            }
        }
    }

    public Map<String, Category> findMax() {
        Map<String, Category> result = new HashMap<>();
        Optional<Category> catMax = categories.stream().max(Category::compareTo);
        if (catMax.isPresent()) {
            Category max = catMax.get();
            result.put("maxCategory", max);
        }
        return result;
    }
}
