package org.example;

import javafx.collections.ObservableList;

public class SampleData {
    public static void fillSampleData(ObservableList<Person> backingList) {
        backingList.add(new Person("Waldo", "Soller", "random notes 1"));
        backingList.add(new Person("Herb", "Dinapoli", "random notes 2"));
        backingList.add(new Person("Shawanna", "Goehring", "random notes 3"));
        backingList.add(new Person("Flossie", "Goehring", "random notes 4"));
        backingList.add(new Person("Magdalen", "Meadors", "random notes 5"));
        backingList.add(new Person("Marylou", "Berube", "random notes 6"));
        backingList.add(new Person("Ethan", "Nieto", "random notes 7"));
        backingList.add(new Person("Elli", "Combes", "random notes 8"));
        backingList.add(new Person("Andy", "Toupin", "random notes 9"));
        backingList.add(new Person("Zenia", "Linwood", "random notes 10"));
    }
}
