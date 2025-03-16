package net.bidibits.linearxp;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerProperties implements IExtendedEntityProperties {
    private int lastLevel;
    public static final String IDENTIFIER = "linearxp_props";

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(IDENTIFIER, new PlayerProperties());
    }

    public static PlayerProperties get(EntityPlayer player) {
        return (PlayerProperties) player.getExtendedProperties(IDENTIFIER);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setInteger("lastLevel", lastLevel);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        lastLevel = compound.getInteger("lastLevel");
    }

    @Override
    public void init(Entity entity, World world) {
    }

    public int getLastLevel() {
        return lastLevel;
    }

    public void setLastLevel(int level) {
        lastLevel = level;
    }
}