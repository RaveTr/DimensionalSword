package com.slepderp.dimensionalswords.api.interfaces;

import com.slepderp.dimensionalswords.api.enums.DimEnergyType;

import net.minecraft.nbt.CompoundNBT;

public interface IDimensionalEnergyCapacityHelper {
	
	/**
	 * allows you to modify current energy
	 * @return current energy stored
	 */
	int currentEnergy();
	
	/**
	 * returns one value
	 * Maximum amount of power block can receive per second, as well as the minimum energy required for it to run
	 * @return maximum transfer rate for energy
	 */
	int setMaxEnergyRecieveRate();
	
	/**
	 * returns one value
	 * Minimum amount of power block can receive per second, as well as the minimum energy required for it to run
	 * @return maximum transfer rate for energy
	 */
	int setMinEnergyRecieveRate();
	
	/**
	 * Maximum amount of power block can receive per second, as well as the minimum energy required for it to run. Can return multiple values w/param
	 * @param maxRecieval maximum energy receivable inside block
	 * @return maximum transfer rate for energy (maxRecieval)
	 */
	int setMaxEnergyRecieveRate(int maxRecieval);
	
	/**
	 * Maximum amount of power block can receive per second, as well as the minimum energy required for it to run. Can return multiple values w/param
	 * This method is for when you don't want to include weather, same functions as the other one
	 * @param minRecieval minimum energy required for block to start an operation
	 * @return minimum transfer rate for energy (minRecieval)
	 */
	int setMinEnergyRecieveRate(int minRecieval);
	
	/**
	 * returns one value instead of multiple by default
	 * sets the max storage that can be stored inside a block
	 * @return the maximum energy that can be stored inside the block
	 */
	int setMaxEnergyStorageCap();
	
	/**
	 * returns one value instead of multiple by default
	 * sets the max storage that can be stored inside a block
	 * @return the minimum energy that can be stored inside the block
	 */
	int setMinEnergyStorageCap();
	
	/**
	 * can return multiple values instead of returning one w/param
	 * sets the max storage that can be stored inside a block
	 * @param maxStorage maximum energy storage inside block
	 * @return the maximum energy that can be stored inside the block (maxStorage)
	 */
	int setMaxEnergyStorageCap(int maxStorage);
	
	/**
	 * can return multiple values instead of returning one w/param
	 * sets the minimum storage that can be stored inside a block. If it's null, it will default to 0
	 * @param minStorage minimum energy storage inside block
	 * @return the minimum energy that can be stored inside the block (minStorage)
	 */
	int setMinEnergyStorageCap(int minStorage);
	
	/**
	 * sets energy to one value
	 * @return energy value 
	 */
	int setEnergy();
	
	/**
	 * sets energy to written value
	 * @param energy amount of energy for it to be set to w/param
	 * @return energy
	 */
	int setEnergy(int energy);
	
	/**
	 * getter for setMaxEnergyRecieveRate
	 * @return value of setMaxEnergyRecieveRate
	 */
	int getMaxEnergyRecieveRate();
	
	/**
	 * getter for setMinEnergyRecieveRate
	 * @return value of setMinEnergyRecieveRate
	 */
	int getMinEnergyRecieveRate();
	
	/**
	 * getter for setMaxEnergyRecieveRate but you can set multiple values and conditions w/param
	 * @return value of setMaxEnergyRecieveRate by default, can (and is recommended to) return (maxRate)
	 */
	int getMaxEnergyRecieveRate(int maxRate);
	
	/**
	 * getter for setMinEnergyRecieveRate but you can set multiple values and conditions w/param
	 * @return value of setMinEnergyRecieveRate by default, can (and is recommended to) return (minRate)
	 */
	int getMinEnergyRecieveRate(int minRate);
	
	/**
	 * default getter for the max energy storage cap
	 * @return value of setMaxEnergyStorageCap
	 */
	int getMaxEnergyStorageCap();
	
	/**
	 * default getter for the minimum energy storage cap
	 * @return value of setMinEnergyStorageCap
	 */
	int getMinEnergyStorageCap();
	
	/**
	 * getter for the max energy storage cap but you can set a custom amount of values in it instead of having to use the default getter w/param
	 * @param maxStorage
	 * @return value within brackets (maxStorage)
	 */
	int getMaxEnergyStorageCap(int maxStorage);
	
	/**
	 * getter for minimum energy storage cap but you can set a custom amount of values in it instead of having to use the default getter w/param
	 * @param minStorage
	 * @return value within brackets (minStorage)
	 */
	int getMinEnergyStorageCap(int minStorage);
	
	/**
	 * getter for setEnergy
	 * @return value of setEnergy
	 */
	int getEnergy();
	
	/**
	 * getter for setEnergy
	 * @return value of setEnergy by default, can (and is recommended to) return (energy)
	 */
	int getEnergy(int energy);
	
    /////////////////////////////////////
	//             Booleans            //
    /////////////////////////////////////
	
	/**
	 * if true, special properties of warm energy types can be applied
	 * @return special properties if true, else returns false
	 */
	boolean isWarmEnergyType();
	
	/**
	 * if true, special properties of cold energy types can be applied
	 * @return special properties if true, else returns false
	 */
	boolean isColdEnergyType();
	
	/**
	 * if true, no special properties/modifiers are given to energy used to power machines. Basically it can use this type of energy
	 * normally like FE
	 * @return no special properties and compatibility with other energy types if true, else returns false
	 */
	boolean isNormalEnergyType();
	
	/**
	 * if true, adds compatibility to the block for using other energy types such as RF, FE, etc.
	 * @return compatibility with other energy types if true, else returns false
	 */
	boolean compatWithOtherCustomEnergyOrForgeEnergyTypes();
	
	/////////////////////////////////////
	//Dimensional Energy type functions//
	/////////////////////////////////////
	
	/**
	 * sets the type of energy supplying a block
	 * @return type of energy supplying block (type)
	 */
	DimEnergyType setDimensionalEnergyType(DimEnergyType type);
	
	/**
	 * getter for setDimensionalEnergy Type
	 * @return value of setDimensionalEnergyType
	 */
	DimEnergyType getDimensionalEnergyType();
	
    /////////////////////////////////////
	//            Functions            //
    /////////////////////////////////////	
	
	/**
	 * if energy types are present from depended on mods, you can set this to the energy type. Requires dimensional energy types
	 * to be false, or isNormalEnergyType to be true. If all are null, this function can still work
	 */
	void setEnergyType();
	
	/**
	 * resets the amount of energy to 0
	 */
	void resetEnergy();
	
	/**
	 * reads energy data inside block/tile entity
	 * @param data data inside the block/tile entity
	 */
	void readData(CompoundNBT data);
	
	/**
	 * saves energy data inside block/tile entity
	 * @param data data to save inside the block/tile energy
	 */
	void writeData(CompoundNBT data);
	
	/**
	 * clears data saved inside block/tile entity
	 * @param dataToClear data to clear up
	 */
	void clearData(CompoundNBT dataToClear);
	
}
