package yandex.enrollment.disk.service;

import yandex.enrollment.disk.models.Item;

import java.util.ArrayList;
import java.util.Date;

public interface ItemServiceInterface {

    boolean create(ArrayList<Item> items, Date updateDate);

    Item get(String id);

    boolean delete(String id);
}
