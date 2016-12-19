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

        // Первый экземпляр Синглтона1
        Singleton1 firstInstance = Singleton1.getInstance();
        System.out.println(firstInstance.toString());

        // Второй экземпляр Синглтона1
//        Singleton1 secondInstance = Singleton1.getInstance();
//        System.out.println(secondInstance.toString());
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

        public static Singleton2 getInstance() {
            return SingletonHolder.instance;
        }
    }


}   // End SingletonUse