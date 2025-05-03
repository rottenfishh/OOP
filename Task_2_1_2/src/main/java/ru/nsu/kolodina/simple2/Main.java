package ru.nsu.kolodina.simple2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server(4444, 3);

        WorkerPool pool = new WorkerPool();
        pool.runWorkers(3);

        int[] arr = new int[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        boolean res = server.runServer(arr);
        System.out.println(res);
    }
}
// клиент в отдельный класс(просто отдельная джарка которая запускается и подключается к серверу)
// клиент уведомлял что еще жив
// и как то распределять нагрузку
// переподключение клиента
// на больших тестовых данных(большой нагрузке)
// графики
// потестить на виртуалке в облаке