package logisticspipes.pipes.basic;

import java.util.HashMap;
import java.util.LinkedList;

import logisticspipes.LogisticsPipes;
import logisticspipes.asm.ModDependentField;
import logisticspipes.asm.ModDependentInterface;
import logisticspipes.asm.ModDependentMethod;
import logisticspipes.interfaces.routing.IFilter;
import logisticspipes.pipes.PipeItemsFirewall;
import logisticspipes.proxy.MainProxy;
import logisticspipes.proxy.SimpleServiceLocator;
import logisticspipes.proxy.te.LPConduitItem;
import logisticspipes.renderer.LogisticsTileRenderController;
import logisticspipes.routing.pathfinder.IPipeInformationProvider;
import logisticspipes.utils.AdjacentTile;
import logisticspipes.utils.OrientationsUtil;
import logisticspipes.utils.WorldUtil;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import thermalexpansion.part.conduit.ConduitBase;
import buildcraft.transport.TileGenericPipe;
import cofh.api.transport.IItemConduit;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.peripheral.IComputerAccess;

@ModDependentInterface(modId={"CoFHCore"}, interfacePath={"cofh.api.transport.IItemConduit"})
public class LogisticsTileGenericPipe extends TileGenericPipe implements IPipeInformationProvider, IItemConduit {

	public boolean turtleConnect[] = new boolean[7];
	
	@SideOnly(Side.CLIENT)
	private LogisticsTileRenderController renderController;

	@ModDependentField(modId="ComputerCraft@1.6")
	public HashMap<IComputerAccess, ForgeDirection> connections;

	@ModDependentField(modId="ComputerCraft@1.6")
	public IComputerAccess currentPC;

	@ModDependentField(modId="ThermalExpansion")
	public LPConduitItem[] localConduit;
	
	public LogisticsTileGenericPipe() {
		if(SimpleServiceLocator.ccProxy.isCC()) {
			connections = new HashMap<IComputerAccess, ForgeDirection>();
		}
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			renderController = new LogisticsTileRenderController(this);
		}
	}
	
	public CoreRoutedPipe getCPipe() {
		if(pipe instanceof CoreRoutedPipe) {
			return (CoreRoutedPipe) pipe;
		}
		return null;
	}

	@Override
	public void invalidate() {
		if(!getCPipe().blockRemove()) {
			this.tileEntityInvalid = true;
			super.invalidate();
			SimpleServiceLocator.thermalExpansionProxy.handleLPInternalConduitRemove(this);
		}
	}

	@Override
	public void onChunkUnload() {
		super.onChunkUnload();
		SimpleServiceLocator.thermalExpansionProxy.handleLPInternalConduitChunkUnload(this);
	}

	@Override
	public void updateEntity() {
		SimpleServiceLocator.thermalExpansionProxy.handleLPInternalConduitUpdate(this);
		super.updateEntity();
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT && MainProxy.isClient(worldObj)) {
			renderController.onUpdate();
		}
	}

	@Override
	public void func_85027_a(CrashReportCategory par1CrashReportCategory) {
		try {
			super.func_85027_a(par1CrashReportCategory);
		} catch(Exception e) {
			if(LogisticsPipes.DEBUG) {
				e.printStackTrace();
			}
		}
		par1CrashReportCategory.addCrashSection("LP-Version", LogisticsPipes.VERSION);
		if(this.pipe != null) {
			par1CrashReportCategory.addCrashSection("Pipe", this.pipe.getClass().getCanonicalName());
			if(this.pipe.transport != null) {
				par1CrashReportCategory.addCrashSection("Transport", this.pipe.transport.getClass().getCanonicalName());
			} else {
				par1CrashReportCategory.addCrashSection("Transport", "null");
			}

			if(this.pipe instanceof CoreRoutedPipe) {
				try {
					((CoreRoutedPipe)this.pipe).addCrashReport(par1CrashReportCategory);
				} catch(Exception e) {
					par1CrashReportCategory.addCrashSectionThrowable("Internal LogisticsPipes Error", e);
				}
			}
		}
	}

	@Override
	public void scheduleNeighborChange() {
		super.scheduleNeighborChange();
		boolean connected[] = new boolean[6];
		WorldUtil world = new WorldUtil(this.getWorld(), this.xCoord, this.yCoord, this.zCoord);
		LinkedList<AdjacentTile> adjacent = world.getAdjacentTileEntities(false);
		for(AdjacentTile aTile: adjacent) {
			if(SimpleServiceLocator.ccProxy.isTurtle(aTile.tile)) {
				connected[aTile.orientation.ordinal()] = true;
			}
		}
		for(int i=0; i<6;i++) {
			if(!connected[i]) {
				turtleConnect[i] = false;
			}
		}
		SimpleServiceLocator.thermalExpansionProxy.handleLPInternalConduitNeighborChange(this);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		for(int i=0;i<turtleConnect.length;i++) {
			nbttagcompound.setBoolean("turtleConnect_" + i, turtleConnect[i]);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		for(int i=0;i<turtleConnect.length;i++) {
			turtleConnect[i] = nbttagcompound.getBoolean("turtleConnect_" + i);
		}
	}
	
	@Override
	public boolean canPipeConnect(TileEntity with, ForgeDirection dir) {
		if(SimpleServiceLocator.ccProxy.isTurtle(with) && !turtleConnect[OrientationsUtil.getOrientationOfTilewithTile(this, with).ordinal()]) return false;
		return super.canPipeConnect(with, dir);
	}
	
	public void queueEvent(String event, Object[] arguments) {
		SimpleServiceLocator.ccProxy.queueEvent(event, arguments, this);
	}

	public void handleMesssage(int computerId, Object message, int sourceId) {
		SimpleServiceLocator.ccProxy.handleMesssage(computerId, message, this, sourceId);
	}
	
	public void setTurtleConnect(boolean flag) {
		SimpleServiceLocator.ccProxy.setTurtleConnect(flag, this);
	}

	public boolean getTurtleConnect() {
		return SimpleServiceLocator.ccProxy.getTurtleConnect(this);
	}

	public int getLastCCID() {
		return SimpleServiceLocator.ccProxy.getLastCCID(this);
	}

	// To remove IF TE supports BC pipes natively.
	@Override
	@Deprecated
	@ModDependentMethod(modId="CoFHCore")
	public ItemStack sendItems(ItemStack stack, ForgeDirection dir) {
		return insertItem(dir, stack);
	}

	@Override
	public ItemStack insertItem(ForgeDirection dir, ItemStack stack) {
		return insertItem(dir, stack, false);
	}

	@Override
	@Deprecated
	@ModDependentMethod(modId="CoFHCore")
	public ItemStack insertItem(ForgeDirection dir, ItemStack stack, boolean simulate) {
		if(this.injectItem(stack, !simulate, dir) == stack.stackSize) {
			return null;
		} else {
			return stack;
		}
	}

	@ModDependentMethod(modId="ThermalExpansion")
	public boolean canTEConduitConnect(ConduitBase conduit, int side) {
		return pipe.canPipeConnect(conduit.getTile(), ForgeDirection.VALID_DIRECTIONS[side].getOpposite());
		
	}

	@ModDependentMethod(modId="ThermalExpansion")
	public LPConduitItem getTEConduit(int side) {
		if(localConduit == null) {
			localConduit = new LPConduitItem[6];
		}
		if(localConduit[side] == null) {
			localConduit[side] = new LPConduitItem(this, side);
			localConduit[side].onNeighborChanged();
		}
		return localConduit[side];
	}

	@SideOnly(Side.CLIENT)
	public void addLaser(ForgeDirection dir, float length, int color, boolean reverse, boolean renderBall) {
		renderController.addLaser(dir, length, color, reverse, renderBall);
	}

	@SideOnly(Side.CLIENT)
	public LogisticsTileRenderController getRenderController() {
		return renderController;
	}

	/* IPipeInformationProvider */
	
	@Override
	public boolean isCorrect() {
		return true;
	}

	@Override
	public int getX() {
		return xCoord;
	}

	@Override
	public int getY() {
		return yCoord;
	}

	@Override
	public int getZ() {
		return zCoord;
	}

	@Override
	public boolean isInitialised() { //TODO: check for more ???
		return initialized && !this.getRoutingPipe().stillNeedReplace();
	}

	@Override
	public boolean isRoutingPipe() {
		return pipe instanceof CoreRoutedPipe;
	}

	@Override
	public CoreRoutedPipe getRoutingPipe() {
		if(pipe instanceof CoreRoutedPipe) {
			return (CoreRoutedPipe) pipe;
		}
		throw new RuntimeException("This is no routing pipe");
	}

	@Override
	public boolean isFirewallPipe() {
		return pipe instanceof PipeItemsFirewall;
	}

	@Override
	public IFilter getFirewallFilter() {
		if(pipe instanceof PipeItemsFirewall) {
			return ((PipeItemsFirewall) pipe).getFilter();
		}
		throw new RuntimeException("This is no firewall pipe");
	}

	@Override
	public TileEntity getTile() {
		return this;
	}

	@Override
	public boolean divideNetwork() {
		return false;
	}

	@Override
	public boolean powerOnly() {
		return false;
	}

	@Override
	public boolean isOnewayPipe() {
		return false;
	}

	@Override
	public boolean isOutputOpen(ForgeDirection direction) {
		return false;
	}

	@Override
	public boolean canConnect(IPipeInformationProvider provider, ForgeDirection direction, boolean flag) {
		return SimpleServiceLocator.buildCraftProxy.checkPipesConnections(this, provider.getTile(), direction, true);
	}

	@Override
	public int getDistance() {
		return 1;
	}
}
