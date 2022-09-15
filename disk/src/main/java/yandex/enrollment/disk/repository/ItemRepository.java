package yandex.enrollment.disk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yandex.enrollment.disk.models.Item;

public interface ItemRepository extends JpaRepository<Item, String> {
}