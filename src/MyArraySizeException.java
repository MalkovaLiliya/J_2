public class MyArraySizeException extends ArrayIndexOutOfBoundsException{
    int a;
    int i;

    public MyArraySizeException(int a, int i) {
        this.a = a;
        this.i = i + 1;
        System.out.println("Несоотвтетствие количеству элементов в строке.\nВ строке: " + i + " Количество элементов: " + a);
    }

    public MyArraySizeException(String s) {
        super(s);
    }
}