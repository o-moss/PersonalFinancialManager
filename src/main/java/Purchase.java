import java.time.LocalDate;

public class Purchase {
    public String title;
    public String date;
    public int sum;
//    public int day;
//    public int month;
//    public int year;

    public Purchase(String title, String date, int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
//        String[] dateParsing = date.split(".");
//        this.day = Integer.parseInt(dateParsing[2]);
//        this.month = Integer.parseInt(dateParsing[1]);
//        this.year = Integer.parseInt(dateParsing[0]);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                '}';
    }
}
