### Обзор

Данная библиотека позволяет выполнять рачёты для производственной трубчатой печи.
А так же расчёты для различных произвольных формул.

### Описание

В нашей библиотеке есть 3 основных класса для расчётов.
В них содержатся статические методы, которые позволяют выполнять расчёты.

Список классов:
1) **ThermalLoad.java** - Расчёт полезной тепловой нагрузки
2) **CombustionProcess.java** - Расчёт процессов горения
3) **FurnaceEfficiency.java** - Расчёт кпд печи

Для подсчёта значений **произвольных** формул нужно использовать класс **Calculator.java**

### Как использовать

##### Расчёты печи

Пример использования расчётов для производственной трубчатой печи.
Польное описание всех методов можно увидеть в javadoc

```java
public class Main() {
    public static void main(String[] args) {
        Double temp = 375.0;
        Double density = 0.921;
        
        //Удельное теплосодержание для нефти
        Double productHeatContent = ThermalLoad.productHeatContent(temp, density);
        
        System.out.println("Удельное теплосодержание для нефти: " + productHeatContent);   
    }
}
```

```
Удельное теплосодержание для нефти: 906.9404573059434
```

##### Расчёты для произвольных формул

Пример использования расчётов для произвольных формул.

Подробнее про возможности формул можно прочитать здесь - https://github.com/fathzer/javaluator
```java
public class Main() {
    public static void main(String[] args) {
        Double carbonContent = 75.0;
        Double hydrogenContent = 10.0;
        Double sulfurContent = 7.5;
        Double oxygenContent = 7.5;
        
        Double result = Calculator.build("(2.67 * C + 8 * H + S - O) / 23.2",
                Variable.build("C", carbonContent),
                Variable.build("H", hydrogenContent),
                Variable.build("S", sulfurContent),
                Variable.build("O", oxygenContent)
        ).getValue();

        System.out.println("Результат формулы: " + result);
    }
}
```

```
Результат формулы: 40875.0
```

#### Документация - javadoc

Для получения документации выполните команду.

```
mvn javadoc:javadoc
```

Просмотреть её можно в папке /target/site/apidocs/index.html.

#### Сборка проекта

Чтобы собрать проект выполните 

```
mvn clean install
```
