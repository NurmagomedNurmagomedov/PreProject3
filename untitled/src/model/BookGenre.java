package model;

import java.util.List;

public class BookGenre {
    int count;
    double avg;
    List<String> listBookReit;

    public BookGenre() {
    }
    public BookGenre(int count, double avg, List<String> listBookReit) {
        this.count = count;
        this.avg = avg;
        this.listBookReit = listBookReit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public List<String> getListBookReit() {
        return listBookReit;
    }

    public void setListBookReit(List<String> listBookReit) {
        this.listBookReit = listBookReit;
    }

    @Override
    public String toString() {
        return "count=" + count +
                ", avg=" + avg +
                ", listBookReit=" + listBookReit +
                '}';
    }
}
