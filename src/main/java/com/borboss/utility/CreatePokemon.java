package com.borboss.utility;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.nbt.NBTTagCompound;

public class CreatePokemon {
    public static Pokemon PokemonCreator(Pokemon pokemon) {
        NBTTagCompound nbt = new NBTTagCompound();
        pokemon.writeToNBT(nbt); // Write to the NBT

        /*      Remove values that may conflict with spawning in the Pokemon       *\
                     Not all values have been tested with/without
        \*   Please make a pull request if there is an issue/unnecessary  removal  */

        nbt.removeTag("originalTrainerUUIDLeast");
        nbt.removeTag("originalTrainerUUIDMost");
        nbt.removeTag("CaughtBall");
        nbt.removeTag("UUIDMost");
        nbt.removeTag("UUIDLeast");
        nbt.removeTag("isInRanch");
        nbt.removeTag("Friendship");
        nbt.removeTag("DynamaxLevel");

        return Pixelmon.pokemonFactory.create(nbt);
    }
}
