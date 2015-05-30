/**
 * Created by Alexander on 29.05.2015.
 */
class Callme2 {
    void call(String msg) { // метод принимает текст. сообщение
        System.out.print("[" + msg); //выводиться сообщение на экран

        System.out.println("]");//конец сообщения
    }
}
class Caller implements Runnable {
    String msg; //объявление переменой
    Callme2 target; //ссылка на объект Callme по имени “target”
    Thread t;//ссылка на поток
    public Caller(Callme2 targ, String s) { //конструктор
        target = targ;
        msg  =s;
        t = new Thread(this); //создаем поток
        t.start();//запуск потока
    }
    //синхронизированные вызовы call()
    public void run() {
        synchronized (target) {
            target.call(msg);
        }

    }
}
class Synch1 {
    public static void main(String args[]) {
        Callme2 target = new Callme2();
        Caller ob1 = new Caller(target, "1");//создание объекта Caller и запуск конструктора
        Caller оb2 = new Caller(target, "2");
        Caller ob3 = new Caller(target, "3");
// wait for threads to end
        try {
            ob1.t.join();
            оb2.t.join();
            ob3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Прервано");
        }
    }
} /*ответ:
     [1]
     [3]
     [2]
     а должно быть [1]
                   [2]
                   [3]
                   ?????????
                        */

