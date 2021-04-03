import javax.sound.midi.Track;
import java.util.Arrays;

public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public static void main(String[] args) {
        Main main = new Main();
        main.metod1();
        main.metod2();

    }

    public void calculate(float [] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sinh(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public void metod1(){
        float [] arr = new float[SIZE];

        long a = System.currentTimeMillis();

        Arrays.fill(arr, 1);
        System.out.println(a + " время заполнения единицами");
        calculate(arr);
        System.out.println(System.currentTimeMillis() - a + " время вычислений для элементов");
        System.out.println(System.currentTimeMillis() + " выход из метода");
    }

    public void metod2(){
        System.out.println("==========================2 метод==========================");
        float [] arr = new float[SIZE];
        Arrays.fill(arr,1);

        float [] arr1 = new float[HALF];
        float [] arr2 = new float[HALF];
        long b = System.currentTimeMillis();

        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);

        Thread ar1 = new Thread(new Runnable(){
            @Override
            public void run(){
                calculate(arr1);
                System.out.println(System.currentTimeMillis() - b + " время вычислений для элементов");
            }
        });
        ar1.start();

        Thread ar2 = new Thread(new Runnable(){
            @Override
            public void run(){
                calculate(arr2);
                System.out.println(System.currentTimeMillis() - b + " время вычислений для элементов");
            }
        });
        ar2.start();

        System.arraycopy(arr1,0, arr, 0, HALF);
        System.arraycopy(arr2,0, arr, HALF, HALF);
        System.out.println(System.currentTimeMillis() + " выход из метода");
        }

    }


