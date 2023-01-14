import java.util.*;

public class Main {
    static List<String> booksList = new ArrayList<>(List.of("Поднятая целина",
            "Как закалялась сталь",
            "Чук и Гек",
            "Мастер и Маргарита",
            "Двенадцать стульев",
            "Золотой теленок",
            "Приключения Шерлока Холмса",
            "Старик и море",
            "Дети капитана Гранта",
            "1984",
            "яяяяя - тестовая книга - должна быть последней на полке"));

    public static void main(String[] args) {
        System.out.println(returnSortedBooksList(booksList));
    }

    public static Map returnSortedBooksList(List booksList) {
        //Сразу отсортируем по алфавиту (по возрастанию) названия книг
        LinkedList<String> sortedList = new LinkedList<>(new TreeSet<String>(booksList));
        //Найдем количество книг, которые будем класть на полку
        int bookShelfCounter = 5; //количество полок
        int minBookShelfCapacity = sortedList.size()/bookShelfCounter; //минимальное количество книг на полке
        int maxBookShelfCapacity = minBookShelfCapacity + 1; //максимальное количество книг на полке
        //Как представить эти полки?
        //Попробуем через Map
        //Здесь ключ - номер полки, значение - список книг
        HashMap<Integer, LinkedList<String>> bookCase = new HashMap<>();
        //Установим начальные значения разных параметров
        int currentBookShelfCapacity = maxBookShelfCapacity; //текущая емкость полки
        int startRange = 0; //начало диапазона для копирования
        int finishRange = startRange + currentBookShelfCapacity; //окончание диапазона для копирования
        int balanceBooks = sortedList.size(); //остаток свободного места на полках
        int balanceBookShelfs = bookShelfCounter; //остаток свободных полок
        System.out.println("осталось " + balanceBooks + " книг положить на " + balanceBookShelfs + " полок");
        //Создадим буферный список книг
        LinkedList<String> bufferBookList = new LinkedList<>();
        //Цикл нужен для получения ключа для Map
        for (int i = 1; i < bookShelfCounter+1; i++) {
            //Добавляем в буферный список часть из отсортированного общего списка
            bufferBookList.addAll(sortedList.subList(startRange, finishRange));
            //Добавляем собранную полку в стеллаж
            //Здесь допущение - представим что полки собираются в стеллаж(для этого и ввели Map)
            bookCase.put(i, bufferBookList);
            //Узнаем остаток свободного места на полках
            balanceBooks = balanceBooks - currentBookShelfCapacity;
            //Узнаем количество еще не заполненных полок
            balanceBookShelfs = bookShelfCounter - i;
            System.out.println("осталось " + balanceBooks + " книг положить на " + balanceBookShelfs + " полок");
            //Делаем проверку оставшихся незаполненных полок на равенство нулю, т.к. дальше будем делать деление
            if (balanceBookShelfs == 0) {
                return bookCase;
            }
            //Если на оставшиеся полки можно в равном количестве разложить книги, то это количество и берем
            if (balanceBooks % balanceBookShelfs == 0) {
                currentBookShelfCapacity = minBookShelfCapacity;
            }
            //Обновляем начало и конец диапазона
            startRange = finishRange;
            finishRange = finishRange + currentBookShelfCapacity;
            //Обновляем буферный список
            bufferBookList = new LinkedList<>();
        }
        return bookCase;
    }
}