package ru.r5am;

/**
 * Created by Zoer on 19.12.2016.
 * Пример реализации паттерна Singleton
 * <p>
 * - В системе должно существовать не более одного экземпляра заданного класса.
 * - Экземпляр должен быть легко доступен для всех клиентов данного класса.
 * - Создание объекта "on demand", то есть, когда он понадобится первый раз, а не во время инициализации системы.
 */

class SingletonUse {
    public static void main(String[] args) {

        /**
         * Синглтон1
         */
        // Первый экземпляр Синглтона1
        Singleton1 firstInstanceSingleton1 = Singleton1.getInstance();
        System.out.println(firstInstanceSingleton1.toString());

        // Второй экземпляр Синглтона1
        // Раскомментировать - ошибка, второй экземпляр.
//        Singleton1 secondInstanceSingleton1 = Singleton1.getInstance();
//        System.out.println(secondInstanceSingleton1.toString());

        /**
         * Синглтон2
         */
        // Первый экземпляр Синглтона2
        Singleton2 firstInstanceSingleton2 = Singleton2.getInstance();
        System.out.println(firstInstanceSingleton2.toString());

        // Второй экземпляр Синглтона2
        // Раскомментировать - получим тот же первый объект.
        Singleton2 secondInstanceSingleton2 = Singleton2.getInstance();
        System.out.println(secondInstanceSingleton2.toString());

        /**
         * Синглтон3
         */
        // Первый экземпляр Синглтона3
        Singleton3 firstInstanceSingleton3 = Singleton3.getInstance();
        System.out.println(firstInstanceSingleton3.toString());

        // Второй экземпляр Синглтона3
        // Раскомментировать - ошибка, второй экземпляр.
//        Singleton3 secondInstanceSingleton3 = Singleton3.getInstance();
//        System.out.println(secondInstanceSingleton3.toString());

        /**
         * Синглтон4
         */
        // Первый экземпляр Синглтона4
        Singleton4 firstInstanceSingleton4 = Singleton4.getInstance();
        System.out.println(firstInstanceSingleton4.toString());

        // Второй экземпляр Синглтона4
        // Раскомментировать - получим тот же первый объект.
        Singleton4 secondInstanceSingleton4 = Singleton4.getInstance();
        System.out.println(secondInstanceSingleton4.toString());
    }


    /**
     * Исключительно для однопоточных приложений
     */
    private static class Singleton1 {
        private static Singleton1 instance;

        private Singleton1() {
        }

        public static Singleton1 getInstance() {
            if (instance == null) {
                instance = new Singleton1();
            } else {
                System.out.println("Error: Second Singleton!");
                System.exit(1);
            }
            return instance;
        }
    }

    /**
     * Initialization on Demand Holder от Била Пью (Bill Pugh)
     * Присутствует проблема с обработкой исключительных ситуаций в конструкторе.
     * Если конструктор класса не вызывает опасений создания исключительных ситуаций,
     * то можно использовать этот метод.
     */
    private static class Singleton2 {
        private Singleton2() {
        }

        private static class SingletonHolder {
            private final static Singleton2 instance = new Singleton2();
        }

        static Singleton2 getInstance() {
            return SingletonHolder.instance;
        }
    }

    /**
     * Недостаток - синхронизация полезна только один раз, при первом обращении к getInstance(), после этого каждый раз,
     * при обращении к этому методу, синхронизация просто забирает время.
     * Если вызов getInstance() не происходит достаточно часто (что значит «достаточно часто» решать вам),
     * то этот метод имеет преимущество перед остальными – прост, понятен, лениво инициализируется, дает возможность
     * обрабатывать исключительные ситуации в конструкторе. А во-вторых, синхронизация в Java перестала быть
     * обременительно медленной настолько, насколько её боятся.
     */
    private static class Singleton3 {
        private static Singleton3 instance;

        private Singleton3() {
        }

        public static synchronized Singleton3 getInstance() {
            if (instance == null) {
                instance = new Singleton3();
            } else {
                System.out.println("Error: Second Singleton!");
                System.exit(1);
            }
            return instance;
        }
    }

    /**
     * С использованием "Double-Checked Locking" и модификатором "volatile" - решает проблему отбирания
     * времени синхронизацией.
     * Но использование "volatile" модификатора может привести к проблемам производительности
     * на мультипроцессорных системах.
     */
    private static class Singleton4 {
        private static volatile Singleton4 instance;

        private Singleton4() {
        }

        public static Singleton4 getInstance() {
            if (instance == null)
                synchronized (Singleton4.class) {
                    if (instance == null)
                        instance = new Singleton4();
                }
            return instance;
        }
    }

}   // End SingletonUse