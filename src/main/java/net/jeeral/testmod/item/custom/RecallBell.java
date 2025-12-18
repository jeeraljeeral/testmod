package net.jeeral.testmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RecallBell extends Item {
    public RecallBell(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        world.playSound(
                user,
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.BLOCK_NOTE_BLOCK_BELL,
                SoundCategory.NEUTRAL,
                0.5F,
                1.0F
        );

        if (!world.isClient()) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;

            net.minecraft.world.TeleportTarget target = serverPlayer.getRespawnTarget(false, net.minecraft.world.TeleportTarget.NO_OP);

            if (target == null) {
                var server = world.getServer();
                if (server != null) {
                    net.minecraft.server.world.ServerWorld overworld = server.getOverworld();
                    net.minecraft.util.math.BlockPos spawnPos = overworld.getSpawnPoint().getPos();

                    target = new net.minecraft.world.TeleportTarget(
                            overworld,
                            new net.minecraft.util.math.Vec3d(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5),
                            net.minecraft.util.math.Vec3d.ZERO,
                            serverPlayer.getYaw(),
                            serverPlayer.getPitch(),
                            net.minecraft.world.TeleportTarget.NO_OP
                    );
                }
            }

            if (target != null) {
                serverPlayer.teleportTo(target);
            }
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return ActionResult.SUCCESS;
    }
}
