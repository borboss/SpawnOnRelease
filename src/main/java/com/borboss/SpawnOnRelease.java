/*                                                  *\
 *
 *      https://github.com/borboss  (2021)
 *       Pixelmon REFORGED Version 8.1.2
 *
\*                                                  */


// Running this on any "knock-offs" is frowned upon
// Running this may not result in the expected outcome if not on reforged 8.1.2.
// No support will be offered if you are running one of these versions,
// since they're most likely based on Pixelmon Reforged 6.x.x.
package com.borboss;
import com.borboss.listeners.*;

import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/*
 * TODO (Everything here may not be realized, these are just IDEAS!)
 * TODO: Create a proper config
 * TODO: Send a message in chat
 * TODO: Some kind of method to prevent farming xp/drops
 * TODO: Proper logging
 * TODO: LOCALIZATION
 */

@Mod(
        modid = SpawnOnRelease.MODID,
        name = SpawnOnRelease.NAME,
        version = SpawnOnRelease.VERSION
)
public class SpawnOnRelease {
    // Variables used outside of this
    public static final String MODID = "spawnonrelease";
    public static final String NAME = "Spawn-On-Release";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler // Loads once the mod begins initializing
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Pixelmon.EVENT_BUS.register(new DeletionEvent()); // Start looking for deletion events
        logger.info("§b[SpawnOnRelease] §fSpawn-On-Release has loaded properly!");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("§b[SpawnOnRelease] §fSpawn-On-Release by borboss has begun starting up! §lPlease be patient!"); // Loads once the mod starts up
    }
}
