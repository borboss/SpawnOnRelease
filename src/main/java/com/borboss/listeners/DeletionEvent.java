/*                                                  *\
 *
 *          https://github.com/borboss (2021)
 *
\*                                                  */
package com.borboss.listeners;

import com.borboss.utility.*;
import com.pixelmonmod.pixelmon.api.events.PixelmonDeletedEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.pixelmonmod.pixelmon.api.enums.DeleteType;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.EntityPlayerMP;
import org.apache.logging.log4j.Logger;

public class DeletionEvent {
    private static Logger logger;


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onDeletion(final PixelmonDeletedEvent event) {
        // Check if the deletion was via method other than PC, so we don't spawn unwanted pokemon.
        if (event.deleteType == DeleteType.PC) {

            // Create Variables
            final Pokemon pokemon = event.pokemon;
            final EntityPlayerMP player = event.player;
            // Create XYZ
            final double
                    X = player.getPosition().getX(),
                    Y = player.getPosition().getY(),
                    Z = player.getPosition().getZ();

            // Double check that the pokemon (actually) exists and is not an egg
            if (pokemon != null && !pokemon.isEgg()) {
                Pokemon newMon = CreatePokemon.PokemonCreator(pokemon); // Create the NBT placeholder
                // Spawn the pokemon using the NBT created above
                newMon.getOrSpawnPixelmon(player.world, X, Y, Z);
                // Send the player a message that it was successful with the location of it.
                player.sendMessage(new TextComponentString("§b[SpawnOnRelease] §aYour §f"+pokemon.getSpecies().getPokemonName()+"§a was released and is now at X: §r"+(int)X+"§a, Y: §r"+(int)Y+"§a, Z: §r"+(int)Z+"§a!"));
            } if (pokemon != null && pokemon.isEgg()) {
                // Send the player a message that it failed because the pokemon is an egg
                player.sendMessage(new TextComponentString("§b[SpawnOnRelease] §aYou can't spawn an egg, so your "+pokemon.getSpecies().getPokemonName()+" egg was released!"));
            } if (pokemon == null) {
                // Log to console that the pokemon was null + warn the player in chat.
                player.sendMessage(new TextComponentString("§b[SpawnOnRelease] §cSomething happened and we received the pokemon as null. Your pokemon is most likely gone forever, please contact an administrator." ));
                logger.warn("§b[SpawnOnRelease] §c§lSomething went terribly wrong. We received a pokemon as null. §r§cThat's a pokemon gone forever. Oops. (§r§l{\"+player.getDisplayName()+\"}§r§c)");
            }
        }
    }
}