package me.sfclog.smalladdon.util;

import com.github.dig.endervaults.api.VaultPluginProvider;
import com.github.dig.endervaults.api.storage.DataStorage;
import com.github.dig.endervaults.api.vault.metadata.VaultDefaultMetadata;
import com.github.dig.endervaults.bukkit.EVBukkitPlugin;
import com.github.dig.endervaults.bukkit.vault.BukkitVault;
import me.sfclog.smalladdon.Main;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.io.IOException;
import java.util.*;

public class EnderVaultAPI {

    public static EVBukkitPlugin plugin = (EVBukkitPlugin) VaultPluginProvider.getPlugin();
    public static DataStorage dataStorage = plugin.getDataStorage();


    public static void migate_endervault(UUID uuid, List<ItemStack> items) {
        List<Inventory> list = new ArrayList<>();
        if(items.size() <= 54) {
            EnderVaultInventoryBuilder builder = new EnderVaultInventoryBuilder();
            for(ItemStack it : items) {
                if(it != null) {
                   builder.add(it);
                }
            }
            list.add(builder.get());
        } else {
            EnderVaultInventoryBuilder builder = new EnderVaultInventoryBuilder();
            for (ItemStack it : items) {
                if (it != null) {
                    if (!builder.add(it)) {
                        list.add(builder.get());
                        builder = new EnderVaultInventoryBuilder();
                        builder.add(it);
                    }
                }
            }
        }
        if(!list.isEmpty()) {
        Main.sendlog("[Migate-Inventory] ["+uuid+"] Load " + list.size() + " Vault !");
        CountData data = new CountData();
        for(Inventory inv : list) {
            if (inv != null) {
                    BukkitVault vault = new BukkitVault(UUID.randomUUID(), uuid, inv, new HashMap<String, Object>(){{
                     put(VaultDefaultMetadata.ORDER.getKey(), data.get());
                     }});
                    try {
                        dataStorage.save(vault);
                        Main.sendlog("[Migate-Inventory] ["+uuid+"] Save Vault !");
                        data.up();
                    } catch (IOException iOException) {
                        iOException.printStackTrace();
                  }
            }
        }
        } else {
            Main.sendlog("[Migate-Inventory] Inventory Empty !");
        }
    }
}
