package Business;

import Persistence.ItemDao;

import java.util.ArrayList;

public class ItemManager {

    public ArrayList<String> listItems(){
        ItemDao itemDao = new ItemDao();
        ArrayList<String> listItemsName = itemDao.getAllItemsName();
        return listItemsName;
    }

    public Item listItemAttribute(int index) {
        Item item;
        ItemDao itemDao = new ItemDao();
        item = itemDao.getItem(index);

        return item;
    }
}
