import java.io.PrintStream;
import java.util.Scanner;

//задание 1 (Создание объекта гита)
class Git {//создаем класс гит
    private String titlePoem;//вводим переменную названия стихотворения
    private String author;// вводим переменную автора
    private String currentlyVersion;// вводим переменную текущей версии
    private String previousVersion;// вводим переменную предыдущей версии

    public Git(String titlePoem, String author) {//прописываем конструктор класса
        this.titlePoem = titlePoem;
        this.author = author;
        this.currentlyVersion = "";
        this.previousVersion = "";
    }

    //задание 2 (Вывод стихотворения)
    @Override
    public String toString() {
        return "«" + titlePoem + " - " + author + "»" + "\n" + currentlyVersion; //выводим строку формата «НазваниеСтиха- ИмяПоэта»
    }

    //задание 3 (Добавление строки)
    public void addLine(String line) {
        currentlyVersion += line + "\n";// добавляем строку к последней(текущей) версии и спускает курсив на следующую строку
    }

    //задание 4 (Удаление строки по номеру)
    public void deletedLine(int line_number) {
        String[] array = currentlyVersion.split("\n");//создаем массив, который разделяем на подстроки
        if (line_number >= 1 && line_number <= array.length) {//рассматривает условия, когда существует хотя бы одна строка
            // и число строк не превышает длины массива
            String str = "";//создаем пустую строку
            for (int i = 0; i < array.length; i++) {//обращаемся к циклу
                if (i != line_number - 1) {//если текущий индекс не является индексом последнего элемента, то
                    str += array[i] + "\n";// добавляем символы новой строки
                }
            }
            currentlyVersion = str;//принимает содержимое объекта, преобразовывает в другой объект, затем
            //сохраняет полученную строку в переменную текущей версии
        } else {
            System.out.println("Неверный номер строки!");//иначе выводим ошибку
        }
    }

    //задание 5 (Вставка строки по номеру)
    public void insertedLine(int line_number, String line) {
        String[] array = currentlyVersion.split("\n");//создаем массив, который разделяем на подстроки
        if (line_number >= 1 && line_number <= array.length) {//рассматривает условия, когда существует хотя бы одна строка
            // и число строк не превышает длины массива
            String str = "";//создаем пустую строку
            for (int i = 0; i < array.length; i++) {//обращаемся к циклу
                if (i == line_number - 1) {//если текущий индекс равен индексу последнего элемента, то
                    str += line + "\n";//добавляем текст в конец строки
                }
                str += array[i] + "\n";// добавляем символы новой строки
            }
            currentlyVersion = str;//принимает содержимое объекта, преобразовывает в другой объект, затем
            //сохраняет полученную строку в переменную текущей версии
        } else {
            System.out.println("Неверный номер строки!");//иначе выводим ошибку
        }
    }

    //задание 6 (Сохранение версии)
    public void saveVersion() {
        previousVersion = currentlyVersion;//заменяем предыдущую версию на текущую

    }

    //задание 7 (Просмотр сохранений)
    public void displayVersion(int version_number) {
        if (version_number == 0) {//если номер версии соответсвует 0, то
            System.out.println("Текущая версия: \n" + currentlyVersion);//выводим текущую версию
        } else if (version_number == 1) {//если номер версии соответсвует 1, то
            System.out.println("Последнее сохранение: \n" + previousVersion);//выводим предыдущую(последнюю версию)
        } else {
            System.out.println("Неверный номер версии\n");//иначе выводим ошибку
        }
    }

    //задание 8 (Удаление сохранения)
    public void deleteSave() {
        previousVersion = "";//заменяем последнюю версию на пустую строку
    }

    //задание 9 (Откат к сохранению)
    public void rollbackToSave() {
        this.currentlyVersion = this.previousVersion;//присваивает значение параметра переменной экземпляра, которая будет новой текущей версией
        this.previousVersion = "";//заменяем последнюю версию на пустую строку
        System.out.println("Откат к сохранению: \n" + currentlyVersion);//выводим новую текущую версию
    }

    //задание 10 (Сравнение версий)
    public void versionToComparison() {
        if (this.currentlyVersion.equals(this.previousVersion))//Если текст в переменной текущей версии точно совпадает
            // с текстом в переменной предыдущей версии, то выводим необходимый результат
            System.out.println("Текущая версия и последнее сохранение совпадают");
        else
            System.out.println("Текущая версия и последнее сохранение не совпадают");
    }

    //задание 11 (Общие строки между версиями)
    public static void printCommonStrings(String currentlyVersion, String previousVersion) {
        String[] currentlyLines = currentlyVersion.split("\n");//разбивает строку текущей версии на массив строк
        String[] previousLines = previousVersion.split("\n");//разбивает строку предыдущей версии на массив строк

        boolean[] flag = new boolean[previousLines.length];//Массив используется в качестве «трекера».
        // Каждый индекс в массиве соответствует строке в массиве.

        for (int i = 0; i < currentlyLines.length; i++) {//рассматриваем цикл для текущей версии
            String string = currentlyLines[i];//строка получает текущую строку и сохраняет ее в переменной
            for (int j = 0; j < previousLines.length; j++) {//рассматриваем цикл для предыдущей версии
                if (string.equals(previousLines[j])) {//сравнение элементов
                    if(!flag[i]){// проверяет, является ли логическое значение, хранящееся в индексе массива, истинным
                        System.out.println(string);//Если условие было, то в этой строке будет выведено значение,
                        // хранящееся в переменной, в консоль
                        flag[i] = true;//После вывода переменной эта строка устанавливает
                        // соответствующий элемент по индексу в массиве в значение
                    }
                    break;
                }
            }
        }
        System.out.println();
    }
    //задание 12 (Различные строки между версиями)
    public static void printVersionDiff(String currentlyVersion, String previousVersion) {
        String[] currentlyLines = currentlyVersion.split("\n");//разбивает строку текущей версии на массив строк
        String[] previousLines = previousVersion.split("\n");//разбивает строку предыдущей версии на массив строк

        boolean[] flag = new boolean[previousLines.length];//Массив используется в качестве «трекера».
        // Каждый индекс в массиве соответствует строке в массиве.

        for (int i = 0; i < currentlyLines.length; i++) {//рассматриваем цикл для текущей версии
            String newLine = currentlyLines[i];//строка получает текущую строку и сохраняет ее в переменной
            for (int j = 0; j < previousLines.length; j++) {//рассматриваем цикл для предыдущей версии
                if (newLine.equals(previousLines[j])) {//сравнение элементов
                    flag[j] = true;// указывает на то, что строка по индексу массива была найдена в массиве.
                    break;
                }
            }
        }

        for (int i = 0; i < previousLines.length; i++) {//рассматриваем цикл для предыдущей версии
            if (!flag[i]) {// проверяет, не найдена ли в массиве текущая строка, соответствующая индексу.
                //Если строка from не найдена, то флаг, соответствующий этой строке, будет ложным
                System.out.println("- " + previousLines[i]);//Если условие истинно, то
                // эта строка выводит текущую строку из массива с префиксом "-",
                // чтобы показать, что эта строка уникальна для старой версии.
            }
        }

        for (int i = 0; i < currentlyLines.length; i++) {//рассматриваем цикл для текущей версии
            String currentLine = currentlyLines[i];//строка получает текущую строку и сохраняет ее в пер
            boolean found = false;
            for (int j = 0; j < previousLines.length; j++) {//рассматриваем цикл для предыдущей версии
                if (currentLine.equals(previousLines[j])) {//сравнение элементов
                    found = true;// указывает на то, что строка по индексу массива была найдена в массиве.
                    break;
                }
            }
            if (!found) {//проверяет, не было ли найдено то, что вы искали, в предыдущих итерациях цикла
                System.out.println("+ " + currentLine);//Если условие истинно, то
                // эта строка выводит текущую строку из массива с префиксом "+",
                // чтобы показать, что эта строка уникальна для новой(текущей) версии.
            }
        }
    }

    public String getCurrentlyVersion() {
        return currentlyVersion;//возвращает значения переменной текущей версии
    }

    public String getPreviousVersion() {
        return previousVersion;//возвращает значения переменной предыдущей версии
    }
}

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args){
        // Создание экземпляра класса Git с русским стихотворением
        Git git = new Git("Название", "Автор");

        // Вывод стихотворения
        System.out.println(git);

        // Добавляем строки в стихотворение
        git.addLine("Строка 1");
        git.addLine("Строка 2");
        git.addLine("Строка 3");

        // Выводим текущую версию стихотворения
        System.out.println("Текущая версия стихотворения:");
        System.out.println(git);

        // Сохраняем текущую версию
        git.saveVersion();

        // Добавляем ещё одну строку
        git.addLine("Строка 4");

        // Выводим обновлённую версию
        System.out.println("\nОбновлённая версия стихотворения:");
        System.out.println(git);

        // Выводим сохраненную и текущую версию
        git.displayVersion(0);
        git.displayVersion(1);


        // Удаляем строку
        git.deletedLine(2); // Удаляем вторую строку

        // Выводим версию после удаления
        System.out.println("\nПосле удаления второй строки:");
        System.out.println(git);

        // Вставляем строку на 2-е место
        git.insertedLine(2, "Строка 2 новая");

        // Выводим версию после вставки
        System.out.println("\nПосле вставки строки на второе место:");
        System.out.println(git);

        // Просмотр сохранений
        //System.out.println("\nПросмотр сохранений:");
        git.displayVersion(1);


        // Сравнение версий
        System.out.println("\nСравнение текущей и предыдущей версии:");
        git.versionToComparison();

        // Различия между версиями
        System.out.println("\nРазличия между версиями:");
        Git.printVersionDiff(git.getCurrentlyVersion(), git.getPreviousVersion());

        // Общие строки между версиями
        System.out.println("\nОбщие строки между версиями:");
        Git.printCommonStrings(git.getCurrentlyVersion(), git.getPreviousVersion());

        // Откат к предыдущей версии
        git.rollbackToSave();

        // Выводим сохраненную и текущую версию
        git.displayVersion(0);
        git.displayVersion(1);

    }
}

