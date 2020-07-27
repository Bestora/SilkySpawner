package de.bestora.silkyspawners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockPlaceEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        //Player player = event.getPlayer();

        if (!block.getType().equals(Material.SPAWNER))
            return;

        CreatureSpawner spawner = (CreatureSpawner) block.getState();

        ItemStack item = event.getItemInHand();
        ItemMeta itemMeta = item.getItemMeta();
        BlockStateMeta blockStateMeta = (BlockStateMeta) itemMeta;
        CreatureSpawner inHandSpawner = (CreatureSpawner) blockStateMeta.getBlockState();

        spawner.setSpawnedType(inHandSpawner.getSpawnedType());
        spawner.update();
    }
}
