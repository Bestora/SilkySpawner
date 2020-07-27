package de.bestora.silkyspawners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockBreakEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (!block.getType().equals(Material.SPAWNER))
            return;


        ItemStack tool = player.getItemInHand();

        if (!tool.containsEnchantment(Enchantment.SILK_TOUCH))
            return;


        final CreatureSpawner spawner = (CreatureSpawner) block.getState();

        ItemStack item = new ItemStack(Material.SPAWNER, 1);
        ItemMeta itemMeta = item.getItemMeta();
        BlockStateMeta blockStateMeta = (BlockStateMeta) itemMeta;

        //player.sendMessage(String.format("Du hast einen Spawner des Typen '%s' erhalten", spawner.getCreatureTypeName()));

        blockStateMeta.setBlockState(spawner);

        blockStateMeta.setDisplayName(String.format("%s Spawner", spawner.getCreatureTypeName()));

        item.setItemMeta(blockStateMeta);

        // https://www.spigotmc.org/resources/nbt-api.7939/
        /*
        NBTItem nbti = new NBTItem(item);
        nbti.mergeCompound(new NBTContainer("{BlockEntityTag:{SpawnData:{id:\""+spawner.getCreatureTypeName()+"\"}}}"));

        item = nbti.getItem();
        */

        World world = player.getWorld();
        world.dropItemNaturally(block.getLocation(), item);
    }
}
