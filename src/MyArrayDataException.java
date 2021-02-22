public class MyArrayDataException extends NumberFormatException{
    int i;
    int j;
    String symbol;

    public MyArrayDataException(int i, int j, String symbol) {
        this.i = i;
        this.j = j;
        System.out.println("Не удалось преобразовать в число символ: " + symbol + " в ячейке: [" + i + "]" + "[" + j + "]");
    }

    public MyArrayDataException(String s, int i, int j, String symbol) {
        super(s);
        this.i = i;
        this.j = j;
        this.symbol = symbol;
    }
}
