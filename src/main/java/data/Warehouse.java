package main.java.data;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    //Properties
    private int id;
    private List<Item> stock;

    //Constructor
    public Warehouse(int warehouseId) {
        this.id = warehouseId;
        this.stock = new ArrayList<Item>();
    }

    //Empty constructor
    public Warehouse() {
    }

    public int getId() {
        return id;
    }

    //Methods
    public int occupancy() {
        return stock.size();
    }

    public void addItem(Item newItem) {
        boolean add = stock.add(newItem);
    }

    public List<Item> search(String searchTerm) {
        List<Item> result = new ArrayList<Item>();
        for (Item item : stock) {
            if (searchTerm.toLowerCase().equals(item.toString())) {
                result.add(item);

            }
        } return result;
    }

    public List<Item> getStock() {
        return this.stock;
    }
}





