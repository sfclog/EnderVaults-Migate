package me.sfclog.smalladdon.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnderVaultInventoryBuilder {

    public List<ItemStack> items;
    public Inventory inv;

    public EnderVaultInventoryBuilder() {
        this.inv = Bukkit.createInventory(null,54,"");
        this.items = new ArrayList<>();
    }
    public boolean add(ItemStack item) {
        if(!(this.items.size() >= 54)) {
            this.items.add(item);
            return true;
        }
        return false;
    }

    public Inventory get() {
        for(ItemStack item : items) {
            if(item != null) {
                this.inv.addItem(item);
            }
        }
        return this.inv;
    }

}
