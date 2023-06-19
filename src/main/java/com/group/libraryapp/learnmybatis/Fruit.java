package com.group.libraryapp.learnmybatis;

public class Fruit {
    private Long id;
    private String name;
    private int price;

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Fruit(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    protected Fruit() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
