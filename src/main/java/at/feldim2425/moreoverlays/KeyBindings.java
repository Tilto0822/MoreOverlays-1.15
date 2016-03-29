package at.feldim2425.moreoverlays;

import at.feldim2425.moreoverlays.chunkbounds.ChunkBoundsHandler;
import at.feldim2425.moreoverlays.lightoverlay.LightOverlayHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    public static KeyBinding lightOverlay = new KeyBinding("key."+MoreOverlays.MOD_ID+".lightoverlay.desc", Keyboard.KEY_F7, "key."+MoreOverlays.MOD_ID+".category");
    public static KeyBinding chunkBounds= new KeyBinding("key."+MoreOverlays.MOD_ID+".chunkbounds.desc", Keyboard.KEY_F9, "key."+MoreOverlays.MOD_ID+".category");
    public static KeyBinding invSearch = new KeyBinding("key."+MoreOverlays.MOD_ID+".invsearch.desc", Keyboard.KEY_Z, "key."+MoreOverlays.MOD_ID+".category");

    public static void init(){
        ClientRegistry.registerKeyBinding(lightOverlay);
        ClientRegistry.registerKeyBinding(chunkBounds);
        ClientRegistry.registerKeyBinding(invSearch);

        MinecraftForge.EVENT_BUS.register(new KeyBindings());
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(InputEvent.KeyInputEvent event)
    {
        if(lightOverlay.isPressed()){
            LightOverlayHandler.toggleMode();
        }

        if(chunkBounds.isPressed()){
            ChunkBoundsHandler.toggleMode();
        }

        if(invSearch.isPressed()){

        }
    }
}