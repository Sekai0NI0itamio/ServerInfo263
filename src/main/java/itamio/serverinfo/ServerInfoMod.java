package itamio.serverinfo;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("serverinfo")
public class ServerInfoMod {
    public ServerInfoMod() {
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("serverinfo").requires(s -> s.hasPermission(0))
            .executes(ctx -> {
                var s = ctx.getSource().getServer(); ctx.getSource().sendSuccess(() -> Component.literal("Server: " + s.getServerName() + " | MOTD: " + s.getMotd() + " | Seed: " + ctx.getSource().getLevel().getSeed()), false);
                return 1;
            }));
    }
}
