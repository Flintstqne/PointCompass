package org.sarcxsm.pointcompass;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class PointCompass extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("pointCompass has started!");
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        // /compass (X,Z) - sets compass coordinates
        // /compass ["69","420"]
        // args[0] = "X"
        // args[1] = "Z"
        if(command.getName().equalsIgnoreCase("compass")){

            if(sender instanceof Player) {
                Player p = (Player) sender;
                int xCoord = Integer.parseInt(args[0]);
                int zCoord = Integer.parseInt(args[1]);
                if (args.length == 0) {
                    p.sendMessage(ChatColor.YELLOW + "Please provide the X and Z coordinates.");
                    p.sendMessage(ChatColor.YELLOW + "Example- X: 69, Z: 420.");
                }
                else if (args.length == 2) {

                    int x = xCoord;
                    int y = 100;
                    int z = zCoord;
                    World w = p.getWorld();
                    Location compassLoc = new Location(w, x, y, z); //defines new location



                    p.sendMessage(ChatColor.YELLOW + "X: " + xCoord + ", " + "Z: " + zCoord + ChatColor.AQUA + " are the coordinates you wish to go to.");
                    PlayerInventory inventory = p.getInventory();
                    ItemStack compass = new ItemStack(Material.COMPASS);
                    ItemMeta mm = compass.getItemMeta();
                    mm.setDisplayName("Pointer Compass");

                    //Lore
                    ArrayList<String> compassLore = new ArrayList<>();
                    compassLore.add(ChatColor.BLUE + "Follow the compass to the coords you requested!");
                    compassLore.add(ChatColor.YELLOW + "X: " + xCoord + ", " + "Z: " + zCoord);
                    mm.setLore(compassLore);
                    //Lore End

                    mm.addEnchant(Enchantment.DURABILITY, 10, true);
                    mm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    compass.setItemMeta(mm);
                    inventory.setItem(4, compass);
                    p.setCompassTarget(compassLoc); // Help please

                }
                else {
                    p.sendMessage(ChatColor.YELLOW + "You provided too many arguments.");
                }

            }
            }
        return true;
        }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        System.out.println("A player has joined!");
        event.setJoinMessage("Welcome to Hell!");
    }
}

