package yandex.enrollment.disk.service;

import org.springframework.stereotype.Service;
import yandex.enrollment.disk.models.Item;
import yandex.enrollment.disk.models.ItemType;
import yandex.enrollment.disk.repository.ItemRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemService implements ItemServiceInterface {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public boolean create(ArrayList<Item> items, Date updateDate) {
        List<String> idRepeats = new ArrayList<>();
        for(Item item : items){
            if (!checkItem(item)) {
                return false;
            }

            if(idRepeats.contains(item.getId())){
                return false;
            } else {
                idRepeats.add(item.getId());
            }
            item.setDate(updateDate);
        }

        itemRepository.saveAll(items);

        for(Item item : items) {
            if (item.getParentId() != null) {
                Item newItem = itemRepository.getOne(item.getParentId());
                if(item.getSize() != null){
                    if(newItem.getSize()!=null){
                        newItem.setSize(newItem.getSize() + item.getSize());
                    } else{
                        newItem.setSize(item.getSize());
                    }
                }
                itemRepository.save(newItem);
            }
        }

        return true;
    }

    private boolean checkItem(Item item){
        if(item.getId() == null){
            return false;
        }

        if(item.getParentId() != null && !itemRepository.existsById(item.getParentId())){
            return false;
        }

        if(item.getParentId() != null && itemRepository.getOne(item.getParentId()).getType() == ItemType.FILE){
            return false;
        }

        if(item.getUrl() != null && item.getType() == ItemType.FOLDER){
            return false;
        }

        if(item.getUrl() != null && item.getUrl().length() > 256) {
            return false;
        }

        if(item.getSize() != null && item.getSize() <= 0){
            return false;
        }

        if(item.getSize() != null && item.getType() == ItemType.FOLDER){
            return false;
        }

        return true;
    }

    @Override
    public Item get(String id) {
        if(!itemRepository.existsById(id)){
            return null;
        }
        return itemRepository.getReferenceById(id);
    }

    @Override
    public boolean delete(String id) {
        if(itemRepository.existsById(id)){
            deleteChildren(id);
            itemRepository.deleteById(id);

            return true;
        }

        return false;
    }

    private void deleteChildren(String parentId){
        for(Item item : itemRepository.findAll()){
            if(item.getParentId() != null && item.getParentId().equals(parentId)){
                deleteChildren(item.getId());
                itemRepository.deleteById(item.getId());
            }
        }
    }
}

