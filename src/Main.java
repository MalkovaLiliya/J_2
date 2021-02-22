public class Main {

    public static void main(String[] args) {
        String[][] arr = {{"98", "10", "12", "9"},
                {"154", "1", "7", "-736"},
                {"-14", "96", "1", "15"},
                {"0", "-95", "65", "2"}};

        try {
            arrayCheck(arr);
        } catch (MyArraySizeException e){
            System.out.println("Невозможно вычислить сумму массива. Неверная размерность.\n" + e);
        } catch (MyArrayDataException e){
            System.out.println("Невозможно вычислить сумму массива.\n" + e);
        }
    }

    public static void arrayCheck(String a[][]) throws MyArraySizeException, MyArrayDataException{

        if (a.length != 4) {
            throw new MyArraySizeException("Несоотвтетствие количеству строк: " + a.length);
        }

        for (int i = 0; i < a.length; i++)
        {
            if (a[i].length != 4) {
                throw new MyArraySizeException(a[i].length, i);
            }
        }

        int sum = 0;
        for (int i = 0; i < a.length; i++)
        {
            for (int j = 0; j < a[i].length; j++)
            {
                try {
                    sum = Integer.parseInt(a[i][j]) + sum;
                } catch (NumberFormatException e){
                    throw new MyArrayDataException(i, j, a[i][j]);
                }
            }
        }
        System.out.println("Сумма элементов массива = " + sum);
    }
}
