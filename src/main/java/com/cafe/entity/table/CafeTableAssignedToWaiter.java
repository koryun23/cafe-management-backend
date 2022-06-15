package com.cafe.entity.table;

import com.cafe.entity.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CAFE_TABLE_WAITER")
public class CafeTableAssignedToWaiter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAFE_TABLE_WAITER_ID_SEQUENCE")
    @SequenceGenerator(name = "CAFE_TABLE_WAITER_ID_SEQUENCE")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "cafe_table_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_CAFE_TABLE_ID_CAFE_TABLE_ID"),
            nullable = false
    )
    private CafeTable cafeTable;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "waiter_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "FK_WAITER_ID_USER_ID"),
            nullable = false
    )
    private User waiter;

    public CafeTableAssignedToWaiter(CafeTable cafeTable, User waiter) {
        this.cafeTable = cafeTable;
        this.waiter = waiter;
    }

    public CafeTableAssignedToWaiter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CafeTable getCafeTable() {
        return cafeTable;
    }

    public void setCafeTable(CafeTable cafeTable) {
        this.cafeTable = cafeTable;
    }

    public User getWaiter() {
        return waiter;
    }

    public void setWaiter(User waiter) {
        this.waiter = waiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTableAssignedToWaiter that = (CafeTableAssignedToWaiter) o;
        return Objects.equals(id, that.id) && Objects.equals(cafeTable, that.cafeTable) && Objects.equals(waiter, that.waiter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cafeTable, waiter);
    }

    @Override
    public String toString() {
        return "CafeTableWaiter{" +
                "id=" + id +
                ", cafeTable=" + cafeTable +
                ", waiter=" + waiter +
                '}';
    }
}
