package logisticspipes.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import logisticspipes.blocks.LogisticsSolderingTileEntity;
import logisticspipes.gui.GuiInvSysConnector;
import logisticspipes.gui.GuiProviderPipe;
import logisticspipes.gui.GuiSupplierPipe;
import logisticspipes.gui.modules.GuiAdvancedExtractor;
import logisticspipes.gui.modules.GuiProvider;
import logisticspipes.gui.orderer.GuiOrderer;
import logisticspipes.gui.popup.GuiDiskPopup;
import logisticspipes.interfaces.IChestContentReceiver;
import logisticspipes.interfaces.IModuleInventoryReceive;
import logisticspipes.interfaces.IOrderManagerContentReceiver;
import logisticspipes.interfaces.ISendQueueContentRecieiver;
import logisticspipes.interfaces.ISneakyOrientationreceiver;
import logisticspipes.logic.BaseLogicCrafting;
import logisticspipes.logic.BaseLogicSatellite;
import logisticspipes.logic.LogicLiquidSupplier;
import logisticspipes.logic.LogicProvider;
import logisticspipes.logic.LogicSupplier;
import logisticspipes.logisticspipes.ExtractionMode;
import logisticspipes.logisticspipes.modules.SneakyOrientation;
import logisticspipes.main.CoreRoutedPipe;
import logisticspipes.main.ItemMessage;
import logisticspipes.modules.ModuleAdvancedExtractor;
import logisticspipes.modules.ModuleApiaristSink;
import logisticspipes.modules.ModuleElectricManager;
import logisticspipes.modules.ModuleItemSink;
import logisticspipes.network.packets.PacketBufferTransfer;
import logisticspipes.network.packets.PacketCraftingLoop;
import logisticspipes.network.packets.PacketInventoryChange;
import logisticspipes.network.packets.PacketItem;
import logisticspipes.network.packets.PacketItems;
import logisticspipes.network.packets.PacketModuleInteger;
import logisticspipes.network.packets.PacketModuleInvContent;
import logisticspipes.network.packets.PacketModuleNBT;
import logisticspipes.network.packets.PacketPipeInteger;
import logisticspipes.network.packets.PacketPipeInvContent;
import logisticspipes.network.packets.PacketPipeUpdate;
import logisticspipes.network.packets.PacketRequestGuiContent;
import logisticspipes.network.packets.PacketRouterInformation;
import logisticspipes.pipes.PipeItemsApiaristSink;
import logisticspipes.pipes.PipeItemsInvSysConnector;
import logisticspipes.pipes.PipeItemsLiquidSupplier;
import logisticspipes.pipes.PipeItemsRequestLogisticsMk2;
import logisticspipes.pipes.PipeLogisticsChassi;
import logisticspipes.proxy.MainProxy;
import logisticspipes.routing.ClientRouter;
import logisticspipes.routing.IRouter;
import logisticspipes.ticks.PacketBufferHandlerThread;
import logisticspipes.utils.ItemIdentifier;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import buildcraft.transport.TileGenericPipe;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class ClientPacketHandler {
	
	public static void onPacketData(NetworkManager manager, Packet250CustomPayload packet, Player player) {
		final DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		onPacketData(data, player);
	}
	
	public static void onPacketData(DataInputStream data, Player player) {
		try {

			final int packetID = data.read();
			switch (packetID) {
				case NetworkConstants.CRAFTING_PIPE_SATELLITE_ID:
					final PacketPipeInteger packetA1 = new PacketPipeInteger();
					packetA1.readData(data);
					onCraftingPipeSetSatellite(packetA1);
					break;
				case NetworkConstants.CRAFTING_PIPE_IMPORT_BACK:
					final PacketInventoryChange packetA = new PacketInventoryChange();
					packetA.readData(data);
					onCraftingPipeSetImport(packetA);
					break;
				case NetworkConstants.SATELLITE_PIPE_SATELLITE_ID:
					final PacketPipeInteger packetB = new PacketPipeInteger();
					packetB.readData(data);
					onSatellitePipeSetSatellite(packetB);
					break;
				case NetworkConstants.ORDERER_CONTENT_ANSWER:
					final PacketRequestGuiContent packetC = new PacketRequestGuiContent();
					packetC.readData(data);
					onOrdererRefreshAnswer(packetC);
					break;
				case NetworkConstants.MISSING_ITEMS:
					final PacketItems packetD = new PacketItems();
					packetD.readData(data);
					onItemsResponse(packetD);
					break;
				case NetworkConstants.CRAFTING_LOOP:
					final PacketCraftingLoop packetE = new PacketCraftingLoop();
					packetE.readData(data);
					onCraftingLoop(packetE);
					break;
				case NetworkConstants.ITEM_SINK_STATUS:
					final PacketModuleInteger packetH = new PacketModuleInteger();
					packetH.readData(data);
					onItemSinkStatusRecive(packetH);
					break;
				case NetworkConstants.PROVIDER_PIPE_MODE_CONTENT:
					final PacketPipeInteger packetF = new PacketPipeInteger();
					packetF.readData(data);
					onProviderPipeModeRecive(packetF);
					break;
				case NetworkConstants.PROVIDER_PIPE_INCLUDE_CONTENT:
					final PacketPipeInteger packetG = new PacketPipeInteger();
					packetG.readData(data);
					onProviderPipeIncludeRecive(packetG);
					break;
				case NetworkConstants.SUPPLIER_PIPE_MODE_RESPONSE:
					final PacketPipeInteger packetI = new PacketPipeInteger();
					packetI.readData(data);
					onSupplierPipeRecive(packetI);
					break;
				case NetworkConstants.EXTRACTOR_MODULE_RESPONSE:
					final PacketModuleInteger packetJ = new PacketModuleInteger();
					packetJ.readData(data);
					onModulePipeRecive(packetJ);
					break;
				case NetworkConstants.PROVIDER_MODULE_MODE_CONTENT:
					final PacketPipeInteger packetK = new PacketPipeInteger();
					packetK.readData(data);
					onProviderModuleModeRecive(packetK);
					break;
				case NetworkConstants.PROVIDER_MODULE_INCLUDE_CONTENT:
					final PacketPipeInteger packetL = new PacketPipeInteger();
					packetL.readData(data);
					onProviderModuleIncludeRecive(packetL);
					break;
				case NetworkConstants.ADVANCED_EXTRACTOR_MODULE_INCLUDED_RESPONSE:
					final PacketModuleInteger packetM = new PacketModuleInteger();
					packetM.readData(data);
					onAdvancedExtractorModuleIncludeRecive(packetM);
					break;
				case NetworkConstants.DISK_CONTENT:
					final PacketItem packetO = new PacketItem();
					packetO.readData(data);
					handleRequestMK2DiskItem(packetO);
					break;
				case NetworkConstants.PIPE_UPDATE:
					final PacketPipeUpdate packetOa = new PacketPipeUpdate();
					packetOa.readData(data);
					handlePacketPipeUpdate(packetOa);
					break;
				case NetworkConstants.DISK_MACRO_REQUEST_RESPONSE:
					final PacketItems packetP = new PacketItems();
					packetP.readData(data);
					handleMacroResponse(packetP);
					break;
				case NetworkConstants.BEE_MODULE_CONTENT:
					final PacketModuleNBT packetQ = new PacketModuleNBT();
					packetQ.readData(data);
					handleBeePacketNBT(packetQ);
					break;
				case NetworkConstants.LIQUID_SUPPLIER_PARTIALS:
					final PacketPipeInteger packetR = new PacketPipeInteger();
					packetR.readData(data);
					onLiquidSupplierPartials(player,packetR);
					break;
				case NetworkConstants.INC_SYS_CON_CONTENT:
					final PacketRequestGuiContent packetS = new PacketRequestGuiContent();
					packetS.readData(data);
					onInvSysConGuiData(player,packetS);
					break;
				case NetworkConstants.SOLDERING_UPDATE_HEAT:
					final PacketPipeInteger packetT = new PacketPipeInteger();
					packetT.readData(data);
					onSolderingUpdateHeat(player, packetT);
					break;
				case NetworkConstants.SOLDERING_UPDATE_PROGRESS:
					final PacketPipeInteger packetU = new PacketPipeInteger();
					packetU.readData(data);
					onSolderingUpdateProgress(player, packetU);
					break;
				case NetworkConstants.SOLDERING_UPDATE_INVENTORY:
					final PacketInventoryChange packetV = new PacketInventoryChange();
					packetV.readData(data);
					onSolderingUpdateInventory(player, packetV);
					break;
				case NetworkConstants.PIPE_CHEST_CONTENT:
					final PacketPipeInvContent packetW = new PacketPipeInvContent();
					packetW.readData(data);
					onSateliteChestInv(player, packetW);
					break;
				case NetworkConstants.ORDER_MANAGER_CONTENT:
					final PacketPipeInvContent packetX = new PacketPipeInvContent();
					packetX.readData(data);
					onOrderManagerContent(player, packetX);
					break;
				case NetworkConstants.ROUTER_UPDATE_CONTENT:
					final PacketRouterInformation packetY = new PacketRouterInformation();
					packetY.readData(data);
					onRouterInformation(player, packetY);
					break;
				case NetworkConstants.BUFFERED_PACKET_TRANSFER:
					final PacketBufferTransfer packetZ = new PacketBufferTransfer();
					packetZ.readData(data);
					onBufferTransfer(packetZ);
					break;
				case NetworkConstants.INC_SYS_CON_RESISTANCE:
					final PacketPipeInteger packetAa = new PacketPipeInteger();
					packetAa.readData(data);
					onInvSysConResistance(player, packetAa);
					break;
				case NetworkConstants.CHASSIE_PIPE_MODULE_CONTENT:
					final PacketPipeInvContent packetAb = new PacketPipeInvContent();
					packetAb.readData(data);
					onChassieInvRecive(player, packetAb);
					break;
				case NetworkConstants.MODULE_INV_CONTENT:
					final PacketModuleInvContent packetAc = new PacketModuleInvContent();
					packetAc.readData(data);
					onModuleInvRecive(packetAc);
					break;
				case NetworkConstants.ELECTRIC_MANAGER_STATE:
					final PacketModuleInteger packetAj = new PacketModuleInteger();
					packetAj.readData(data);
					onElectricModuleStateChange(packetAj);
					break;
				case NetworkConstants.SEND_QUEUE_CONTENT:
					final PacketPipeInvContent packetAk = new PacketPipeInvContent();
					packetAk.readData(data);
					onSendQueueInventory(packetAk);
					break;
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void onCraftingPipeSetSatellite(PacketPipeInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if (pipe == null) {
			return;
		}

		if (!(pipe.pipe.logic instanceof BaseLogicCrafting)) {
			return;
		}

		((BaseLogicCrafting) pipe.pipe.logic).setSatelliteId(packet.integer);
	}

	private static void onCraftingPipeSetImport(PacketInventoryChange packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if (pipe == null) {
			return;
		}

		if (!(pipe.pipe.logic instanceof BaseLogicCrafting)) {
			return;
		}

		final BaseLogicCrafting craftingPipe = (BaseLogicCrafting) pipe.pipe.logic;
		for (int i = 0; i < packet.itemStacks.size(); i++) {
			craftingPipe.setDummyInventorySlot(i, packet.itemStacks.get(i));
		}
	}

	private static void onSatellitePipeSetSatellite(PacketPipeInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if (pipe == null) {
			return;
		}

		if (!(pipe.pipe.logic instanceof BaseLogicSatellite)) {
			return;
		}

		((BaseLogicSatellite) pipe.pipe.logic).setSatelliteId(packet.integer);
	}

	private static void onOrdererRefreshAnswer(PacketRequestGuiContent packet) {
		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiOrderer) {
			((GuiOrderer) FMLClientHandler.instance().getClient().currentScreen).handlePacket(packet);
		}
	}

	private static void onItemsResponse(PacketItems packet) {
		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiOrderer) {
			((GuiOrderer)FMLClientHandler.instance().getClient().currentScreen).handleRequestAnswer(packet.items,!packet.error,(GuiOrderer)FMLClientHandler.instance().getClient().currentScreen,FMLClientHandler.instance().getClient().thePlayer);
		} else if(packet.error) {
			for (final ItemMessage items : packet.items) {
				FMLClientHandler.instance().getClient().thePlayer.addChatMessage("Missing: " + items);
			}
		} else {
			for (final ItemMessage items : packet.items) {
				FMLClientHandler.instance().getClient().thePlayer.addChatMessage("Requested: " + items);
			}
			FMLClientHandler.instance().getClient().thePlayer.addChatMessage("Request successful!");
		}
	}

	private static void onCraftingLoop(PacketCraftingLoop packet) {
		final ItemIdentifier item = packet.items.get(0).getItemIdentifier();
		FMLClientHandler.instance().getClient().thePlayer.addChatMessage("Logistics: Possible crafting loop while trying to craft " + item.getFriendlyName()
				+ " !! ABORTING !!");
	}

	private static void onItemSinkStatusRecive(PacketModuleInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if (pipe == null) {
			return;
		}
		
		if(packet.slot == -1) {
			if (!(pipe.pipe instanceof CoreRoutedPipe)) {
				return;
			}
			if(!(((CoreRoutedPipe)pipe.pipe).getLogisticsModule() instanceof ModuleItemSink)) {
				return;
			}
			ModuleItemSink module = (ModuleItemSink) ((CoreRoutedPipe)pipe.pipe).getLogisticsModule();
			module.setDefaultRoute(packet.integer == 1);
			return;
		}
		
		if (!(pipe.pipe instanceof PipeLogisticsChassi)) {
			return;
		}
		if(((PipeLogisticsChassi)pipe.pipe).getModules() == null) return;
		if(((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot) instanceof ModuleItemSink) {
			ModuleItemSink module = (ModuleItemSink) ((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot);
			module.setDefaultRoute(packet.integer == 1);
		}
	}

	private static void onProviderPipeModeRecive(PacketPipeInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if (pipe == null) {
			return;
		}

		if (!(pipe.pipe.logic instanceof LogicProvider)) {
			return;
		}
		
		ExtractionMode mode = ((LogicProvider) pipe.pipe.logic).getExtractionMode();
		int modeint = mode.ordinal();
		while(modeint != packet.integer) {
			((LogicProvider) pipe.pipe.logic).nextExtractionMode();
			modeint = ((LogicProvider) pipe.pipe.logic).getExtractionMode().ordinal();
			if(mode.ordinal() == modeint) {
				//loop break
				break;
			}
		}
	}

	private static void onProviderPipeIncludeRecive(PacketPipeInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if (pipe == null) {
			return;
		}

		if (!(pipe.pipe.logic instanceof LogicProvider)) {
			return;
		}
		((LogicProvider) pipe.pipe.logic).setFilterExcluded(packet.integer == 1);
		
		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiProviderPipe) {
			((GuiProviderPipe) FMLClientHandler.instance().getClient().currentScreen).refreshInclude();
		}
	}

	private static void onSupplierPipeRecive(PacketPipeInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if (pipe == null) {
			return;
		}

		if (!(pipe.pipe.logic instanceof LogicSupplier)) {
			return;
		}
		((LogicSupplier) pipe.pipe.logic).setRequestingPartials(packet.integer == 1);
		
		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiSupplierPipe) {
			((GuiSupplierPipe) FMLClientHandler.instance().getClient().currentScreen).refreshMode();
		}
	}

	private static void onModulePipeRecive(PacketModuleInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if (pipe == null) {
			return;
		}
		
		if(packet.slot == -1) {
			if(pipe.pipe instanceof CoreRoutedPipe && ((CoreRoutedPipe)pipe.pipe).getLogisticsModule() instanceof ISneakyOrientationreceiver) {
				((ISneakyOrientationreceiver)((CoreRoutedPipe)pipe.pipe).getLogisticsModule()).setSneakyOrientation(SneakyOrientation.values()[packet.integer]);
			}
			return;
		}
		
		if(pipe.pipe instanceof PipeLogisticsChassi && ((PipeLogisticsChassi)pipe.pipe).getModules() != null && ((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot) instanceof ISneakyOrientationreceiver) {
			ISneakyOrientationreceiver recieiver = (ISneakyOrientationreceiver) ((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot);
			recieiver.setSneakyOrientation(SneakyOrientation.values()[packet.integer]);
		}
	}

	private static void onProviderModuleModeRecive(PacketPipeInteger packet) {
		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiProvider) {
			((GuiProvider) FMLClientHandler.instance().getClient().currentScreen).handleModuleModeRecive(packet);
		}
	}

	private static void onProviderModuleIncludeRecive(PacketPipeInteger packet) {
		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiProvider) {
			((GuiProvider) FMLClientHandler.instance().getClient().currentScreen).handleModuleIncludeRecive(packet);
		}
	}

	private static void onAdvancedExtractorModuleIncludeRecive(PacketModuleInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if (pipe == null) {
			return;
		}
		
		if(packet.slot == -1) {
			if(pipe.pipe instanceof CoreRoutedPipe && ((CoreRoutedPipe)pipe.pipe).getLogisticsModule() instanceof ModuleAdvancedExtractor) {
				((ModuleAdvancedExtractor)((CoreRoutedPipe)pipe.pipe).getLogisticsModule()).setItemsIncluded(packet.integer == 1);
			}
			return;
		}
		
		if(pipe.pipe instanceof PipeLogisticsChassi && ((PipeLogisticsChassi)pipe.pipe).getModules() != null && ((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot) instanceof ModuleAdvancedExtractor) {
			ModuleAdvancedExtractor recieiver = (ModuleAdvancedExtractor) ((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot);
			recieiver.setItemsIncluded(packet.integer == 1);
		}
	}

	private static void handlePacketPipeUpdate(PacketPipeUpdate packet) {
		TileGenericPipe tile = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(tile == null) {
			return;
		}
		if(tile.pipe == null) {
			return;
		}
		new TilePacketWrapper(new Class[] { TileGenericPipe.class, tile.pipe.transport.getClass(), tile.pipe.logic.getClass() }).fromPayload(new Object[] { tile.pipe.container, tile.pipe.transport, tile.pipe.logic },packet.getPayload());
	}

	private static void handleRequestMK2DiskItem(PacketItem packet) {
		final TileGenericPipe tile = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(tile == null) {
			return;
		}
		if(tile.pipe instanceof PipeItemsRequestLogisticsMk2) {
			((PipeItemsRequestLogisticsMk2)tile.pipe).setDisk(packet.itemstack);
		}
	}
	
	private static void handleMacroResponse(PacketItems packet) {
		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiOrderer) {
			if(((GuiOrderer) FMLClientHandler.instance().getClient().currentScreen).getSubGui() instanceof GuiDiskPopup) {
				((GuiOrderer) FMLClientHandler.instance().getClient().currentScreen).handleRequestAnswer(packet.items, packet.error, ((GuiOrderer) FMLClientHandler.instance().getClient().currentScreen).getSubGui(),FMLClientHandler.instance().getClient().thePlayer);
			}
		}
	}

	private static void handleBeePacketNBT(PacketModuleNBT packet) {
		final TileGenericPipe tile = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(tile == null) {
			return;
		}
		ModuleApiaristSink sink;
		if(packet.slot == -1 && tile.pipe instanceof PipeItemsApiaristSink) {
			sink = (ModuleApiaristSink) ((PipeItemsApiaristSink)tile.pipe).getLogisticsModule();
		} else if(tile.pipe instanceof CoreRoutedPipe && ((CoreRoutedPipe)tile.pipe).getLogisticsModule().getSubModule(packet.slot) instanceof ModuleApiaristSink) {
			sink = (ModuleApiaristSink) ((CoreRoutedPipe)tile.pipe).getLogisticsModule().getSubModule(packet.slot);
		} else {
			return;
		}
		packet.handle(sink);
	}

	private static void onLiquidSupplierPartials(Player player, PacketPipeInteger packet) {
		final TileGenericPipe tile = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(tile == null) {
			return;
		}
		if(tile.pipe instanceof PipeItemsLiquidSupplier && tile.pipe.logic instanceof LogicLiquidSupplier) {
			((LogicLiquidSupplier)tile.pipe.logic).setRequestingPartials((packet.integer % 10) == 1);		
		}
	}
	
	private static void onInvSysConGuiData(Player player, PacketRequestGuiContent packet) {
		if (FMLClientHandler.instance().getClient().currentScreen instanceof GuiInvSysConnector) {
			((GuiInvSysConnector) FMLClientHandler.instance().getClient().currentScreen).handleContentAnswer(packet._allItems);
		}
	}

	private static void onSolderingUpdateHeat(Player player, PacketPipeInteger packet) {
		final TileEntity tile = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(packet.posX, packet.posY, packet.posZ);
		if(tile instanceof LogisticsSolderingTileEntity) {
			LogisticsSolderingTileEntity station = (LogisticsSolderingTileEntity) tile;
			station.heat = packet.integer;
		}
	}

	private static void onSolderingUpdateProgress(Player player, PacketPipeInteger packet) {
		final TileEntity tile = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(packet.posX, packet.posY, packet.posZ);
		if(tile instanceof LogisticsSolderingTileEntity) {
			LogisticsSolderingTileEntity station = (LogisticsSolderingTileEntity) tile;
			station.progress = packet.integer;
		}
	}

	private static void onSolderingUpdateInventory(Player player, PacketInventoryChange packet) {
		final TileEntity tile = FMLClientHandler.instance().getClient().theWorld.getBlockTileEntity(packet.posX, packet.posY, packet.posZ);
		if(tile instanceof LogisticsSolderingTileEntity) {
			LogisticsSolderingTileEntity station = (LogisticsSolderingTileEntity) tile;
			for(int i=0;i<station.getSizeInventory();i++) {
				if(i >= packet.itemStacks.size()) break;
				ItemStack stack = packet.itemStacks.get(i);
				station.setInventorySlotContents(i, stack);
			}
		}
	}

	private static void onSateliteChestInv(Player player, PacketPipeInvContent packet) {
		final TileGenericPipe tile = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(tile == null) {
			return;
		}
		if(tile.pipe instanceof IChestContentReceiver) {
			((IChestContentReceiver)tile.pipe).setReceivedChestContent(packet._allItems);
		}
	}

	private static void onOrderManagerContent(Player player, PacketPipeInvContent packet) {
		final TileGenericPipe tile = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(tile == null) {
			return;
		}
		if(tile.pipe instanceof IOrderManagerContentReceiver) {
			((IOrderManagerContentReceiver)tile.pipe).setOrderManagerContent(packet._allItems);
		}
	}

	private static void onRouterInformation(Player player, PacketRouterInformation packet) {
		World world = MainProxy.getWorld(packet._dimension);
		final TileGenericPipe pipe = getPipe(world, packet.posX, packet.posY, packet.posZ);
		if(pipe == null) {
			return;
		}
		if(pipe.pipe instanceof CoreRoutedPipe) {
			IRouter router = ((CoreRoutedPipe)pipe.pipe).getRouter();
			if(router instanceof ClientRouter) {
				((ClientRouter)router).handleRouterPacket(packet);
			}
		}
	}

	private static void onBufferTransfer(PacketBufferTransfer packet) {
		PacketBufferHandlerThread.handlePacket(packet);
	}

	private static void onInvSysConResistance(Player player, PacketPipeInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(pipe == null) {
			return;
		}
		if(pipe.pipe instanceof PipeItemsInvSysConnector) {
			PipeItemsInvSysConnector invCon = (PipeItemsInvSysConnector) pipe.pipe;
			invCon.resistance = packet.integer;
		}
	}

	private static void onChassieInvRecive(Player player, PacketPipeInvContent packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(pipe == null) {
			return;
		}
		if(pipe.pipe instanceof PipeLogisticsChassi) {
			PipeLogisticsChassi chassie = (PipeLogisticsChassi) pipe.pipe;
			chassie.handleModuleItemIdentifierList(packet._allItems);
		}
	}

	private static void onModuleInvRecive(PacketModuleInvContent packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(pipe == null) {
			return;
		}
		if(pipe.pipe instanceof PipeLogisticsChassi && ((PipeLogisticsChassi)pipe.pipe).getModules() != null && ((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot) instanceof IModuleInventoryReceive) {
			IModuleInventoryReceive module = (IModuleInventoryReceive) ((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot);
			module.handleInvContent(packet._allItems);
		}
	}

	private static void onElectricModuleStateChange(PacketModuleInteger packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(pipe == null) {
			return;
		}
		
		if(pipe.pipe instanceof PipeLogisticsChassi && ((PipeLogisticsChassi)pipe.pipe).getModules() != null && ((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot) instanceof ModuleElectricManager) {
			ModuleElectricManager module = (ModuleElectricManager) ((PipeLogisticsChassi)pipe.pipe).getModules().getSubModule(packet.slot);
			module.setDischargeMode(packet.integer == 1);
		}
	}

	private static void onSendQueueInventory(PacketPipeInvContent packet) {
		final TileGenericPipe pipe = getPipe(FMLClientHandler.instance().getClient().theWorld, packet.posX, packet.posY, packet.posZ);
		if(pipe == null) {
			return;
		}
		if(pipe.pipe instanceof ISendQueueContentRecieiver) {
			ISendQueueContentRecieiver receiver = (ISendQueueContentRecieiver) pipe.pipe;
			receiver.handleSendQueueItemIdentifierList(packet._allItems);
		}
	}

	// BuildCraft method
	/**
	 * Retrieves pipe at specified coordinates if any.
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	private static TileGenericPipe getPipe(World world, int x, int y, int z) {
		if(world == null) return null;
		if (!world.blockExists(x, y, z)) {
			return null;
		}

		final TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (!(tile instanceof TileGenericPipe)) {
			return null;
		}

		return (TileGenericPipe) tile;
	}
	// BuildCraft method end

}