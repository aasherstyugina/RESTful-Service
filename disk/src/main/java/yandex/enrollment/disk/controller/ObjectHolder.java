package yandex.enrollment.disk.controller;

import yandex.enrollment.disk.models.Item;

import java.util.ArrayList;
import java.util.Date;

public class ObjectHolder {
    private ArrayList<Item> items;

    private Date updateDate;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
