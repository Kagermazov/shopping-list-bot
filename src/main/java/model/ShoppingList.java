package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ShoppingList {

    private Long id;
    private Long ownerId;
    private String title;
    private List<ShoppingItem> items;
    private LocalDateTime createdAt;

    public ShoppingList() {
    }

    public ShoppingList(Long id, Long ownerId, String title, List<ShoppingItem> items, LocalDateTime createdAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.items = items;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ShoppingItem> getItems() {
        return items;
    }

    public void setItems(List<ShoppingItem> items) {
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(id, that.id) && Objects.equals(ownerId, that.ownerId) && Objects.equals(title, that.title) && Objects.equals(items, that.items) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, title, items, createdAt);
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", title='" + title + '\'' +
                ", items=" + items +
                ", createdAt=" + createdAt +
                '}';
    }
}
