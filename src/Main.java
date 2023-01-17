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
        List sortedList = new ArrayList<>(booksList);
        //Сразу отсортируем по алфавиту (по возрастанию) названия книг
        Collections.sort(sortedList);
        //Найдем количество книг, которые будем класть на полку
        int bookShelfCounter = 5; //количество полок
        int minBookShelfCapacity = sortedList.size() / bookShelfCounter; //минимальное количество книг на полке
        int maxBookShelfCapacity = minBookShelfCapacity + 1; //максимальное количество книг на полке
        //Как представить эти полки?
        //Попробуем через Map
        //Здесь ключ - номер полки, значение - список книг
        HashMap<Integer, LinkedList<String>> bookCase = new HashMap<>();
        //Установим начальные значения разных параметров
        int currentBookShelfCapacity = maxBookShelfCapacity; //текущая емкость полки
        int startRange = 0; //начало диапазона для копирования
        int finishRange = startRange + currentBookShelfCapacity; //окончание диапазона для копирования
        int balanceBooks = sortedList.size(); //количество пустых полок
        int balanceBookShelfs; //количество книг, которые осталось разложить
        //Цикл нужен для получения ключа для Map
        for (int i = 1; i < bookShelfCounter + 1; i++) {
            //Книги из диапазона кладем на полку
            bookCase.put(i, new LinkedList<>(sortedList.subList(startRange, finishRange)));
            //Уменьшаем остаток неразложенных книг
            balanceBooks =  balanceBooks - currentBookShelfCapacity;
            //Уменьшаем количество пустых полок
            balanceBookShelfs = bookShelfCounter - i;
            if (balanceBookShelfs != 0) {
                //Если оставшиеся книги можно в равном количестве разложить на оставшиеся полки,
                //то меняем количество книг, выкладываемых на полку
                //например, было по 3 книги, теперь кладем по 2 книги на полку
                if (balanceBooks % balanceBookShelfs == 0) {
                    currentBookShelfCapacity = minBookShelfCapacity;
                }
                //Обновляем начало и конец диапазона
                startRange = finishRange;
                finishRange = finishRange + currentBookShelfCapacity;
            }
        }
        return bookCase;
    }
}