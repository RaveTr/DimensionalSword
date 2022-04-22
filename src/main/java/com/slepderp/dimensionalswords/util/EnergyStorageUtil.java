package com.slepderp.dimensionalswords.util;

import com.slepderp.dimensionalswords.api.enums.DimEnergyType;
import com.slepderp.dimensionalswords.api.interfaces.IDimensionalEnergyCapacityHelper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;

public class EnergyStorageUtil extends EnergyStorage implements ICapabilityProvider, IDimensionalEnergyCapacityHelper{
	protected final LazyOptional<EnergyStorageUtil> l;
	
	public EnergyStorageUtil(int capacity, int maxTransfer, DimEnergyType dimensionaltype, DimEnergyType weathertype) {
		super(capacity, maxTransfer);
		this.l = LazyOptional.of(() -> this);
	}

	public EnergyStorageUtil(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
		this.l = LazyOptional.of(() -> this);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return null;
	}

	@Override
	public int setMaxEnergyRecieveRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMinEnergyRecieveRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMaxEnergyRecieveRate(int maxRecieval) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMinEnergyRecieveRate(int minRecieval) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMaxEnergyStorageCap() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMinEnergyStorageCap() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMaxEnergyStorageCap(int maxStorage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setMinEnergyStorageCap(int minStorage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setEnergy(int energy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyRecieveRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinEnergyRecieveRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyRecieveRate(int maxRate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinEnergyRecieveRate(int minRate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyStorageCap() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinEnergyStorageCap() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxEnergyStorageCap(int maxStorage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinEnergyStorageCap(int minStorage) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEnergy(int energy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isWarmEnergyType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isColdEnergyType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNormalEnergyType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean compatWithOtherCustomEnergyOrForgeEnergyTypes() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DimEnergyType setDimensionalEnergyType(DimEnergyType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DimEnergyType getDimensionalEnergyType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEnergyType() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetEnergy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readData(CompoundNBT data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeData(CompoundNBT data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearData(CompoundNBT dataToClear) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int currentEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

}
