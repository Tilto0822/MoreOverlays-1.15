package at.feldim2425.moreoverlays.api.itemsearch;

import at.feldim2425.moreoverlays.itemsearch.DefaultSlotView;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Slot;

import java.util.ArrayList;

public final class SlotHandler {

    public static final SlotHandler INSTANCE = new SlotHandler();

    private final ArrayList<IOverrideSlotPos> overrides = new ArrayList<>();

    /*
     *  Register a IOverrideSlotPos for non GuiContainers
     */
    public void addPositionOverride(IOverrideSlotPos slotPos) {
        if (overrides.contains(slotPos) || slotPos instanceof ContainerScreen<?>)
            return;
        overrides.add(slotPos);
    }

    public IViewSlot getViewSlot(ContainerScreen<?> container, Slot slot) {
        if (container instanceof IOverrideSlotPos) {
            IViewSlot slot1 = ((IOverrideSlotPos) container).getSlot(container, slot);
            if (slot1 != null)
                return slot1;
        } else {
            if (!overrides.isEmpty()) {
                for (IOverrideSlotPos override : overrides) {
                    IViewSlot slot1 = override.getSlot(container, slot);
                    if (slot1 != null)
                        return slot1;
                }
            }
        }

        return new DefaultSlotView(slot);
    }
}
