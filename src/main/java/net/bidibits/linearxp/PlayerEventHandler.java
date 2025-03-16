package net.bidibits.linearxp;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerEventHandler {
    @SubscribeEvent
    public void onPlayerTick(net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            PlayerProperties props = PlayerProperties.get(player);
            if (!player.worldObj.isRemote) {
                if (props != null) {
                    int currentLevel = player.experienceLevel;
                    int lastLevel = props.getLastLevel();
                    int k = LinearXP.xpPerLevel;

                    if (currentLevel < lastLevel) {
                        // spending
                        player.experienceTotal = currentLevel * k;
                        player.experience = 0.0F;
                    } else {
                        // gaining
                        int level = player.experienceTotal / k;
                        float progress = (float) (player.experienceTotal % k) / (float) k;
                        if (progress < 0.0F || progress > 1.0F) progress = 0.0F;
                        player.experienceLevel = level;
                        player.experience = progress;
                    }
                    props.setLastLevel(player.experienceLevel);
                }
            } else {
                if (props != null) {
                    int currentLevel = player.experienceLevel;
                    int lastLevel = props.getLastLevel();
                    int k = LinearXP.xpPerLevel;

                    if (currentLevel < lastLevel) {
                        // spending
                        player.experienceTotal = currentLevel * k;
                        player.experience = 0.0F;
                    } else {
                        // gaining
                        int level = player.experienceTotal / k;
                        float progress = (float) (player.experienceTotal % k) / (float) k;
                        if (progress < 0.0F || progress > 1.0F) progress = 0.0F;
                        player.experienceLevel = level;
                        player.experience = progress;
                    }
                    props.setLastLevel(player.experienceLevel);
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer) {
            PlayerProperties.register((EntityPlayer) event.entity);
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        PlayerProperties oldProps = PlayerProperties.get(event.original);
        if (oldProps != null) {
            PlayerProperties newProps = PlayerProperties.get(event.entityPlayer);
            if (newProps != null) {
                newProps.setLastLevel(oldProps.getLastLevel());
            }
        }
    }
}