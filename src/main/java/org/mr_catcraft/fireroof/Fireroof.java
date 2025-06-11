package org.mr_catcraft.fireroof;

import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Fireroof extends JavaPlugin implements Listener {

    private int netherBurnHeight;
    private int overworldBurnHeight;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfiguration();
        getServer().getPluginManager().registerEvents(this, this);
    }

    private void loadConfiguration() {
        FileConfiguration config = getConfig();
        netherBurnHeight = config.getInt("nether-burn-height", 128);
        overworldBurnHeight = config.getInt("overworld-burn-height", 0);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        if ((world.getEnvironment() == World.Environment.NETHER && player.getLocation().getY() >= netherBurnHeight) ||
                (world.getEnvironment() == World.Environment.NORMAL && player.getLocation().getY() <= overworldBurnHeight)) {
            ignitePlayer(player);
        }
    }

    private void ignitePlayer(Player player) {
        player.setFireTicks(20);
        player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 5, 0.5, 0.5, 0.5);

        new BukkitRunnable() {
            @Override
            public void run() {
                if ((player.getWorld().getEnvironment() == World.Environment.NETHER && player.getLocation().getY() >= netherBurnHeight) ||
                        (player.getWorld().getEnvironment() == World.Environment.NORMAL && player.getLocation().getY() <= overworldBurnHeight)) {
                    ignitePlayer(player);
                }
            }
        }.runTaskLater(this, 10);
    }
}
