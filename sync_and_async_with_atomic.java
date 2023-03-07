package com.onix;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class Main {
/**
    private AtomicInteger atInt = new AtomicInteger(0);

    public void increment(){
        atInt.getAndIncrement();
    }
    public void reset(){
        atInt.set(0);
    }

    public int value(){
        return atInt.get();
    }
 **/

    public static void main(String[] args) {
        int b1 = fizzle(10);
        fizzle(10, b2 -> {
            System.out.println(b2);
            return null;
        });

        int c1 = boggle(10,20);
        boggle(10,20, c2 -> {
           System.out.println(c2);
           return null;
        });

    }
    private static int fizzle(int a){
        return a+1;
    }
    private static int boggle(int a, int b){
        return a+b;
    }

    private static void calculate2(int v, Function<Integer,Void> cb){
        helper = qux ->{
            if(qux<v){
                boggle(v,qux,res -> helper.apply(qux+res));
            }
            else cb.apply(qux);
            return null;
        };
        fizzle(v, helper::apply);
    }
    // fizzle_old = a -> a+1
    // fizzle_new = (a, cb) -> cb.apply(a+1)
    // BiFunction<Integer, Function<Integer, Void>, Void> calculate = (v, cb) -> {. . .}
    private static void calculate1(int v, Function<Integer,Void> cb){
        helper = qux ->{
            if(qux<v){
                boggle(v,qux,res -> helper.apply(qux+res));
            }
            else cb.apply(qux);
            return null;
        };
        fizzle(v, t -> helper.apply(t));
    }
    //AtomicInteger atomicInteger = new AtomicInteger();

    private static void fizzle(int a, Function<Integer, Void> cb){
        int res = fizzle(a);
        cb.apply(res);
    }
    private static void boggle(int a, int b, Function<Integer,Void> cb){
        cb.apply(boggle(a,b));
    }

    private static AtomicInteger res_fizzle = new AtomicInteger();
    private static AtomicInteger res_boggle = new AtomicInteger();
    private static AtomicInteger count = new AtomicInteger();
    private static Function<Integer, Void> helper;
    interface helper {
    }



    private static void calculate3(int v, Function<Integer,Void> cb){
        Runnable helper = () -> {
            if (count.get() == 1)       // Если счетчик равен 1, то есть счетчик другой операции отработал
                cb.apply(res_fizzle.get() / res_boggle.get());       //Возращаем в callback результат деления
            else {
                count.incrementAndGet();        // Иначе добавляем 1 для сигнала следующей операции
            }
        };
        fizzle(v, res -> {
            res_fizzle.set(res);    // Записываю результат fizzle
            helper.run();
            return null;
        });
        boggle(v, 1, res -> {
            res_boggle.set(res);    // Записываю результат boggle
            helper.run();
            return null;
        });
    }


    /*static class Counter_fizzle{
        private AtomicInteger atInt = new AtomicInteger(0);

        public void increment(){
            atInt.getAndIncrement();
        }

        public int value(){
            return atInt.get();
        }
    }
*/
    /*static class Counter_boogle{
        private AtomicInteger atInt = new AtomicInteger(0);

        public void increment(){
            atInt.getAndIncrement();
        }

        public int value(){
            return atInt.get();
        }
    }
*/

}
