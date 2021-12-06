package test.java;

import main.java.data.Item;
import main.java.data.Warehouse;
import main.java.data.WarehouseRepository;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        Warehouse one = new Warehouse(1);
        Warehouse two = new Warehouse(2);
        one.addItem(new Item("brand new", "keyboard", 0, new Date()));
        System.out.println("should result 1 : ");
        System.out.println(one.occupancy());
        System.out.println("should result 0 : ");
        System.out.println(two.occupancy());

        for (Warehouse warehouse : WarehouseRepository.WAREHOUSE_LIST) {
            System.out.println(warehouse.toString());
        }
    }

}

